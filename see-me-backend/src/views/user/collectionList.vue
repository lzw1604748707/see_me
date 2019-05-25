<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="70px"
      class="query-form">
      <el-input v-model="query.title"
        placeholder="请输入作品集标题"
        style="width:150px"></el-input>
      <el-input v-model="query.creater"
        placeholder="请输入作者名称"
        style="width:150px"></el-input>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
        <el-button type="primary"
          @click="handleAdd">添加作品集</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      ref="collectionTable"
      height="580"
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      stripe
      border>
      <el-table-column type="index"
        label="序号"
        :index="realIndex"
        width="70"></el-table-column>
      <el-table-column prop="title"
        label="作品集标题"></el-table-column>
      <el-table-column prop="projectCoverList"
        width="250"
        label="封面集">
        <template slot-scope="scope">
          <div class="table__cover-list">
            <img class="table__cover"
              v-for="(coverUrl,index) of coverList(scope.row.coverListStr)"
              :key="index"
              :src="coverUrl"
              @click="handleCoverCardPreview(coverUrl)">
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="creater"
        label="创建者昵称"></el-table-column>
      <el-table-column prop="createDate"
        sortable
        label="创建时间"
        :formatter="formatDate"></el-table-column>
      <el-table-column class=""
        label="操作"
        width="300">
        <template slot-scope="scope">
          <el-button size="mini"
            type="info"
            plain
            @click="handleRead(scope.row)">查看</el-button>
          <el-button size="mini"
            type="primary"
            plain
            @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini"
            plain
            @click="handleDownShelf(scope.$index, scope.row)">{{scope.row.status?'上架':'下架'}}</el-button>
          <el-button size="mini"
            type="danger"
            plain
            @click="handleDelete(scope.row)">删除</el-button>
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
    <el-dialog title="查看"
      :visible.sync="infoDialogVisible"
      width="800px">
      <!-- 用户详情 -->
      <el-form style="position:relative;"
        inline
        label-width="120px">
        <el-form-item style="width:40%;"
          label="作品集标题：">
          <span class="school-name">{{infoForm.title}}</span>
        </el-form-item>
        <el-form-item style="width:40%;"
          label="状态：">
          <span>{{infoForm.status?'上架中':'已下架'}}</span>
        </el-form-item>
        <el-form-item style="width:40%;"
          label="创建人：">
          <span>{{infoForm.creater}}</span>
        </el-form-item>
        <el-form-item style="width:40%;"
          label="创建时间：">
          <span>{{infoForm.createDate}}</span>
        </el-form-item>
        <el-form-item style="display:flex;"
          label="项目列表：">
          <div class="form__project-list">
            <div v-if="!infoForm.projectList">暂无项目</div>
            <projectCard class="project-card"
              v-for="project of infoForm.projectList"
              :key="project.id"
              :project="project"></projectCard>
          </div>
        </el-form-item>
      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="closeDialog('infoDialogVisible')">关闭</el-button>
      </span>
    </el-dialog>

    <el-dialog :title="isEdit?'编辑':'新增'"
      :visible.sync="addEditDialogVisible"
      @close="closeDialog('addEditDialogVisible')"
      width="800px">
      <!-- 用户详情 -->
      <el-form ref="infoForm"
        style="position:relative;"
        :model="infoForm"
        :rules="rules"
        label-width="120px">
        <el-form-item style="display:block;"
          prop="title"
          label="作品集标题：">
          <el-input v-model="infoForm.title"></el-input>
        </el-form-item>
        <el-form-item class="school-item"
          label="项目列表：">
          <el-select style="width:300px;"
            @change="addPresetProjectList"
            filterable
            placeholder="请选择项目名称(支持搜索)">
            <el-option v-for="project in projectList"
              :key="project.id"
              :label="project.title"
              :value="project.id">
            </el-option>
          </el-select>
          <div class="form__project-list collection--edit">
            <projectCard class="project-card"
              v-for="project of presetProjectList"
              :key="project.id"
              :isShowClose="true"
              @remove="removeProjectCard"
              :project="project"></projectCard>
          </div>
        </el-form-item>
      </el-form>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="closeDialog('addEditDialogVisible')">关闭</el-button>
        <el-button v-if="isEdit"
          type="primary"
          @click="handleSubmitUpdate">更新</el-button>
        <el-button v-else
          type="primary"
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
import projectCard from './projectCard'
import NProgress from "nprogress"; // Progress 进度条
import {
  list,
  remove,
  save,
  update,
  changeStatus,
  findAllProjectToCardList
} from "@/api/collection_list";

export default {
  components: {
    projectCard
  },
  data() {
    return {
      query: {
        creater: "",
        title: ""
      },
      infoDialogVisible: false,
      addEditDialogVisible: false,
      isShowImgPreview: false,
      previewImageUrl: "",
      selectProjectId: '',
      presetProjectList: [],
      projectList: '',
      isEdit: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 0
      },
      infoForm: {},
      rules: {
        title: [{ required: true, message: "请输入作品集标题", trigger: "blur" }],
        author: [{ required: true, message: "请选择作者名称", trigger: "blur" }]
      },
      tableData: []
    };
  },
  computed: {
    realIndex() {
      const _this = this
      return function (index) {
        return (_this.page.pageNumber - 1) * _this.page.pageSize + index + 1
      }
    },
    coverList() {
      return function (coverListStr) {
        return coverListStr ? coverListStr.split(',').splice(0, 5) : []
      }
    }
  },
  created() { },
  mounted() {
    this.getList();
  },
  methods: {
    handleCoverCardPreview(url) {
      this.previewImageUrl = url
      this.isShowImgPreview = true
    },
    addPresetProjectList(id) {
      let tempIndex = this.projectList.findIndex(item => item.id === id)
      this.presetProjectList.push(this.projectList[tempIndex])
      this.projectList.splice(tempIndex, 1)
      console.log(this.presetProjectList);
    },
    removeProjectCard(id) {
      let tempIndex = this.presetProjectList.findIndex(item => item.id === id)
      this.projectList.push(this.presetProjectList[tempIndex])
      this.presetProjectList.splice(tempIndex, 1)
    },
    closeDialog(flag) {
      this[flag] = false
      this.infoForm = []
      this.presetProjectList = []
      if (this.$refs["infoForm"]) { this.$refs["infoForm"].resetFields(); }
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.page.pageNumber = 1
      this.getList();
    },
    handleCurrentChange(val) {
      console.log('当前页', val);
      this.page.pageNumber = val;
      this.getList();
    },
    formatDate(...dataList) {
      return this.$moment(dataList[2]).format("YYYY-MM-DD");
    },
    handleRead(row) {
      this.infoDialogVisible = true;
      this.infoForm = Object.assign({},
        { id: row.id, createrId: row.createrId, creater: row.creater, title: row.title, status: row.status, createDate: row.createDate },
        row.projectIdListStr ? this.arrangeProjectList(row) : {})
      console.log(this.infoForm);
    },
    handleEdit(row) {
      this.addEditDialogVisible = true
      this.isEdit = true
      this.infoForm = Object.assign({},
        { id: row.id, createrId: row.createrId, creater: row.creater, title: row.title })
      let infoProjectList = row.projectIdListStr ? this.arrangeProjectList(row).projectList : []
      this.presetProjectList = [...infoProjectList]
      this.reFindAllProjectToCardList().then(() => {
        if (infoProjectList.length) {
          infoProjectList.forEach(realInfo => {
            let tempIndex = this.projectList.findIndex(item => item.id.toString() === realInfo.id.toString())
            if (tempIndex !== -1) {
              this.projectList.splice(tempIndex, 1)
            }
          })
        }
      })
    },
    handleAdd() {
      this.addEditDialogVisible = true;
      this.isEdit = false
      this.reFindAllProjectToCardList()
      this.infoForm = Object.assign({}, null);
    },
    arrangeProjectList(row) {
      let idList = row.projectIdListStr.split(',')
      let coverList = row.coverListStr.split(',')
      let titleList = row.titleListStr.split(',')
      let viewCountList = row.viewCountListStr.split(',')
      let commentCountList = row.commentCountListStr.split(',')
      let tempProjectList = []
      //建立一个临时存放的组合进行拼接
      idList.forEach((id, index) => {
        tempProjectList.push({
          id: id,
          cover: coverList[index],
          title: titleList[index],
          viewCount: viewCountList[index],
          commentCount: commentCountList[index]
        })
      });
      return { projectList: tempProjectList }
    },
    handleSearch() {
      this.getList();
    },
    handleDownShelf(index, row) {
      console.log('当前状态', row.status);

      let tempStatus = row.status === 1 ? 0 : 1


      let params = { id: row.id, status: tempStatus }
      changeStatus(params).then(res => {
        console.log('成功');
        this.$message.success(res.data.msg);

        console.log();

        this.$refs.collectionTable.data[index].status = tempStatus
      })
    },
    handleDelete(id) {
      NProgress.start();
      remove(id).then(res => {
        NProgress.done();
        this.$message.success(res.data.msg);
        this.getList();
      })
    },
    handleSubmitSave() {
      this.infoForm.projectIdListStr = this.presetProjectList.map(item => item.id).join(',')
      console.log('要保存的数据', this.infoForm);
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            NProgress.start();
            let para = Object.assign({ createrId: -1 }, this.infoForm);
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
    handleSubmitUpdate() {
      this.infoForm.projectIdListStr = this.presetProjectList.map(item => item.id).join(',')
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          console.log('更新的当前参数是', this.infoForm);

          this.$confirm("确认更新吗？", "提示", {}).then(() => {
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
    handleRemove(index, row) {
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

    getList: function () {
      let params = {
        title: this.query.title,
        creater: this.query.creater,
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize
      };
      list(params)
        .then(res => {
          this.tableData = res.data.page.list;
          this.page.total = res.data.page.totalRow;
        })
        .catch(error => {
          this.$message.error(error);
          console.log(error);
        });
    },
    reFindAllProjectToCardList() {
      return findAllProjectToCardList().then(res => {
        this.projectList = res.data
      })
    }
  }
};
</script>

<style <style lang="scss" scoped>
.form__project-list {
  .project-card {
    margin-right: 15px;
    &:nth-child(4n) {
      margin-right: 0;
    }
  }
  width: 645px;
  display: flex;
  flex-wrap: wrap;
}
.collection--edit {
  margin-top: 15px;
}
.table__cover-list {
  display: flex;
  align-items: center;
  .table__cover {
    margin-right: 15px;
    width: 30px;
    height: 30px;
    cursor: pointer;
    &:last-child {
      margin-right: 0;
    }
  }
}
</style>

