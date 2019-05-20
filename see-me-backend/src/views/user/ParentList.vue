<template>
<div>
<!-- 搜索 -->
<el-form inline :model="query" label-position="right" label-width="60px" class="query-form">
  <el-select v-model="selectSchoolId" placeholder="請選擇學校" style="margin-left:10px;">
    <el-option key="0" label="請選擇學校" value=""></el-option>
    <el-option v-for="school in schoolList" :key="school.id" :label="school.name" :value="school.id"></el-option>
  </el-select>
  <el-input v-model="query.studentName" placeholder="請輸入學生姓名" style="width:150px;" ></el-input>
  <el-input v-model="query.studentCode" placeholder="請輸入學籍號" style="width:150px;" ></el-input>
  <el-form-item>
    <el-button type="primary" @click="handleSearch">搜索</el-button>
  </el-form-item>
</el-form>
<!-- 数据表格 -->
<el-table :data="tableData" class="table" stripe border v-loading="loading">
  <el-table-column type="index" label="序號"  width="70"></el-table-column>
  <el-table-column prop="parentName" label="家長姓名"></el-table-column>
  <el-table-column prop="parentPhone" label="家長手機"></el-table-column>
  <el-table-column prop="studentName" label="學生姓名"></el-table-column>
  <el-table-column prop="studentCode" label="學生學籍號"></el-table-column>
  <el-table-column prop="schoolName" label="所屬學校"></el-table-column>
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

</div>
</template>

<script type="text/ecmascript-6">
import { list, schoolList } from "@/api/parent_list";

export default {
  data() {
    return {
      query: {
        name: "",
        schoolId: "",
        studentName: "",
        studentCode: ""
      },
      loading: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: 0
      },
      selectSchoolId: this.$route.query.schoolId
        ? this.$route.query.schoolId * 1
        : "",
      schoolList: [],
      tableData: []
    };
  },
  created() {},
  mounted() {
    this.getList();
    this.getSchoolList();
  },
  watch: {},
  methods: {
    handleSizeChange: function(val) {
      this.page.pageSize = val;
      this.getList();
    },
    handleCurrentChange: function(val) {
      this.pageNumber = val;
      this.getList();
    },
    handleSearch: function() {
      this.getList();
    },
    getList: function() {
      let params = {
        studentName: this.query.studentName,
        studentCode: this.query.studentCode,
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
    getSchoolList: function() {
      schoolList()
        .then(res => {
          this.schoolList = res.data.schoolList;
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

</style>
