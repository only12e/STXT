<template>
  <div id="RightTOP">
    <div id="rt4" style="height: 300px; margin-top: 20px;"></div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      // 直接设置数据
      dateList: ["2023-06", "2023-07", "2023-08", "2023-09", "2023-10", "2023-11", "2023-12", "2024-01", "2024-02", "2024-03", "2024-04", "2024-05"], 
      numList: [0.6, 0.5, 0.4, 0.87, 0.65, 0.41, 0.61, 0.65, 0.36, 0.95, 0.14, 0.51],
      numList2: [0.7, 0.6, 0.9, 0.84, 0.65, 0.49, 0.5, 0.74, 0.61, 0.84, 0.61, 0.25]
    };
  },
  created() {

  },

  mounted() { this.getList() },

  beforeDestroy() {

  },

  methods: {
    getList(){
      // 在这里不再需要获取数据，数据已通过静态数组赋值
      this.init()
    },

    init(){
      let myCharts = echarts.init(document.getElementById('rt4'))
      myCharts.setOption({
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          textStyle: {
            color: 'white'
          }
        },
        xAxis: [{
          type: 'category',
          data: this.dateList,
          boundaryGap: false
        }],
        yAxis: [{
          type: 'value',
          name: '销售额'
        }],
        series: [{
          name: '销售额',
          type: 'line',
          data: this.numList,
          smooth: true
        },
        {
          name: '合同额',
          type: 'line',
          data: this.numList2,
          smooth: true
        }]
      })
    }
  }
};
</script>

<style lang='scss' scoped>

</style>
