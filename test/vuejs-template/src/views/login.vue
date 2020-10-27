<template>
  <div class="loginWarpper content">
    <h3>用户登录</h3>
    <div class="form">
      <div class="row">
        <label>手机号</label>
        <el-input placeholder="手机号" v-model="telephone" clearable></el-input>
      </div>

      <div class="row">
        <label>密码</label>
        <el-input placeholder="密码" v-model="password"  clearable></el-input>
      </div>

    </div>
    <el-button type="primary" @click="login">登录</el-button>
    <el-button type="success" @click="goRegister">注册</el-button>
  </div>
</template>

<script type="text/ecmascript-6">
import api from "@/api/api";

const SUCCESS = 0;
export default {
  data() {
    return {
      telephone: "",
      password: ""
    };
  },
  created() {
    document.title = "login";
  },
  methods: {
    login() {
      if (this.telephone == null || this.telephone == "") {
        this.$message.error("手机号不能为空");
        return;
      }

      if (this.password == null || this.password == "") {
        this.$message.error("密码不能为空");
        return;
      }
      this._submitForm();
    },
    _submitForm() {
      api.login(this.telephone, this.password).then(response => {
        if (response.code == SUCCESS) {
          this.$message({
            message: "登录成功",
            type: "success"
          });
          this.$router.push({
            path: "/listGoods"
          });
        } else {
          this.$message.error("登录失败了,原因为" + response.message);
        }
      });
    },
    goRegister() {
      this.$router.push({
        path: "/register"
      });
    }
  }
};
</script>

<style lang="less">
.loginWarpper {
  h3 {
    font-size: 23px;
    font-weight: lighter;
  }

  .form {
    padding-top: 10px;
    .row {
      margin: 20px 0;
      label {
        display: block;
        margin-bottom: 10px;
      }
    }
  }
}
</style>
