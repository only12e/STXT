<template>
	<div style="padding: 20px;">
		<div>
			<el-breadcrumb separator="/">
				<el-breadcrumb-item :to="{path:'/index'}">首页</el-breadcrumb-item>
				<el-breadcrumb-item :to="{path:''}">考试主页面</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div>
			<el-button type="success" size="mini" @click="$router.push(`/selectTest`)">开始测评</el-button>
		</div>
		<hr >
		<h2>已测评任务查询</h2>
		  <div class="app-container">
		    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
		    
		      <el-form-item label="任务名称" prop="taskName">
		        <el-input
		          v-model="queryParams.taskName"
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
		          v-model="daterangeFinishTime"
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
		
		
		    <el-table v-loading="loading" :data="testTaskList" @selection-change="handleSelectionChange">
		      <el-table-column type="selection" width="55" align="center" />
		      <el-table-column label="序号" align="center" prop="id" />
		      <!-- <el-table-column label="测评题目详情" align="center" prop="questionDetail" /> -->
		      <el-table-column label="测评周期" align="center" prop="testDate" />
		      <el-table-column label="测评任务名称" align="center" prop="taskName" />
		      <el-table-column label="安全等级" align="center" prop="level" :formatter="levelFormat" />
		      <el-table-column label="得分" align="center" prop="score" />
		      <el-table-column label="总分" align="center" prop="totalScore" />
		      <el-table-column label="答题时长" align="center" prop="replyTime" />
		      <el-table-column label="完成时间" align="center" prop="finishTime" width="180">
		        <template slot-scope="scope">
		          <span>{{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span>
		        </template>
		      </el-table-column>
		      <el-table-column label="备注" align="center" prop="remark" />
		      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
		        <template slot-scope="scope">
		          <el-button
		            size="mini"
		            type="text"
		            icon="el-icon-edit"
		            @click="$router.push(`/testDetail?id=${scope.row.id}`)"
		            v-hasPermi="['bid:testTask:edit']"
		          >详情</el-button>
	
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
import { listTestTask, getTestTask, delTestTask, addTestTask, updateTestTask, exportTestTask } from "@/api/bid/testTask";

export default {
  name: "TestTask",
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
      // 测评任务表格数据
      testTaskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 安全等级字典
      levelOptions: ['低','中','高'],
      // 完成时间时间范围
      daterangeFinishTime: [],
      // 创建时间时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        questionDetail: null,
        testDate: null,
        taskName: null,
        level: null,
        score: null,
        totalScore: null,
        replyTime: null,
        finishTime: null,
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
  },
  methods: {
    /** 查询测评任务列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeFinishTime && '' != this.daterangeFinishTime) {
        this.queryParams.params["beginFinishTime"] = this.daterangeFinishTime[0];
        this.queryParams.params["endFinishTime"] = this.daterangeFinishTime[1];
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listTestTask(this.queryParams).then(response => {
        this.testTaskList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 安全等级字典翻译
    levelFormat(row, column) {
      return this.selectDictLabel(this.levelOptions, row.level);
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
        questionDetail: null,
        testDate: null,
        taskName: null,
        level: null,
        score: null,
        totalScore: null,
        replyTime: null,
        finishTime: null,
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
      this.daterangeFinishTime = [];
      this.daterangeCreateTime = [];
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
      this.title = "添加测评任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTestTask(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改测评任务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTestTask(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTestTask(this.form).then(response => {
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
      this.$confirm('是否确认删除测评任务编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTestTask(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有测评任务数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTestTask(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
<style>
</style>
