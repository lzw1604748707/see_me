<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="70px"
      class="query-form">
      <el-input v-model="query.title"
        placeholder="请输入资讯名称"
        style="width:150px;"></el-input>
      <el-select style="width:150px;"
        v-model="query.createField"
        filterable
        placeholder="请选择展示领域">
        <el-option v-for="field of fieldList"
          :key="field.id"
          :label="field.fieldName"
          :value="field.id"></el-option>
      </el-select>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
        <el-button type="primary"
          @click="handleAdd">添加资讯</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      class="table"
      stripe
      border>
      <el-table-column type="index"
        label="序号"
        width="70"></el-table-column>
      <el-table-column prop="cover"
        label="封面图"
        width="100">
        <template slot-scope="scope">
          <img style="width:30px;height:30px;cursor: pointer;"
            :src="scope.row.cover"
            @click="handleCoverCardPreview(scope.row.cover)">
        </template>
      </el-table-column>
      <el-table-column prop="title"
        label="资讯标题"></el-table-column>
      <el-table-column prop="adminName"
        label="咨询发布人"></el-table-column>
      <el-table-column prop="readCount"
        label="阅读量"></el-table-column>
      <el-table-column prop="fieldName"
        label="展示领域"></el-table-column>
      <el-table-column prop="createDate"
        sortable
        label="创建时间"
        :formatter="formatDate"></el-table-column>
      <el-table-column prop="status"
        label="状态">
        <template slot-scope="scope">
          <span>{{statusName(scope.row.status)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作"
        width="230">
        <template slot-scope="scope">
          <!-- <el-button size="mini"
            type="warning"
            @click="handlePreview(scope.$index, scope.row)">预览</el-button> -->
          <el-button size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini"
            type="danger"
            @click="handleRemove(scope.$index, scope.row)">刪除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination class="table__pagination"
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page.pageNumber"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.total">
    </el-pagination>

    <el-dialog top="2vh"
      :title="isEdit?'编辑':'新增'"
      :visible.sync="addEditDialogVisible"
      width="60%">
      <!-- 学校详情 -->
      <el-form ref="infoForm"
        inline
        :model="infoForm"
        :rules="rules"
        label-width="120px">
        <el-form-item prop="title"
          label="标题：">
          <el-input v-model="infoForm.title"
            placeholder="请输入标题："></el-input>
        </el-form-item>
        <el-form-item prop="createField"
          label="展示领域：">
          <el-select style="width:300px;"
            filterable
            v-model="infoForm.fieldId"
            placeholder="请选择创作领域">
            <el-option v-for="field of fieldList"
              :key="field.id"
              :label="field.fieldName"
              :value="field.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="cover"
          style="width:400px;"
          label="封面：">
          <el-upload class="img-uploader"
            action="/admin/upload"
            :headers="headers"
            :show-file-list="false"
            :on-success="handleCoverSuccess"
            :on-remove="handleCoverRemove"
            :before-upload="beforeAvatarUpload">
            <img class="img"
              v-if="cover"
              :src="cover">
            <i v-else
              class="el-icon-plus img-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item prop="status"
          style="margin-left: -80px;"
          label="状态：">
          <el-select style="width:300px;"
            v-model="infoForm.status"
            placeholder="请选择状态">
            <el-option :key="0"
              label="草稿"
              :value="0"></el-option>
            <el-option :key="1"
              label="进行中"
              :value="1"></el-option>
            <el-option :key="2"
              label="过期"
              :value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="extCover"
          style="display:block;"
          label="配图：">
          <el-upload action="/admin/upload"
            list-type="picture-card"
            :headers="headers"
            :file-list="extImageList"
            :on-success="handleExtCoverSuccess"
            :on-remove="handleExtCoverRemove"
            :on-preview="handlePictureCardPreview"
            :before-upload="beforeAvatarUpload">
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
        <el-form-item prop="content"
          label="正文">
          <vue-editor useCustomImageHandler
            @imageAdded="handleImageAdded"
            v-model="infoForm.content"></vue-editor>
        </el-form-item>
      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="closeDialog">关闭</el-button>
        <el-button type="primary"
          v-if="isEdit"
          @click="handleSubmitUpdate">更新</el-button>
        <el-button type="primary"
          v-else
          @click="handleSubmitSave">提交</el-button>
      </span>
    </el-dialog>

    <div class="pircture__preview"
      v-show="isShowImgPreview"
      @click="isShowImgPreview=false">
      <img class="preview__img"
        :src="previewImageUrl"
        alt="">
    </div>

  </div>
</template>

<script type="text/ecmascript-6">
// Progress 进度条
import NProgress from "nprogress";
import { upload } from "@/axios/upload";
import {
  list,
  findById,
  remove,
  save,
  update,
  findCreateFileldList
} from "@/api/info_list";

export default {
  data() {
    return {
      query: {
        title: "",
        createField: ''
      },

      addEditDialogVisible: false,
      isEdit: false,

      isShowImgPreview: false,
      previewImageUrl: "",

      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 0
      },
      fieldList: [],
      //本来想放在 infoForm 里面的，但是显示不出来
      cover: "",
      extCoverList: [],

      infoForm: {
        title: '',
        cover: "",
        extCoverList: [],
        content: ""
      },
      rules: {
        title: [{ required: true, message: "请输入资讯标题", trigger: "blur" }],
        cover: [{ required: true, message: "请上传封面", trigger: "change" }],
        createFieldId: [{ required: true, message: "请选择展示领域", trigger: "change" }],
        content: [
          { required: true, message: "请输入正文", trigger: "blur" },
          { min: 20, message: "长度不能少于20个字符", trigger: "blur" }
        ]
      },
      tableData: []
    };
  },
  computed: {
    statusName() {
      return function (status) {
        const statusNameList = ['草稿', '进行中', '过期']
        return statusNameList[status]
      }
    },
    headers() {
      return {
        jxtAdminSessionId: this.$store.state.token
      };
    }
  },
  methods: {
    closeDialog() {
      this.addEditDialogVisible = false
      this.$refs["infoForm"].resetFields();
    },
    handleSizeChange: function (val) {
      this.page.pageSize = val;
      this.getList();
    },
    handleCoverCardPreview(val) {
      this.previewImageUrl = val;
      this.isShowImgPreview = true;
    },
    handleCurrentChange: function (val) {
      this.pageNumber = val;
      this.getList();
    },
    formatDate(...dataList) {
      return this.$moment(dataList[2]).format("YYYY-MM-DD");
    },
    handleSearch() {
      this.getList();
    },
    handleAdd: function () {
      this.isEdit = false;
      this.cover = "";
      this.extCoverList = [];
      this.infoForm = Object.assign({}, null);
      this.addEditDialogVisible = true;
    },
    handleEdit: function (index, row) {
      this.cover = "";
      this.extImageList = [];
      this.isEdit = true;
      this.reFindDetailByid(row.id)
      this.addEditDialogVisible = true;
    },
    reFindDetailByid(id) {
      let params = { id: id }
      findById(params).then(res => {
        this.infoForm = res.data
        this.cover = this.infoForm.cover;
        if (this.infoForm.imagesPath) {
          this.infoForm.imagesPath.split(',').forEach(imageUrl => {
            this.extImageList.push({ url: imageUrl })
          })
        }
      })
    },
    handlePictureCardPreview: function (file) {
      this.previewImageUrl = file.url;
      this.isShowImgPreview = true;
    },
    handleExtCoverSuccess: function (res, file) {
      this.extImageList.push(res.file);
    },
    handleCoverRemove: function (file, fileList) {
      this.infoForm.cover = "";
    },
    handleCoverSuccess(res, file) {
      this.cover = res.file.url;
      this.infoForm.cover = res.file.url;
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
    handleImageAdded: function (file, Editor, cursorLocation) {
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
    handleSubmitSave: function () {
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            NProgress.start();
            this.infoForm.imagesPath = this.extImageList.join(',')
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
            this.infoForm.imagesPath = this.extImageList.join(',')
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
      this.$confirm("确定删除吗？")
        .then(_ => {
          let id = row.id;
          remove(id).then(res => {
            this.$message.success(res.data.msg);
            this.getList();
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
        author: this.query.author,
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize
      };
      list(params).then(res => {
        this.tableData = res.data.page.list;
        this.page.total = res.data.page.totalRow;
      })
        .catch(error => {
          this.$message.error(error);
          console.log(error);
        });
    },
    reFindCreateFieldList() {
      findCreateFileldList().then(res => {
        this.fieldList = res.data
        console.log('参数', this.fieldList);
      })
    }
  },
  mounted() {
    this.getList();
    this.reFindCreateFieldList()
  }
};
</script>

<style>
</style>
