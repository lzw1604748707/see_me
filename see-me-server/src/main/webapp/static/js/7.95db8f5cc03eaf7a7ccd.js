webpackJsonp([7],{CwvD:function(e,t,a){var n=a("TXvC");"string"==typeof n&&(n=[[e.i,n,""]]),n.locals&&(e.exports=n.locals);a("rjj0")("2d3cab76",n,!0)},TXvC:function(e,t,a){(e.exports=a("FZ+f")(!0)).push([e.i,"\n.table__pagination[data-v-3ad4eb5c] {\n  margin-top: 5px;\n}\n.add__prompt[data-v-3ad4eb5c] {\n  color: #999;\n  margin-top: 40px;\n  text-align: center;\n}\n","",{version:3,sources:["/Users/eucalyptus-leave/Desktop/personal/see_me/see-me-backend/src/views/user/createFieldList.vue"],names:[],mappings:";AACA;EACE,gBAAgB;CACjB;AACD;EACE,YAAY;EACZ,iBAAiB;EACjB,mBAAmB;CACpB",file:"createFieldList.vue",sourcesContent:["\n.table__pagination[data-v-3ad4eb5c] {\n  margin-top: 5px;\n}\n.add__prompt[data-v-3ad4eb5c] {\n  color: #999;\n  margin-top: 40px;\n  text-align: center;\n}\n"],sourceRoot:""}])},"a/pe":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("woOf"),i=a.n(n),r=a("Y81h"),o=a.n(r),l=a("eOoE"),s={data:function(){return{query:{provider:"",fieldName:""},addDialogVisible:!1,page:{pageNumber:1,pageSize:10,total:0},infoForm:{},rules:{fieldName:[{required:!0,message:"请输入创作领域名称",trigger:"blur"}]},tableData:[]}},computed:{realIndex:function(){var e=this;return function(t){return(e.page.pageNumber-1)*e.page.pageSize+t+1}},statusName:function(){return function(e){return["审核中","通过","未通过"][e]}}},mounted:function(){this.getList()},methods:{closeDialog:function(e){this[e]=!1,this.infoForm=[],this.$refs.infoForm&&this.$refs.infoForm.resetFields()},handleSizeChange:function(e){this.page.pageSize=e,this.page.pageNumber=1,this.getList()},handleCurrentChange:function(e){console.log("当前页",e),this.page.pageNumber=e,this.getList()},formatDate:function(){return this.$moment(arguments.length<=2?void 0:arguments[2]).format("YYYY-MM-DD")},changeStatus:function(e,t,a){var n=this;(function(e){return Object(l.a)({url:"/admin/user/createfield/changeStatus",data:e,method:"post"})})({id:t,status:a}).then(function(t){n.$message.success(t.data.msg),n.$refs.createFieldTable.data[e].status=a})},handleAdd:function(){this.addDialogVisible=!0,this.infoForm=i()({},null)},handleSearch:function(){this.getList()},handleSubmitSave:function(){var e=this;this.$refs.infoForm.validate(function(t){t&&e.$confirm("确认提交吗？","提示",{}).then(function(){o.a.start();(function(e){return Object(l.a)({url:"/admin/user/createfield/save",method:"post",data:e})})(i()({providerId:-1},e.infoForm)).then(function(t){o.a.done(),e.$message.success(t.data.msg),e.$refs.infoForm.resetFields(),e.addDialogVisible=!1,e.getList()})})})},handleDelete:function(e){var t=this;this.$confirm("确定刪除吗？").then(function(a){(function(e){return Object(l.a)({url:"/admin/user/createfield/remove/"+e,method:"get"})})(e).then(function(e){t.$message.success(e.data.msg),t.getList()}).catch(function(e){t.$message.error(e)})}).catch(function(e){})},getList:function(){var e=this;(function(e){return Object(l.a)({url:"/admin/user/createfield/list",method:"post",data:e})})({fieldName:this.query.fieldName,provider:this.query.provider,pageNumber:this.page.pageNumber,pageSize:this.page.pageSize}).then(function(t){e.tableData=t.data.page.list,e.page.total=t.data.page.totalRow}).catch(function(t){e.$message.error(t),console.log(t)})}}},c={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-form",{staticClass:"query-form",attrs:{inline:"",model:e.query,"label-position":"right","label-width":"70px"}},[a("el-input",{staticStyle:{width:"150px"},attrs:{placeholder:"请输入领域名称"},model:{value:e.query.fieldName,callback:function(t){e.$set(e.query,"fieldName",t)},expression:"query.fieldName"}}),e._v(" "),a("el-input",{staticStyle:{width:"150px"},attrs:{placeholder:"请输入提供者昵称"},model:{value:e.query.provider,callback:function(t){e.$set(e.query,"provider",t)},expression:"query.provider"}}),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.handleSearch}},[e._v("搜索")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.handleAdd}},[e._v("添加领域")])],1)],1),e._v(" "),a("el-table",{ref:"createFieldTable",attrs:{data:e.tableData,border:"",height:"580","header-cell-style":{"text-align":"center"},"cell-style":{"text-align":"center"},stripe:""}},[a("el-table-column",{attrs:{align:"center",type:"index",label:"序号",index:e.realIndex,width:"70"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"fieldName",label:"领域名称"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"provider",label:"提供者昵称"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",sortable:"",prop:"createDate",label:"创建时间",formatter:e.formatDate}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"status",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.statusName(t.row.status)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"230"},scopedSlots:e._u([{key:"default",fn:function(t){return[1!==t.row.status?a("el-button",{attrs:{size:"mini",type:"success",plain:""},on:{click:function(a){e.changeStatus(t.$index,t.row.id,1)}}},[e._v("通过")]):e._e(),e._v(" "),2!==t.row.status?a("el-button",{attrs:{size:"mini",type:"warning",plain:""},on:{click:function(a){e.changeStatus(t.$index,t.row.id,2)}}},[e._v("回绝")]):e._e(),e._v(" "),a("el-button",{attrs:{size:"mini",type:"danger",plain:""},on:{click:function(a){e.handleDelete(t.row.id)}}},[e._v("删除")])]}}])})],1),e._v(" "),a("el-pagination",{staticClass:"table__pagination",attrs:{background:"","current-page":e.page.pageNumber,"page-sizes":[10,20,30,40],"page-size":e.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.page.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),a("el-dialog",{attrs:{title:"新增",visible:e.addDialogVisible,width:"600px"},on:{"update:visible":function(t){e.addDialogVisible=t},close:function(t){e.closeDialog("addDialogVisible")}}},[a("el-form",{ref:"infoForm",staticStyle:{position:"relative"},attrs:{model:e.infoForm,rules:e.rules,"label-width":"120px"}},[a("el-form-item",{attrs:{prop:"fieldName",label:"创作领域名称："}},[a("el-input",{model:{value:e.infoForm.fieldName,callback:function(t){e.$set(e.infoForm,"fieldName",t)},expression:"infoForm.fieldName"}})],1),e._v(" "),a("div",{staticClass:"add__prompt"},[e._v("注意：以看否官方的身份提交无需审核，将直接展示在平台中")])],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.closeDialog("addDialogVisible")}}},[e._v("关闭")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmitSave}},[e._v("提交")])],1)],1)],1)},staticRenderFns:[]},u=a("VU/8")(s,c,!1,function(e){a("CwvD")},"data-v-3ad4eb5c",null);t.default=u.exports}});
//# sourceMappingURL=7.95db8f5cc03eaf7a7ccd.js.map