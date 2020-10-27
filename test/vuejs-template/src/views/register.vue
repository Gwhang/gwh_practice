<template>
  <div class="registerWarpper content">
    <h3>用户注册</h3>
    <div class="form">
      <div class="row">
        <label>手机号</label>
        <el-input placeholder="手机号" v-model="user.telephone" clearable></el-input>
      </div>

      <div class="row">
        <label>验证码</label>
        <el-input placeholder="验证码" v-model="user.otpCode"  clearable></el-input>
      </div>

      <div class="row">
        <label>用户名</label>
        <el-input placeholder="用户名" v-model="user.name"  clearable></el-input>
      </div>

      <div class="row">
        <label>性别</label>
        <el-input placeholder="性别" v-model="user.gender"  clearable></el-input>
      </div>

      <div class="row">
        <label>年龄</label>
        <el-input placeholder="年龄" v-model="user.age"  clearable></el-input>
      </div>

      <div class="row">
        <label>密码</label>
        <el-input type="password" placeholder="密码" v-model="user.password"  clearable></el-input>
      </div>

    </div>
    <el-button type="primary" @click="register">提交注册</el-button>
  </div>
</template>

<script type="text/ecmascript-6">
import api from "@/api/api";

const SUCCESS = 0;
export default {
  data() {
    return {
      user: {}
    };
  },
  created() {
    document.title = "register";
  },
  methods: {
    register() {
      if (this.user.telephone == null || this.user.telephone == "") {
        this.$message.error("手机号不能为空");
        return;
      }

      if (this.user.otpCode == null || this.user.otpCode == "") {
        this.$message.error("验证码不能为空");
        return;
      }

      if (this.user.name == null || this.user.name == "") {
        this.$message.error("用户名不能为空");
        return;
      }

      if (this.user.gender == null || this.user.gender == "") {
        this.$message.error("性别不能为空");
        return;
      }

      if (this.user.age == null || this.user.age == "") {
        this.$message.error("年龄不能为空");
        return;
      }

      if (this.user.password == null || this.user.password == "") {
        this.$message.error("密码不能为空");
        return;
      }
      this._submitForm();
    },
    _submitForm() {
      api.register(this.user).then(response => {
        if (response.code == SUCCESS) {
          this.$router.push({
            path: "/login"
          });
        } else {
          this.$message.error("注册失败了,原因为" + response.message);
        }
      });
    }
  }
};
</script>

<style lang="less">
.registerWarpper {
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
