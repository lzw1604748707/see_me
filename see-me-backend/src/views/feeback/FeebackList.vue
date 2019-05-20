<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="60px"
      class="query-form">
      <el-select v-model="query.feebackType"
        placeholder="请选择学校"
        style="margin-left:10px;">
        <el-option key="0"
          label="请选择反馈类型"
          value=""></el-option>
        <el-option v-for="item in feebackTypeList"
          :key="item.value"
          :label="item.name"
          :value="item.value"></el-option>
      </el-select>
      <el-form-item>
        <el-button type="primary"
          @click="handleSearch">搜索</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      class="table"
      stripe
      border
      v-loading="loading">
      <el-table-column type="index"
        label="序号"
        width="70"></el-table-column>
      <el-table-column prop="account"
        label="反馈账户"></el-table-column>
      <el-table-column prop="accountName"
        label="反馈人"></el-table-column>
      <el-table-column prop="accountType"
        label="身份">
        <template slot-scope="scope">
          <span v-if="scope.row.accountType === 1">学校</span>
          <span v-else-if="scope.row.accountType === 2">教师</span>
          <span v-else>家长</span>
        </template>
      </el-table-column>
      <el-table-column prop="remark"
        label="意见"></el-table-column>
      <el-table-column prop="type"
        label="类型">
        <template slot-scope="scope">
          <span v-if="scope.row.accountType === 1">优化建议</span>
          <span v-else-if="scope.row.accountType === 2">平台问题</span>
          <span v-else-if="scope.row.accountType === 3">合作意向</span>
          <span v-else>其他</span>
        </template>
      </el-table-column>
      <el-table-column prop="createAt"
        label="时间"></el-table-column>
      <el-table-column label="操作"
        width="100">
        <template slot-scope="scope">
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

  </div>
</template>

<script type="text/ecmascript-6">
import { list, remove } from "@/api/feeback_list";

export default {
  data() {
    return {
      query: {
        feebackType: ""
      },
      loading: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: ""
      },
      feebackTypeList: [
        {
          value: 1,
          name: "优化建议"
        },
        {
          value: 2,
          name: "平台问题"
        },
        {
          value: 3,
          name: "合作意向"
        },
        {
          value: 4,
          name: "其他"
        }
      ],
      tableData: []
    };
  },
  created() { },
  mounted() {
    this.getList();
  },
  watch: {},
  methods: {
    handleSizeChange: function (val) {
      this.page.pageSize = val;
      this.getList();
    },
    handleCurrentChange: function (val) {
      this.pageNumber = val;
      this.getList();
    },
    handleSearch: function () {
      this.getList();
    },
    getList: function () {
      let params = {
        feebackType: this.query.feebackType,
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize,
        schoolId: this.selectSchoolId
      };
      this.loading = true;
      list(params)
        .then(res => {
          this.tableData = res.data.page.list;
          this.page.pageNumber = res.data.page.pageNumber;
          this.page.pageSize = res.data.page.pageSize;
          this.page.total = res.data.page.totalRow;
          this.loading = false;
        })
        .catch(error => {
          this.loading = false;
          this.$message.error(error);
          console.log(error);
        });
    },
    handleRemove: function (index, row) {
      this.$confirm("确定删除吗？")
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
    }
  }
};
</script>

<style>
</style>
