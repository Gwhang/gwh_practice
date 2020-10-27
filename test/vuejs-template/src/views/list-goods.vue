<template>
  <div class="listGoods">
    <h3>商品列表浏览</h3>
    <table>
      <tr>
        <th>商品名</th>
        <th>商品图片</th>
        <th>商品描述</th>
        <th>商品价格</th>
        <th>商品库存</th>
        <th>商品销量</th>
      </tr>
      <tbody>
        <tr v-for="(item,index) in goodsList" :key="index" @click="goDetail(item.id)">
          <td>{{item.title}}</td>
          <td><img :src="item.imgUrl"></td>
          <td>{{item.description}}</td>
          <td>{{item.price}}</td>
          <td>{{item.stock}}</td>
          <td>{{item.sales}}</td>
        </tr>
      </tbody>
    </table>
 
  </div>
</template>

<script type="text/ecmascript-6">
import api from "@/api/api";

const SUCCESS = 0;
export default {
  data() {
    return {
      goodsList: []
    };
  },
  created() {
    document.title = "listGoods";
    this._getGoodsList();
  },
  methods: {
    _getGoodsList() {
      api.getGoodsList().then(response => {
        if (response.code == SUCCESS) {
          this.goodsList = response.data.goodsList;
        }
      });
    },
    goDetail(id) {
      this.$router.push({ path: "/goodsDetail", query: { id: id } });
    }
  }
};
</script>

<style lang="less">
.listGoods {
  width: 100%;
  height: 100%;
  background: #fff;
  position: fixed;
  overflow: auto;

  h3 {
    font-size: 23px;
    font-weight: lighter;
    text-align: center;
    margin-top: 20px;
    margin-bottom: 20px;
  }

  table {
    margin-top: 20px;
    margin: 0 auto;
    width: 70%;

    tbody {
      tr {
        border-top: 1px solid #999;
      }
    }

    th,
    td {
      text-align: center;
    }
    th {
      line-height: 40px;
      font-weight: bold;
    }
    td {
      height: 100px;
      line-height: 100px;
      img {
        width: 30%;
        height: 70%;
        vertical-align: middle;
      }
    }
  }
}
</style>
