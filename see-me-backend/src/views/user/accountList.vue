<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="70px"
      class="query-form">
      <el-input v-model="query.name"
        placeholder="请输入用户名称"
        style="width:150px"></el-input>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
        <!-- <el-button type="primary">导出学校列表</el-button> -->
        <el-button type="primary"
          @click="handleAdd">添加用户</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      class="table"
      stripe
      border>
      <el-table-column align="center"
        type="index"
        label="序号"
        width="70"></el-table-column>
      <el-table-column align="center"
        prop="avatar"
        label="头像"
        width="70">
        <template slot-scope="scope">
          <img v-if="scope.row.avatar"
            style="width:40px;height:40px;cursor: pointer;"
            :src="scope.row.avatar"
            @click="handleCoverCardPreview(scope.row.avatar)">
          <img v-else
            style="width:40px;height:40px;cursor: pointer;"
            src="/static/img/system.jpg"
            @click="handleCoverCardPreview(scope.row.avatar)">
        </template>
      </el-table-column>
      <el-table-column align="center"
        prop="name"
        label="用户昵称"></el-table-column>
      <el-table-column align="center"
        prop="account"
        label="账号"></el-table-column>
      <el-table-column align="center"
        prop="userType"
        label="用户类型"></el-table-column>
      <el-table-column align="center"
        prop="status"
        label="状态">
        <template slot-scope="scope">
          <span>{{getStatusName(scope.row.status)}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center"
        prop="lastLoginDate"
        label="上次登录时间"
        sortable
        width="140"
        :formatter="formatDateJoinAt"></el-table-column>
      <el-table-column align="center"
        prop="nextPayAt"
        label="冻结时间"
        width="120"
        :formatter="formatDateNextPayAt"></el-table-column>
      <el-table-column align="center"
        class=""
        label="操作"
        width="230">
        <template slot-scope="scope">
          <el-button size="mini"
            type="info"
            @click="handleRead(scope.$index, scope.row)">查看</el-button>
          <el-button size="mini"
            @click="ChangeStatus(scope.row)">{{scope.row.status===2?'解冻':'冻结'}}</el-button>
          <el-button size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
      <!-- 用户详情 -->
      <el-form style="position:relative;"
        label-width="120px">
        <div class="user__avatar">
          <div class="user__avatar--img">
            <img v-if="infoForm.avatar"
              style="width:150px;height:150px;"
              :src="infoForm.avatar">
            <img v-else
              style="width:150px;height:150px;vertical-align: bottom;"
              src="/static/img/system.jpg"
              alt="">
          </div>
          <span>用户头像</span>
        </div>
        <el-form-item class="
            school-item"
          label="用户昵称：">
          <span class="school-name">{{infoForm.name}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="账号：">
          <span>{{infoForm.account}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="性别：">
          <span>{{infoForm.sex}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="职业：">
          <span>{{infoForm.career}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="地区：">
          <span>{{infoForm.area}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="用户类型：">
          <span>{{infoForm.userType}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="手机号码：">
          <span>{{infoForm.mobile}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="关注者数量：">
          <span>{{infoForm.followers}}</span>
          <span style="margin-left:40px;">
            <el-button type="primary"
              size="mini"
              round
              @click="reFindFollowerslist">关注者列表</el-button>
          </span>
        </el-form-item>
        <el-form-item class="school-item"
          label="正在关注数量：">
          <span>{{infoForm.followings}}</span>
          <span style="margin-left:40px;">
            <el-button type="primary"
              size="mini"
              round
              @click="reFindFollowingsList">正在关注列表</el-button>
          </span>
        </el-form-item>
        <el-form-item class="school-item"
          label="状态：">
          <span>{{getStatusName(infoForm.status)}}</span>
        </el-form-item>
        <el-form-item v-if="infoForm.status!==0"
          class="school-item"
          label="冻结时间:">
          <span>{{infoForm.freezeDate}}</span>
        </el-form-item>
        <el-form-item v-if="infoForm.status!==0"
          class="school-item"
          label="冻结原因：">
          <span>{{infoForm.freezeCause}}</span>
        </el-form-item>
        <el-form-item class="school-item"
          label="入驻时间：">
          <span>{{infoForm.createDate}}</span>
        </el-form-item>

        <el-form-item>
          <el-button type="primary"
            v-if="infoForm.status !== 1"
            @click="handleChangeStop">停用用户</el-button>
          <el-button type="success"
            v-else
            @click="handleChangeStop">启用用户</el-button>
          <el-button type="danger"
            @click="handleRemove">删除用户</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="infoDialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>

    <el-dialog title="编辑"
      :visible.sync="editDialogVisible"
      width="800px">
      <!-- 用户详情 -->
      <el-form ref="infoForm"
        style="position:relative;"
        inline="true"
        :model="infoForm"
        :rules="rules"
        label-width="120px">
        <el-form-item class="user__avatar"
          prop="cover"
          label="头像：">
          <el-upload class="avatar-uploader"
            action="/admin/upload"
            :headers="headers"
            :show-file-list="false"
            :on-success="handleCoverSuccess"
            :on-remove="handleCoverRemove"
            with-credentials='true'
            :before-upload="beforeAvatarUpload">
            <img v-if="avatar"
              :src="avatar"
              class="avatar">
            <i v-else
              class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item style="display:block;"
          prop="name"
          label="用户昵称：">
          <el-input v-model="infoForm.name"></el-input>
        </el-form-item>
        <el-form-item style="display:block;"
          prop="account"
          label="登陆账号：">
          <span>{{infoForm.account}}</span>
        </el-form-item>
        <el-form-item style="display:block;"
          prop="password"
          label="密码：">
          <el-input v-model="infoForm.password"></el-input>
        </el-form-item>
        <el-form-item prop="career"
          label="职业：">
          <el-input v-model="infoForm.career"></el-input>
        </el-form-item>
        <el-form-item prop="sex"
          label="性别：">
          <el-radio-group v-model="infoForm.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="area"
          label="地区：">
          <el-input v-model="infoForm.area"></el-input>
        </el-form-item>
        <el-form-item prop="mobile"
          label="手机号码：">
          <el-input v-model="infoForm.mobile"></el-input>
        </el-form-item>
        <el-form-item prop="email"
          label="电子邮箱：">
          <el-input v-model="infoForm.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="editDialogVisible = false">关闭</el-button>
        <el-button type="primary"
          @click="handleSubmitUpdate">提交</el-button>
      </span>
    </el-dialog>
    <el-dialog title="新增"
      :visible.sync="addDialogVisible"
      width="500px">
      <!-- 学校详情 -->
      <el-form ref="infoForm"
        :model="infoForm"
        :rules="rules"
        label-width="120px">
        <el-form-item prop="name"
          label="用户昵称：">
          <el-input v-model="infoForm.name"></el-input>
        </el-form-item>
        <el-form-item prop="account"
          label="登陆账号：">
          <el-input v-model="infoForm.account"></el-input>
        </el-form-item>
        <el-form-item prop="password"
          label="密码：">
          <el-input v-model="infoForm.password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="addDialogVisible = false">关闭</el-button>
        <el-button type="primary"
          @click="handleSubmitSave">确定</el-button>
      </span>
    </el-dialog>
    <div class="pircture__preview"
      v-show="isShowImgPreview"
      @click="isShowImgPreview=false">
      <img class="preview__img"
        :src="previewImageUrl||'/static/img/system.jpg'"
        alt="">
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import NProgress from "nprogress"; // Progress 进度条
import {
  list,
  remove,
  save,
  update,
  changeFreeze,
  changeStop,
  followersList,
  followingsList
} from "@/api/user_list";

export default {
  data() {
    return {
      query: {
        name: ""
      },
      infoDialogVisible: false,
      addDialogVisible: false,
      editDialogVisible: false,
      isShowImgPreview: false,
      previewImageUrl: "",
      isEdit: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 0
      },
      avatar: '',
      infoForm: {},
      rules: {
        name: [{ required: true, message: "请输入用户昵称", trigger: "blur" }],
        area: [{ required: true, message: "请输入所在地", trigger: "blur" }],
        email: [{ required: true, message: "请输入电子邮箱", trigger: "blur" }],
        moblie: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          { min: 6, max: 11, message: "长度在 6 到 11 个字符", trigger: "blur" }
        ],
        account: [{ required: true, message: "请输入登陆账户", trigger: "blur" }]
      },
      tableData: []
    };
  },
  created() { },
  mounted() {
    this.getList();
  },
  methods: {

    handleCoverCardPreview(url) {
      this.previewImageUrl = url;
      this.isShowImgPreview = true;
    },
    getStatusName(status) {
      const statusNameList = ['正常', '停用', '冻结']
      return statusNameList[status]
    },
    handleCoverSuccess(res, file) {
      this.avatar = res.file.url;
      this.infoForm.avatar = res.file.url;
    },
    handleCoverRemove(file, fileList) {
      this.infoForm.avatar = "";
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg" || "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传图片只能是 JPG或者 png 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过 2MB!");
      }
      if (isJPG && isLt2M) {
      }
      return isJPG && isLt2M;
    },
    handleSizeChange(val) {
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
    handleEdit: function (index, row) {
      this.editDialogVisible = true
      this.infoForm = Object.assign({}, row)
    },
    handleAdd: function () {
      this.addDialogVisible = true;
      this.infoForm = Object.assign({}, null);
    },
    ChangeStatus(row) {
      this.$confirm("确认提交吗？", "提示", {}).then(() => {
        NProgress.start();
        if (row.status === 2) {
          row.status = 1
        } else {
          row.status = 2
        }
        let params = { id: row.id, status: row.status }
        changeFreeze(params).then(res => {
          this.$message.success(res.data.msg);
          this.getList()
        });
      });
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
              this.addDialogVisible = false;
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
              this.editDialogVisible = false;
              this.getList();
            });
          });
        }
      });
    },
    handleRemove: function (index, row) {
      this.$confirm("确定刪除吗？")
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
    handleChangeStop() {
      const massage = this.infoForm.status === 1 ? '启用' : '停用'
      this.$confirm(`确定${massage}吗？`)
        .then(_ => {
          this.infoForm.status = this.infoForm.status === 1 ? 0 : 1
          const params = { id: this.infoForm.id, status: this.infoForm.status }
          changeStop(params)
            .then(res => {
              this.getList();
              this.$message.success(res.data.msg);
            })
            .catch(error => {
              this.$message.error(error);
            });
        })
        .catch(_ => { });
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
          if (this.page.pageNumber * this.page.pageSize < res.data.page.totalRow) {
            this.page.pageNumber++
          }
          this.page.total = res.data.page.totalRow;
        })
        .catch(error => {
          this.$message.error(error);
          console.log(error);
        });
    },
    reFindFollowerslist() {
      this.page.pageNumber = 1
      this.page.pageSize = 10
      this.query.name = ''
      let params = {
        id: this.infoForm.id,
        name: this.query.name,
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize
      };
      followersList(params)
        .then(res => {
          this.tableData = res.data.page.list;
          if (this.page.pageNumber * this.page.pageSize < res.data.page.totalRow) {
            this.page.pageNumber++
          }
          this.infoDialogVisible = false
          this.page.total = res.data.page.totalRow;
        })
        .catch(error => {
          this.$message.error(error);
          console.log(error);
        });
    },
    reFindFollowingsList() {
      this.page.pageNumber = 1
      this.page.pageSize = 10
      this.query.name = ''
      let params = {
        id: this.infoForm.id,
        name: this.query.name,
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize
      };
      followingsList(params)
        .then(res => {
          this.tableData = res.data.page.list;
          if (this.page.pageNumber * this.page.pageSize < res.data.page.totalRow) {
            this.page.pageNumber++
          }
          this.infoDialogVisible = false
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

<style <style lang="scss" scoped>
.school-item {
  margin-bottom: 1px;
}
.school-name {
  font-weight: 700;
}
.user__avatar {
  position: absolute;
  align-items: center;
  right: 115px;
  top: 0;
  text-align: center;
  &--img {
    border-radius: 15px;
    overflow: hidden;
    box-shadow: 0 0 5px #999;
    margin-bottom: 5px;
  }
}
</style>

