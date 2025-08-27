<template>
  <div id="CenterTop">
    <div class="top">
      <el-row>
        <el-col :span="8">
          今年累计销售额
          <dv-digital-flop :config="config1" class="yzh" />
        </el-col>
        <el-col :span="8">
          本月累计销售额
          <dv-digital-flop :config="config2" class="yzh" />
        </el-col>
        <el-col :span="8">
          本日累计销售额
          <dv-digital-flop :config="config3" class="yzh" />
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          今年累计碳排放
          <dv-digital-flop :config="config4" class="yzh" />
        </el-col>
        <el-col :span="8">
          本月累计碳排放
          <dv-digital-flop :config="config5" class="yzh" />
        </el-col>
        <el-col :span="8">
          本日累计碳排放
          <dv-digital-flop :config="config6" class="yzh" />
        </el-col>
      </el-row>
    </div>
    <div id="Bottom">
      <div style="margin-left: 80px; margin-right: 20px;">碳排放排行榜</div>
      <dv-scroll-board :config="config7" class="dv-scroll-board"/>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      config1: {
        number: [202409220]
      },
      config2: {
        number: [200490]
      },
      config3: {
        number: [20040]
      },
      config4: {},
      config5: {},
      config6: {},
      config7: {}
    };
  },
  created() {},
  mounted() {
    this.getList();
  },
  beforeDestroy() {},
  methods: {
    getList() {
      this.$axios.get("http://localhost:9090/carbonReport/saleOverall").then(res => {
        console.log(res);
        let abc = res.data.data;
        this.config1 = {
          number: [202409220],
          toFixed: 2,
          content: '{nt}'
        };
        this.config2 = {
          number: [200490],
          toFixed: 2,
          content: '{nt}'
        };
        this.config3 = {
          number: [20040],
          toFixed: 2,
          content: '{nt}'
        };
      });
      this.$axios.get("http://localhost:9090/carbonReport/productPowerReport/year").then(res => {
        console.log(res);
        this.config4 = {
          number: [res.data.rows[0].totalCarbonEmission],
          toFixed: 2,
          content: '{nt}'
        };
      });
      this.$axios.get("http://localhost:9090/carbonReport/productPowerReport/month").then(res => {
        console.log(res);
        this.config5 = {
          number: [res.data.rows[0].totalCarbonEmission],
          toFixed: 2,
          content: '{nt}'
        };
      });
      this.$axios.get("http://localhost:9090/carbonReport/productPowerReport/day").then(res => {
        console.log(res);
        this.config6 = {
          number: [res.data.rows[0].totalCarbonEmission],
          toFixed: 2,
          content: '{nt}'
        };
      });
      this.$axios.get("http://localhost:9090/carbonReport/equipmentPowerReport/list").then(res => {
        console.log(res);
        let abc = res.data.rows;
        this.config7 = {
          header: ["编号", "设备", "生产数量", "碳排放"],
          data: []
        };
        for (let i = 0; i < 8; i++) {
          this.config7.data.push([i + 1, abc[i].equipmentName, abc[i].productQuantity, abc[i].totalCarbonEmission]);
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.top {
  text-align: center;
  margin-top: 20px;
}
.yzh {
  height: 50px;
}

#Bottom {
  display: flex;
  flex-direction: column;
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
  margin-top: 20px;
}

.dv-scroll-board {
  height: 280px;
  width: 680px;
  margin-left: 20px;
  margin-right: 20px;
}
</style>
