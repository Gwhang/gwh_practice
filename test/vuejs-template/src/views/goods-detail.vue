<template>
  <div class="goodsDetail content">
    <h3>商品详情</h3>
    <div class="form">
      <div class="row" style="color: red">
        <p v-if="goods.promoStatus == 1">秒杀活动将于{{goods.startDate}}开始售卖 倒计时: {{delta}}秒</p>
        <p v-if="goods.promoStatus == 2">秒杀正在进行中</p>
      </div>

      <div class="row">
        <p>{{goods.title}}</p>
      </div>

      <div class="row">
        <label>商品描述</label>
        <label>{{goods.description}}</label>
        <p></p>
      </div>

      <div class="row" v-if="goods.promoStatus == 1">
        <label>价格</label>
        <p>{{goods.price}}</p>
      </div>

      <div class="row">
        <label style="color: red">秒杀价格</label>
        <p style="color: red">{{goods.promoPrice}}</p>
      </div>

      <div class="row">
        <div class="goods-img">
          <img :src="goods.imgUrl">
        </div>
      </div>

      <div class="row">
        <label>库存</label>
        <p>{{goods.stock}}</p>
      </div>

       <div class="row">
        <label>销量</label>
        <p>{{goods.sales}}</p>
      </div>
    </div>
     <el-button type="primary" @click="buy" :disabled="goods.promoStatus == 1">下 单</el-button>
    <el-button type="info" @click="goback">返 回</el-button>
  </div>
</template>

<script type="text/ecmascript-6">
import api from "@/api/api";

const SUCCESS = 0;
export default {
  data() {
    return {
      id: null,
      goods: {},
      delta: null
    };
  },
  created() {
    document.title = "goodsDetail";

    let id = this.$route.query.id;
    if (id) {
      this.id = id;
      this._getGoodsDetail();
    }
  },
  methods: {
    _getGoodsDetail() {
      api.getGoodsDetail(this.id).then(response => {
        if (response.code == SUCCESS) {
          this.goods = response.data.goods;
          setInterval(this.countDown, 1000);
        }
      });
    },
    countDown() {
      let startTime = this.goods.startDate.replace(new RegExp("-", "gm"), "/");
      startTime = new Date(startTime).getTime();
      let nowTime = Date.parse(new Date());
      this.delta = (startTime - nowTime) / 1000;

      if(this.delta <= 0){
        this.goods.promoStatus = 2;
      }
    },
    buy() {
      let order = {
        goodsId: this.id,
        amount: 1,
        promoId: this.goods.promoId
      };
      api.createOrder(order).then(response => {
        if (response.code == SUCCESS) {
          this.$message({
            message: "下单成功",
            type: "success"
          });
          this._getGoodsDetail();
        } else {
          this.$message.error("下单失败了,原因为" + response.message);
          if (response.code == 20003) {
            this.$router.push({
              path: "/login"
            });
          }
        }
      });
    },
    goback() {
      this.$router.back();
    }
  }
};
</script>

<style lang="less">
.goodsDetail {
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

      p {
        line-height: 30px;
      }

      .goods-img {
        img {
          width: auto;
          height: 140px;
        }
      }
    }
  }
}
</style>
