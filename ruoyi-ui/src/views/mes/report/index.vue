<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产工单" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入生产工单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="工序路线" prop="routeId">
        <el-input
          v-model="queryParams.routeId"
          placeholder="请输入工序路线号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工序号" prop="processId">
        <el-input
          v-model="queryParams.processId"
          placeholder="请输入工序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="工序排序" prop="processIndex">
        <el-input
          v-model="queryParams.processIndex"
          placeholder="请输入工序排序"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报工人id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入报工人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报工时间" prop="reportTime">
        <el-date-picker clearable
          v-model="queryParams.reportTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择报工时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="报工数量" prop="reportNumber">
        <el-input
          v-model="queryParams.reportNumber"
          placeholder="请输入报工数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车间id" prop="workshopId">
        <el-input
          v-model="queryParams.workshopId"
          placeholder="请输入车间id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工位id" prop="stationId">
        <el-input
          v-model="queryParams.stationId"
          placeholder="请输入工位id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mes:report:add']"
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
          v-hasPermi="['mes:report:edit']"
        >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mes:report:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mes:report:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="报工号" align="center" prop="reportId" /> -->
      <el-table-column label="工单号" align="center" prop="orderId" width="80" />
      <el-table-column label="工序路线" align="center" prop="routeName" width="auto" />
      <el-table-column label="工序号" align="center" prop="processName" />
      <el-table-column label="工序排序" align="center" prop="processIndex">
        <template slot-scope="scope">
          <span>第{{ scope.row.processIndex }}道工序</span>
        </template>
      </el-table-column>
      <el-table-column label="报工人" align="center" prop="userName" />
      <el-table-column label="报工数量" align="center" prop="reportNumber" />
      <el-table-column label="车间" align="center" prop="workshopName" width="150" />
      <el-table-column label="工位" align="center" prop="stationName" />
      <el-table-column label="报工时间" align="center" prop="reportTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reportTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:report:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:report:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改报工对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="生产工单号" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入生产工单号" />
        </el-form-item>
        <el-form-item label="工序路线号" prop="routeId">
          <el-input v-model="form.routeId" placeholder="请输入工序路线号" />
        </el-form-item>
        <el-form-item label="工序号" prop="processId">
          <el-input v-model="form.processId" placeholder="请输入工序号" />
        </el-form-item>
        <el-form-item label="工序排序" prop="processIndex">
          <el-input v-model="form.processIndex" placeholder="请输入工序排序" />
        </el-form-item>
        <el-form-item label="报工人id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入报工人id" />
        </el-form-item>
        <el-form-item label="报工时间" prop="reportTime">
          <el-date-picker clearable
            v-model="form.reportTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报工时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报工数量" prop="reportNumber">
          <el-input v-model="form.reportNumber" placeholder="请输入报工数量" />
        </el-form-item>
        <el-form-item label="车间id" prop="workshopId">
          <el-input v-model="form.workshopId" placeholder="请输入车间id" />
        </el-form-item>
        <el-form-item label="工位id" prop="stationId">
          <el-input v-model="form.stationId" placeholder="请输入工位id" />
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
import { listReport, getReport, delReport, addReport, updateReport } from "@/api/mes/report";

export default {
  name: "Report",
  data() {
    return {
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
      // 报工表格数据
      reportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        routeId: null,
        processId: null,
        processIndex: null,
        userId: null,
        reportTime: null,
        reportNumber: null,
        workshopId: null,
        stationId: null
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
  },
  methods: {
    /** 查询报工列表 */
    getList() {
      this.loading = true;
      listReport(this.queryParams).then(response => {
        this.reportList = response.rows;
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
        reportId: null,
        orderId: null,
        routeId: null,
        processId: null,
        processIndex: null,
        userId: null,
        reportTime: null,
        reportNumber: null,
        workshopId: null,
        stationId: null
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
      this.ids = selection.map(item => item.reportId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加报工";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reportId = row.reportId || this.ids
      getReport(reportId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改报工";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reportId != null) {
            updateReport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReport(this.form).then(response => {
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
      const reportIds = row.reportId || this.ids;
      this.$modal.confirm('是否确认删除报工编号为"' + reportIds + '"的数据项？').then(function() {
        return delReport(reportIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mes/report/export', {
        ...this.queryParams
      }, `report_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
