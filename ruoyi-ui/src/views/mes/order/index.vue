<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入工单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批次号" prop="orderBatchId">
        <el-input
          v-model="queryParams.orderBatchId"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="创建人" prop="orderCreator">
        <el-input
          v-model="queryParams.orderCreator"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="状态" prop="orderStatus">
        <el-select v-model="queryParams.orderStatus" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.mes_order_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="物料" prop="orderMaterialId">
        <el-input
          v-model="queryParams.orderMaterialId"
          placeholder="请输入物料"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="计划完工日期" prop="orderDeadline">
        <el-date-picker clearable
          v-model="queryParams.orderDeadline"
          type="date"
          value-format="yyyy-MM-dd"
          style="width:100%;"
          placeholder="请选择计划完工日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="开始生产日期" prop="orderStartDate">
        <el-date-picker clearable
          v-model="queryParams.orderStartDate"
          type="date"
          value-format="yyyy-MM-dd"
          style="width:100%;"
          placeholder="请选择开始生产日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="实际完工日期" prop="orderEndDate">
        <el-date-picker clearable
          v-model="queryParams.orderEndDate"
          type="date"
          value-format="yyyy-MM-dd"
          style="width:100%;"
          placeholder="请选择实际完工日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="生产数量" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入生产数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['mes:order:add']"
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
          v-hasPermi="['mes:order:edit']"
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
          v-hasPermi="['mes:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mes:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工单号" align="center" prop="orderId" />
      <el-table-column label="批次号" align="center" prop="orderBatchId" />
      <el-table-column label="创建人" align="center" prop="orderCreator" />
      <el-table-column label="状态" align="center" prop="orderStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mes_order_status" :value="scope.row.orderStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="物料" align="center" prop="orderMaterialId" />
      <el-table-column label="计划完工日期" align="center" prop="orderDeadline" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderDeadline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始生产日期" align="center" prop="orderStartDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderStartDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际完工日期" align="center" prop="orderEndDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产数量" align="center" prop="orderNumber" />
      <el-table-column label="创建时间" align="center" prop="orderCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:order:remove']"
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

    <!-- 添加或修改生产工单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="批次号" prop="orderBatchId">
              <el-input v-model="form.orderBatchId" placeholder="请输入批次号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="创建人" prop="orderCreator">
              <el-input v-model="form.orderCreator" placeholder="请输入创建人" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态" prop="orderStatus">
              <el-select v-model="form.orderStatus" placeholder="请选择状态">
                <el-option
                  v-for="dict in dict.type.mes_order_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>  
          <el-col :span="8">
            <el-form-item label="物料" prop="orderMaterialName">
              <el-input v-model="form.orderMaterialName" placeholder="请输入物料" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="物料编号" prop="orderMaterialId">
              <el-input v-model="form.orderMaterialId" placeholder="请输入物料编号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="规格型号" prop="orderMaterialModel">
              <el-input v-model="form.orderMaterialModel" placeholder="请输入规格型号" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="计量单位" prop="orderMaterialUnit">
              <el-input v-model="form.orderMaterialUnit" placeholder="请输入计量单位" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="生产数量" prop="orderNumber">
              <el-input v-model="form.orderNumber" placeholder="请输入生产数量" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="计划完工" prop="orderDeadline">
              <el-date-picker clearable
                v-model="form.orderDeadline"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:100%;"
                placeholder="请选择计划完工日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开始生产" prop="orderStartDate">
              <el-date-picker clearable
                v-model="form.orderStartDate"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:100%;"
                placeholder="请选择开始生产日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="实际完工" prop="orderEndDate">
              <el-date-picker clearable
                v-model="form.orderEndDate"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:100%;"
                placeholder="请选择实际完工日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider content-position="center">工单工序信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddOrderRoute">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteOrderRoute">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="orderRouteList" :row-class-name="rowOrderRouteIndex" @selection-change="handleOrderRouteSelectionChange" ref="orderRoute">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="工序路线编号" prop="routeId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.routeId" placeholder="请输入工序路线编号" />
            </template>
          </el-table-column>
          <el-table-column label="工序编号" prop="processId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.processId" placeholder="请输入工序编号" />
            </template>
          </el-table-column>
          <el-table-column label="工序顺序" prop="processIndex" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.processIndex" placeholder="请输入工序顺序" />
            </template>
          </el-table-column>
          <el-table-column label="车间编号" prop="workshopId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.workshopId" placeholder="请输入车间编号" />
            </template>
          </el-table-column>
          <el-table-column label="工位编号" prop="stationId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.stationId" placeholder="请输入工位编号" />
            </template>
          </el-table-column>
          <el-table-column label="完成数量" prop="orderFinishNumber" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.orderFinishNumber" placeholder="请输入完成数量" />
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/mes/order";

export default {
  name: "Order",
  dicts: ['mes_order_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedOrderRoute: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 生产工单表格数据
      orderList: [],
      // 工单工序表格数据
      orderRouteList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        orderBatchId: null,
        orderCreator: null,
        orderStatus: null,
        orderMaterialId: null,
        orderDeadline: null,
        orderStartDate: null,
        orderEndDate: null,
        orderNumber: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderId: [
          { required: true, message: "工单号不能为空", trigger: "blur" }
        ],
        orderStatus: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        orderMaterialId: [
          { required: true, message: "物料不能为空", trigger: "blur" }
        ],
        orderNumber: [
          { required: true, message: "生产数量不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询生产工单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
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
        orderId: null,
        orderBatchId: null,
        orderCreator: null,
        orderStatus: null,
        orderMaterialId: null,
        orderDeadline: null,
        orderStartDate: null,
        orderEndDate: null,
        orderNumber: null,
        orderCreateTime: null
      };
      this.orderRouteList = [];
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
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产工单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrder(orderId).then(response => {
        this.form = response.data;
        this.orderRouteList = response.data.orderRouteList;
        this.open = true;
        this.title = "修改生产工单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.orderRouteList = this.orderRouteList;
          if (this.form.orderId != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除生产工单编号为"' + orderIds + '"的数据项？').then(function() {
        return delOrder(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 工单工序序号 */
    rowOrderRouteIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 工单工序添加按钮操作 */
    handleAddOrderRoute() {
      let obj = {};
      obj.routeId = "";
      obj.processId = "";
      obj.processIndex = "";
      obj.workshopId = "";
      obj.stationId = "";
      obj.orderFinishNumber = "";
      this.orderRouteList.push(obj);
    },
    /** 工单工序删除按钮操作 */
    handleDeleteOrderRoute() {
      if (this.checkedOrderRoute.length == 0) {
        this.$modal.msgError("请先选择要删除的工单工序数据");
      } else {
        const orderRouteList = this.orderRouteList;
        const checkedOrderRoute = this.checkedOrderRoute;
        this.orderRouteList = orderRouteList.filter(function(item) {
          return checkedOrderRoute.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleOrderRouteSelectionChange(selection) {
      this.checkedOrderRoute = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mes/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
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