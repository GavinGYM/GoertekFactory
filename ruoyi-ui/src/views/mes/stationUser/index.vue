<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工位" prop="stationId">
        <!-- <el-input
          v-model="queryParams.stationId"
          placeholder="请输入工位ID"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select v-model="queryParams.stationId" placeholder="请选择工位" clearable @change="handleQuery" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in stationList"
            :key="item.stationList"
            :label="item.stationName"
            :value="item.stationId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="人员" prop="userId">
        <!-- <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select v-model="queryParams.userId" placeholder="请选择用户" clearable @change="handleQuery" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in userList"
            :key="item.userId"
            :label="item.nickName"
            :value="item.userId"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="添加人" prop="addUserId">
        <el-input
          v-model="queryParams.addUserId"
          placeholder="请输入添加人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加时间" prop="addTime">
        <el-date-picker clearable
          v-model="queryParams.addTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择添加时间">
        </el-date-picker>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mes:stationUser:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['mes:stationUser:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mes:stationUser:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mes:stationUser:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stationUserList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="工位绑定编号" align="center" prop="stationUserId" /> -->
      <el-table-column label="车间名称" align="center" prop="workshopName" />
      <el-table-column label="工位名称" align="center" prop="stationName" />
      <el-table-column label="人员" align="center" prop="userName" />
      <el-table-column label="人员昵称" align="center" prop="userNickName" />
      <el-table-column label="添加人" align="center" prop="addUserName" />
      <el-table-column label="添加时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:stationUser:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:stationUser:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改工位绑定对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" input-width="200px">
        <el-form-item label="车间" prop="workshopName">
          <el-input v-model="form.workshopName" :disabled="true" width = "80px"/>
        </el-form-item>

        <el-form-item label="工位" prop="stationId">
          <!-- <el-input v-model="form.stationId" placeholder="请输入工位ID" /> -->
          <el-select v-model="form.stationId" placeholder="请选择工位" clearable @change="changeStation">
            <el-option
              v-for="item in stationList"
              :key="item.stationList"
              :label="item.stationName"
              :value="item.stationId"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" :disabled="true"/>
        </el-form-item>

        <el-form-item label="用户昵称" prop="userId">
          <!-- <el-input v-model="form.userId" placeholder="请输入用户ID" /> -->
          <el-select v-model="form.userId" placeholder="请选择用户" clearable @change="changeUser">
            <el-option
              v-for="item in userList"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStationUser, getStationUser, delStationUser, addStationUser, updateStationUser } from "@/api/mes/stationUser";
import { listStation } from "@/api/mes/station";
import { listWorkshop } from "@/api/mes/workshop";
import { listUser } from "@/api/system/user";

export default {
  name: "StationUser",
  data() {
    return {
      // 工位列表
      stationList: [],
      // 用户列表
      userList: [],
      // 车间列表
      workshopList: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 工位绑定表格数据
      stationUserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stationId: null,
        userId: null,
        addUserId: null,
        addTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getStationList();
    this.getWorkshopList();
    this.getUserList();
  },
  methods: {
    /** 当所选用户发送变更时 */
    changeUser(val) {
      for(let user of this.userList) {
        if(user.userId === val) {
          this.form.userName = user.userName;
        }
      }
    },
    /** 当所选工位发生变更时 */
    changeStation(val) {
      this.form.workshopId = this.stationList.find(item => item.stationId === val).workshopId;
      for (let workshop of this.workshopList) {
        if (workshop.workshopId === this.form.workshopId) {
          this.form.workshopName = workshop.workshopName;
        }
      }
    },  
    /** 获取用户列表 */
    getUserList() {
      listUser({}).then(response => {
        this.userList = response.rows;
      });
    },
    /** 获取工位列表 */
    getStationList() {
      listStation({}).then(response => {
        this.stationList = response.rows;
      });
    },
    /** 获取车间列表 */
    getWorkshopList() {
      listWorkshop({}).then(response => {
        this.workshopList = response.rows;
      });
    },
    /** 查询工位绑定列表 */
    getList() {
      this.loading = true;
      listStationUser(this.queryParams).then(response => {
        this.stationUserList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        stationUserId: null,
        stationId: null,
        userId: null,
        addUserId: null,
        addTime: null,
        // 用户名
        userName: null, 
        // 当前车间
        workshopId: null,
        // 当前车间名称
        workshopName: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.stationUserId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工位绑定";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const stationUserId = row.stationUserId || this.ids
      getStationUser(stationUserId).then(response => {
        this.form = response.data;
        this.changeUser(response.data.userId);
        this.changeStation(response.data.stationId);
        this.open = true;
        this.title = "修改工位绑定";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.stationUserId != null) {
            updateStationUser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStationUser(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const stationUserIds = row.stationUserId || this.ids;
      this.$modal.confirm('是否确认删除工位绑定编号为"' + stationUserIds + '"的数据项？').then(function() {
        return delStationUser(stationUserIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mes/stationUser/export', {
        ...this.queryParams
      }, `stationUser_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style>
/* .el-form-item {
  margin-right: 0 !important;
}
.el-form-item__label {
  position: absolute;
}
.el-form-item__content {
  width: 100%;
  padding-left: 80px;
} */
.el-select,
.el-input_inner {
  width: 100%;
}
</style>