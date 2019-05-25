<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="70px"
      class="query-form">
      <el-input v-model="query.fieldName"
        placeholder="请输入领域名称"
        style="width:150px"></el-input>
      <el-input v-model="query.provider"
        placeholder="请输入提供者昵称"
        style="width:150px"></el-input>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
        <el-button type="primary"
          @click="handleAdd">添加领域</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      border
      height="580"
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      ref="createFieldTable"
      stripe>
      <el-table-column align="center"
        type="index"
        label="序号"
        :index="realIndex"
        width="70"></el-table-column>
      <el-table-column align="center"
        prop="fieldName"
        label="领域名称"></el-table-column>
      <el-table-column align="center"
        prop="provider"
        label="提供者昵称"></el-table-column>
      <el-table-column align="center"
        sortable
        prop="createDate"
        label="创建时间"
        :formatter="formatDate"></el-table-column>
      <el-table-column align="center"
        prop="status"
        label="状态">
        <template slot-scope="scope">
          <span>{{statusName(scope.row.status)}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center"
        class=""
        label="操作"
        width="230">
        <template slot-scope="scope">
          <el-button size="mini"
            type="success"
            plain
            v-if="scope.row.status!==1"
            @click="changeStatus(scope.$index,scope.row.id,1)">通过</el-button>
          <el-button size="mini"
            type="warning"
            plain
            v-if="scope.row.status!==2"
            @click="changeStatus(scope.$index,scope.row.id,2)">回绝</el-button>
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
      width="600px">
      <!-- 用户详情 -->
      <el-form ref="infoForm"
        style="position:relative;"
        :model="infoForm"
        :rules="rules"
        label-width="120px">
        <el-form-item prop="fieldName"
          label="创作领域名称：">
          <el-input v-model="infoForm.fieldName"></el-input>
        </el-form-item>
        <div class="add__prompt">注意：以看否官方的身份提交无需审核，将直接展示在平台中</div>
      </el-form>
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
  changeStatus
} from "@/api/createfield_list";

export default {
  data() {
    return {
      query: {
        provider: "",
        fieldName: ""
      },
      addDialogVisible: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 0
      },
      infoForm: {},
      rules: {
        fieldName: [{ required: true, message: "请输入创作领域名称", trigger: "blur" }]
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
    statusName() {
      return function (status) {
        const statusNameList = ['审核中', '通过', '未通过']
        return statusNameList[status]
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
    changeStatus(rowIndex, id, status) {
      let params = { id: id, status: status }
      changeStatus(params).then(res => {
        this.$message.success(res.data.msg)
        this.$refs.createFieldTable.data[rowIndex].status = status
      })
    },
    handleAdd() {
      this.addDialogVisible = true;
      this.infoForm = Object.assign({}, null);
    },
    handleSearch() {
      this.getList();
    },
    handleSubmitSave() {
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            NProgress.start();
            let para = Object.assign({ providerId: -1 }, this.infoForm);
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
    getList: function () {
      let params = {
        fieldName: this.query.fieldName,
        provider: this.query.provider,
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
.table__pagination {
  margin-top: 5px;
}
.add__prompt {
  color: #999;
  margin-top: 40px;
  text-align: center;
}
</style>