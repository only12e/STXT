<template>
  <div id="RightBottom">
    <Seam :data="productList" style="height: 300px;margin-top: 20px;margin-left:20px;overflow: hidden;">
    <ul v-for="(a,i) in productList" style="height: 80px;">
      <span>编号:{{ i }} &nbsp;</span>
      <span>产品:{{ a.materialName}} &nbsp;</span>
      <span>模型:{{ a.materialModel }} &nbsp;</span>
      <span>规格:{{ a.materialSpecification }}&nbsp;</span>
      <span>单位:{{ a.materialUnit }}&nbsp;</span>
      <span>总库存:{{ a.totalInventory }}&nbsp;</span>
      <span>已生产:{{ a.productQuantity }}&nbsp;</span>
      <span>代生产:{{ a.requireQuantity }}&nbsp;</span>
      <span>总碳排放:{{ a.totalCarbonEmission }}&nbsp;</span>
    </ul>
    </Seam>
  </div>
  </template>
  
  <script>
  import Seam from "vue-seamless-scroll"
  export default {
    components: {Seam},
  
    data() {
      return {
        productList:[]
      };
    },
    computed: {
    },
    created() {
      
    },
  
    mounted() { 
      this.getList()
    },
    methods: {
      getList(){
        this.$axios.get("http://localhost:9090/carbonReport/productPowerReport/list").then(res=>{
          console.log(res);
             this.productList = res.data.rows
        })
      }
      
  
    },
  };
  </script>
  <style lang='scss' scoped>
  
  </style>