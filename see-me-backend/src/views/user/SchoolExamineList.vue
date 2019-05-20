<template>
<div>
<!-- 搜索 -->
<el-form inline :model="query" label-position="right" label-width="60px" class="query-form">
  <el-select v-model="selectSchoolId" style="margin-left:10px;">
    <el-option key="0" label="請選擇學校" value=""></el-option>
    <el-option v-for="school in schoolList" :key="school.id" :label="school.name" :value="school.id"></el-option>
  </el-select>
  <el-form-item>
    <el-button type="primary" @click="handleSearch">搜索</el-button>
  </el-form-item>
</el-form>
<!-- 数据表格 -->
<el-table :data="tableData" class="table" stripe border v-loading="loading">
  <el-table-column type="index" label="序號"  width="70"></el-table-column>
  <el-table-column prop="area" label="區域"></el-table-column>
  <el-table-column prop="name" label="學校名稱"></el-table-column>
  <el-table-column prop="updateAt" label="更新时间"></el-table-column>
  <el-table-column label="操作" width="100" >
    <template slot-scope="scope">
      <el-button size="mini" type="info" @click="handleRead(scope.$index, scope.row)">查看</el-button>
    </template>
  </el-table-column>
</el-table>


<el-dialog title="查看" :visible.sync="infoDialogVisible" width="800px" >
  <vue-editor v-model="infoForm.content"></vue-editor>
  <span slot="footer" class="dialog-footer">
    <el-button @click="infoDialogVisible = false">關閉</el-button>
  </span>
</el-dialog>


</div>
</template>

<script type="text/ecmascript-6">
import { list, pass, reject, schoolList } from "@/api/school_examine_list";
export default {
  data() {
    return {
      query: {
        schoolId: ""
      },
      infoDialogVisible: false,
      loading: false,
      infoForm: {
        id: "",
        content: ""
      },
      schoolList: [],
      selectSchoolId: "",
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
    handleRead: function(index, row) {
      this.infoForm = Object.assign({}, row);
      this.infoDialogVisible = true;
    },
    getList: function() {
      let params = {
        schoolId: this.selectSchoolId
      };
      this.loading = true;
      list(params)
        .then(res => {
          this.tableData = res.data.list;
          this.loading = false;
        })
        .catch(error => {
          this.loading = false;
          this.$message.error(error);
          console.log(error);
        });
    },
    handlePass: function() {
      pass()
        .then(res => {
          this.schoolList = res.data.schoolList;
        })
        .catch(error => {
          this.$message.error(error);
          console.log(error);
        });
    },
    handleReject: function() {
      reject()
        .then(res => {
          this.schoolList = res.data.schoolList;
        })
        .catch(error => {
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
