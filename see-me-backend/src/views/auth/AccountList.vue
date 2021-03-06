<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="60px"
      class="query-form">
      <el-input v-model="query.name"
        placeholder="请输入管理员姓名"
        style="width:150px;margin-left:10px;"></el-input>
      <el-input v-model="query.account"
        placeholder="请输入账号"
        style="width:150px;"></el-input>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
        <el-button type="primary"
          @click="handleAdd">添加管理员</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      class="table"
      stripe
      border
      v-loading="loading">
      <el-table-column type="index"
        :index="realIndex"
        label="序号"
        width="70"></el-table-column>
      <el-table-column prop="name"
        label="姓名"></el-table-column>
      <el-table-column prop="roleName"
        label="权限组"></el-table-column>
      <el-table-column prop="username"
        label="账户"> </el-table-column>
      <el-table-column prop="lastLoginAt"
        label="上次登录时间"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini"
            type="primary"
            plain
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini"
            v-if="scope.row.roleId!==0"
            type="danger"
            plain
            @click="handleRemove(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page.pageNumber"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.total">
    </el-pagination>

    <el-dialog title="新增/编辑"
      :visible.sync="addEditDialogVisible"
      @close="closeDialog('addEditDialogVisible')"
      width="500px">
      <!-- 学校详情 -->
      <el-form ref="infoForm"
        :model="infoForm"
        :rules="rules"
        label-width="120px">
        <el-form-item prop="name"
          label="管理员名称：">
          <el-input v-model="infoForm.name"
            style="width:300px;"></el-input>
        </el-form-item>
        <el-form-item prop="username"
          label="登陆账号：">
          <el-input v-model="infoForm.username"
            style="width:300px;"></el-input>
        </el-form-item>
        <el-form-item prop="roleId"
          v-if="infoForm.roleId!==0"
          label="权限组：">
          <el-select v-model="infoForm.roleId"
            :disabled="infoForm.roleId===0"
            :title="infoForm.roleId===0?'不能修改超级管理员的权限':''"
            placeholder="请选择权限组"
            style="width:300px;">
            <el-option v-for="role in selectRoleList"
              :key="role.id"
              :label="role.name"
              :value="role.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="closeDialog('addEditDialogVisible')">关闭</el-button>
        <el-button type="primary"
          v-if="isEdit"
          @click="handleSubmitUpdate">提交</el-button>
        <el-button type="primary"
          v-else
          @click="handleSubmitSave">确认</el-button>
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
        account: ""
      },
      loading: false,
      addEditDialogVisible: false,
      isEdit: false,
      infoForm: {
        id: "",
        name: "",
        roleId: ""
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
        name: [{ required: true, message: "请输入名称", trigger: "blur" }],
        username: [{ required: true, message: "请输入登录账号", trigger: "blur" }],
        roleId: [{ required: true, message: "请选择权限组", trigger: "blur" }]
      }
    };
  },
  computed: {
    realIndex() {
      const _this = this
      return function (index) {
        return (_this.page.pageNumber - 1) * _this.page.pageSize + index + 1
      }
    },
    selectRoleList() {
      return this.roleList.filter(item => item.id !== 0)
    }
  },
  mounted() {
    this.getList();
    this.getRoleList();
  },
  watch: {},
  methods: {
    closeDialog(flag) {
      this[flag] = false
      this.infoForm = []
      if (this.$refs["infoForm"]) { this.$refs["infoForm"].resetFields(); }
    },
    handleSizeChange: function (val) {
      this.page.pageSize = val;
      this.getList();
    },
    handleCurrentChange: function (val) {
      this.pageNumber = val;
      this.getList();
    },
    handleSearch: function () {
      this.getList();
    },
    handleAdd: function () {
      this.isEdit = false;
      this.selectRoleId = ''
      this.infoForm = Object.assign({}, null);
      this.addEditDialogVisible = true;
    },
    handleEdit: function (index, row) {
      this.isEdit = true;
      this.infoForm = Object.assign({}, row);
      this.selectRoleId = row.roleId + "";
      this.addEditDialogVisible = true;
    },
    getList: function () {
      let params = {
        username: this.query.account,
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
    getRoleList: function () {
      getRoleList()
        .then(res => {
          this.roleList = res.data.list;
        })
        .catch(error => {
          this.$message.error(error);
          console.log(error);
        });
    },
    handleSubmitSave: function () {
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
    handleSubmitUpdate: function () {
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
    handleRemove: function (index, row) {
      this.$confirm(`确认删除${row.name}的管理员身份么`)
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
        .catch(_ => { });
    }
  }
};
</script>

<style>
</style>
