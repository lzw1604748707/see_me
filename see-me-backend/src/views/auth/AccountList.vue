<template>
<div>
<!-- 搜索 -->
<el-form inline :model="query" label-position="right" label-width="60px" class="query-form">
  <el-input v-model="query.name" placeholder="請輸入管理员姓名" style="width:150px;margin-left:10px;" ></el-input>
  <el-input v-model="query.username" placeholder="請輸入账号" style="width:150px;" ></el-input>
  <el-form-item>
    <el-button type="primary" @click="handleSearch">搜索</el-button>
    <el-button type="primary" @click="handleAdd">添加管理員</el-button>
  </el-form-item>
</el-form>
<!-- 数据表格 -->
<el-table :data="tableData" class="table" stripe border v-loading="loading">
  <el-table-column type="index" label="序號"  width="70"></el-table-column>
  <el-table-column prop="name" label="姓名"></el-table-column>
  <el-table-column prop="roleName" label="權限組"></el-table-column>
  <el-table-column prop="username" label="賬號"> </el-table-column>
  <el-table-column prop="lastLoginAt" label="上次登錄時間"></el-table-column>
  <el-table-column label="操作">
    <template slot-scope="scope">
      <el-button size="mini" type="info" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
      <el-button size="mini" type="danger" @click="handleRemove(scope.$index, scope.row)">删除</el-button>
    </template>
  </el-table-column>
</el-table>
<!-- 分页组件 -->
<el-pagination
  @size-change="handleSizeChange"
  @current-change="handleCurrentChange"
  :current-page="page.pageNumber"
  :page-sizes="[10, 20, 30, 40]"
  :page-size="page.pageSize"
  layout="total, sizes, prev, pager, next, jumper"
  :total="page.total">
</el-pagination>



<el-dialog title="新增/编辑" :visible.sync="addEditDialogVisible" width="800px" >
  <!-- 学校详情 -->
 <el-form ref="infoForm" :model="infoForm" :rules="rules" label-width="120px">
	<el-form-item prop="name" label="權限組名稱：">
	  <el-input v-model="infoForm.name"></el-input>
	</el-form-item>
	<el-form-item prop="username" label="登陸賬號：">
   <el-input v-model="infoForm.username"></el-input>
	</el-form-item>
	<el-form-item prop="roleId" label="權限組：">
   <el-select v-model="selectRoleId" placeholder="請選擇權限組">
    <el-option key="0" v-if="infoForm.roleId === 0" label="超級管理員" value="0"></el-option>
    <el-option v-for="role in roleList":key="role.id" :label="role.name" :value="role.id"></el-option>
  </el-select>
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
import { list, remove, update, save, getRoleList } from "@/api/account_list";
import NProgress from "nprogress"; // Progress 进度条

export default {
  data() {
    return {
      query: {
        name: "",
        username: ""
      },
      loading: false,
      addEditDialogVisible: false,
      isEdit: false,
      selectRoleId: "",
      infoForm: {
        id: "",
        name: "",
        roleId: "0"
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
      roleList: [],
      tableData: [],
      rules: {
        name: [{ required: true, message: "請輸入名稱", trigger: "blur" }],
        username: [{ required: true, message: "請輸入登陸賬號", trigger: "blur" }],
        roleId: [{ required: true, message: "請选择权限组" }]
      }
    };
  },
  created() {},
  mounted() {
    this.getList();
    this.getRoleList();
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
    handleSearch: function() {
      this.getList();
    },
    handleAdd: function() {
      this.isEdit = false;
      this.selectRoleId = ''
      this.infoForm = Object.assign({}, null);
      this.addEditDialogVisible = true;
    },
    handleEdit: function(index, row) {
      this.isEdit = true;
      this.infoForm = Object.assign({}, row);
      this.selectRoleId = row.roleId + "";
      this.addEditDialogVisible = true;
    },
    getList: function() {
      let params = {
        username: this.query.username,
        name: this.query.name,
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize
      };
      this.loading = true;
      list(params)
        .then(res => {
          this.tableData = res.data.page.list;
          this.page.pageNumber = res.data.page.pageNumber;
          this.page.pageSize = res.data.page.pageSize;
          this.page.total = res.data.page.totalRow;
          this.loading = false;
        })
        .catch(error => {
          this.loading = false;
          this.$message.error(error);
          console.log(error);
        });
    },
    getRoleList: function() {
      getRoleList()
        .then(res => {
          this.roleList = res.data.list;
        })
        .catch(error => {
          this.$message.error(error);
          console.log(error);
        });
    },
    handleSubmitSave: function() {
      this.infoForm.roleId = this.selectRoleId;
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
      this.infoForm.roleId = this.selectRoleId;
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            NProgress.start();
            let para = Object.assign({}, this.infoForm);
            console.log(para);
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
