<template>
	<div>
		<div>
			<el-breadcrumb separator="/">
				<el-breadcrumb-item :to="{path:'/index'}">首页</el-breadcrumb-item>
				<el-breadcrumb-item :to="{path:'/enterprise/testMain'}">考试主页面</el-breadcrumb-item>
				<el-breadcrumb-item :to="{path:'/selectTest'}">选择考试</el-breadcrumb-item>
				<el-breadcrumb-item :to="{path:''}">考试</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<!-- 倒计时 -->
		<div>
			<countDown ref="count"></countDown>
		</div>
		<!-- 题目渲染 -->
		<div v-if="questionList.length>0" style="text-align: left;margin-left: 5%;">
			<div>{{currentIndex+1}}.{{questionList[currentIndex].question}}
			<span v-if="questionList[currentIndex].type=='1'">（单选题）</span>
			<span v-if="questionList[currentIndex].type=='2'">（多选题）</span>
			<span v-if="questionList[currentIndex].type=='3'">（判断题）</span>
			</div>
			<div v-if="questionList[currentIndex].type=='2'" style="margin-top: 30px;">
				<el-checkbox-group v-model="selectList[currentIndex].optionIds">
					<el-checkbox v-for="item in questionList[currentIndex].options" :key="item.id" :label="item.id">{{item.content}}</el-checkbox>
				</el-checkbox-group>
			</div>
			<div v-else style="margin-top: 30px;">
				<el-radio-group v-model="selectList[currentIndex].optionIds[0]">
					<el-radio v-for="item in questionList[currentIndex].options" :key="item.id" :label="item.id">{{item.content}}</el-radio>
				</el-radio-group>
			</div>
			<div style="margin-top: 20px;">
				<el-button type="success" size="mini" @click="preQuestion">上一题</el-button>
				<el-button type="success" size="mini" v-if="currentIndex!=questionList.length-1" @click="nextQuestion">下一题</el-button>
				<el-button type="success" size="mini" v-else @click="nextQuestion">交卷</el-button>
			</div>
		</div>
	</div>
</template>

<script>
	import {listQuestion,detailListQuestion,check} from "@/api/bid/question"
	import { addTestTask } from "@/api/bid/testTask"
	import  countDown from "@/components/countDown"
	export default {
		components:{
			countDown
		},
		data() {
			return {
				questionList:[],
				currentIndex:0,
				selectList:[]
			}
		},
		created() {
			// console.log(detailListQuestion());
			detailListQuestion({templateId:this.$route.query.templateId}).then(res=>{
				console.log(res);
				this.questionList=res.rows
				res.rows.forEach(item=>{
					this.selectList.push({
						questionId:item.id,
						optionIds:[]
					})
				})
				console.log(this.selectList);
			})
		},
		methods:{
			preQuestion(){
				if(this.currentIndex>0){
					this.currentIndex--
				}
			},
			nextQuestion(){
				if(this.questionList[this.currentIndex].type!='2'){
					let optionId =this.selectList[this.currentIndex].optionIds[0]
					this.selectList[this.currentIndex].optionIds=[]
					this.selectList[this.currentIndex].optionIds.push(optionId)
				}
				if(this.currentIndex==this.questionList.length-1){
					this.$refs.count.stop()
					check(this.selectList).then(res=>{
						// console.log(res);
						addTestTask({
							questionDetail:res.data.result.join(";"),
							testDate:this.$route.query.testDate,
							taskName:this.$route.query.taskName,
							level:this.$route.query.level,
							score:res.data.score,
							totalScore:30,
							replyTime:localStorage.getItem("replyTime")
						}).then(res=>{
							this.msgSuccess('交卷成功！')
							this.$router.push(`/testDetail?id=${res.data}`)
						})
					})
					return 
				}
				this.currentIndex++
			}
		}
	}
</script>

<style>
</style>
