<template>
	<div>
		<h3>距离考试结束还有</h3>
		<h4 style="color: #13CE66;">{{format(countTime)}}</h4>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				countTime:3600,
				timer:null
			}
		},
		created() {
			this.start()
		},
		beforeDestroy() {
			clearInterval(this.timer)
		},
		methods:{
			format(time){
				const m =Math.floor(time/60)
				const s =Math.floor(time%60)
				return `${m}分${s}秒`
			},
			start(){
					this.timer=setInterval(()=>{
						if(this.countTime>0){
							this.countTime-=1
						}
						else {
							
						}
					},1000)
			},
			stop(){
				clearInterval(this.timer)
				localStorage.setItem("replyTime",3600-this.countTime)
			}
		}
	}
</script>

<style>
</style>
