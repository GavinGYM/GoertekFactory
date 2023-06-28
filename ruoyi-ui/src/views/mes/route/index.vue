<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工序路线名称" prop="routeName">
        <el-input
          v-model="queryParams.routeName"
          placeholder="请输入工序路线名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="routeStatus">
        <el-select v-model="queryParams.routeStatus" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="添加时间" prop="routeAddTime">
        <el-date-picker clearable
          v-model="queryParams.routeAddTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择添加时间">
        </el-date-picker>
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
          v-hasPermi="['mes:route:add']"
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
          v-hasPermi="['mes:route:edit']"
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
          v-hasPermi="['mes:route:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mes:route:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="routeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工序编号" align="center" prop="routeId" />
      <el-table-column label="工序路线名称" align="center" prop="routeName" />
      <el-table-column label="工序路线说明" align="center" prop="routeComment" />
      <el-table-column label="状态" align="center" prop="routeStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.routeStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="添加时间" align="center" prop="routeAddTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.routeAddTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:route:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:route:remove']"
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

    <!-- 添加或修改工序路线对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工序路线名称" prop="routeName">
          <el-input v-model="form.routeName" placeholder="请输入工序路线名称" />
        </el-form-item>
        <el-form-item label="工序路线说明" prop="routeComment">
          <el-input v-model="form.routeComment" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="状态" prop="routeStatus">
          <el-radio-group v-model="form.routeStatus">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-divider content-position="center">工序明细</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddRouteProcess">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteRouteProcess">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="routeProcessList" :row-class-name="rowRouteProcessIndex" @selection-change="handleRouteProcessSelectionChange" ref="routeProcess">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="排序" align="center" prop="index" width="auto"/>
          <el-table-column label="工序" align="center" prop="processId" width="auto">
            <template slot-scope="scope">
              <el-select v-model="scope.row.processId" placeholder="请选择工序">
                <el-option
                  v-for="item in processList"
                  :key="item.processId"
                  :label="item.processName"
                  :value="item.processId"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRoute, getRoute, delRoute, addRoute, updateRoute } from "@/api/mes/route";
import { listProcess } from "@/api/mes/process";

export default {
  name: "Route",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedRouteProcess: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 工序表格数据
      processList: [],
      // 工序路线表格数据
      routeList: [],
      // 工序与路线关系表格数据
      routeProcessList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        routeName: null,
        routeComment: null,
        routeStatus: null,
        routeAddTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        routeName: [
          { required: true, message: "工序路线名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getProcessList();
  },
  methods: {
    /** 查询工序列表 */
    getProcessList() {
      listProcess({}).then(response => {
        this.processList = response.rows;
      });
    },
    /** 查询工序路线列表 */
    getList() {
      this.loading = true;
      listRoute(this.queryParams).then(response => {
        this.routeList = response.rows;
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
        routeId: null,
        routeName: null,
        routeComment: null,
        routeStatus: null,
        routeAddTime: null
      };
      this.routeProcessList = [];
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
      this.ids = selection.map(item => item.routeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工序路线";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const routeId = row.routeId || this.ids
      getRoute(routeId).then(response => {
        this.form = response.data;
        this.routeProcessList = response.data.routeProcessList;
        this.open = true;
        this.title = "修改工序路线";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.routeProcessList.forEach((item, index) => {
            item.processIndex = index;
          });
          this.form.routeProcessList = this.routeProcessList;
          if (this.form.routeId != null) {
            updateRoute(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRoute(this.form).then(response => {
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
      const routeIds = row.routeId || this.ids;
      this.$modal.confirm('是否确认删除工序路线编号为"' + routeIds + '"的数据项？').then(function() {
        return delRoute(routeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 工序与路线关系序号 */
    rowRouteProcessIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 工序与路线关系添加按钮操作 */
    handleAddRouteProcess() {
      let obj = {};
      this.routeProcessList.push(obj);
    },
    /** 工序与路线关系删除按钮操作 */
    handleDeleteRouteProcess() {
      if (this.checkedRouteProcess.length == 0) {
        this.$modal.msgError("请先选择要删除的工序与路线关系数据");
      } else {
        const routeProcessList = this.routeProcessList;
        const checkedRouteProcess = this.checkedRouteProcess;
        this.routeProcessList = routeProcessList.filter(function(item) {
          return checkedRouteProcess.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleRouteProcessSelectionChange(selection) {
      this.checkedRouteProcess = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mes/route/export', {
        ...this.queryParams
      }, `route_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
