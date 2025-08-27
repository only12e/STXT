<template>
 <div id="LeftBottom">
  <dv-scroll-board :config="config" style="margin-top: 20px; margin-left: 20px; height: 250px; width: 500px;"></dv-scroll-board>
 </div>
</template>

<script>




export default {
  components: { },
  data() {
    return {
      config:{}
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
      this.$axios.get("http://localhost:9090/material/materialInventory/list").then(res=>{
        console.log(res);
        let abc = res.data.rows
        this.config ={
        header:["编号","物料","仓库","当前库存","状态"],
        data:[]
        }
        for(let i = 0;i<abc.length;i++){
          let status=''
          if(abc[i].totalInventory>abc[i].maxInventory){
            status="溢出".fontcolor('Green')
          }else
            if(abc[i].totalInventory<abc[i].maxInventory){
              status="不足".fontcolor('Red')
            }else{
              status="正常"
            }
            this.config.data.push([i+1,abc[i].materialName,abc[i].warehouseName,abc[i].totalInventory,status])
          
        }
        
      })
    }
    
  },
};
</script>
<style lang='scss' scoped>

</style>