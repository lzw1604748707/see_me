webpackJsonp([4],{"0CA1":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("woOf"),i=a.n(n),o=a("eOoE"),s={data:function(){return{query:{feebackType:"",status:""},loading:!1,page:{pageNumber:1,pageSize:10,total:""},feebackTypeList:[{value:0,name:"优化建议"},{value:1,name:"平台问题"},{value:2,name:"合作意向"},{value:3,name:"其他"}],statusList:[{value:0,name:"待确认"},{value:1,name:"已确认"},{value:2,name:"已处理"},{value:3,name:"已回绝"}],addDialogVisible:!1,infoForm:{remark:"",type:""},rules:{type:[{required:!0,message:"请选择建议类型",trigger:"change"}],remark:[{required:!0,message:"请输入建议内容",trigger:"blur"}]},tableData:[]}},computed:{realIndex:function(){var e=this;return function(t){return(e.page.pageNumber-1)*e.page.pageSize+t+1}},identity:function(){return function(e){return["游客","用户","合作商"][e]}},steerType:function(){return function(e){return["优化","平台问题反馈","合作","其他"][e]}},statusName:function(){return function(e){return["待确认","已确认","已处理","已回绝"][e]}}},methods:{closeDialog:function(e){this[e]=!1,this.infoForm=[],this.$refs.infoForm&&this.$refs.infoForm.resetFields()},onChangeStatus:function(e,t){var a=this,n={id:this.$refs.feebackTable.data[e].id,status:t};1!==t?this.$confirm("确定要执行该操作(请确保处理妥当)？").then(function(t){a.reSaveStatus(e,n)}):this.reSaveStatus(e,n)},handleSizeChange:function(e){this.page.pageSize=e,this.page.pageNumber=1,this.getList()},handleCurrentChange:function(e){this.pageNumber=e,this.getList()},handleSearch:function(){this.page.pageNumber=1,console.log("触发事件"),this.getList()},getList:function(){var e=this,t={feebackType:this.query.feebackType,status:this.query.status,pageNumber:this.page.pageNumber,pageSize:this.page.pageSize};this.loading=!0,function(e){return Object(o.a)({url:"/admin/feeback/list",method:"post",data:e})}(t).then(function(t){e.tableData=t.data.page.list,e.page.total=t.data.page.totalRow,e.loading=!1}).catch(function(t){e.loading=!1,e.$message.error(t),console.log(t)})},reSaveStatus:function(e,t){var a=this;(function(e){return Object(o.a)({url:"/admin/feeback/changeStatus",data:e,method:"post"})})(t).then(function(n){a.$message.success(n.data.msg),a.$refs.feebackTable.data[e].status=t.status})},handleAdd:function(){this.infoForm=i()({},null),this.addDialogVisible=!0},handleSubmitSave:function(){var e=this;this.$refs.infoForm.validate(function(t){t&&e.$confirm("确认提交吗？","提示",{}).then(function(){(function(e){return Object(o.a)({url:"/admin/feeback/save",method:"post",data:e})})(i()({accountId:0,accountType:0},e.infoForm)).then(function(t){e.$message.success(t.data.msg),e.$refs.infoForm.resetFields(),e.addDialogVisible=!1,e.getList()})})})},handleRemove:function(e){var t=this;this.$confirm("确定删除吗？").then(function(a){(function(e){return Object(o.a)({url:"/admin/feeback/remove/"+e,method:"get"})})(e.id).then(function(e){t.$message.success(e.data.msg),t.getList()}).catch(function(e){t.$message.error(e)})}).catch(function(e){})}},mounted:function(){this.getList()}},l={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-form",{staticClass:"query-form",attrs:{inline:"",model:e.query,"label-position":"right","label-width":"60px"}},[a("el-select",{staticStyle:{"margin-left":"10px"},attrs:{placeholder:"请选择反馈类型"},on:{change:e.handleSearch},model:{value:e.query.feebackType,callback:function(t){e.$set(e.query,"feebackType",t)},expression:"query.feebackType"}},[a("el-option",{key:-1,attrs:{label:"全部",value:""}}),e._v(" "),e._l(e.feebackTypeList,function(e){return a("el-option",{key:e.value,attrs:{label:e.name,value:e.value}})})],2),e._v(" "),a("el-select",{staticStyle:{"margin-left":"10px"},attrs:{placeholder:"请选择处理状态"},on:{change:e.handleSearch},model:{value:e.query.status,callback:function(t){e.$set(e.query,"status",t)},expression:"query.status"}},[a("el-option",{key:-1,attrs:{label:"全部",value:""}}),e._v(" "),e._l(e.statusList,function(e){return a("el-option",{key:e.value,attrs:{label:e.name,value:e.value}})})],2),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.handleAdd}},[e._v("添加意见")])],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"feebackTable",staticClass:"table",attrs:{data:e.tableData,stripe:"",border:""}},[a("el-table-column",{attrs:{type:"index",index:e.realIndex,label:"序号",width:"70"}}),e._v(" "),a("el-table-column",{attrs:{prop:"account",label:"反馈账户"}}),e._v(" "),a("el-table-column",{attrs:{prop:"accountName",label:"反馈人"}}),e._v(" "),a("el-table-column",{attrs:{prop:"accountType",label:"身份"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.identity(t.row.accountType)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"remark",label:"意见"}}),e._v(" "),a("el-table-column",{attrs:{prop:"type",label:"类型"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.steerType(t.row.type)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"type",label:"类型"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.statusName(t.row.status)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"createDate",sortable:"",label:"创建时间",formatter:e.formatDate}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:"230"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"danger",plain:""},on:{click:function(a){e.handleRemove(t.row)}}},[e._v("删除")]),e._v(" "),0===t.row.status?a("el-button",{attrs:{size:"mini",type:"primary",plain:""},on:{click:function(a){e.onChangeStatus(t.$index,1)}}},[e._v("确认")]):e._e(),e._v(" "),1===t.row.status?a("el-button",{attrs:{size:"mini",type:"success",plain:""},on:{click:function(a){e.onChangeStatus(t.$index,2)}}},[e._v("已处理")]):e._e(),e._v(" "),1===t.row.status?a("el-button",{attrs:{size:"mini",type:"warning",plain:""},on:{click:function(a){e.onChangeStatus(t.$index,3)}}},[e._v("回绝")]):e._e()]}}])})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.page.pageNumber,"page-sizes":[10,20,30,40],"page-size":e.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.page.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),a("el-dialog",{attrs:{title:"新增",visible:e.addDialogVisible,width:"500px"},on:{"update:visible":function(t){e.addDialogVisible=t},close:function(t){e.closeDialog("addDialogVisible")}}},[a("el-form",{ref:"infoForm",attrs:{model:e.infoForm,rules:e.rules,"label-width":"110px"}},[a("el-form-item",{attrs:{prop:"type",label:"建议类型："}},[a("el-select",{staticStyle:{width:"300px"},attrs:{placeholder:"请选择建议类型"},model:{value:e.infoForm.type,callback:function(t){e.$set(e.infoForm,"type",t)},expression:"infoForm.type"}},e._l(e.feebackTypeList,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.value}})}))],1),e._v(" "),a("el-form-item",{attrs:{prop:"remark",label:"建议内容："}},[a("el-input",{staticStyle:{width:"300px"},attrs:{type:"textarea",rows:5},model:{value:e.infoForm.remark,callback:function(t){e.$set(e.infoForm,"remark",t)},expression:"infoForm.remark"}})],1),e._v(" "),a("div",{staticClass:"add__prompt"},[a("p",[e._v("注意：将以游客的身份进行建议")]),e._v(" "),a("p",[e._v("(该功能用以避免交互和开发的冲突)")])])],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.closeDialog("addDialogVisible")}}},[e._v("关闭")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmitSave}},[e._v("提交")])],1)],1)],1)},staticRenderFns:[]},r=a("VU/8")(s,l,!1,function(e){a("KpTI")},null,null);t.default=r.exports},KpTI:function(e,t,a){var n=a("QB1l");"string"==typeof n&&(n=[[e.i,n,""]]),n.locals&&(e.exports=n.locals);a("rjj0")("a9fddcce",n,!0)},QB1l:function(e,t,a){(e.exports=a("FZ+f")(!0)).push([e.i,"\n.add__prompt {\n  color: #999;\n  margin-top: 40px;\n  text-align: center;\n}\n","",{version:3,sources:["/Users/eucalyptus-leave/Desktop/personal/see_me/see-me-backend/src/views/feeback/FeebackList.vue"],names:[],mappings:";AACA;EACE,YAAY;EACZ,iBAAiB;EACjB,mBAAmB;CACpB",file:"FeebackList.vue",sourcesContent:["\n.add__prompt {\n  color: #999;\n  margin-top: 40px;\n  text-align: center;\n}\n"],sourceRoot:""}])}});
//# sourceMappingURL=4.412fa281e83443900c72.js.map