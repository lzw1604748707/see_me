<template>
  <div>
    <!-- 搜索 -->
    <el-form inline
      :model="query"
      label-position="right"
      label-width="60px"
      class="query-form">
      <el-select v-model="query.feebackType"
        @change="handleSearch"
        placeholder="请选择反馈类型"
        style="margin-left:10px;">
        <el-option :key="-1"
          label="全部"
          value=""></el-option>
        <el-option v-for="item in feebackTypeList"
          :key="item.value"
          :label="item.name"
          :value="item.value"></el-option>
      </el-select>
      <el-select v-model="query.status"
        @change="handleSearch"
        placeholder="请选择处理状态"
        style="margin-left:10px;">
        <el-option :key="-1"
          label="全部"
          value=""></el-option>
        <el-option v-for="item in statusList"
          :key="item.value"
          :label="item.name"
          :value="item.value"></el-option>
      </el-select>
      <el-form-item>
        <el-button type="primary"
          @click="handleAdd">添加意见</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData"
      ref="feebackTable"
      class="table"
      stripe
      border
      v-loading="loading">
      <el-table-column type="index"
        :index="realIndex"
        label="序号"
        width="70"></el-table-column>
      <el-table-column prop="account"
        label="反馈账户"></el-table-column>
      <el-table-column prop="accountName"
        label="反馈人"></el-table-column>
      <el-table-column prop="accountType"
        label="身份">
        <template slot-scope="scope">
          <span>{{identity(scope.row.accountType)}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="remark"
        label="意见"></el-table-column>
      <el-table-column prop="type"
        label="类型">
        <template slot-scope="scope">
          <span>{{steerType(scope.row.type)}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="type"
        label="类型">
        <template slot-scope="scope">
          <span>{{statusName(scope.row.status)}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="createDate"
        sortable
        label="创建时间"
        :formatter="formatDate"></el-table-column>
      <el-table-column label="操作"
        width="230">
        <template slot-scope="scope">
          <el-button size="mini"
            type="danger"
            plain
            @click="handleRemove(scope.row)">删除</el-button>
          <el-button v-if="scope.row.status===0"
            size="mini"
            type="primary"
            plain
            @click="onChangeStatus(scope.$index, 1)">确认</el-button>
          <el-button v-if="scope.row.status===1"
            size="mini"
            type="success"
            plain
            @click="onChangeStatus(scope.$index, 2)">已处理</el-button>
          <el-button size="mini"
            v-if="scope.row.status===1"
            type="warning"
            plain
            @click="onChangeStatus(scope.$index,3)">回绝</el-button>

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
    <el-dialog title="新增"
      :visible.sync="addDialogVisible"
      @close="closeDialog('addDialogVisible')"
      width="500px">
      <!-- 项目新增 -->
      <el-form ref="infoForm"
        :model="infoForm"
        :rules="rules"
        label-width="110px">
        <el-form-item prop="type"
          label="建议类型：">
          <el-select style="width:300px;"
            v-model="infoForm.type"
            placeholder="请选择建议类型">
            <el-option v-for="feeback of feebackTypeList"
              :key="feeback.id"
              :label="feeback.name"
              :value="feeback.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="remark"
          label="建议内容：">
          <el-input type="textarea"
            :rows="5"
            style="width:300px;"
            v-model="infoForm.remark"></el-input>
        </el-form-item>
        <div class="add__prompt">
          <p>注意：将以游客的身份进行建议</p>
          <p>(该功能用以避免交互和开发的冲突)</p>
        </div>

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
import { list, remove, save, reSaveStatus } from "@/api/feeback_list";

export default {
  data() {
    return {
      query: {
        feebackType: "",
        status: ''
      },
      loading: false,
      page: {
        pageNumber: 1,
        pageSize: 10,
        total: ""
      },
      feebackTypeList: [
        {
          value: 0,
          name: "优化建议"
        },
        {
          value: 1,
          name: "平台问题"
        },
        {
          value: 2,
          name: "合作意向"
        },
        {
          value: 3,
          name: "其他"
        }
      ],
      statusList: [
        {
          value: 0,
          name: "待确认"
        },
        {
          value: 1,
          name: "已确认"
        },
        {
          value: 2,
          name: "已处理"
        },
        {
          value: 3,
          name: "已回绝"
        }
      ],
      addDialogVisible: false,
      infoForm: {
        remark: '',
        type: ''
      },
      rules: {
        type: [
          { required: true, message: '请选择建议类型', trigger: 'change' }
        ],
        remark: [{ required: true, message: "请输入建议内容", trigger: "blur" }]
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
    identity() {
      return function (userType) {
        const identityList = ['游客', '用户', '合作商']
        return identityList[userType]
      }
    },
    steerType() {
      return function (type) {
        const steerTypeList = ['优化', '平台问题反馈', '合作', '其他']
        return steerTypeList[type]
      }
    },
    statusName() {
      return function (status) {
        const statusList = ['待确认', '已确认', '已处理', '已回绝']
        return statusList[status]
      }
    }

  },
  methods: {
    closeDialog(flag) {
      this[flag] = false
      this.infoForm = []
      if (this.$refs["infoForm"]) { this.$refs["infoForm"].resetFields(); }
    },
    onChangeStatus(index, status) {
      let params = { id: this.$refs.feebackTable.data[index].id, status: status }
      if (status !== 1) {
        this.$confirm("确定要执行该操作(请确保处理妥当)？")
          .then(_ => {
            this.reSaveStatus(index, params)
          })
      } else {
        this.reSaveStatus(index, params)
      }
    },
    handleSizeChange: function (val) {
      this.page.pageSize = val;
      this.page.pageNumber = 1
      this.getList();
    },
    handleCurrentChange: function (val) {
      this.pageNumber = val;
      this.getList();
    },
    handleSearch: function () {
      this.page.pageNumber = 1
      console.log('触发事件');
      this.getList();
    },
    getList: function () {
      let params = {
        feebackType: this.query.feebackType,
        status: this.query.status,
        pageNumber: this.page.pageNumber,
        pageSize: this.page.pageSize
      };
      this.loading = true;
      list(params)
        .then(res => {
          this.tableData = res.data.page.list;
          this.page.total = res.data.page.totalRow;
          this.loading = false;
        })
        .catch(error => {
          this.loading = false;
          this.$message.error(error);
          console.log(error);
        });
    },
    reSaveStatus(index, params) {
      reSaveStatus(params).then(res => {
        this.$message.success(res.data.msg);
        this.$refs.feebackTable.data[index].status = params.status
      })
    },
    handleAdd: function () {
      this.infoForm = Object.assign({}, null);
      this.addDialogVisible = true;
    },
    handleSubmitSave: function () {
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            let para = Object.assign({ accountId: 0, accountType: 0 }, this.infoForm);
            save(para).then(res => {
              this.$message.success(res.data.msg);
              this.$refs["infoForm"].resetFields();
              this.addDialogVisible = false;
              this.getList();
            });
          });
        }
      });
    },
    handleRemove(row) {
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
  },
  mounted() {
    this.getList();
  }
};
</script>

<style>
.add__prompt {
  color: #999;
  margin-top: 40px;
  text-align: center;
}
</style>
