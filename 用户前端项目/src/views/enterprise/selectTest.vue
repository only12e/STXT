<template>
	<div>
		<div>
			<el-breadcrumb separator="/">
				<el-breadcrumb-item :to="{path:'/index'}">首页</el-breadcrumb-item>
				<el-breadcrumb-item :to="{path:'/enterprise/testMain'}">考试主页面</el-breadcrumb-item>
				<el-breadcrumb-item :to="{path:''}">选择考试</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		 <div class="app-container">
		    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
		 
		      <el-form-item label="任务名称" prop="name">
		        <el-input
		          v-model="queryParams.name"
		          placeholder="请输入测评任务名称"
		          clearable
		          size="small"
		          @keyup.enter.native="handleQuery"
		        />
		      </el-form-item>
				  <el-form-item label="安全等级" prop="level">
				        <el-select v-model="queryParams.level" placeholder="请选择安全等级" clearable filterable size="small">
				          <el-option
				            v-for="dict in levelOptions"
				            :key="dict.dictValue"
				            :label="dict.dictLabel"
				            :value="dict.dictValue"
				          />
				        </el-select>
				      </el-form-item>
		
		      <el-form-item label="时间区间">
		        <el-date-picker
		          v-model="daterangePublishTime"
		          size="small"
		          style="width: 240px"
		          value-format="yyyy-MM-dd"
		          type="daterange"
		          range-separator="-"
		          start-placeholder="开始日期"
		          end-placeholder="结束日期"
		        ></el-date-picker>
		      </el-form-item>
		      <el-form-item>
		        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
		        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
		      </el-form-item>
		    </el-form>
		
		    <el-row :gutter="10" class="mb8">
		      <el-col :span="1.5">
		        <el-button
		          type="primary"
		          plain
		          icon="el-icon-plus"
		          size="mini"
		          @click="handleAdd"
		          v-hasPermi="['bid:task:add']"
		        >新增</el-button>
		      </el-col>
		      <el-col :span="1.5">
		        <el-button
		          type="success"
		          plain
		          icon="el-icon-edit"
		          size="mini"
		          :disabled="single"
		          @click="handleUpdate"
		          v-hasPermi="['bid:task:edit']"
		        >修改</el-button>
		      </el-col>
		      <el-col :span="1.5">
		        <el-button
		          type="danger"
		          plain
		          icon="el-icon-delete"
		          size="mini"
		          :disabled="multiple"
		          @click="handleDelete"
		          v-hasPermi="['bid:task:remove']"
		        >删除</el-button>
		      </el-col>
		      <el-col :span="1.5">
		        <el-button
		          type="warning"
		          plain
		          icon="el-icon-download"
		          size="mini"
		          @click="handleExport"
		          v-hasPermi="['bid:task:export']"
		        >导出</el-button>
		      </el-col>
		      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
		    </el-row>
		
		    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
		      <el-table-column type="selection" width="55" align="center" />
		      <el-table-column label="序号" align="center" prop="id" />
		      <el-table-column label="模板id" align="center" prop="templateId" :formatter="templateIdFormat" />
		      <el-table-column label="测评任务名称" align="center" prop="name" />
		      <el-table-column label="测评周期" align="center" prop="testDate" />
		      <el-table-column label="答题时长" align="center" prop="replyTime" />
		      <el-table-column label="状态" align="center" prop="status" />
		      <el-table-column label="问题数量" align="center" prop="questionNum" />
		      <el-table-column label="发布时间" align="center" prop="publishTime" width="180">
		        <template slot-scope="scope">
		          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
		        </template>
		      </el-table-column>
		      <el-table-column label="备注" align="center" prop="remark" />
		      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
		        <template slot-scope="scope">
		          <el-button
		            size="mini"
		            type="text"
		            icon="el-icon-edit"
		            @click="beginTest(scope.row)"
		         
		          >开始考试</el-button>
		   
		        </template>
		      </el-table-column>
		    </el-table>
		    
		    <pagination
		      v-show="total>0"
		      :total="total"
		      :page.sync="queryParams.pageNum"
		      :limit.sync="queryParams.pageSize"
		      @pagination="getList"
		    />
		
		  
		  </div>
	</div>
</template>

<script>
import { listTask, getTask, delTask, addTask, updateTask, exportTask } from "@/api/bid/task";

export default {
  name: "Task",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 任务发布表格数据
      taskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 模板id字典
      templateIdOptions: [],
      // 发布时间时间范围
      daterangePublishTime: [],
      // 创建时间时间范围
      daterangeCreateTime: [],
      // 更新时间时间范围
      daterangeUpdateTime: [],
	  levelOptions:[],
      // 查询参数
      queryParams: {
		 level:null,
        pageNum: 1,
        pageSize: 10,
        templateId: null,
        name: null,
        testDate: null,
        replyTime: null,
	
        status: null,
        questionNum: null,
        publishTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
	    this.getDicts("level").then(response => {
	      this.levelOptions = response.data;
	    });
    this.getQueryData("query_template").then(res => {
      this.templateIdOptions = res.data;
	  // console.log(this.templateIdOptions);
    });
  },
  methods: {
	  beginTest(row){
		this.$router.push({
			path:'/testing',
			query:{
				testDate:row.testDate,
				taskName:row.name,
				level:row.level,
				templateId:row.templateId
			}
		})  
	  },
    /** 查询任务发布列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangePublishTime && '' != this.daterangePublishTime) {
        this.queryParams.params["beginPublishTime"] = this.daterangePublishTime[0];
        this.queryParams.params["endPublishTime"] = this.daterangePublishTime[1];
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
	  this.queryParams.status='1'
      listTask(this.queryParams).then(response => {
        this.taskList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
	  // 安全等级字典翻译
	    levelFormat(row, column) {
	      return this.selectDictLabel(this.levelOptions, row.level);
	    },
    // 模板id字典翻译
    templateIdFormat(row, column) {
      return this.selectDictLabel(this.templateIdOptions, row.templateId);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        templateId: null,
        name: null,
        testDate: null,
        replyTime: null,
        status: "0",
        questionNum: null,
        publishTime: null,
        createTime: null,
        updateTime: null,
        remark: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangePublishTime = [];
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加任务发布";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTask(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务发布";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTask(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTask(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除任务发布编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTask(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有任务发布数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTask(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

<style>
</style>
