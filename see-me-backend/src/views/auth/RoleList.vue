<template>
<div>
<!-- 搜索 -->
<el-form inline label-position="right" label-width="60px" class="query-form">
  <el-form-item>
    <el-button type="primary" @click="handleAdd">添加權限組</el-button>
  </el-form-item>
</el-form>
<!-- 数据表格 -->
<el-table :data="tableData" class="table" stripe border v-loading="loading">
  <el-table-column type="index" label="序號"  width="70"></el-table-column>
  <el-table-column prop="name" label="權限組"></el-table-column>
  <el-table-column prop="roleAccount" label="管理員數量"></el-table-column>
  <el-table-column prop="createAt" label="添加時間"> </el-table-column>
  <el-table-column label="操作">
    <template slot-scope="scope">
      <el-button size="mini" type="info" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
      <el-button size="mini" type="danger" @click="handleRemove(scope.$index, scope.row)">删除</el-button>
    </template>
  </el-table-column>
</el-table>


<el-dialog title="新增/编辑" :visible.sync="addEditDialogVisible" width="800px" >
  <!-- 学校详情 -->
 <el-form ref="infoForm" :model="infoForm" :rules="rules" label-width="120px">
	<el-form-item prop="name" label="權限組名稱：">
	  <el-input v-model="infoForm.name"></el-input>
	</el-form-item>
	<el-form-item prop="menus" label="權限：">
   <el-tree :data="menus" show-checkbox expand-on-click-node node-key="id" ref="tree" 
      :default-expanded-keys="infoForm.menus" 
      :default-checked-keys="infoForm.menus" 
      :props="defaultProps">
   </el-tree>
	</el-form-item>
</el-form>
  <span slot="footer" class="dialog-footer">
    <el-button @click="addEditDialogVisible = false">關閉</el-button>
    <el-button type="primary" v-if="isEdit" @click="handleSubmitUpdate">提 交</el-button>
    <el-button type="primary" v-else @click="handleSubmitSave">確 定</el-button>
  </span>
</el-dialog>


</div>
</template>

<script type="text/ecmascript-6">
import { list, remove, update, save, getMenuList } from "@/api/role_list";
import NProgress from "nprogress"; // Progress 进度条

export default {
  data() {
    return {
      loading: false,
      addEditDialogVisible: false,
      isEdit: false,
      menus: [],
      infoForm: {
        id: "",
        name: "",
        menus: []
      },
      defaultProps: {
        children: "children",
        label: "label"
      },
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 0
      },
      tableData: [],
      rules: {
        name: [{ required: true, message: "請輸入名稱", trigger: "blur" }],
        menus: [{ required: true, message: "請選擇", trigger: "blur" }]
      }
    };
  },
  created() {},
  mounted() {
    this.getList();
    this.getMenus();
  },
  watch: {},
  methods: {
    handleSizeChange: function(val) {
      this.page.pageSize = val;
      this.getList();
    },
    handleCurrentChange: function(val) {
      this.pageNumber = val;
      this.getList();
    },
    handleAdd: function() {
      this.isEdit = false;
      this.addEditDialogVisible = true;
      this.infoForm = {};
    },
    handleEdit: function(index, row) {
      this.isEdit = true;
      this.addEditDialogVisible = true;
      this.infoForm = Object.assign({}, row);
      setTimeout(() => {
        this.$refs.tree.setCheckedKeys(row.menus);
      }, 100);
    },
    getList: function() {
      this.loading = true;
      list()
        .then(res => {
          this.tableData = res.data.list;
          this.loading = false;
        })
        .catch(error => {
          this.loading = false;
          this.$message.error(error);
          console.log(error);
        });
    },
    getMenus: function() {
      getMenuList()
        .then(res => {
          this.menus = res.data.list;
        })
        .catch(error => {
          this.$message.error(error);
          console.log(error);
        });
    },
    handleSubmitSave: function() {
      this.infoForm.menus = this.$refs.tree.getCheckedKeys();
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            NProgress.start();
            let para = Object.assign({}, this.infoForm);
            save(para).then(res => {
              NProgress.done();
              this.$message.success(res.data.msg);
              this.$refs["infoForm"].resetFields();
              this.addEditDialogVisible = false;
              this.getList();
            });
          });
        }
      });
    },
    handleSubmitUpdate: function() {
      this.infoForm.menus = this.$refs.tree.getCheckedKeys();
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            NProgress.start();
            let para = Object.assign({}, this.infoForm);
            update(para).then(res => {
              NProgress.done();
              this.$message.success(res.data.msg);
              this.$refs["infoForm"].resetFields();
              this.addEditDialogVisible = false;
              this.getList();
            });
          });
        }
      });
    },
    handleRemove: function(index, row) {
      this.$confirm("確定刪除嗎？")
        .then(_ => {
          let id = row.id;
          remove(id)
            .then(res => {
              this.$message.success(res.data.msg);
              this.getList();
            })
            .catch(error => {
              this.$message.error(error);
            });
        })
        .catch(_ => {});
    }
  }
};
</script>

<style>

</style>
