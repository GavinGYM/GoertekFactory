<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--物料分类-->
      <el-col :span="4" :xs="24">
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
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="物料名称" prop="materialItemName">
            <el-input
              v-model="queryParams.materialItemName"
              placeholder="请输入物料名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="规格型号" prop="materialItemModel">
            <el-input
              v-model="queryParams.materialItemModel"
              placeholder="请输入规格型号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="计量单位" prop="materialItemUnit">
            <el-select v-model="queryParams.materialItemUnit" placeholder="请选择计量单位" clearable>
              <el-option
                v-for="dict in dict.type.mes_unit"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="物料分类" prop="materialItemClass">
            <el-input
              v-model="queryParams.materialItemClass"
              placeholder="请输入物料分类"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="物料类型" prop="materialItemType">
            <el-select v-model="queryParams.materialItemType" placeholder="请选择物料类型" clearable>
              <el-option
                v-for="dict in dict.type.material_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="工序路线" prop="materialItemRoute">
            <el-input
              v-model="queryParams.materialItemRoute"
              placeholder="请输入工序路线"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="状态" prop="materialItemStatus">
            <el-select v-model="queryParams.materialItemStatus" placeholder="请选择状态" clearable>
              <el-option
                v-for="dict in dict.type.sys_normal_disable"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="添加时间" prop="materialItemAddTime">
            <el-date-picker clearable
              v-model="queryParams.materialItemAddTime"
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
              v-hasPermi="['mes:item:add']"
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
              v-hasPermi="['mes:item:edit']"
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
              v-hasPermi="['mes:item:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['mes:item:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
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
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['mes:item:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['mes:item:remove']"
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
      </el-col>
    </el-row>

    <!-- 添加或修改物料信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物料名称" prop="materialItemName">
          <el-input v-model="form.materialItemName" placeholder="请输入物料名称" />
        </el-form-item>
        <el-form-item label="规格型号" prop="materialItemModel">
          <el-input v-model="form.materialItemModel" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="计量单位" prop="materialItemUnit">
          <el-select v-model="form.materialItemUnit" placeholder="请选择计量单位">
            <el-option
              v-for="dict in dict.type.mes_unit"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="物料分类" prop="materialItemClass">
          <!-- <el-input v-model="form.materialItemClass" placeholder="请输入物料分类" /> -->
          <treeselect 
            v-model="form.materialItemClass" 
            :options="materialOptions" 
            :show-count="true" 
            placeholder="请选择物料分类" 
          />
        </el-form-item>
        <el-form-item label="物料类型" prop="materialItemType">
          <el-select v-model="form.materialItemType" placeholder="请选择物料类型">
            <el-option
              v-for="dict in dict.type.material_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工序路线" prop="materialItemRoute">
          <!-- <el-input v-model="form.materialItemRoute" placeholder="请输入工序路线" /> -->

            <el-select v-model="form.materialItemRoute" placeholder="请输入工序路线" clearable>
              <el-option
                v-for="item in routeList"
                :key="item.routeId"
                :label="item.routeName"
                :value="item.routeId"
              />
            </el-select>
        
         </el-form-item>
        <el-form-item label="状态" prop="materialItemStatus">
          <el-radio-group v-model="form.materialItemStatus">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="materialItemComment">
          <el-input v-model="form.materialItemComment" type="textarea" placeholder="请输入内容" />
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
import { listItem, getItem, delItem, addItem, updateItem } from "@/api/mes/item";
import { listMaterial, getMaterial } from "@/api/mes/material";
import { listRoute } from "@/api/mes/route";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Item",
  dicts: ['material_type', 'sys_normal_disable', 'mes_unit'],
  components: { Treeselect },
  data() {
    return {
      // 物料类别
      materialName: undefined,
      // 物料分类树选项
      materialOptions: [],
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 工序路线数据
      routeList: [],
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
      // 物料信息表格数据
      itemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
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
      // 表单参数
      form: {},
      // 表单校验
      rules: {
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
    this.getTreeselect();
    this.getRouteList();
  },
  methods: {
    // /** 查询设备名称 */
    // getMaterialClassName(materialClassId) {
    //   getMaterial(materialClassId).then(response => {
    //     console.log(response);
    //     console.log(response.data.materialName);
    //     return response ? response.data.materialName : null;
    //   });
    // },
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
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.materialItemClass = data.id;
      this.handleQuery();
    },
    /** 查询物料信息列表 */
    getList() {
      this.loading = true;
      listItem(this.queryParams).then(response => {
        this.itemList = response.rows;
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
        materialItemId: null,
        materialItemName: null,
        materialItemModel: null,
        materialItemUnit: null,
        materialItemClass: null,
        materialItemType: null,
        materialItemRoute: null,
        materialItemStatus: null,
        materialItemAddTime: null,
        materialItemComment: null
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
      this.ids = selection.map(item => item.materialItemId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加物料信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const materialItemId = row.materialItemId || this.ids
      getItem(materialItemId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物料信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.materialItemId != null) {
            updateItem(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addItem(this.form).then(response => {
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
      const materialItemIds = row.materialItemId || this.ids;
      this.$modal.confirm('是否确认删除物料信息编号为"' + materialItemIds + '"的数据项？').then(function() {
        return delItem(materialItemIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mes/item/export', {
        ...this.queryParams
      }, `item_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
