<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="70px"
      class="query-form">
      <el-input v-model="query.name"
        placeholder="请输入学校名称"
        style="width:150px"></el-input>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
        <el-button type="primary">导出学校列表</el-button>
        <el-button type="primary"
          @click="handleAdd">添加学校</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      class="table"
      stripe
      border>
      <el-table-column type="index"
        label="序号"
        width="70"></el-table-column>
      <el-table-column prop="name"
        label="学校名称"></el-table-column>
      <el-table-column prop="area"
        label="所在区域"></el-table-column>
      <el-table-column prop="leader"
        label="负责人"></el-table-column>
      <el-table-column prop="contact"
        label="联系方式"></el-table-column>
      <el-table-column prop="teacherCount"
        label="教师数量"></el-table-column>
      <el-table-column prop="studentCount"
        label="学生数量"></el-table-column>
      <el-table-column prop="classCount"
        label="班级数量"></el-table-column>
      <el-table-column prop="joinAt"
        label="入驻时间"
        :formatter="formatDateJoinAt"></el-table-column>
      <el-table-column prop="status"
        label="状态">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 1">正常</span>
          <span v-else>停用</span>
        </template>
      </el-table-column>
      <el-table-column prop="nextPayAt"
        label="下次缴费"
        sortable
        width="120"
        :formatter="formatDateNextPayAt"></el-table-column>
      <el-table-column label="操作"
        width="230">
        <template slot-scope="scope">
          <el-button size="mini"
            type="info"
            @click="handleRead(scope.$index, scope.row)">查看</el-button>
          <el-button size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini"
            type="danger"
            @click="handleRemove(scope.$index, scope.row)">刪除</el-button>
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

    <el-dialog title="查看"
      :visible.sync="infoDialogVisible"
      width="800px">
      <!-- 学校详情 -->
      <el-form label-width="120px">
        <el-form-item class="school-item"
          label="学校名称">
          <span class="school-name">{{infoForm.name}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="所在区域：">
          <span>{{infoForm.area}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="学校负责人：">
          <span>{{infoForm.leader}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="教师数量：">
          <span>{{infoForm.teacherCount}}</span>
          <span style="margin-left:40px;">
            <el-button type="primary"
              size="mini"
              round
              @click="handleTeacherList">教师列表</el-button>
          </span>
        </el-form-item>
        <el-form-item class="school-item"
          label="学生数量：">
          <span>{{infoForm.studentCount}}</span>
          <span style="margin-left:40px;">
            <el-button type="primary"
              size="mini"
              round
              @click="handleStudentList">学生列表：</el-button>
          </span>
        </el-form-item>
        <el-form-item class="school-item"
          label="家长数量：">
          <span>{{infoForm.parentCount}}</span>
          <span style="margin-left:40px;">
            <el-button type="primary"
              size="mini"
              round
              @click="handleParentList">家长列表:</el-button>
          </span>
        </el-form-item>
        <el-form-item class="school-item"
          label="班级数量：">
          <span>{{infoForm.classCount}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="入驻时间：">
          <span>{{infoForm.joinAt}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="下次缴费时间:">
          <span>{{infoForm.nextPayAt}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="学校地址：">
          <span>{{infoForm.address}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="备注信息：">
          <span>{{infoForm.remark}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="管理员手机号：">
          <span>{{infoForm.contact}}</span>
          <span style="margin-left:40px;">
            <el-button type="primary"
              size="mini"
              round
              @click="handleResetPassword">重置密码</el-button>
          </span>
        </el-form-item>
        <el-form-item class="school-item"
          label="学校logo：">
          <img style="width:200px;height:200px;"
            src="../../assets/logo.png">
          <!-- <img style="width:200px;height:200px;" :src="infoForm.logo"> -->
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
            v-if="infoForm.status === 1"
            @click="handleStop">停用学校</el-button>
          <el-button type="success"
            v-else
            @click="handleEnable">启用学校</el-button>
          <el-button type="danger"
            @click="handleRemove">删除学校</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="infoDialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>

    <el-dialog title="新增/编辑"
      :visible.sync="addEditDialogVisible"
      width="800px">
      <!-- 学校详情 -->
      <el-form ref="infoForm"
        :model="infoForm"
        :rules="rules"
        label-width="120px">
        <el-form-item prop="name"
          label="学校名称：">
          <el-input v-model="infoForm.name"></el-input>
        </el-form-item>
        <el-form-item prop="area"
          label="所在区域">
          <el-select v-model="infoForm.area"
            placeholder="请选择">
            <el-option key="1"
              label="澳门"
              value=“澳门></el-option>
            <el-option key="2"
              label="珠海"
              value="珠海"></el-option>
            <el-option key="3"
              label="香港"
              value="香港"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="leader"
          label="学校负责人：">
          <el-input v-model="infoForm.leader"></el-input>
        </el-form-item>
        <el-form-item prop="address"
          label="学校地址：">
          <el-input v-model="infoForm.address"></el-input>
        </el-form-item>
        <el-form-item prop="contact"
          label="管理员手机：">
          <el-input v-model="infoForm.contact"></el-input>
        </el-form-item>
        <el-form-item prop="remark"
          label="备注信息：">
          <el-input type="textarea"
            v-model="infoForm.remark"></el-input>
        </el-form-item>
        <el-form-item prop="nextPayAt"
          label="下次缴费时间：">
          <el-date-picker type="date"
            placeholder="选择日期"
            v-model="infoForm.nextPayAt"
            value-format="yyyy-MM-dd"
            style="width: 100%;"></el-date-picker>
        </el-form-item>
      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="addEditDialogVisible = false">关闭</el-button>
        <el-button type="primary"
          v-if="isEdit"
          @click="handleSubmitUpdate">提交</el-button>
        <el-button type="primary"
          v-else
          @click="handleSubmitSave">确定</el-button>

      </span>
    </el-dialog>

  </div>
</template>

<script type="text/ecmascript-6">
import NProgress from "nprogress"; // Progress 进度条

import {
  list,
  remove,
  save,
  stop,
  update,
  resetLeaderPhone
} from "@/api/school_list";

export default {
  data() {
    return {
      query: {
        name: ""
      },
      infoDialogVisible: false,
      addEditDialogVisible: false,
      isEdit: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 0
      },
      infoForm: {},
      rules: {
        name: [{ required: true, message: "请输入学校名称", trigger: "blur" }],
        area: [{ required: true, message: "请输入所在區域", trigger: "blur" }],
        leader: [{ required: true, message: "请输入管理员名称", trigger: "blur" }],
        contact: [
          { required: true, message: "请输入管理员手机", trigger: "blur" },
          { min: 6, max: 11, message: "长度在 6 到 11 个字符", trigger: "blur" }
        ],
        nextPayAt: [
          { required: true, message: "请选择下次缴费日期", trigger: "change" }
        ],
        address: [{ required: true, message: "请输入学校地址", trigger: "blur" }]
      },
      tableData: []
    };
  },
  created() { },
  mounted() {
    this.getList();
  },
  methods: {
    handleSizeChange: function (val) {
      this.page.pageSize = val;
      this.getList();
    },
    handleCurrentChange: function (val) {
      this.pageNumber = val;
      this.getList();
    },
    formatDateJoinAt: function (row, column) {
      return this.$moment(row.joinAt).format("YYYY-MM-DD");
    },
    formatDateNextPayAt: function (row, column) {
      return this.$moment(row.nextPayAt).format("YYYY-MM-DD");
    },
    handleRead: function (index, row) {
      this.infoDialogVisible = true;
      this.infoForm = Object.assign({}, row);
    },
    handleAdd: function () {
      this.isEdit = false;
      this.addEditDialogVisible = true;
      this.infoForm = Object.assign({}, null);
    },
    handleEdit: function (index, row) {
      this.isEdit = true;
      this.addEditDialogVisible = true;
      this.infoForm = Object.assign({}, row);
    },
    handleSearch: function () {
      this.getList();
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
        .catch(_ => { });
    },
    handleStop: function (index, row) {
      this.$confirm("確定停用嗎？")
        .then(_ => {
          let id = this.infoForm.id;
          stop(id)
            .then(res => {
              this.infoForm.status = 0;
              this.getList();
              this.$message.success(res.data.msg);
            })
            .catch(error => {
              this.$message.error(error);
            });
        })
        .catch(_ => { });
    },
    handleEnable: function (index, row) {
      this.$confirm("確定启用嗎？")
        .then(_ => {
          let id = this.infoForm.id;
          stop(id)
            .then(res => {
              this.infoForm.status = 1;
              this.getList();
              this.$message.success(res.data.msg);
            })
            .catch(error => {
              this.$message.error(error);
            });
        })
        .catch(_ => { });
    },
    handleResetPassword: function (index, row) {
      this.$confirm("確定重置密码嗎？")
        .then(_ => {
          let id = this.infoForm.id;
          resetLeaderPhone(id)
            .then(res => {
              this.$message.success(res.data.msg);
            })
            .catch(error => {
              this.$message.error(error);
            });
        })
        .catch(_ => { });
    },
    handleTeacherList: function () {
      const schoolId = this.infoForm.id;
      this.$router.push({ path: "teacher", query: { shcoolId: schoolId } }); // -> /user/123
    },
    handleStudentList: function () {
      const schoolId = this.infoForm.id;
      this.$router.push({ path: "student", query: { shcoolId: schoolId } }); // -> /user/123
    },
    handleParentList: function () {
      const schoolId = this.infoForm.id;
      this.$router.push({ path: "parent", query: { shcoolId: schoolId } }); // -> /user/123
    },
    getList: function () {
      let params = {
        name: this.query.name,
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize
      };
      list(params)
        .then(res => {
          this.tableData = res.data.page.list;
          this.page.pageNumber = res.data.page.pageNumber;
          this.page.pageSize = res.data.page.pageSize;
          this.page.total = res.data.page.totalRow;
        })
        .catch(error => {
          this.$message.error(error);
          console.log(error);
        });
    }
  }
};
</script>

<style>
.school-item {
  margin-bottom: 1px;
}
.school-name {
  font-weight: 700;
}
</style>
