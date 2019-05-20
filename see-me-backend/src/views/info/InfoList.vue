<template>
<div>
<!-- 搜索 -->
<el-form inline :model="query" label-position="right" label-width="70px" class="query-form">
  <el-input v-model="query.title" placeholder="請輸入資訊名稱" style="width:150px;"></el-input>
  <el-form-item>
    <el-button type="primary" @click="handleSearch" >搜索</el-button>
    <el-button type="primary" @click="handleAdd">添加資訊</el-button>
  </el-form-item>
</el-form>
<!-- 数据表格 -->
<el-table :data="tableData" class="table" stripe border>
  <el-table-column type="index" label="序號"  width="70"></el-table-column>
  <el-table-column prop="title" label="名稱"></el-table-column>
  <el-table-column prop="cover" label="封面圖" width="70">
    <template  slot-scope="scope">
      <img style="width:30px;height:30px;cursor: pointer;"  :src="scope.row.cover" @click="handleCoverCardPreview(scope.row.cover)">
    </template>
  </el-table-column>
  <el-table-column prop="accountName" label="資訊發佈人"></el-table-column>
  <el-table-column prop="readCount" label="閱讀量"></el-table-column>
  <el-table-column prop="account" label="操作人"></el-table-column>
  <el-table-column prop="status" label="狀態">
    <template  slot-scope="scope">
      <span v-if="scope.row.status === true ">上架</span>
      <span v-else>下架</span>
    </template>
  </el-table-column>
  <el-table-column label="操作" width="230" >
    <template slot-scope="scope">
      <el-button size="mini" type="warning" @click="handleDownShelf(scope.$index, scope.row)">下架</el-button>
      <el-button size="mini"  @click="handleEdit(scope.$index, scope.row)">編輯</el-button>
      <el-button size="mini" type="danger" @click="handleRemove(scope.$index, scope.row)">刪除</el-button>
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



<el-dialog title="新增/编辑" :visible.sync="addEditDialogVisible" width="80%" >
  <!-- 学校详情 -->
 <el-form ref="infoForm" :model="infoForm" :rules="rules" label-width="80px">
	<el-form-item prop="title" label="標題：">
	  <el-input v-model="infoForm.title"></el-input>
	</el-form-item>
	<el-form-item prop="cover" label="封面：">
    <el-upload
      class="avatar-uploader"
      action="/admin/upload"
      :headers="headers"
      :show-file-list="false"
      :on-success="handleCoverSuccess"
      :on-remove="handleCoverRemove"
      :before-upload="beforeAvatarUpload">
      <img v-if="cover" :src="cover" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
	</el-form-item>
  <el-form-item prop="extCover" label="配圖：">
    <el-upload
      action="/admin/upload"
      list-type="picture-card"
      :headers="headers"
      :file-list="extCoverList"
      :on-success="handleExtCoverSuccess"
      :on-remove="handleExtCoverRemove"
      :on-preview="handlePictureCardPreview"
      :before-upload="beforeAvatarUpload"
      >
      <i class="el-icon-plus"></i>
    </el-upload>
	</el-form-item>
  <el-form-item prop="schoolIdList" label="範圍：">
		 <el-select v-model="infoForm.area" v-if="isEdit===false"  @change="getSchoolList" placeholder="請選擇區域">
      <el-option  key="0" label="全部" value="0"></el-option>  
      <el-option  key="1" label="澳門" value="澳門"></el-option>  
      <el-option  key="2" label="珠海" value="珠海"></el-option>  
      <el-option  key="3" label="香港" value="香港"></el-option>  
    </el-select>
     <el-select v-model="schoolIdList" :disabled="isEdit" multiple placeholder="請選擇學校">
      <el-option  key="0" label="全部" value="0"></el-option>  
      <el-option v-for="school in schoolList":key="school.id" :label="school.name" :value="school.id"></el-option>
    </el-select>
	</el-form-item>
  <el-form-item prop="content" label="正文">
      <vue-editor useCustomImageHandler  @imageAdded="handleImageAdded" v-model="infoForm.content"></vue-editor>
	</el-form-item>
</el-form>
  <span slot="footer" class="dialog-footer">
    <el-button @click="addEditDialogVisible = false">關閉</el-button>
    <el-button type="primary" v-if="isEdit" @click="handleSubmitUpdate">提 交</el-button>
    <el-button type="primary" v-else @click="handleSubmitSave">確 定</el-button>
  </span>
</el-dialog>


<el-dialog :visible.sync="dialogImgVisible" size="tiny">
  <img width="100%" :src="dialogImageUrl" alt="">
</el-dialog>

</div>
</template>

<script type="text/ecmascript-6">
// Progress 进度条
import NProgress from "nprogress";
import { upload } from "@/axios/upload";
import {
  list,
  remove,
  save,
  downShelf,
  update,
  getSchoolList
} from "@/api/info_list";

export default {
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
        total: 1
      },
      //本来想放在 infoForm 里面的，但是显示不出来
      cover: "",
      extCoverList: [],
      schoolList: [],
      schoolIdList: [],
      infoForm: {
        cover: "",
        extCoverList: [],
        schoolIdList: [],
        content: "",
        area: ""
      },
      rules: {
        title: [{ required: true, message: "請輸入學校名稱", trigger: "blur" }],
        cover: [{ required: true, message: "請上傳封面", trigger: "change" }],
        area: [{ required: true, message: "請選擇推送範圍", trigger: "change" }],
        content: [
          { required: true, message: "請輸入正文", trigger: "blur" },
          { min: 20, message: "長度不能少於20個字符", trigger: "blur" }
        ]
      },
      tableData: []
    };
  },
  created() {},
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
    handleSizeChange: function(val) {
      this.page.pageSize = val;
      this.getList();
    },
    handleCoverCardPreview: function(val) {
      this.dialogImageUrl = val;
      this.dialogImgVisible = true;
    },
    handleCurrentChange: function(val) {
      this.pageNumber = val;
      this.getList();
    },
    formatDateJoinAt: function(row, column) {
      return this.$moment(row.joinAt).format("YYYY-MM-DD");
    },
    formatDateNextPayAt: function(row, column) {
      return this.$moment(row.nextPayAt).format("YYYY-MM-DD");
    },
    handleSearch: function() {
      this.getList();
    },
    handleAdd: function() {
      this.isEdit = false;
      this.cover = "";
      this.extCoverList = [];
      this.infoForm = Object.assign({}, null);
      this.addEditDialogVisible = true;
    },
    handleEdit: function(index, row) {
      this.cover = "";
      this.extCoverList = [];
      this.isEdit = true;
      this.infoForm = Object.assign({}, row);
      this.cover = this.infoForm.cover;
      this.infoForm.schoolIdList = row.schoolIdList;
      this.infoForm.extCoverList.forEach(element => {
        const file = {
          url: element.url
        };
        this.extCoverList.push(file);
      });
      this.getSchoolList(0);
      this.addEditDialogVisible = true;
    },
    handlePictureCardPreview: function(file) {
      this.dialogImageUrl = file.url;
      this.dialogImgVisible = true;
    },
    handleExtCoverSuccess: function(res, file) {
      this.extCoverList.push(res.file);
      this.infoForm.extCoverList = this.extCoverList;
    },
    handleExtCoverRemove: function(file, fileList) {
      this.infoForm.extCoverList = this.extCoverList;
    },
    handleCoverRemove: function(file, fileList) {
      this.infoForm.cover = "";
    },
    handleCoverSuccess: function(res, file) {
      this.cover = res.file.url;
      this.infoForm.cover = res.file.url;
    },
    beforeAvatarUpload: function(file) {
      const isJPG = file.type === "image/jpeg" || "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      if (isJPG && isLt2M) {
      }
      return isJPG && isLt2M;
    },
    handleImageAdded: function(file, Editor, cursorLocation) {
      upload(file, "/admin/upload")
        .then(result => {
          var url = result.data.file.url; // Get url from response
          if (url.indexOf("http") === -1) {
            url = "http://127.0.0.1:80" + url;
          }
          Editor.insertEmbed(cursorLocation, "image", url);
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleSubmitSave: function() {
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
    handleSubmitUpdate: function() {
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
    },
    handleDownShelf: function(index, row) {
      this.$confirm("確定下架嗎？")
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
        .catch(_ => {});
    },
    getList: function() {
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
    getSchoolList: function(val) {
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
