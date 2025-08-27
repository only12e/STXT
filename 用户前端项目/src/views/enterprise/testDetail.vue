<template>
	<div>
		<div>
			<el-breadcrumb separator="/">
				<el-breadcrumb-item :to="{path:'/index'}">首页</el-breadcrumb-item>
				<el-breadcrumb-item :to="{path:'/enterprise/testMain'}">考试主页面</el-breadcrumb-item>
				<!-- <el-breadcrumb-item :to="{path:''}">选择考试</el-breadcrumb-item> -->
				<el-breadcrumb-item :to="{path:''}">考试详情页</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div>
			<h2>答题时间：{{detail.replyTime}}秒</h2>
			<h2>总得分：{{detail.score}}</h2>
			<hr >
			<div  style="margin: 0 auto;">
						<el-row v-for="(item,index) in questionList" :key="index" style="margin-bottom: 10px;">
							<span>{{item}}</span>
						</el-row>
			</div>
		</div>
		<div style="margin-left: 90%;">
			<el-button type="success" @click="$router.push(`/enterprise/testMain`)">返回</el-button>
		</div>
	</div>
</template>

<script>
	import { getTestTask } from "@/api/bid/testTask"
	export default {
		data() {
			return {
				detail:{},
				questionList:[]
			}
		},
		created() {
			getTestTask(this.$route.query.id).then(res=>{
				this.detail=res.data
				this.questionList=res.data.questionDetail.split(";")
			})
		},
		
	}
</script>

<style>
</style>
