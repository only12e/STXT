<template>
  <div id="CenterBottom">
    <div id="asd" style="height: 400px;width: 680px;margin-top: 20px; margin-left: 20px;"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 直接设置数据
      barData: [400, 500, 600, 430, 540, 400, 600, 730, 540, 601, 300, 501, 200],
      category: ["2023-06", "2023-07", "2023-08", "2023-09", "2023-10", "2023-11", "2023-12", "2024-01", "2024-02", "2024-03", "2024-04", "2024-05", "2024-06"], 
      lineData: [100, 200, 600, 400, 500, 240, 601, 305, 406, 405, 440, 445],
      rateData: [0.3, 0.8, 0.5, 0.4, 0.65, 0.12, 0.62, 0.8, 0.1, 0.9, 0.4, 0.6, 0.7]
    };
  },
  props: {},
  mounted() {
    this.init();
  },
  beforeDestroy() {},
  methods: {
    init() {
      let myCharts = echarts.init(document.getElementById('asd'));
      myCharts.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        legend: {
          data: ["计划销售", "销售量", "达成率"],
          textStyle: {
            color: 'white'
          }
        },
        xAxis: [{
          type: 'category',
          data: this.category,
          axisPointer: {
            type: 'shadow'
          }
        }],
        yAxis: [{
            type: 'value',
            name: '计划销售',
            max: 800,
            min: 0,
            interval: 200
          },
          {
            type: 'value',
            name: '达成率',
            max: 1,
            min: 0,
            interval: 0.1
          }
        ],
        series: [{
            name: '计划销售',
            type: 'bar',
            data: this.lineData,
          },
          {
            name: '销售量',
            type: 'bar',
            data: this.barData,
          },
          {
            name: '达成率',
            type: 'line',
            data: this.rateData,
            yAxisIndex: 1,
            smooth: true
          }
        ]
      });
    }
  }
}
</script>

<style lang="scss" scoped>
</style>