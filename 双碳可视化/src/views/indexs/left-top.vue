<template>
  <div id="LeftTop">
    <div class="wid">
      <dv-digital-flop :config="config1" class="data bg1"></dv-digital-flop>
      <div class="text">耗电量</div>
    </div>
    <div class="wid">
      <dv-digital-flop :config="config2" class="data bg2"></dv-digital-flop>
      <div class="text">耗水量</div>
    </div>
    <div class="wid">
      <dv-digital-flop :config="config3" class="data bg3"></dv-digital-flop>
      <div class="text">总碳排放</div>
    </div>

  </div>
</template>

<script>

export default {
  data() {
    return {
      config1:{},
      config2:{},
      config3:{}
    };
  },
  filters: {
    
  },
  created() {
  
  },
  mounted() {
    this.getList()
  },
  beforeDestroy() {

  },
  methods: {
    getList(){
      this.$axios.get("http://localhost:9090/carbonReport/productPowerReport/year").then(res=>{
        console.log(res);
         this.config1={
           number:[res.data.rows[0].totalPowerConsume + res.data.rows[0].totalOfficePowerConsume],
           content:'{nt}',
           style:{
             fill:'red',
             fontSize:25
           }
         }
         this.config2={
          number:[res.data.rows[0].totalOfficeWaterConsume + res.data.rows[0].totalWaterConsume],
          content:'{nt}',
          style:{
             fill:'yellow',
             fontSize:25
           }
         }
         this.config3={
          number:[res.data.rows[0].totalCarbonEmission],
          content:'{nt}',
          style:{
            fontSize:25
          }
         }
      })
    }

  },
};
</script>
<style lang='scss' scoped>
#LeftTop{
  display: flex;
  justify-content: center;
  margin-top: 20px;
  padding: 10px;
}
.wid{

  width: 100%;
}
.data{
  aspect-ratio: 1/1;
  background-size: contain;
  background-repeat: no-repeat;
}
.bg1{
  background-image: url(../../assets/img/left_top_hong.png);
}
.bg2{
  background-image: url(../../assets/img/left_top_huang.png);
}
.bg3{
  background-image: url(../../assets/img/left_top_lan.png);
}
.text{
  text-align: center;
}
</style>