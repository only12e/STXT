<template>
  <div ref="mapChart" style="width: 700px; height: 500px; border: 1px solid #000;"></div>
</template>

<script>
import * as echarts from 'echarts';
import chinaJson from '@/assets/china.json'; // 假设china.json在src/assets目录下

export default {
  props: ['startCenter', 'endCenter', 'routeData'],
  mounted() {
    this.initMap();
  },
  methods: {
    initMap() {
      // 判断坐标数据是否有效
      if (!this.startCenter || !this.endCenter || !this.routeData) {
        console.error('Missing coordinate data!');
        return;
      }

      this.routeData.shift();
      console.log(this.routeData);

      // 初始化图表实例
      const chart = echarts.init(this.$refs.mapChart);

      // 注册地图数据
      echarts.registerMap('china', chinaJson);

      // 发货省份、途经点、收货省份的经纬度坐标
      const start = { name: '北京', coord: this.startCenter }; // 发货省份
      const transitPoints = this.routeData; // 途经点
      const end = { name: '上海', coord: this.endCenter };   // 收货省份

      // 生成所有连线数据（出发点→途径点1→途径点2→...→终点）
      const linesData = [];
      let prevPoint = start;
      for (const point of transitPoints) {
        linesData.push({
          fromName: prevPoint.name,
          toName: point.name,
          coords: [prevPoint.coord, point.coord]
        });
        prevPoint = point;
      }
      // 添加最后一个途径点到终点的连线
      linesData.push({
        fromName: prevPoint.name,
        toName: end.name,
        coords: [prevPoint.coord, end.coord]
      });

      // 设置地图配置项
      const option = {
        tooltip: {
          trigger: 'item',
          formatter: '{b}' // 展示地区名称
        },
        geo: {
          map: 'china', // 使用注册的“中国”地图
          roam: true, // 开启缩放和拖拽功能
          itemStyle: {
            normal: {
              areaColor: '#f4f4f4',
              borderColor: '#111'
            },
            emphasis: {
              areaColor: '#d1d1d1'
            }
          },
          label: {
            show: true,  // 始终显示省份名称
            color: '#333',  // 设置名称颜色
            fontSize: 8,  // 设置字体大小
            fontWeight: 'bold',  // 设置字体粗细
          },
          zlevel: 0, // 地图图层的 zlevel 为 0（地图背景层）
        },
        series: [
          // 绘制路线（多段线）
          {
            type: 'lines',
            coordinateSystem: 'geo',
            data: linesData,
            lineStyle: {
              color: '#FF6347', // 路线颜色（红色）
              width: 2, // 路线宽度
              type: 'solid' // 路线类型
            },
            zlevel: 2  // 确保线路绘制在其他元素上面
          },
          // 绘制发货省份（蓝色圆点）
          {
            type: 'scatter',
            coordinateSystem: 'geo',
            data: [
              { name: start.name, value: start.coord.concat ? start.coord.concat([10]) : start.coord }
            ],
            symbol: 'circle',
            symbolSize: 10, // 点的大小
            itemStyle: {
              color: '#1E90FF' // 发货省份的颜色（蓝色）
            },
            zlevel: 2  // 发货点在地图点标记上层
          },
          // 绘制途经点（蓝色圆点）
          {
            type: 'scatter',
            coordinateSystem: 'geo',
            data: transitPoints.map(p => ({
              name: p.name,
              value: p.coord && p.coord.concat ? p.coord.concat([10]) : p.coord // 确保 coord 有值并且有效
            })),
            symbol: 'circle',
            symbolSize: 10, // 点的大小
            itemStyle: {
              color: '#1E90FF' // 途经点的颜色（蓝色）
            },
            zlevel: 2  // 途经点在地图点标记上层
          },
          // 绘制收货省份（红色圆点）
          {
            type: 'scatter',
            coordinateSystem: 'geo',
            data: [
              { name: end.name, value: end.coord.concat ? end.coord.concat([10]) : end.coord }
            ],
            symbol: 'circle',
            symbolSize: 10, // 点的大小
            itemStyle: {
              color: '#FF4500' // 收货省份的颜色（红色）
            },
            zlevel: 2  // 收货点在地图点标记上层
          }
        ]
      };

      // 使用配置项设置图表
      chart.setOption(option);

      // 确保容器大小变动时，图表也能正确调整
      window.addEventListener('resize', () => {
        chart.resize();
      });
    }
  }
};
</script>

<style scoped>
/* 样式可以根据需求调整 */
</style>