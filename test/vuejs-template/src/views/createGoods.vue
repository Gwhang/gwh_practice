<template>
  <div class="createGoods content">
    <h3>创建商品</h3>
    <div class="form">
      <div class="row">
        <label>商品名</label>
        <el-input placeholder="商品名" v-model="goods.title" clearable></el-input>
      </div>

      <div class="row">
        <label>商品描述</label>
        <el-input placeholder="商品描述" v-model="goods.description"  clearable></el-input>
      </div>

      <div class="row">
        <label>价格</label>
        <el-input placeholder="价格" v-model="goods.price"  clearable></el-input>
      </div>

      <div class="row">
        <label>图片</label>
        <el-input placeholder="图片" v-model="goods.imgUrl"  clearable></el-input>
      </div>

      <div class="row">
        <label>库存</label>
        <el-input placeholder="库存" v-model="goods.stock"  clearable></el-input>
      </div>
    </div>
    <el-button type="primary" @click="createGoods">提交创建</el-button>
  </div>
</template>

<script type="text/ecmascript-6">
import api from "@/api/api";

const SUCCESS = 0;
export default {
  data() {
    return {
      goods: {}
    };
  },
  created() {
    document.title = "createGoods";
  },
  methods: {
    createGoods() {
      if (this.goods.title == null || this.goods.title == "") {
        this.$message.error("商品名称不能为空");
        return;
      }

      if (this.goods.description == null || this.goods.description == "") {
        this.$message.error("商品描述不能为空");
        return;
      }

      if (this.goods.price == null || this.goods.price == "") {
        this.$message.error("商品价格不能为空");
        return;
      }

      if (this.goods.imgUrl == null || this.goods.imgUrl == "") {
        this.$message.error("商品图片不能为空");
        return;
      }

      if (this.goods.stock == null || this.goods.stock == "") {
        this.$message.error("商品库存不能为空");
        return;
      }
      this._submitForm();
    },
    _submitForm() {
      api.createGoods(this.goods).then(response => {
        if (response.code == SUCCESS) {
          this.$router.push({
            path: "/list"
          });
        } else {
          this.$message.error("创建失败了,原因为" + response.message);
        }
      });
    }
  }
};
</script>

<style lang="less">
.createGoods {
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
