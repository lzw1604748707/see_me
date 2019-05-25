<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="70px"
      class="query-form">
      <el-input v-model="query.title"
        placeholder="请输入项目标题"
        style="width:150px"></el-input>
      <el-input v-model="query.commentator"
        placeholder="请输入评论人昵称"
        style="width:150px"></el-input>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
        <el-button type="primary"
          @click="handleAdd">添加评论</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      height="580"
      stripe
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      border>
      <el-table-column type="index"
        label="序号"
        :index="realIndex"
        width="70"></el-table-column>
      <el-table-column prop="projectName"
        label="作品名称"></el-table-column>
      <el-table-column prop="commentator"
        label="评论者昵称"></el-table-column>
      <el-table-column prop="content"
        label="评论内容">
      </el-table-column>
      <el-table-column prop="createDate"
        sortable
        label="创建时间"
        :formatter="formatDate">
      </el-table-column>
      <el-table-column class=""
        label="操作"
        width="230">
        <template slot-scope="scope">
          <el-button size="mini"
            type="danger"
            plain
            @click="handleDelete(scope.row.id)">删除</el-button>
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

    <el-dialog title="新增"
      :visible.sync="addDialogVisible"
      @close="closeDialog('addDialogVisible')"
      width="500px">
      <!-- 用户详情 -->
      <el-form ref="infoForm"
        style="position:relative;"
        :model="infoForm"
        :rules="rules"
        label-width="120px">
        <el-form-item style="display:block;"
          prop="worksId"
          label="项目名称：">
          <el-select style="width:300px;"
            v-model="infoForm.worksId"
            filterable
            placeholder="请选择项目名称">
            <el-option v-for="project in projectList"
              :key="project.id"
              :label="project.title"
              :value="project.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评论内容："
          prop="content">
          <el-input style="width:300px;"
            v-model="infoForm.content"></el-input>
        </el-form-item>
      </el-form>
      <div class="add__prompt">注意：将以看否官方的身份进行留言</div>
      <span slot="footer"
        class="dialog-footer">
        <el-button @click="closeDialog('addDialogVisible')">关闭</el-button>
        <el-button type="primary"
          @click="handleSubmitSave">提交</el-button>
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
  findAllProjectList
} from "@/api/comment_list";

export default {
  data() {
    return {
      query: {
        commentator: "",
        title: ""
      },
      addDialogVisible: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 0
      },
      projectList: [],
      infoForm: {},
      rules: {
        worksId: [{ required: true, message: "请选择项目名称", trigger: "blur" }],
        content: [{ required: true, message: "请输入评论内容", trigger: "blur" }]
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
    }
  },
  mounted() {
    this.getList();
  },
  methods: {
    closeDialog(flag) {
      this[flag] = false
      this.infoForm = []
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
    handleAdd() {
      this.addDialogVisible = true;
      this.infoForm = Object.assign({}, null);
      this.reFindAllProjectList()
    },
    handleSearch() {
      this.getList();
    },
    handleSubmitSave() {
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            NProgress.start();
            let para = Object.assign({ createrId: -1 }, this.infoForm);
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
    handleDelete(id) {
      this.$confirm("确定刪除吗？")
        .then(_ => {
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
    reFindAllProjectList() {
      findAllProjectList().then(res => {
        this.projectList = res.data
      })
    },
    getList: function () {
      let params = {
        commentator: this.query.commentator,
        title: this.query.title,
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
    }
  }
};
</script>

<style <style lang="scss" scoped>
.add__prompt {
  color: #999;
  margin-top: 40px;
  text-align: center;
}
</style>

