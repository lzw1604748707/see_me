webpackJsonp([2],{"0CA1":function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=t("eOoE");var l={data:function(){return{query:{feebackType:""},loading:!1,page:{pageNumber:1,pageSize:10,total:""},feebackTypeList:[{value:1,name:"優化建議"},{value:2,name:"平台問題"},{value:3,name:"合作意向"},{value:4,name:"其他"}],tableData:[]}},created:function(){},mounted:function(){this.getList()},watch:{},methods:{handleSizeChange:function(n){this.page.pageSize=n,this.getList()},handleCurrentChange:function(n){this.pageNumber=n,this.getList()},handleSearch:function(){this.getList()},getList:function(){var n=this,e={feebackType:this.query.feebackType,pageNumber:this.page.pageNumber,pageSize:this.page.pageSize,schoolId:this.selectSchoolId};this.loading=!0,function(n){return Object(a.a)({url:"/admin/feeback/list",method:"post",data:n})}(e).then(function(e){n.tableData=e.data.page.list,n.page.pageNumber=e.data.page.pageNumber,n.page.pageSize=e.data.page.pageSize,n.page.total=e.data.page.totalRow,n.loading=!1}).catch(function(e){n.loading=!1,n.$message.error(e),console.log(e)})},handleRemove:function(n,e){var t=this;this.$confirm("確定刪除嗎？").then(function(n){(function(n){return Object(a.a)({url:"/admin/feeback/remove/"+n,method:"get"})})(e.id).then(function(n){t.$message.success(n.data.msg),t.getList()}).catch(function(n){t.$message.error(n)})}).catch(function(n){})}}},o={render:function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",[t("el-form",{staticClass:"query-form",attrs:{inline:"",model:n.query,"label-position":"right","label-width":"60px"}},[t("el-select",{staticStyle:{"margin-left":"10px"},attrs:{placeholder:"請選擇學校"},model:{value:n.query.feebackType,callback:function(e){n.$set(n.query,"feebackType",e)},expression:"query.feebackType"}},[t("el-option",{key:"0",attrs:{label:"請選擇反饋類型",value:""}}),n._v(" "),n._l(n.feebackTypeList,function(n){return t("el-option",{key:n.value,attrs:{label:n.name,value:n.value}})})],2),n._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:n.handleSearch}},[n._v("搜索")])],1)],1),n._v(" "),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:n.loading,expression:"loading"}],staticClass:"table",attrs:{data:n.tableData,stripe:"",border:""}},[t("el-table-column",{attrs:{type:"index",label:"序號",width:"70"}}),n._v(" "),t("el-table-column",{attrs:{prop:"account",label:"反饋賬號"}}),n._v(" "),t("el-table-column",{attrs:{prop:"accountName",label:"反饋人"}}),n._v(" "),t("el-table-column",{attrs:{prop:"accountType",label:"身份"},scopedSlots:n._u([{key:"default",fn:function(e){return[1===e.row.accountType?t("span",[n._v("學校")]):2===e.row.accountType?t("span",[n._v("教師")]):t("span",[n._v("家長")])]}}])}),n._v(" "),t("el-table-column",{attrs:{prop:"remark",label:"意見"}}),n._v(" "),t("el-table-column",{attrs:{prop:"type",label:"類型"},scopedSlots:n._u([{key:"default",fn:function(e){return[1===e.row.accountType?t("span",[n._v("優化建議")]):2===e.row.accountType?t("span",[n._v("平台問題")]):3===e.row.accountType?t("span",[n._v("合作意向")]):t("span",[n._v("其他")])]}}])}),n._v(" "),t("el-table-column",{attrs:{prop:"createAt",label:"時間"}}),n._v(" "),t("el-table-column",{attrs:{label:"操作",width:"100"},scopedSlots:n._u([{key:"default",fn:function(e){return[t("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(t){n.handleRemove(e.$index,e.row)}}},[n._v("删除")])]}}])})],1),n._v(" "),t("el-pagination",{attrs:{"current-page":n.page.pageNumber,"page-sizes":[10,20,30,40],"page-size":n.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:n.page.total},on:{"size-change":n.handleSizeChange,"current-change":n.handleCurrentChange}})],1)},staticRenderFns:[]};var r=t("Z0/y")(l,o,!1,function(n){t("OHFu")},null,null);e.default=r.exports},OHFu:function(n,e,t){var a=t("VCtX");"string"==typeof a&&(a=[[n.i,a,""]]),a.locals&&(n.exports=a.locals);t("FIqI")("fe116234",a,!0,{})},VCtX:function(n,e,t){(n.exports=t("XLS9")(!0)).push([n.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n","",{version:3,sources:[],names:[],mappings:"",file:"FeebackList.vue",sourceRoot:""}])}});
//# sourceMappingURL=2.be652d569da8b60dc9ae.js.map