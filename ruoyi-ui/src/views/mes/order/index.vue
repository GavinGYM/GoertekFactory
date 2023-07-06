<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="false" v-show="showSearch" label-width="68px">
      <el-row>
        <el-col :span="5">
          <el-form-item label="工单号" prop="orderId">
            <el-input
              v-model="queryParams.orderId"
              placeholder="请输入工单号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="批次号" prop="orderBatchId">
            <el-input
              v-model="queryParams.orderBatchId"
              placeholder="请输入批次号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
          <!-- <el-form-item label="创建人" prop="orderCreator">
          <el-input
            v-model="queryParams.orderCreator"
            placeholder="请输入创建人"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item> -->
        <el-col :span="4">
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
        </el-col>
        <el-col :span="5">
          <el-form-item label="物料" prop="orderMaterialId">
            <el-input
              v-model="queryParams.orderMaterialId"
              placeholder="请输入物料"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
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
        <el-col :span="5">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
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
          size="mini"
          :disabled="multiple"
          @click="handleStart"
        >开始排产</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          size="mini"
          :disabled="multiple"
          @click="handlePause"
        >工单暂停</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          size="mini"
          :disabled="multiple"
          @click="handleStop"
        >强制关闭</el-button>
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
      <!-- <el-table-column label="展开" align="center" prop="unfold" type="expand" >
      </el-table-column> -->
      <el-table-column label="报工" align="center" width="60">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-claim"
            @click="handleReport(scope.row)"
          >报工</el-button>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="orderStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mes_order_status" :value="scope.row.orderStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="工单号" align="center" prop="orderId" />
      <el-table-column label="批次号" align="center" prop="orderBatchId" />
      <!-- <el-table-column label="创建人" align="center" prop="orderCreator" /> -->
      <el-table-column label="物料编号" align="center" prop="orderMaterialId" />
      <el-table-column label="物料名称" align="center" prop="orderMaterialName" />
      <el-table-column label="生产数量" align="center" prop="orderNumber" />
      <el-table-column label="物料单位" align="center" prop="orderMaterialUnit">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mes_unit" :value="scope.row.orderMaterialUnit"/>
        </template>
      </el-table-column>
      <el-table-column label="计划完工日期" align="center" prop="orderDeadline" width="100px">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderDeadline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始生产日期" align="center" prop="orderStartDate" width="100px">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderStartDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际完工日期" align="center" prop="orderEndDate" width="100px">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="orderCreateTime" width="100px">
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
    <!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 报工弹出层 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
      <el-dialog :title="reportTitle" :visible.sync="reportOpen" width="900px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="批次号" prop="orderBatchId">
                <!-- <el-input v-model="form.orderBatchId" placeholder="请输入批次号" :disabled="true" /> -->
                <span>{{ form.orderBatchId }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="物料" prop="orderMaterialName">
                <!-- <el-input v-model="form.orderMaterialName" placeholder="请输入物料" :disabled="true"> -->
                <span>{{ form.orderMaterialName }}</span>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="物料编号" prop="orderMaterialId">
                <!-- <el-input v-model="form.orderMaterialId" placeholder="请输入物料编号" :disabled="true" /> -->
                <span>{{ form.orderMaterialId }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="规格型号" prop="orderMaterialModel">
                <!-- <el-input v-model="form.orderMaterialModel" placeholder="请输入规格型号" :disabled="true" /> -->
                <span>{{ form.orderMaterialModel }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="生产数量" prop="orderNumber">
                <!-- <el-input v-model="form.orderNumber" placeholder="请输入生产数量" :disabled="true" /> -->
                <span>{{ form.orderNumber }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="计量单位" prop="orderMaterialUnit">
                <el-select v-model="form.orderMaterialUnit" placeholder="请选择计量单位" :disabled="true">
                  <el-option
                    v-for="dict in dict.type.mes_unit"
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
              <el-form-item label="计划完工" prop="orderDeadline" >
                <el-date-picker clearable
                  v-model="form.orderDeadline"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width:100%;"
                  :disabled="true"
                  placeholder="请选择计划完工日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="开始生产" prop="orderStartDate" >
                <el-date-picker clearable
                  v-model="form.orderStartDate"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width:100%;"
                  :disabled="true"
                  placeholder="暂未开始生产">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="实际完工" prop="orderEndDate" >
                <el-date-picker clearable
                  v-model="form.orderEndDate"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width:100%;"
                  :disabled="true"
                  placeholder="暂未完工">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <el-divider content-position="center">请选择工序进行报工</el-divider>
          <el-table :data="orderRouteList" :row-class-name="rowOrderRouteIndex" ref="orderRoute">
            <!-- <el-table-column type="selection" width="50" align="center" /> -->
            <el-table-column label="序号" align="center" prop="processIndex" width="50">
              <template slot-scope="scope">
                <span>{{ scope.row.processIndex + 1 }}</span>
              </template>
            </el-table-column>
            <!-- <el-table-column label="工序路线编号" prop="routeId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.routeId" placeholder="请输入工序路线编号" />
            </template>
          </el-table-column> -->
            <el-table-column label="工序编号" align="center" prop="processId" width="auto">
              <template slot-scope="scope">
                <!-- <el-input v-model="scope.row.processId" placeholder="请输入工序编号" /> -->
                <el-select v-model="scope.row.processId" placeholder="工序" clearable :disabled="true">
                    <el-option
                      v-for="item in processList"
                      :key="item.processId"
                      :label="item.processName"
                      :value="item.processId"
                    />
                  </el-select>
              </template>
            </el-table-column>
            <!-- <el-table-column label="工序" prop="processName" width="auto">
              <template slot-scope="scope">
                <el-input v-model="scope.row.processName" placeholder="请输入工序编号" :disabled="true"/>
              </template>
          </el-table-column> -->
            <!-- <el-table-column label="工序顺序" prop="processIndex" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.processIndex" placeholder="请输入工序顺序" />
            </template>
          </el-table-column> -->
            <el-table-column label="工位" align="center" prop="stationId" width="auto">
              <template slot-scope="scope">
                <el-select v-model="scope.row.stationId" placeholder="请选择工位" clearable :disabled="true">
                  <el-option
                    v-for="item in stationList"
                    :key="item.stationId"
                    :label="item.stationName"
                    :value="item.stationId"
                  />
                </el-select>
              </template>
            </el-table-column>
          
            <el-table-column label="车间编号" align="center" prop="workshopName" width="auto">
              <template slot-scope="scope">
                <!-- <el-input v-model="scope.row.workshopName" placeholder="请输入车间编号" :disabled="true"/> -->
                <el-select v-model="scope.row.workshopId" placeholder="请选择车间" clearable :disabled="true">
                  <el-option
                    v-for="item in workshopList"
                    :key="item.workshopId"
                    :label="item.workshopName"
                    :value="item.workshopId"
                  />
                </el-select>
              </template>
            </el-table-column>

            <el-table-column label="已报工数量" align="center" prop="reportNumber" width="auto">
              <template slot-scope="scope">
                <span> {{ scope.row.orderFinishNumber }}</span>
              </template>
            </el-table-column>
            <el-table-column label="报工" align="center" prop="reportButton" width="100px">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="primary"
                  plain
                  icon="el-icon-s-claim"
                  @click="handleReportOrderRoute(scope.row)"
                >报工</el-button>
              </template>
            </el-table-column>

          </el-table>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <!-- <el-button type="primary" @click="submitForm">确 定</el-button> -->
          <el-button @click="reportCancel">返回</el-button>
        </div>
      </el-dialog>
    <!-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 报工弹出层 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->

    <!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 报工填报层 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
    <el-dialog :title="reportDetailTitle" :visible.sync="reportDetailopen" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="批次号" prop="orderBatchId">
              <!-- <el-input v-model="form.orderBatchId" placeholder="请输入批次号" :disabled="true" /> -->
              <span>{{ form.orderBatchId }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="物料" prop="orderMaterialName">
              <!-- <el-input v-model="form.orderMaterialName" placeholder="请输入物料" :disabled="true"> -->
              <span>{{ form.orderMaterialName }}</span>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="物料编号" prop="orderMaterialId">
              <!-- <el-input v-model="form.orderMaterialId" placeholder="请输入物料编号" :disabled="true" /> -->
              <span>{{ form.orderMaterialId }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="规格型号" prop="orderMaterialModel">
              <!-- <el-input v-model="form.orderMaterialModel" placeholder="请输入规格型号" :disabled="true" /> -->
              <span>{{ form.orderMaterialModel }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="生产数量" prop="orderNumber">
              <!-- <el-input v-model="form.orderNumber" placeholder="请输入生产数量" :disabled="true" /> -->
              <span>{{ form.orderNumber }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计量单位" prop="orderMaterialUnit">
              <el-select v-model="form.orderMaterialUnit" placeholder="请选择计量单位" :disabled="true">
                <el-option
                  v-for="dict in dict.type.mes_unit"
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
            <el-form-item label="计划完工" prop="orderDeadline" >
              <el-date-picker clearable
                v-model="form.orderDeadline"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:100%;"
                :disabled="true"
                placeholder="请选择计划完工日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开始生产" prop="orderStartDate" >
              <el-date-picker clearable
                v-model="form.orderStartDate"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:100%;"
                :disabled="true"
                placeholder="暂未开始生产">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="实际完工" prop="orderEndDate" >
              <el-date-picker clearable
                v-model="form.orderEndDate"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:100%;"
                :disabled="true"
                placeholder="暂未完工">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="工序名称" prop="processName" >
              <!-- <span>{{ reportInfoList.processId }}</span> -->
              <el-select v-model="reportInfoList.processId" placeholder="工序" clearable :disabled="true">
                <el-option
                  v-for="item in processList"
                  :key="item.processId"
                  :label="item.processName"
                  :value="item.processId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="工序顺序" prop="processName" >
              <span>第{{ reportInfoList.processIndex + 1 }}道工序</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <el-divider content-position="center">请在确认上方信息后，填写下方报工单</el-divider>
      <el-form ref="reportForm" :model="reportForm" :rules="reportRules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="报工人" prop="userId">
              <el-select v-model="reportForm.userId" placeholder="请选择用户" clearable>
                <el-option
                  v-for="item in userList"
                  :key="item.userId"
                  :label="item.nickName"
                  :value="item.userId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报工时间" prop="reportTime">
              <el-date-picker clearable
                v-model="reportForm.reportTime"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:100%;"
                placeholder="请选择报工时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报工数量" prop="reportNumber">
              <el-input v-model="reportForm.reportNumber" placeholder="请输入报工数量" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitReportForm">确 定</el-button>
        <el-button @click="reportFormCancel">返回</el-button>
      </div>
    </el-dialog>
    <!-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 报工填报层 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->

    <!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 物料查询弹出层 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
    <el-dialog :title="materialTitle" :visible.sync="materialOpen" width="950px" append-to-body>

      <el-row :gutter="20">
        <!--物料分类-->
        <el-col :span="6" :xs="24">
          <div class="head-container">
            <el-input
              v-model="materialName"
              placeholder="请输入物料分类"
              clearable
              size="small"
              prefix-icon="el-icon-search"
              style="margin-bottom: 20px"
            />
          </div>
          <div class="head-container">
            <el-tree
              :data="materialOptions"
              :props="defaultProps"
              :expand-on-click-node="false"
              :filter-node-method="filterNode"
              ref="tree"
              node-key="id"
              default-expand-all
              highlight-current
              @node-click="handleNodeClick"
            />
          </div>
        </el-col>
        <el-col :span="18" :xs="24">
          <el-form :model="materialQueryParams" ref="materialQueryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="物料名称" prop="materialItemName">
              <el-input
                v-model="materialQueryParams.materialItemName"
                placeholder="请输入物料名称"
                clearable
                @keyup.enter.native="handleMaterialQuery"
              />
            </el-form-item>
            <el-form-item label="规格型号" prop="materialItemModel">
              <el-input
                v-model="materialQueryParams.materialItemModel"
                placeholder="请输入规格型号"
                clearable
                @keyup.enter.native="handleMaterialQuery"
              />
            </el-form-item>
            <el-form-item label="物料类型" prop="materialItemType">
              <el-select v-model="materialQueryParams.materialItemType" placeholder="请选择物料类型" clearable>
                <el-option
                  v-for="dict in dict.type.material_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleMaterialQuery">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetMaterialQuery">重置</el-button>
            </el-form-item>
          </el-form>

          <el-table v-loading="materialLoading" :data="itemList" @row-click="handleMaterialRowClick" @selection-change="handleSelectionChange">
            <!-- <el-table-column type="selection" width="55" align="center" /> -->
            <el-table-column label="编号" align="center" prop="materialItemId" />
            <el-table-column label="物料名称" align="center" prop="materialItemName" />
            <el-table-column label="规格型号" align="center" prop="materialItemModel" />
            <el-table-column label="计量单位" align="center" prop="materialItemUnit">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.mes_unit" :value="scope.row.materialItemUnit"/>
              </template>
            </el-table-column>
            <!-- <el-table-column label="物料分类" align="center" prop="materialItemClass">
            <template slot-scope="scope">
              {{ getMaterialClassName(scope.row.materialItemClass) }}
            </template>
          </el-table-column> -->
            <el-table-column label="物料类型" align="center" prop="materialItemType">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.material_type" :value="scope.row.materialItemType"/>
              </template>
            </el-table-column>
            <el-table-column label="状态" align="center" prop="materialItemStatus">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.materialItemStatus"/>
              </template>
            </el-table-column>
            <el-table-column label="添加时间" align="center" prop="materialItemAddTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.materialItemAddTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </template>
            </el-table-column>
          </el-table>
        
          <pagination
            v-show="total > 0"
            :total="materialTotal"
            :page.sync="materialQueryParams.pageNum"
            :limit.sync="materialQueryParams.pageSize"
            @pagination="materialgetList"
          />
        </el-col>
      </el-row>

    </el-dialog>
    <!-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 物料查询弹出层 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->

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
              <el-input v-model="username" placeholder="请输入创建人" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态" prop="orderStatus">
              <el-select v-model="form.orderStatus" placeholder="请选择状态" :disabled="true">
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
              <el-input v-model="form.orderMaterialName" placeholder="请输入物料">
                <el-button slot="append" icon="el-icon-search" @click="handleMaterialQueryOpen"></el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="物料编号" prop="orderMaterialId">
              <el-input v-model="form.orderMaterialId" placeholder="请输入物料编号" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="规格型号" prop="orderMaterialModel">
              <el-input v-model="form.orderMaterialModel" placeholder="请输入规格型号" :disabled="true" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="计量单位" prop="orderMaterialUnit">
              <el-select v-model="form.orderMaterialUnit" placeholder="请选择计量单位" :disabled="true">
                <el-option
                  v-for="dict in dict.type.mes_unit"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
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
            <el-form-item label="开始生产" prop="orderStartDate" >
              <el-date-picker clearable
                v-model="form.orderStartDate"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:100%;"
                :disabled="true"
                placeholder="暂未开始生产">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="实际完工" prop="orderEndDate" >
              <el-date-picker clearable
                v-model="form.orderEndDate"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:100%;"
                :disabled="true"
                placeholder="暂未完工">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider content-position="center">工单工序信息</el-divider>
        <!-- <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddOrderRoute">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteOrderRoute">删除</el-button>
          </el-col>
        </el-row> -->
        <el-table :data="orderRouteList" :row-class-name="rowOrderRouteIndex" @selection-change="handleOrderRouteSelectionChange" ref="orderRoute">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="processIndex" width="50"/>
          <!-- <el-table-column label="工序路线编号" prop="routeId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.routeId" placeholder="请输入工序路线编号" />
            </template>
          </el-table-column> -->
          <el-table-column label="工序编号" prop="processId" width="auto">
            <template slot-scope="scope">
              <!-- <el-input v-model="scope.row.processId" placeholder="请输入工序编号" /> -->
              <el-select v-model="scope.row.processId" placeholder="工序" clearable :disabled="true">
                  <el-option
                    v-for="item in processList"
                    :key="item.processId"
                    :label="item.processName"
                    :value="item.processId"
                  />
                </el-select>
            </template>
          </el-table-column>
          <!-- <el-table-column label="工序" prop="processName" width="auto">
              <template slot-scope="scope">
                <el-input v-model="scope.row.processName" placeholder="请输入工序编号" :disabled="true"/>
              </template>
          </el-table-column> -->
          <!-- <el-table-column label="工序顺序" prop="processIndex" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.processIndex" placeholder="请输入工序顺序" />
            </template>
          </el-table-column> -->
          <el-table-column label="工位" prop="stationId" width="auto">
            <template slot-scope="scope">
              <el-select v-model="scope.row.stationId" placeholder="请选择工位" clearable @change="changeStation(scope)">
                <el-option
                  v-for="item in stationList"
                  :key="item.stationId"
                  :label="item.stationName"
                  :value="item.stationId"
                />
              </el-select>
            </template>
          </el-table-column>
          
          <el-table-column label="车间编号" prop="workshopName" width="auto">
            <template slot-scope="scope">
              <!-- <el-input v-model="scope.row.workshopName" placeholder="请输入车间编号" :disabled="true"/> -->
              <el-select v-model="scope.row.workshopId" placeholder="请选择车间" clearable :disabled="true">
                <el-option
                  v-for="item in workshopList"
                  :key="item.workshopId"
                  :label="item.workshopName"
                  :value="item.workshopId"
                />
              </el-select>
            </template>
          </el-table-column>
          <!-- <el-table-column label="完成数量" prop="orderFinishNumber" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.orderFinishNumber" placeholder="请输入完成数量" />
            </template>
          </el-table-column> -->
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
import { listItem, getItem, delItem, addItem, updateItem } from "@/api/mes/item";
import { listMaterial, getMaterial } from "@/api/mes/material";
import { listRoute, getRoute } from "@/api/mes/route";
import { listStation } from "@/api/mes/station";
import { listWorkshop } from "@/api/mes/workshop";
import { listProcess, getProcess } from "@/api/mes/process";
import { listUser } from "@/api/system/user";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { listOrder, getOrder, delOrder, addOrder, updateOrder, changeOrderStatus } from "@/api/mes/order";
import { addReport } from "@/api/mes/report";


export default {
  name: "Order",
  dicts: ['mes_order_status', 'material_type', 'sys_normal_disable', 'mes_unit'],
  data() {
    return {
      // 工位列表
      stationList: [],
      // 车间列表
      workshopList: [],
      // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓查询物料产品弹窗所用数据↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
      // 物料查询参数
      materialQueryParams: {
        pageNum: 1,
        pageSize: 10,
        materialItemName: null,
        materialItemModel: null,
        materialItemUnit: null,
        materialItemClass: null,
        materialItemType: null,
        materialItemRoute: null,
        materialItemStatus: null,
        materialItemAddTime: null,
      },
      // 物料产品表格数据
      itemList: [],
      // 物料产品总条数
      materialTotal: 0,
      // 物料产品遮罩层
      materialLoading: true,
      // 物料类别
      materialName: undefined,
      // 物料分类树选项
      materialOptions: [],
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 工序与路线关系表格数据
      routeProcessList: [],
      // 工序路线数据
      routeList: [],
      // ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑查询物料产品弹窗所用数据↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
      // 用户列表
      userList: [],
      // 工序填报表头数据
      reportInfoList: [],

      // 当前用户名
      username: null,
      // 当前用户id
      userId: null,

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
      // 工序列表
      processList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示报工弹出层
      reportOpen: false,
      // 是否显示报工填报层
      reportDetailopen: false,
      // 报工弹出层所用Id暂存
      reportOrderId: null,
      // 是否显示物料查询弹出层
      materialOpen: false,
      // 物料查询弹出层标题
      materialTitle: "",
      // 报工弹出层标题
      reportTitle: "报工工序选择",
      // 报工填报层标题
      reportDetailTitle: "报工信息填写",
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
      // 报工单表单
      reportForm: {
        reportTime: new Date(),
      },
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
  watch: {
    // 根据名称筛选物料树
    materialName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    this.getProcessList();
    this.getStationList();
    this.getWorkshopList();
    this.materialgetList();
    this.getTreeselect();
    this.getRouteList();
    this.getUserList();
    this.username = this.$store.state.user.name;
  },
  methods: {
    /** 获取用户列表 */
    getUserList() {
      listUser({}).then(response => {
        this.userList = response.rows;
      });
    },
    /** 获取工序列表 */
    getProcessList() {
      listProcess({}).then(response => {
        this.processList = response.rows;
      });
    },
    /** 获取车间列表 */
    getWorkshopList() {
      listWorkshop({}).then(response => {
        this.workshopList = response.rows;
      });
    },
    /** 当所选工位发生变更时 */
    changeStation(scope) {
      const stationId = scope.row.stationId;
      const workshop = this.workshopList.find(item => item.workshopId === this.stationList.find(item => item.stationId === stationId).workshopId);
      scope.row.workshopId = workshop.workshopId;
      scope.row.workshopName = workshop.workshopName;

      // this.workshopId = this.stationList.find(item => item.stationId === val).workshopId;
      // for (let workshop of this.workshopList) {
      //   if (workshop.workshopId === this.workshopId) {
      //     this.workshopName = workshop.workshopName;
      //   }
      // }
    },
    /** 获取工位列表 */
    getStationList() {
      listStation({}).then(response => {
        this.stationList = response.rows;
      });
    },
    // 处理物料点击事件
    handleMaterialRowClick(data) {
      this.form.orderMaterialName = data.materialItemName;
      this.form.orderMaterialId = data.materialItemId;
      this.form.orderMaterialModel = data.materialItemModel;
      this.form.orderMaterialUnit = data.materialItemUnit;
      this.getRouteProcessList(data.materialItemRoute);
      this.materialOpen = false;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.materialQueryParams.materialItemClass = data.id;
      this.handleMaterialQuery();
    },
    /** 搜索按钮操作 */
    handleMaterialQuery() {
      this.materialQueryParams.pageNum = 1;
      this.materialgetList();
    },
    /** 重置按钮操作 */
    resetMaterialQuery() {
      this.resetForm("materialQueryForm");
      this.materialQueryParams.materialItemClass = null;
      this.handleMaterialQuery();
    },
    /** 查询具体工序路线 */
    getRouteProcessList(routeId) {
      getRoute(routeId).then(response => {
        // this.routeProcessList = response.data.routeProcessList;
        this.orderRouteList = response.data.routeProcessList;
      });
    },
    /** 查询工序路线列表 */
    getRouteList() {
      listRoute({}).then(response => {
        this.routeList = response.rows;
      });
    },
    /** 查询物料分类下拉树结构 */
    getTreeselect() {
      listMaterial().then(response => {
        response.data = response.data.map(obj => {
          obj.id = obj.materialId;
          obj.label = obj.materialName;
          delete obj.materialId;
          delete obj.materialName;
          return obj;
        });
        const data = { id: 0, label: '所有物料', children: [] };
        data.children = this.handleTree(response.data, "id", "materialParentId");
        this.materialOptions.push(data);
      });
    },

    /** 查询物料产品列表 */
    materialgetList() {
      this.materialLoading = true;
      listItem(this.materialQueryParams).then(response => {
        this.itemList = response.rows;
        this.materialTotal = response.materialTotal;
        this.materialLoading = false;
      });
    },
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
    reportCancel() {
      this.reportOpen = false;
      this.reset();
    },
    // 报工填报提交按钮
    submitReportForm(){
      this.$refs["reportForm"].validate(valid => {
        if (valid) {
          this.reportForm.orderId = this.reportOrderId;
          this.reportForm.routeId = this.reportInfoList.routeId;
          this.reportForm.processId = this.reportInfoList.processId;
          this.reportForm.stationId = this.reportInfoList.stationId;
          this.reportForm.workshopId = this.reportInfoList.workshopId;
          this.reportForm.processIndex = this.reportInfoList.processIndex;
          addReport(this.reportForm).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.reportDetailopen = false;
            getOrder(reportOrderId).then(response => {
              this.orderRouteList = response.data.orderRouteList;
            });
          });
        }
      });
    },
    // 报工填报取消按钮
    reportFormCancel() {
      this.reportDetailopen = false;
      this.reportForm = {};
      this.reportForm.reportTime = new Date();
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        unfold: false,
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
      this.routeProcessList = [];
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
    /** 报工按钮操作 */
    handleReport(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      this.reportOrderId = orderId;
      getOrder(orderId).then(response => {
        this.form = response.data;
        this.reportOpen = true;
        this.orderRouteList = response.data.orderRouteList;
        getItem(response.data.orderMaterialId).then(response => {
          this.form.orderMaterialName = response.data.materialItemName;
          this.form.orderMaterialId = response.data.materialItemId;
          this.form.orderMaterialModel = response.data.materialItemModel;
          this.form.orderMaterialUnit = response.data.materialItemUnit;
        });
      });
    },
    /** 报工填报按钮操作 */
    handleReportOrderRoute(row) {
      this.reportOpen = false;
      this.reportDetailopen = true;
      this.reportInfoList = row;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产工单";
    },
    /** 物料查询按钮操作 */
    handleMaterialQueryOpen() {
      this.materialOpen = true;
      this.materialTitle = "物料产品选择";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrder(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.orderRouteList = response.data.orderRouteList;
        this.title = "修改生产工单";
        getItem(response.data.orderMaterialId).then(response => {
          this.form.orderMaterialName = response.data.materialItemName;
          this.form.orderMaterialId = response.data.materialItemId;
          this.form.orderMaterialModel = response.data.materialItemModel;
          this.form.orderMaterialUnit = response.data.materialItemUnit;
        });
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
    /** 开始排产按钮操作 */
    handleStart(row) {
      const orderIds = row.orderId || this.ids;
      let count = 0;
      for(let orderId of orderIds) {
        getOrder(orderId).then(response => {
          if(response.data.orderStatus == 1) {
            this.$modal.msgError("工单编号为" + orderId + "的工单已开始排产，不能重复开始排产");
            flag = false;
            return;
          } else if(response.data.orderStatus == 3) {
            this.$modal.msgError("工单编号为" + orderId + "的工单已完工，不能开始排产");
            flag = false;
            return;
          } else if(response.data.orderStatus == 4) {
            this.$modal.msgError("工单编号为" + orderId + "的工单已强制关闭，不能开始排产");
            flag = false;
            return;
          } else {
            count++;
            if(count == orderIds.length) {
              this.handleUpdateStatus(row, 1);
            }
          }
        });
      }
    },

    /** 暂停排产按钮操作 */
    handlePause(row) {
      const orderIds = row.orderId || this.ids;
      let count = 0;
      for(let orderId of orderIds) {
        getOrder(orderId).then(response => {
          if(response.data.orderStatus == 0) {
            this.$modal.msgError("工单编号为" + orderId + "的工单未开始排产，不能暂停排产");
            flag = false;
            return;
          } else if(response.data.orderStatus == 2) {
            this.$modal.msgError("工单编号为" + orderId + "的工单已暂停排产，不能重复暂停排产");
            flag = false;
            return;
          } else if (response.data.orderStatus == 3) {
            this.$modal.msgError("工单编号为" + orderId + "的工单已完工，不能暂停排产");
            flag = false;
            return;
          } else if(response.data.orderStatus == 4) {
            this.$modal.msgError("工单编号为" + orderId + "的工单已强制关闭，不能暂停排产");
            flag = false;
            return;
          } else {
            count++;
            if(count == orderIds.length) {
              this.handleUpdateStatus(row, 2);
            }
          }
        });
      }
    },

    /** 强制关闭按钮操作 */
    handleStop(row) {
      this.handleUpdateStatus(row, 4);
    },

    /** 修改工单状态按钮操作 */
    handleUpdateStatus(row, status) {
      const orderIds = row.orderId || this.ids;
      if (status == 4) {
        this.$modal.confirm('强制关闭操作会令工单编号为"' + orderIds + '"的工单(包括该工单的下的报工单等数据)失效,仍要强制关闭吗?').then(function() {
          return changeOrderStatus(orderIds, status);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("强制关闭成功");
        }).catch(() => {});
      }
      else {
        this.$modal.confirm('是否确认修改工单编号为"' + orderIds + '"的数据项状态？').then(function () {
          return changeOrderStatus(orderIds, status);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("修改状态成功");
        }).catch(() => { });
      }

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