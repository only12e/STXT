<template>
  <div class="contents">
    <div class="contetn_left">
      <div class="pagetab">
      </div>
      <dv-border-box-13>
        <div style="margin-top: 20px;margin-left: 20px;text-align:center;color:#46A6FF">能耗总览</div>
        <LeftTop></LeftTop>
      </dv-border-box-13>
      <dv-border-box-13>
        <div style="margin-top: 20px;margin-left: 20px;text-align:center;color:#46A6FF">能耗占比</div>
        <LeftCenter></LeftCenter>
      </dv-border-box-13>
      <dv-border-box-13>
        <div style="margin-top: 20px;margin-left: 20px;text-align:center;color:#46A6FF">库存预警</div>
        <LeftBottom></LeftBottom>
      </dv-border-box-13>
    </div> 
    <div class="contetn_center">
      <div class="pagetab">
      </div>
      <dv-border-box-13>
        <div style="margin-top: 20px;margin-left: 20px;text-align:center;color:#46A6FF">数据总览</div>
        <CenterTop></CenterTop>
      </dv-border-box-13>
      <dv-border-box-13>
        <div style="margin-top: 20px;margin-left: 20px;text-align:center;color:#46A6FF">销售计划完成率</div>
        <CenterBottom></CenterBottom>
      </dv-border-box-13>
    </div>
    <div class="contetn_right">
      <div class="pagetab"></div>
      <dv-border-box-13>
        <div style="margin-top: 20px;margin-left: 20px;text-align:center;color:#46A6FF">销售统计</div>
        <RightTop></RightTop>
      </dv-border-box-13>
      <dv-border-box-13>
        <div style="margin-top: 20px;margin-left: 20px;text-align:center;color:#46A6FF">销售排名(TOP8)</div>
        <RightCenter></RightCenter>
      </dv-border-box-13>
      <dv-border-box-13>
        <div style="margin-top: 20px;margin-left: 20px;text-align:center;color:#46A6FF">生产统计构成</div>
        <RightBottom></RightBottom>
      </dv-border-box-13>
    </div>
  </div>
</template>

<script>
import LeftTop from "../indexs/left-top.vue";
import LeftCenter from "../indexs/left-center.vue";
import LeftBottom from "../indexs/left-bottom.vue";
import CenterTop from "../indexs/center-top.vue";
import CenterBottom from "../indexs/center-bottom.vue";
import RightTop from "../indexs/right-top.vue";
import RightCenter from "../indexs/right-center.vue";
import RightBottom from "../indexs/right-bottom.vue";
export default {
  components:{LeftTop,LeftCenter,LeftBottom,CenterTop,CenterBottom,RightTop,RightCenter,RightBottom},
  data() {
    return {

    };
  },
  filters: {
    numsFilter(msg) {
      return msg || 0;
    },
  },
  created() {
  },

  mounted() { },
  methods: {

  },
};
</script>
<style lang="scss" scoped>
// 内容
.contents {
  .contetn_left,
  .contetn_right {
    width: 540px;
    box-sizing: border-box;
    // padding: 16px 0;
  }

  .contetn_center {
    width: 720px;
  }

  //左右两侧 三个块
  .contetn_lr_item {
    height: 310px;
  }

  .contetn_center_top {
    width: 100%;
  }

  // 中间
  .contetn_center {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
  }

  .contetn_center_bottom {
    height: 315px;
  }

  //左边 右边 结构一样
  .contetn_left,
  .contetn_right {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    position: relative;
  }
}

@keyframes rotating {
  0% {
    -webkit-transform: rotate(0) scale(1);
    transform: rotate(0) scale(1);
  }
  50% {
    -webkit-transform: rotate(180deg) scale(1.1);
    transform: rotate(180deg) scale(1.1);
  }
  100% {
    -webkit-transform: rotate(360deg) scale(1);
    transform: rotate(360deg) scale(1);
  }
}
</style>
