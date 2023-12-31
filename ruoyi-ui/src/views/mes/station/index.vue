<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工位编号" prop="stationId">
        <el-input
          v-model="queryParams.stationId"
          placeholder="请输入工位编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工位名称" prop="stationName">
        <el-input
          v-model="queryParams.stationName"
          placeholder="请输入工位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属车间" prop="workshopId">
        <el-input
          v-model="queryParams.workshopId"
          placeholder="请输入所属车间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="绑定工序" prop="processId">
        <el-input
          v-model="queryParams.processId"
          placeholder="请输入绑定工序"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="绑定设备" prop="equipmentId">
        <el-input
          v-model="queryParams.equipmentId"
          placeholder="请输入绑定设备"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="stationStatus">
        <el-select v-model="queryParams.stationStatus" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
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
          v-hasPermi="['mes:station:add']"
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
          v-hasPermi="['mes:station:edit']"
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
          v-hasPermi="['mes:station:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mes:station:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工位编号" align="center" prop="stationId" />
      <el-table-column label="工位名称" align="center" prop="stationName" />
      <el-table-column label="所属车间" align="center">
        <template slot-scope="scope">
          {{ getWorkshopName(scope.row.workshopId) }}
        </template>
      </el-table-column>
      <el-table-column label="绑定工序" align="center" >
        <template slot-scope="scope">
          {{ getProcessName(scope.row.processId) }}
        </template>
      </el-table-column>
      <el-table-column label="绑定设备" align="center" >
        <template slot-scope="scope">
          {{ getEquipmentName(scope.row.equipmentId) }}
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="stationStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.stationStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="stationComment" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:station:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:station:remove']"
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

    <!-- 添加或修改工位信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工位名称" prop="stationName">
          <el-input v-model="form.stationName" placeholder="请输入工位名称" />
        </el-form-item>
        <el-form-item label="所属车间" prop="workshopId">
          <!-- <el-input v-model="form.workshopId" placeholder="请输入所属车间" /> -->
          <el-select v-model="form.workshopId" placeholder="请选择所属车间" clearable>
            <el-option
              v-for="item in workshopList"
              :key="item.workshopId"
              :label="item.workshopName"
              :value="item.workshopId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="绑定工序" prop="processId">
          <!-- <el-input v-model="form.processId" placeholder="请输入绑定工序" /> -->
          <el-select v-model="form.processId" placeholder="请选择绑定工序" clearable>
            <el-option
              v-for="item in processList"
              :key="item.processId"
              :label="item.processName"
              :value="item.processId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="绑定设备" prop="equipmentId">
          <!-- <el-input v-model="form.equipmentId" placeholder="请输入绑定设备" /> -->
          <el-select v-model="form.equipmentId" placeholder="请选择绑定设备" clearable>
            <el-option
              v-for="item in equipmentList"
              :key="item.equipmentId"
              :label="item.equipmentName"
              :value="item.equipmentId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="stationStatus">
          <el-radio-group v-model="form.stationStatus">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="stationComment">
          <el-input v-model="form.stationComment" type="textarea" placeholder="请输入内容" />
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
import { listStation, getStation, delStation, addStation, updateStation } from "@/api/mes/station";
import { listWorkshop } from "@/api/mes/workshop";
import { listProcess } from "@/api/mes/process";
import { listEquipment } from "@/api/mes/equipment";

export default {
  name: "Station",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 车间列表
      workshopList: [],
      // 工序列表
      processList: [],
      // 设备列表
      equipmentList: [],
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
      // 工位信息表格数据
      stationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stationId: null,
        stationName: null,
        workshopId: null,
        processId: null,
        equipmentId: null,
        stationStatus: null,
        stationComment: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stationId: [
          { required: true, message: "工位编号不能为空", trigger: "blur" }
        ],
        stationName: [
          { required: true, message: "工位名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getWorkshopList();
    this.getEquipmentList();
    this.getProcessList();
  },
  methods: {
    /** 查询车间名称 */
    getWorkshopName(workshopId) {
      const workshop = this.workshopList.find(w => w.workshopId === workshopId);
      return workshop ? workshop.workshopName : "";
    },
    /** 查询工序名称 */
    getProcessName(processId) {
      const process = this.processList.find(p => p.processId === processId);
      return process ? process.processName : "";
    },
    /** 查询设备名称 */
    getEquipmentName(equipmentId) {
      const equipment = this.equipmentList.find(e => e.equipmentId === equipmentId);
      return equipment ? equipment.equipmentName : "";
    },
    /** 获取车间列表 */
    getWorkshopList() {
      listWorkshop({}).then(response => {
        this.workshopList = response.rows;
      });
    },
    /** 获取工序列表 */
    getProcessList() {
      listProcess({}).then(response => {
        this.processList = response.rows;
      });
    },
    /** 获取设备列表 */
    getEquipmentList() {
      listEquipment({}).then(response => {
        this.equipmentList = response.rows;
      });
    },
    /** 查询工位信息列表 */
    getList() {
      this.loading = true;
      listStation(this.queryParams).then(response => {
        this.stationList = response.rows;
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
        stationId: null,
        stationName: null,
        workshopId: null,
        processId: null,
        equipmentId: null,
        stationStatus: null,
        stationComment: null
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
      this.ids = selection.map(item => item.stationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工位信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const stationId = row.stationId || this.ids
      getStation(stationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工位信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.stationId != null) {
            updateStation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStation(this.form).then(response => {
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
      const stationIds = row.stationId || this.ids;
      this.$modal.confirm('是否确认删除工位信息编号为"' + stationIds + '"的数据项？').then(function() {
        return delStation(stationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mes/station/export', {
        ...this.queryParams
      }, `station_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
