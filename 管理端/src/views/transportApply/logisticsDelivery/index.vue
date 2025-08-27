<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="订单号" prop="orderNo">
                <el-input v-model="queryParams.orderNo" placeholder="请输入订单号" clearable size="small"
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="发货单号" prop="deliveryNo">
                <el-input v-model="queryParams.deliveryNo" placeholder="请输入销售发货" clearable size="small"
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="发货人" prop="deliveryUser">
                <el-input v-model="queryParams.deliveryUser" placeholder="请输入发货人" clearable size="small"
                    @keyup.enter.native="handleQuery" />
            </el-form-item>

            <el-form-item label="收货人" prop="receiveUser">
                <el-input v-model="queryParams.receiveUser" placeholder="请输入收货人" clearable size="small"
                    @keyup.enter.native="handleQuery" />
            </el-form-item>


            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                    v-hasPermi="['transportApply:logisticsDelivery:add']">新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                    v-hasPermi="['transportApply:logisticsDelivery:edit']">修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
                    @click="handleDelete" v-hasPermi="['transportApply:logisticsDelivery:remove']">删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                    v-hasPermi="['transportApply:logisticsDelivery:export']">导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="logisticsDeliveryList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="编号" align="center" prop="id" />
            <el-table-column label="订单号" align="center" prop="orderNo" />
            <el-table-column label="发货单号" align="center" prop="deliveryNo" />
            <el-table-column label="发货人" align="center" prop="deliveryUser" />
            <el-table-column label="发货人联系电话" align="center" prop="deliveryUserContact" />
            <el-table-column label="发货地址" align="center" prop="deliveryAdress" />
            <el-table-column label="收货人" align="center" prop="receiveUser" />
            <el-table-column label="收货人联系电话" align="center" prop="receiveUserContact" />
            <el-table-column label="收货地址" align="center" prop="receiveAdress" />
            <el-table-column label="物流途径地" align="center" prop="wuliuRoute" />
            <el-table-column label="发货状态" align="center" prop="deliveryStatus" :formatter="deliveryStatusFormat" />
            <!-- <el-table-column label="物流地图" align="center" prop="wuliuMap" />
            <el-table-column label="备注" align="center" prop="remark" />
            <el-table-column label="备用字段1" align="center" prop="remark1" />
            <el-table-column label="备用字段2" align="center" prop="remark2" /> -->
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="250px">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                        v-hasPermi="['transportApply:logisticsDelivery:edit']">修改</el-button>
                    <el-button type="text" icon="el-icon-plus" size="mini" @click="handleAdd"
                        v-hasPermi="['transportApply:logisticsDelivery:add']">新增</el-button>
                    <el-button type="text" icon="el-icon-plus" size="mini"
                        v-hasPermi="['transportApply:logisticsDelivery:add']"
                        @click="preWuLiu(scope.row)">物流预览</el-button>
                    <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                        v-hasPermi="['transportApply:logisticsDelivery:remove']">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />

        <!-- 添加或修改物流发货对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="订单号" prop="orderNo">
                    <el-input v-model="form.orderNo" placeholder="---系统自动生成" disabled="" />
                </el-form-item>
                <el-form-item label="发货单号" prop="deliveryNo">
                    <el-input v-model="form.deliveryNo" placeholder="请输入销售发货" disabled="">
                        <el-button type="primary" slot="append" @click="openDelivery=true">选择</el-button>
                    </el-input>
                </el-form-item>
                <el-form-item label="发货人" prop="deliveryUser">
                    <el-input v-model="form.deliveryUser" placeholder="请输入发货人" />
                </el-form-item>
                <el-form-item label="发货人联系电话" prop="deliveryUserContact">
                    <el-input v-model="form.deliveryUserContact" placeholder="请输入发货人联系电话" />
                </el-form-item>
                <el-form-item label="发货地址" prop="deliveryAdress">
                    <el-input v-model="form.deliveryAdress" placeholder="请输入发货地址" />
                </el-form-item>
                <el-form-item label="收货人" prop="receiveUser">
                    <el-input v-model="form.receiveUser" placeholder="请输入收货人" />
                </el-form-item>
                <el-form-item label="收货人联系电话" prop="receiveUserContact">
                    <el-input v-model="form.receiveUserContact" placeholder="请输入收货人联系电话" />
                </el-form-item>
                <el-form-item label="收货地址" prop="receiveAdress">
                    <el-input v-model="form.receiveAdress" placeholder="请输入收货地址" />
                </el-form-item>
                <el-form-item label="物流途径地" prop="wuliuRoute">
                    <!-- <el-input v-model="form.wuliuRoute" placeholder="请输入物流途径地" /> -->
                    <el-select v-model="form.wuliuRoute" placeholder="请选择物流途径地" :multiple="true">
                        <el-option v-for="(item,index) in areaList" :key="index" :label="item" :value="item">
                        </el-option>
                    </el-select>

                </el-form-item>
                <el-form-item label="发货状态" prop="deliveryStatus">
                    <el-select v-model="form.deliveryStatus" placeholder="请选择发货状态" clearable filterable>
                        <el-option v-for="dict in deliveryStatusOptions" :key="dict.dictValue" :label="dict.dictLabel"
                            :value="dict.dictValue"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
        <el-dialog title="物流进度预览" :visible.sync="dialogVisible" :close-on-click-modal="false">
            <div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center;">
                <LogisticsDeliveryMap v-if="dialogVisible" :startCenter="startCenter" :endCenter="endCenter"
                    :routeData="routeData">
                </LogisticsDeliveryMap>
            </div>
        </el-dialog>
        <DeliverySelect :open="openDelivery" @onDeliverySelected="handleDelivery"
            @onDeliveryCancel="openDelivery=false"></DeliverySelect>
      
    </div>
</template>

<script>
    import { listLogisticsDelivery, getLogisticsDelivery, delLogisticsDelivery, addLogisticsDelivery, updateLogisticsDelivery, exportLogisticsDelivery } from "@/api/transportApply/logisticsDelivery";
    import ImageUpload from '@/components/ImageUpload';
    import DeliverySelect from '@/components/Sale/DeliverySelect.vue'
    import LogisticsDeliveryMap from '@/components/LogisticsDeliveryMap'
    import china from '@/assets/china.json'
    export default {
        name: "LogisticsDelivery",
        components: {
            ImageUpload,
            DeliverySelect,
            LogisticsDeliveryMap
        },
        data() {
            return {
                routeData: [{

                }],
                areaList: [
                    '山东省', '海南省', '河北省', '山西省', '辽宁省', '浙江省', '湖南省', '北京市'
                ],
                startCenter: null,
                endCenter: null,
                dialogVisible: false,
                openDelivery: false,
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
                // 物流发货表格数据
                logisticsDeliveryList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 发货状态字典
                deliveryStatusOptions: [],
                // 创建时间时间范围
                daterangeCreateTime: [],
                // 更新时间时间范围
                daterangeUpdateTime: [],
                // 查询参数
                queryParams: {
                    deliveryNo: null,
                    pageNum: 1,
                    pageSize: 10,
                    orderNo: null,
                    deliveryId: null,
                    deliveryUser: null,
                    deliveryUserContact: null,
                    deliveryAdress: null,
                    receiveUser: null,
                    receiveUserContact: null,
                    receiveAdress: null,
                    wuliuRoute: null,
                    deliveryStatus: null,
                    wuliuMap: null,
                    remark1: null,
                    remark2: null
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                    // orderNo: [
                    //     { required: true, message: "订单号不能为空", trigger: "blur" }
                    // ],
                    deliveryUser: [
                        { required: true, message: "发货人不能为空", trigger: "blur" }
                    ],
                    deliveryUserContact: [
                        { required: true, message: "发货人联系电话不能为空", trigger: "blur" }
                    ],
                    deliveryAdress: [
                        { required: true, message: "发货地址不能为空", trigger: "blur" }
                    ],
                    receiveUser: [
                        { required: true, message: "收货人不能为空", trigger: "blur" }
                    ],
                    receiveUserContact: [
                        { required: true, message: "收货人联系电话不能为空", trigger: "blur" }
                    ],
                    receiveAdress: [
                        { required: true, message: "收货地址不能为空", trigger: "blur" }
                    ],
                }
            };
        },
        created() {
            this.getAddressList()
            this.getList();
            this.getDicts("logistics_delivery_status").then(response => {
                this.deliveryStatusOptions = response.data;
            });
        },
        methods: {
            getAddressList() {
                const feature = china.features.find(f => f.properties.name === name);
                console.log(111, feature.properties);


            },
            getCenterByName(name) {
                // 遍历features数组
                const feature = china.features.find(f => f.properties.name === name);
                // 如果找到了，返回center数组，否则返回null
                return feature ? feature.properties.center : null;
            },
            preWuLiu(row) {
                if (row) {
                    let list = row.wuliuRoute.split(',')
                    // console.log(list);
                    this.startCenter = this.getCenterByName(list[0])
                    this.endCenter = this.getCenterByName(list[list.length - 1])
                    // ['海南省', '山东省', '东北省', '北京市']
                    const result = list.slice(1, -1);
                    let a = []
                    for (let i = 0; i < result.length; i++) {
                        this.routeData.push({
                            name: result[i],
                            coord: this.getCenterByName(result[i])
                        })
                    }
                    // console.log(this.routeData);
                    this.dialogVisible = true
                }



            },
            handleDelivery(list) {
                if (list) {
                    let a = list[0]
                    console.log(a);
                    this.form.deliveryId = a.id
                    this.form.deliveryNo = a.deliveryNo

                }
                this.openDelivery = false
            },
            /** 查询物流发货列表 */
            getList() {
                this.loading = true;
                this.queryParams.params = {};
                if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
                    this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
                    this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
                }
                if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
                    this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
                    this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
                }
                listLogisticsDelivery(this.queryParams).then(response => {
                    this.logisticsDeliveryList = response.rows;
                    this.total = response.total;
                    this.loading = false;
                });
            },
            // 发货状态字典翻译
            deliveryStatusFormat(row, column) {
                return this.selectDictLabel(this.deliveryStatusOptions, row.deliveryStatus);
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
                    orderNo: null,
                    deliveryId: null,
                    deliveryNo: null,
                    deliveryUser: null,
                    deliveryUserContact: null,
                    deliveryAdress: null,
                    receiveUser: null,
                    receiveUserContact: null,
                    receiveAdress: null,
                    wuliuRoute: null,
                    deliveryStatus: null,
                    wuliuMap: null,
                    createTime: null,
                    updateTime: null,
                    createBy: null,
                    remark: null,
                    remark1: null,
                    remark2: null,
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
                this.daterangeCreateTime = [];
                this.daterangeUpdateTime = [];
                this.resetForm("queryForm");
                this.handleQuery();
            },
            // 多选框选中数据
            handleSelectionChange(selection) {
                this.ids = selection.map(item => item.id)
                this.single = selection.length !== 1
                this.multiple = !selection.length
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加物流发货";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getLogisticsDelivery(id).then(response => {
                    this.form = response.data;
                    this.form.wuliuRoute = response.data.wuliuRoute.split(',')
                    this.open = true;
                    this.title = "修改物流发货";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        this.form.wuliuRoute = this.form.wuliuRoute.join(',')
                        if (this.form.id != null) {
                            updateLogisticsDelivery(this.form).then(response => {
                                this.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addLogisticsDelivery(this.form).then(response => {
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
                this.$confirm('是否确认删除物流发货编号为"' + ids + '"的数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function () {
                    return delLogisticsDelivery(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                })
            },
            /** 导出按钮操作 */
            handleExport() {
                const queryParams = this.queryParams;
                this.$confirm('是否确认导出所有物流发货数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function () {
                    return exportLogisticsDelivery(queryParams);
                }).then(response => {
                    this.download(response.msg);
                })
            }
        }
    };
</script>