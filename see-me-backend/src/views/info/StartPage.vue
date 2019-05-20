<template>
  <div class="startpage">
    <el-form label-width="80px">
      <el-card class="box-card">
        <div slot="header"
          class="clearfix">
          <span>家长端APP</span>
          <el-button style="float: right; padding: 3px 0"
            type="primary"
            @click="hanleSubmitParent">提交</el-button>
        </div>
        <el-form-item label="封面："
          required>
          <el-upload class="avatar-uploader"
            action="/admin/upload"
            :headers="headers"
            :show-file-list="false"
            :on-success="handleParentSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="parentCover"
              :src="parentCover"
              class="avatar">
            <i v-else
              class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="外链："
          required>
          <el-input v-model="parentUrl"></el-input>
        </el-form-item>
      </el-card>

      <el-card class="box-card"
        style="margin-top:10px;">
        <div slot="header"
          class="clearfix">
          <span>教师端APP</span>
          <el-button style="float: right; padding: 3px 0"
            type="primary"
            @click="hanleSubmitTeacher">提交</el-button>
        </div>
        <el-form-item label="封面：">
          <el-upload class="avatar-uploader"
            action="/admin/upload"
            :headers="headers"
            :show-file-list="false"
            :on-success="handleTeacherSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="teacherCover"
              :src="teacherCover"
              class="avatar">
            <i v-else
              class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="外链：">
          <el-input v-model="teacherUrl"></el-input>
        </el-form-item>
      </el-card>
    </el-form>
  </div>
</template>

<script type="text/ecmascript-6">
import NProgress from "nprogress"; // Progress 进度条
import { teacherPageSave, parentPageSave, getCover } from "@/api/startpage";
export default {
  data() {
    return {
      parentCover: "",
      parentUrl: "",
      teacherCover: "",
      teacherUrl: "",
      tableData: []
    };
  },
  created() { },
  mounted() {
    this.getCover();
  },
  computed: {
    headers() {
      return {
        jxt_admin_session_id: this.$store.state.token
      };
    }
  },
  methods: {
    handleParentSuccess: function (res, file) {
      this.parentCover = res.file.url;
    },
    handleTeacherSuccess: function (res, file) {
      this.teacherCover = res.file.url;
    },
    beforeAvatarUpload: function (file) {
      const isJPG = file.type === "image/jpeg" || "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传图片只能是 JPG 或 png 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过 2MB!");
      }
      if (isJPG && isLt2M) {
      }
      return isJPG && isLt2M;
    },
    hanleSubmitParent: function () {
      if (!this.parentCover || !this.parentUrl) {
        this.$message.error("请上传启动页并填写外链");
        return;
      }
      this.$confirm("确认提交吗？", "提示", {}).then(() => {
        NProgress.start();
        let para = {
          cover: this.parentCover,
          url: this.parentUrl
        };
        parentPageSave(para).then(res => {
          NProgress.done();
          this.parentCover = res.data.url;
          this.$message.success(res.data.msg);
        });
      });
    },
    hanleSubmitTeacher: function () {
      if (!this.teacherCover || !this.teacherUrl) {
        this.$message.error("请上传启动页并填写外链");
        return;
      }
      this.$confirm("确认提交吗？", "提示", {}).then(() => {
        NProgress.start();
        let para = {
          cover: this.teacherCover,
          url: this.teacherUrl
        };
        teacherPageSave(para).then(res => {
          NProgress.done();
          this.teacherCover = res.data.url;
          this.$message.success(res.data.msg);
        });
      });
    },
    getCover: function (val) {
      getCover()
        .then(res => {
          if (res.data.parent) {
            this.parentCover = res.data.parent.cover;
            this.parentUrl = res.data.parent.url;
          }
          if (res.data.teacher) {
            this.teacherCover = res.data.teacher.cover;
            this.teacherUrl = res.data.teacher.url;
          }
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
.startpage {
  margin-top: 10px;
}

.box-card {
  width: 600px;
}
</style>
