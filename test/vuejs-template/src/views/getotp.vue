<template>
  <div class="getOtp content">
    <h3>获取otp信息</h3>
    <div class="form">
      <label>手机号</label>
      <el-input placeholder="手机号" v-model="telephone" clearable></el-input>
    </div>
    <el-button type="primary" @click="getPhone">获取otp短信</el-button>
  </div>
</template>

<script type="text/ecmascript-6">
import api from "@/api/api";

const SUCCESS = 0;
export default {
  data() {
    return {
      telephone: ""
    };
  },
  created() {
    document.title = "getotp";
  },
  methods: {
    getPhone() {
      if (this.telephone != null && this.telephone != "") {
        api.getOtp(this.telephone).then(response => {
          if (response.status == SUCCESS) {
             debugger;
            this.$router.push({
              path: "/register"
            });
          }else{
            this.$message.error("otp发送失败,原因为"+response.message);
          }
        });
      } else {
        this.$message.error("手机号不能为空");
      }
    }
  }
};
</script>

<style lang="less">
.getOtp {
  h3 {
    font-size: 23px;
    font-weight: lighter;
  }

  .form {
    margin: 24px 0 20px;

    label {
      display: block;
      margin-bottom: 10px;
    }
  }
}
</style>
