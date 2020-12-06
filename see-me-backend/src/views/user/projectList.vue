<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="70px"
      class="query-form">
      <el-input v-model="query.title"
        placeholder="请输入项目名称"
        style="width:150px;"></el-input>
      <el-input v-model="query.author"
        placeholder="请输入作者名称"
        style="width:150px;"></el-input>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
        <el-button type="primary"
          @click="handleAdd">添加项目</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      height="580"
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      ref="projectTable"
      stripe
      border>
      <el-table-column type="index"
        :index="realIndex"
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
      <el-table-column prop="author"
        label="作者"></el-table-column>
      <el-table-column prop="viewCount"
        label="浏览数"></el-table-column>
      <el-table-column prop="praiseCount"
        sortable
        label="好评数"></el-table-column>
      <el-table-column prop="status"
        label="状态">
        <template slot-scope="scope">
          <span v-if="scope.row.status">上架</span>
          <span v-else>下架</span>
        </template>
      </el-table-column>
      <el-table-column label="操作"
        width="230">
        <template slot-scope="scope">
          <el-button size="mini"
            type="warning"
            plain
            @click="handleDownShelf(scope.$index,scope.row)">{{scope.row.status?'下架':'上架'}}</el-button>
          <el-button size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini"
            type="danger"
            plain
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

    <el-dialog :title="isEdit?'编辑':'新增'"
      :visible.sync="addEditDialogVisible"
      @close="closeDialog('addEditDialogVisible')"
      width="50%">
      <!-- 项目新增 -->
      <el-form ref="infoForm"
        :model="infoForm"
        :rules="rules"
        label-width="110px">
        <el-form-item prop="title"
          label="标题：">
          <el-input style="width:300px;"
            v-model="infoForm.title"></el-input>
        </el-form-item>
        <el-form-item prop="createField"
          label="创作领域：">
          <el-select style="width:300px;"
            v-model="infoForm.createFields"
            placeholder="请选择创作领域">
            <el-option v-for="field of fieldList"
              :key="field.id"
              :label="field.fieldName"
              :value="''+field.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="cover"
          label="封面：">
          <el-upload class="img-uploader"
            action="/upload"
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
        <el-form-item prop="extCover"
          label="配图：">
          <el-upload ref='upload'
            action="/upload"
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

      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="closeDialog('addEditDialogVisible')">关闭</el-button>
        <el-button type="primary"
          v-if="isEdit"
          @click="handleSubmitUpdate">提交</el-button>
        <el-button type="primary"
          v-else
          @click="handleSubmitSave">确定</el-button>
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
import NProgress from 'nprogress'
import {upload} from '@/axios/upload'
import {
  list,
  findById,
  remove,
  save,
  update,
  changeStatus,
  findCreateFileldList
} from '@/api/project_list'

export default {
  data() {
    return {
      query: {
        title: '',
        author: ''
      },
      addEditDialogVisible: false,
      isShowImgPreview: false,
      previewImageUrl: '',
      isEdit: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 1
      },
      fieldList: [],
      //本来想放在 infoForm 里面的，但是显示不出来
      cover: '',
      linkImageList: [],
      extImageList: [],
      infoForm: {},
      rules: {
        title: [{required: true, message: '请输入项目标题', trigger: 'blur'}],
        cover: [{required: true, message: '请上传封面', trigger: 'change'}]
      },
      tableData: []
    }
  },
  created() {},
  mounted() {
    this.getList()
  },
  computed: {
    realIndex() {
      const _this = this
      return function (index) {
        return (_this.page.pageNumber - 1) * _this.page.pageSize + index + 1
      }
    },
    headers() {
      return {
        jxtAdminSessionId: this.$store.state.token
      }
    }
  },
  methods: {
    closeDialog(flag) {
      this[flag] = false
      this.infoForm = []
      this.cover = ''
      this.extImageList = []
      if (this.$refs['infoForm']) {
        this.$refs['infoForm'].resetFields()
      }
      console.log(this.$refs)

      this.$refs['upload'].clearFiles()
    },
    handleCoverCardPreview: function (val) {
      this.previewImageUrl = val
      this.isShowImgPreview = true
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.page.pageNumber = 1
      this.getList()
    },
    handleCurrentChange(val) {
      console.log('当前页', val)
      this.page.pageNumber = val
      this.getList()
    },
    handleSearch: function () {
      this.getList()
    },
    handleAdd: function () {
      this.isEdit = false
      this.cover = ''
      this.linkImageList = []
      this.infoForm = Object.assign({}, null)
      this.addEditDialogVisible = true
      this.reFindCreateFieldList()
    },
    handleEdit: function (index, row) {
      this.isEdit = true
      this.linkImageList = []
      this.reFindCreateFieldList()
      this.reFindDetailByid(row.id)
    },
    reFindDetailByid(id) {
      let params = {id: id}
      findById(params).then(res => {
        this.infoForm = res.data
        this.cover = this.infoForm.cover
        const tempImageList = []
        if (this.infoForm.imagesPath) {
          this.infoForm.imagesPath.split(',').forEach(imageUrl => {
            tempImageList.push({url: imageUrl})
            this.linkImageList.push({url: imageUrl})
          })
        }
        this.extImageList = tempImageList
        this.addEditDialogVisible = true
      })
    },
    handlePictureCardPreview: function (file) {
      this.previewImageUrl = file.url
      this.isShowImgPreview = true
    },
    handleExtCoverSuccess(res) {
      this.linkImageList.push(res.file)
    },
    handleExtCoverRemove(res) {
      let removeImageIndex = 0
      if (res.response) {
        removeImageIndex = this.linkImageList.findIndex(
          file => file.name === res.response.file.name
        )
        this.linkImageList.splice(removeImageIndex, 1)
      } else {
        removeImageIndex = this.linkImageList.findIndex(
          file => file.uid === res.uid
        )
        this.linkImageList.splice(removeImageIndex, 1)
      }
    },
    handleCoverRemove: function (file, fileList) {
      this.infoForm.cover = ''
    },
    handleCoverSuccess(res, file) {
      this.cover = res.file.url
      this.infoForm.cover = res.file.url
    },
    handleDownShelf(index, row) {
      let tempStatus = row.status === 1 ? 0 : 1

      let params = {id: row.id, status: tempStatus}
      changeStatus(params).then(res => {
        this.$message.success(res.data.msg)
        this.$refs.projectTable.data[index].status = tempStatus
      })
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传图片只能是 JPG或者 png 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!')
      }
      if (isJPG && isLt2M) {
      }
      return isJPG && isLt2M
    },
    handleImageAdded: function (file, Editor, cursorLocation) {
      upload(file, '/upload')
        .then(result => {
          var url = result.data.file.url // Get url from response
          if (url.indexOf('http') === -1) {
            url = 'http://127.0.0.1:80' + url
          }
          Editor.insertEmbed(cursorLocation, 'image', url)
        })
        .catch(err => {
          console.log(err)
        })
    },
    handleSubmitSave: function () {
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            NProgress.start()
            this.addEditDialogVisible = false
            this.infoForm.createrId = -1
            this.infoForm.imagesPath = this.linkImageList
              .map(file => file.url)
              .join(',')
            let para = Object.assign({}, this.infoForm)
            save(para).then(res => {
              NProgress.done()
              this.$message.success(res.data.msg)
              this.$refs['infoForm'].resetFields()
              this.getList()
            })
          })
        }
      })
    },
    handleSubmitUpdate: function () {
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            NProgress.start()
            this.addEditDialogVisible = false
            this.infoForm.imagesPath = this.linkImageList
              .map(file => file.url)
              .join(',')
            let para = Object.assign({}, this.infoForm)
            update(para).then(res => {
              NProgress.done()
              this.$message.success(res.data.msg)
              this.$refs['infoForm'].resetFields()
              this.getList()
            })
          })
        }
      })
    },
    handleRemove: function (index, row) {
      this.$confirm('确定删除吗？')
        .then(_ => {
          let id = row.id
          remove(id)
            .then(res => {
              this.$message.success(res.data.msg)
              this.getList()
            })
            .catch(error => {
              this.$message.error(error)
            })
        })
        .catch(_ => {})
    },
    getList: function () {
      let params = {
        title: this.query.title,
        author: this.query.author,
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize
      }
      list(params)
        .then(res => {
          this.tableData = res.data.page.list
          this.page.total = res.data.page.totalRow
        })
        .catch(error => {
          this.$message.error(error)
          console.log(error)
        })
    },
    reFindCreateFieldList() {
      findCreateFileldList().then(res => {
        this.fieldList = res.data
      })
    }
  }
}
</script>

<style>
.school-item {
  margin-bottom: 1px;
}
.school-name {
  font-weight: 700;
}
</style>