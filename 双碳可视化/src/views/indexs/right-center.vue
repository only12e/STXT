<template>
<div id="RightCenter">
  <dv-capsule-chart :config="config" style="height: 250px; margin-left: 20px;"></dv-capsule-chart>
</div>
</template>

<script>
export default {
  data() {
    return {
config:{}
    };
  },
  created() {

  },
  beforeDestroy() {

  },
  mounted(){
    this.getList()
  },
  methods: {
    getList(){
      this.$axios.get("http://localhost:9090/carbonReport/saleCustomer/ranking").then(res=>{
        console.log(res);
        let abc=res.data.data
        this.config={
          data:abc
        }
      })
    }
  },
};
</script>
<style lang='scss' scoped></style>