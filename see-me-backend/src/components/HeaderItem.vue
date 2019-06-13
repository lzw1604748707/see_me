<template>
  <div>
    <el-header class="header"
      height="80px"
      :style="{ 'background-color':'#08224a' }">
      <div style="height:inherit; display:flex; align-items: center;">
        <img src="../assets/logo.png"
          alt="element-logo"
          class="header-logo">
        <span class="header__title">See Me backend</span>
      </div>
      <ul class="header-operations">
        <li>欢迎你，{{this.$store.state.name}}</li>
        <li @click="dialogVisible = true">修改密码</li>
        <li @click="hanleLogOut">退出</li>
        <!-- <li @click="showThemeDialog">切换主题色</li> -->
      </ul>

    </el-header>

    <el-dialog title="提示"
      :visible.sync="dialogVisible"
      width="30%">

      <el-form :model="infoForm"
        status-icon
        :rules="rules2"
        ref="infoForm"
        label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="原密码："
          prop="oldPassword">
          <el-input type="password"
            v-model="infoForm.oldPassword"
            auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码："
          prop="password">
          <el-input type="password"
            v-model="infoForm.password"
            auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码："
          prop="checkPass">
          <el-input type="password"
            v-model="infoForm.checkPass"
            auto-complete="off"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer"
        class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary"
          @click="submitForm('infoForm')">更新</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script type="text/ecmascript-6">
import { logout } from "@/api/login";
import { removeToken } from "@/utils/token";
import { resetPassword } from "@/api/my";
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.infoForm.checkPass !== "") {
          this.$refs.infoForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请在此输入密码"));
      } else if (value !== this.infoForm.password) {
        callback(new Error("兩次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      primaryColor: "#5589B1",
      dialogVisible: false,
      infoForm: {
        password: "",
        oldPassword: "",
        checkPass: ""
      },
      rules2: {
        oldPassword: [{ validator: validatePass, trigger: "blur" }],
        password: [
          { min: 6, max: 18, message: "长度在 5 到 18 个字符", trigger: "blur" },
          { validator: validatePass, trigger: "blur" }
        ],
        checkPass: [
          { min: 6, max: 18, message: "长度在 5 到 18 个字符", trigger: "blur" },
          { validator: validatePass2, trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submitForm: function (formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          let params = Object.assign({}, this.infoForm);
          console.log(params);
          resetPassword(params)
            .then(res => {
              logout();
              this.$message.success(res.data.msg);
              _this.$router.push({ path: "/admin/login" });
            })
            .catch(error => {
              this.$message.error(error);
              console.log(error);
            });
        } else {
          return false;
        }
      });
    },
    resetForm: function (formName) {
      this.$refs[formName].resetFields();
    },
    hanleLogOut: function () {
      removeToken();
      var _this = this;
      this.$confirm("确认退出吗", "提示", {}).then(() => {
        this.$store.commit("logout");
        logout();
        _this.$router.push({ path: "/admin/login" });
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  &__title {
    color: #fff;
    font-size: 30px;
    margin-left: 30px;
  }
}
</style>
