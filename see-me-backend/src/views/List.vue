<template>
  <div>
    <el-form inline
      :model="query"
      label-position="right"
      label-width="60px"
      class="query-form">
      <el-form-item label="姓名"
        prop="name">
        <el-input v-model="query.name"
          placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="日期"
        prop="date">
        <el-date-picker v-model="query.date"
          type="daterange"
          placeholder="-">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData"
      class="table"
      stripe
      border>
      <el-table-column prop="date"
        label="日期"
        sortable
        width="200"></el-table-column>
      <el-table-column prop="name"
        label="姓名"
        width="200"></el-table-column>
      <el-table-column prop="address"
        label="地址"></el-table-column>
      <el-table-column prop="zip"
        label="邮箱"
        width="200"></el-table-column>
      <el-table-column label="操作"
        width="160">
        <template slot-scope="scope">
          <el-button size="mini">编辑</el-button>
          <el-button size="mini"
            type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page.currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.total">
    </el-pagination>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: "list",
  data() {
    return {
      primaryColor: "#5589B1",
      page: {
        currentPage: 1,
        pageSize: 10,
        total: 11
      },
      tableData: [
        {
          date: "2016-05-08",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        },
        {
          date: "2016-05-07",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        },
        {
          date: "2016-05-08",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        },
        {
          date: "2016-05-07",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        },
        {
          date: "2016-05-08",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        },
        {
          date: "2016-05-07",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        },
        {
          date: "2016-05-08",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        },
        {
          date: "2016-05-07",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        },
        {
          date: "2016-05-09",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        }
      ],
      query: {
        name: "",
        date: []
      }
    };
  },
  created() { },
  mounted() { },
  methods: {
    handleSizeChange: function (val) {
      this.page.pageSize = val;
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange: function (val) {
      this.currentPage = val;
      console.log(`当前页: ${val}`);
      this.tableData = [
        {
          date: "2016-05-10",
          name: "张强",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333
        }
      ];
    }
  }
};
</script>

<style>
</style>
