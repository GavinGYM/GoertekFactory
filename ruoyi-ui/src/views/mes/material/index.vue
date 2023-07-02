<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="父物料ID" prop="materialParentId">
        <el-input
          v-model="queryParams.materialParentId"
          placeholder="请输入父物料ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料名称" prop="materialName">
        <el-input
          v-model="queryParams.materialName"
          placeholder="请输入物料名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="显示顺序" prop="materialOrder">
        <el-input
          v-model="queryParams.materialOrder"
          placeholder="请输入显示顺序"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mes:material:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="materialList"
      row-key="materialId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      :default-sort="{prop: 'materialOrder', order: 'ascending'}"
    >
      <el-table-column label="物料名称" align="left" prop="materialName" />
      <el-table-column label="物料顺序" align="center" prop="materialOrder"  class-name="hidden-column" />
      <el-table-column label="物料说明" align="center" prop="materialComment" />
      <!-- 在这里设置min-width是因为由于第二列隐藏了，因此后面每一列都往前移动了一列，需要设置的就变成了最后一列 -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" min-width="1%">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mes:material:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['mes:material:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mes:material:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改物料分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父物料ID" prop="materialParentId">
          <treeselect v-model="form.materialParentId" :options="materialOptions" :normalizer="normalizer" placeholder="请选择父物料ID" />
        </el-form-item>
        <el-form-item label="物料名称" prop="materialName">
          <el-input v-model="form.materialName" placeholder="请输入物料名称" />
        </el-form-item>
        <el-form-item label="显示顺序" prop="materialOrder">
          <!-- <el-input v-model="form.materialOrder" placeholder="请输入显示顺序" /> -->
          <el-input-number v-model="form.materialOrder" @change="handleChange" :min="0" :max="10" label="描述文字"></el-input-number>
        </el-form-item>
        <el-form-item label="物料说明" prop="materialComment">
          <el-input v-model="form.materialComment" type="textarea" placeholder="请输入内容" />
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
import { listMaterial, getMaterial, delMaterial, addMaterial, updateMaterial } from "@/api/mes/material";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Material",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 物料分类表格数据
      materialList: [],
      // 物料分类树选项
      materialOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        materialParentId: null,
        materialName: null,
        materialOrder: null,
        materialComment: null
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
    /** 查询物料分类列表 */
    getList() {
      this.loading = true;
      listMaterial(this.queryParams).then(response => {
        this.materialList = this.handleTree(response.data, "materialId", "materialParentId");
        this.loading = false;
      });
    },
    /** 转换物料分类数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.materialId,
        label: node.materialName,
        children: node.children
      };
    },
	/** 查询物料分类下拉树结构 */
    getTreeselect() {
      listMaterial().then(response => {
        this.materialOptions = [];
        const data = { materialId: 0, materialName: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "materialId", "materialParentId");
        this.materialOptions.push(data);
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
        materialId: null,
        materialParentId: null,
        materialName: null,
        materialOrder: null,
        materialComment: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.materialId) {
        this.form.materialParentId = row.materialId;
      } else {
        this.form.materialParentId = 0;
      }
      this.open = true;
      this.title = "添加物料分类";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.materialParentId = row.materialId;
      }
      getMaterial(row.materialId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物料分类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.materialId != null) {
            updateMaterial(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaterial(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除物料分类编号为"' + row.materialId + '"的数据项？').then(function() {
        return delMaterial(row.materialId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>

<style>
.hidden-column {
  display: none;
  visibility:hidden;
  min-width: 0;
}
</style>
```