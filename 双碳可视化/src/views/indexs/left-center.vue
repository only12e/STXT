<template>
 <div id="LeftCenter">
  <div id="rt2" style="height: 300px; margin-top: 20px;margin-left: 20px;"></div>
 </div>
</template>

<script>



export default {
  data() {
    return {
      
    };
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
      this.$axios.get("http://localhost:9090/carbonReport/emissionChart/percent").then(res=>{
        console.log(res);
        let abc = res.data.data[0]
        this.totalOfficePowerConsume=abc.totalOfficePowerConsume
        this.totalOfficeWaterConsume=abc.totalOfficeWaterConsume
        this.totalPowerConsume=abc.totalPowerConsume
        this.totalWaterConsume=abc.totalWaterConsume
        this.title=this.totalOfficePowerConsume+this.totalOfficeWaterConsume+this.totalPowerConsume+this.totalWaterConsume
        this.init()
      })
    },
    init(){
      let myCharts =echarts.init(document.getElementById('rt2'))
      myCharts.setOption({
        title:{
          text:this.title,
          subtext:'能耗总数',
          left:'center',
          top:'center',
          textStyle:{
            color:'white'
          }
        },
        series:[{
          type:'pie',
          radius:["40%","70%"],
          label:{
            position:'outer',
            fromatter:'{b}:{c} {d}%',
            textStyle:{
              color:"white"
            }
          },
          emphasis:{
            label:{
              fontWeight:'bold',
              fontSize:25,
              textStyle:{
                color:'white'
              }
            }
          },
          data:[
            {value:this.totalOfficePowerConsume,name:"办公室耗电量"},
            {value:this.totalOfficeWaterConsume,name:"办公室耗水量"},
            {value:this.totalWaterConsume,name:"生产耗水量"},
            {value:this.totalPowerConsume,name:"生产耗电量"}
          ]
        }]
      })
    }
  },
};
</script>
<style lang='scss' scoped>
</style>