<template>
  <div class="login">
    <!-- :rules="rules2"  下面的参数 -->
    <el-form :model="loginForm"
      ref="loginForm"
      label-position="left"
      label-width="0px"
      class="demo-ruleForm login-container">
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <div class="login__input">
          <el-input :class="{ input__account: accountVerify }"
            v-model="loginForm.username"
            auto-complete="off"
            :placeholder="accountVerify ? '请输入账号' : '账号'"
            @blur="verify(loginForm.username, 'accountVerify')"></el-input>
        </div>
        <!-- <el-input type="text"
          v-model="loginForm.username"
          auto-complete="off"
          placeholder="账号"></el-input> -->
      </el-form-item>
      <el-form-item prop="password">
        <div class="login__input">
          <el-input type="password"
            :class="{ input__password: passwordVerify }"
            v-model="loginForm.password"
            auto-complete="off"
            :placeholder="`${passwordVerify ? '请输入密码' : '密码'}`"
            @blur="verify(loginForm.password, 'passwordVerify')"></el-input>
        </div>
        <!-- <el-input type="password"
          v-model="loginForm.password"
          auto-complete="off"
          placeholder="密码"></el-input> -->
      </el-form-item>
      <!-- <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox> -->
      <el-form-item class="form__submit">
        <el-button type="primary"
          class="form__submit-button"
          @click.native.prevent="handleLogin"
          :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { login } from "@/api/login";
export default {
  data() {
    return {
      logining: false,
      dialogVisible: false,
      loginForm: {
        username: "",
        password: ""
      },
      rules2: {
        username: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      checked: true,
      accountVerify: false,
      passwordVerify: false
    };
  },
  methods: {
    verify(value, name) {
      this[name] = value === ''
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.logining = true;
          var loginParams = {
            username: this.loginForm.username,
            password: this.loginForm.password
          };
          login(loginParams)
            .then(res => {
              const account = res.data.account;
              this.$store.dispatch('login', { account }).then(() => { // 生成可访问的路由表
                const menus = account.menus;
                this.$store.dispatch('generateRoutes', { menus }).then(() => { // 生成可访问的路由表
                  this.$router.addRoutes(this.$store.state.addRouters) // 动态添加可访问路由表
                  this.$router.push({ path: "/admin" }); //登录成功之后重定向到首页
                })
              })
            })
            .catch(error => {
              this.$message.error(error);
              this.logining = false;
            });
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/assets/css/theme.scss";
.login {
  width: 100vw;
  height: 100vh;
  background: url("https://api.i-meto.com/bing?random") round;
  display: flex;
  justify-content: center;
  align-items: center;

  .login__input {
    margin-bottom: 15px;
    /deep/ .el-input {
      font-size: 15px;
    }
    /deep/ input::-webkit-input-placeholder {
      color: #fff;
    }
    .input {
      &__account {
        /deep/ input::-webkit-input-placeholder {
          color: $--color-danger;
          font-weight: 900;
          filter: drop-shadow(1px 1px 2px #fff);
        }
      }
      &__password {
        /deep/ input::-webkit-input-placeholder {
          color: $--color-danger;
          font-weight: 900;
          filter: drop-shadow(1px 1px 2px #fff);
        }
      }
      &__verify {
        color: $--color-primary;
        height: 40px;
        line-height: 40px;
      }
    }
    /** Begin 覆写element-ui样式 **/
    /deep/ .el-input__inner {
      border: none;
      background: none;
      background: rgba(255, 255, 255, 0.2);
      color: #fff;
      border-bottom: 1px solid #fff;
      // filter: drop-shadow(0px 0px 5px #fff);
    }
    /deep/ .el-input.is-active /deep/.el-input__inner,
    /deep/ .el-input__inner:focus {
      box-shadow: inset 0px 0px 5px #fff;
    }
    /** End 覆写element-ui样式 **/
  }
  .form__submit {
    width: 100%;
    display: flex;
    justify-content: center;
    &-button {
      width: 150px;
      border-radius: 20px;
      background: none;
      font-size: 16px;
      border-color: #fff;
      color: #fff;
      text-shadow: 1px 1px 1px #ddd;
      box-shadow: 0 0 10px #ddd;
    }
  }
  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #fff;
  }

  .remember {
    margin: 0px 0px 35px 0px;
  }
  .code {
    width: 400px;
  }
}
</style>