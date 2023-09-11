<!-- 赞赏模块 -->
<template>
  <div class="tRewardBox tcommonBox">
    <header>
      <h1>
        <a href="#/DetailShare" target="_blank"> 赞赏 </a>
      </h1>
    </header>
    <section>
<!--      <div style="text-align: center;">
        <img src="static/img/coffee.jpg" alt="" style="max-width: 80%; margin: 0 auto;" class="coffee" />
      </div>-->
      <h1>赞赏说明：</h1>
      <p style="text-align: center;">
        请备注留下你的姓名/昵称方便公布赞赏记录。
      </p>
      <h1>赞赏方式：</h1>
          <div class="donate-tip">
            <img
              :src="
        rewardData.wechat_image
          ? rewardData.wechat_image
          : 'static/img/wx_pay.png'
      "
              :onerror="$store.state.errorImg"
              style="max-width: 20%;display: block; margin: 0 auto;"
            />
            <span style="text-align: center; margin: 0 auto; display: block;">微信扫一扫，请我喝杯咖啡</span>
          </div>

<!--        <el-col :span="12" class="donate-item">
          <div class="donate-tip">
            <img
              :src="
                rewardData.alipay_image
                  ? rewardData.alipay_image
                  : 'static/img/ali_pay.jpg'
              "
              :onerror="$store.state.errorImg"
            />
            <span style="padding-top: 14px">支付宝扫一扫，请我喝杯咖啡</span>
          </div>
        </el-col>-->
      <h1>@赞赏记录：</h1>
      <el-table :data="tableData" border style="margin:0px auto;width:60%">
        <el-table-column
          prop="payTime"
          label="日期"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="name"
          label="赞赏人"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="money"
          label="金额"
          align="center"
        ></el-table-column>
      </el-table>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    //选项 / 数据
    return {
      rewardData: "", //赞赏二维码
      tableData: [
        {
          //赞赏数据
          "payTime":"2023-5-27",
          "name":"一位不愿透漏性别的帅哥",
          "money":"10"
        },
      ],
    };
  },
  methods: {
    //事件处理器
    showInitDate: function (date, full) {
      //时间处理
      return initDate(date, full);
    },
  },
  components: {
    //定义组件
  },
  created() {
    //生命周期函数
    var that = this;
  },
};
</script>

<style>
.tRewardBox section {
  padding-bottom: 20px;
}
.tRewardBox section h1 {
  margin: 10px 0;
  font-size: 25px;
  font-weight: 700;
  /*text-align: center;*/
  line-height: 30px;
}
.tRewardBox section p {
  margin: 10px 0;
  line-height: 24px;
}
.tRewardBox section hr {
  background: #ccc;
  margin-bottom: 30px;
}

.tRewardBox .donate-item {
  text-align: right;
  color: #44b549;
}
.tRewardBox .donate-item:last-child {
  text-align: left;
  color: #00a0e9;
}
.tRewardBox .donate-item img {
  width: 100%;
  display: block;
  height: auto;
}
.tRewardBox .donate-item div {
  display: inline-block;
  width: 150px;
  padding: 0 6px;
  text-align: center;
  box-sizing: border-box;
}
.tRewardBox .donate-item div span {
  display: inline-block;
  width: 100%;
  margin: 10px 0;
  text-align: center;
}
.tRewardBox .el-table__body-wrapper {
  overflow: hidden;
}
.el-table--enable-row-hover .el-table__body tr:hover > td {
  background: transparent;
}
</style>
