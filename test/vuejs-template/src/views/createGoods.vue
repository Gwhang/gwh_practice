<template>
  <div class="createGoods content">
    <h3>创建商品</h3>
    <div class="form">
      <div class="row">
        <label>商品名</label>
        <el-input placeholder="商品名" v-model="item.title" clearable></el-input>
      </div>

      <div class="row">
        <label>商品描述</label>
        <el-input placeholder="商品描述" v-model="item.description"  clearable></el-input>
      </div>

      <div class="row">
        <label>价格</label>
        <el-input placeholder="价格" v-model="item.price"  clearable></el-input>
      </div>

      <div class="row">
        <label>图片</label>
        <el-input placeholder="图片" v-model="item.imgUrl"  clearable></el-input>
      </div>

      <div class="row">
        <label>库存</label>
        <el-input placeholder="库存" v-model="item.stock"  clearable></el-input>
      </div>
    </div>
    <el-button type="primary" @click="createItem">提交创建</el-button>
  </div>
</template>

<script type="text/ecmascript-6">
import api from "@/api/api";

const SUCCESS = 0;
export default {
  data() {
    return {
      item: {}
    };
  },
  created() {
    document.title = "createItem";
  },
  methods: {
    createItem() {
      if (this.item.title == null || this.item.title == "") {
        this.$message.error("商品名称不能为空");
        return;
      }

      if (this.item.description == null || this.item.description == "") {
        this.$message.error("商品描述不能为空");
        return;
      }

      if (this.item.price == null || this.item.price == "") {
        this.$message.error("商品价格不能为空");
        return;
      }

      if (this.item.imgUrl == null || this.item.imgUrl == "") {
        this.$message.error("商品图片不能为空");
        return;
      }

      if (this.item.stock == null || this.item.stock == "") {
        this.$message.error("商品库存不能为空");
        return;
      }
      this._submitForm();
    },
    _submitForm() {
      api.createItem(this.item).then(response => {
        if (response.status == "success") {
          this.$router.push({
            path: "/listGoods"
          });
        } else {
          this.$message.error("创建失败了,原因为" + response.data.errMsg);
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
