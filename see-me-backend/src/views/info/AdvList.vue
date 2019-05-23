<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="70px"
      class="query-form">
      <el-input v-model="query.title"
        placeholder="请输入广告名称"
        style="width:150px;"></el-input>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
        <el-button type="primary"
          @click="handleAdd">添加广告</el-button>
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
      <el-table-column prop="title"
        label="名称"></el-table-column>
      <el-table-column prop="cover"
        label="封面图"
        width="70">
        <template slot-scope="scope">
          <img style="width:30px;height:30px;cursor: pointer;"
            :src="scope.row.cover"
            @click="handleCoverCardPreview(scope.row.cover)">
        </template>
      </el-table-column>
      <el-table-column prop="accountName"
        label="外链"></el-table-column>
      <el-table-column prop="readCount"
        label="阅读量"></el-table-column>
      <el-table-column prop="account"
        label="操作人"></el-table-column>
      <el-table-column prop="account"
        label="操作账户"></el-table-column>
      <el-table-column prop="status"
        label="状态">
        <template slot-scope="scope">
          <span v-if="scope.row.status === true ">上架</span>
          <span v-else>下架</span>
        </template>
      </el-table-column>
      <el-table-column label="操作"
        width="230">
        <template slot-scope="scope">
          <el-button size="mini"
            type="warning"
            @click="handleDownShelf(scope.$index, scope.row)">下架</el-button>
          <el-button size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini"
            type="danger"
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
      width="800px">
      <!-- 学校详情 -->
      <el-form ref="infoForm"
        :model="infoForm"
        :rules="rules"
        label-width="80px">
        <el-form-item prop="title"
          label="标题：">
          <el-input v-model="infoForm.title"></el-input>
        </el-form-item>
        <el-form-item prop="cover"
          label="封面：">
          <el-upload class="avatar-uploader"
            action="/admin/upload"
            :headers="headers"
            :show-file-list="false"
            :on-success="handleCoverSuccess"
            :on-remove="handleCoverRemove"
            :before-upload="beforeAvatarUpload">
            <img v-if="cover"
              :src="cover"
              class="avatar">
            <i v-else
              class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item prop="url"
          label="外链：">
          <el-input v-model="infoForm.url"></el-input>
        </el-form-item>
        <el-form-item prop="schoolIdList"
          label="范围：">
          <el-select v-model="infoForm.area"
            v-if="isEdit === false"
            @change="handleSelectChange"
            placeholder="请选择区域">
            <el-option key="0"
              label="全部"
              value="0"></el-option>
            <el-option key="1"
              label="澳门"
              value="澳门"></el-option>
            <el-option key="2"
              label="珠海"
              value="珠海"></el-option>
            <el-option key="3"
              label="香港"
              value="香港"></el-option>
          </el-select>
          <el-select v-model="schoolIdList"
            multiple
            :disabled="isEdit"
            @change="handleSelectShcoolChange"
            placeholder="请选择学校">
            <el-option key="0"
              label="全部"
              value="0"></el-option>
            <el-option v-for="school in schoolList"
              :key="school.id"
              :label="school.name"
              :value="school.id"></el-option>
          </el-select>
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

    <el-dialog :visible.sync="dialogImgVisible"
      size="tiny">
      <img width="100%"
        :src="dialogImageUrl"
        alt="">
    </el-dialog>

  </div>
</template>

<script type="text/ecmascript-6">
// Progress 进度条
import NProgress from "nprogress";
import {
  list,
  remove,
  save,
  downShelf,
  update,
  getSchoolList
} from "@/api/adv_list";

export default {
  components: {},
  data() {
    return {
      query: {
        title: ""
      },
      addEditDialogVisible: false,
      dialogImgVisible: false,
      dialogImageUrl: "",
      isEdit: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 0
      },
      //本来想放在 infoForm 里面的，但是显示不出来
      cover: "",
      schoolList: [],
      schoolIdList: [],
      infoForm: {
        cover: "",
        schoolIdList: [],
        area: ""
      },
      rules: {
        title: [{ required: true, message: "请输入用户昵称", trigger: "blur" }],
        cover: [{ required: true, message: "请上传封面", trigger: "change" }],
        schoolIdList: [
          { required: true, message: "请选择推送范围", trigger: "change" }
        ]
      },
      tableData: []
    };
  },
  created() { },
  mounted() {
    this.getList();
  },
  computed: {
    headers() {
      return {
        jxtAdminSessionId: this.$store.state.token
      };
    }
  },
  methods: {
    handleSizeChange: function (val) {
      this.page.pageSize = val;
      this.getList();
    },
    handleCoverCardPreview: function (val) {
      this.dialogImageUrl = val;
      this.dialogImgVisible = true;
    },
    handleCurrentChange: function (val) {
      this.pageNumber = val;
      this.getList();
    },
    handleSelectChange: function (val) {
      this.schoolIdList = [];
      this.infoForm.area = val;
      this.getSchoolList(val);
    },
    handleSelectShcoolChange: function () {
      this.infoForm.schoolIdList = this.schoolIdList;
    },
    formatDateJoinAt: function (row, column) {
      return this.$moment(row.joinAt).format("YYYY-MM-DD");
    },
    formatDateNextPayAt: function (row, column) {
      return this.$moment(row.nextPayAt).format("YYYY-MM-DD");
    },
    handleSearch: function () {
      this.getList();
    },
    handleAdd: function () {
      this.isEdit = false;
      this.addEditDialogVisible = true;
      this.cover = "";
      this.infoForm = Object.assign({}, null);
    },
    handleEdit: function (index, row) {
      this.cover = "";
      this.isEdit = true;
      this.infoForm = Object.assign({}, row);
      this.cover = this.infoForm.cover;
      this.schoolIdList = row.schoolIdList;
      this.getSchoolList(0);
      this.addEditDialogVisible = true;
    },
    handlePictureCardPreview: function (file) {
      this.dialogImageUrl = file.url;
      this.dialogImgVisible = true;
    },
    handleCoverRemove: function (file, fileList) {
      this.infoForm.cover = "";
    },
    handleCoverSuccess: function (res, file) {
      this.cover = res.file.url;
      this.infoForm.cover = res.file.url;
    },
    beforeAvatarUpload: function (file) {
      const isJPG = file.type === "image/jpeg" || "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传图片只能是 JPG或 png 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过 2MB!");
      }
      if (isJPG && isLt2M) {
      }
      return isJPG && isLt2M;
    },
    handleSubmitSave: function () {
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            NProgress.start();
            this.infoForm.schoolIdList = this.schoolIdList;
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
      this.$confirm("确认删除吗？")
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
    handleDownShelf: function (index, row) {
      this.$confirm("确定下架吗？")
        .then(_ => {
          let id = row.id;
          downShelf(id)
            .then(res => {
              this.infoForm.status = false;
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
        title: this.query.title,
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
    },
    getSchoolList: function (val) {
      if (this.isEdit === false) {
        this.infoForm.schoolIdList = [];
      }
      let params = {
        area: val
      };
      getSchoolList(params)
        .then(res => {
          this.schoolList = res.data.list;
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
