webpackJsonp([10],{"/VOE":function(n,e,t){(n.exports=t("FZ+f")(!0)).push([n.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n","",{version:3,sources:[],names:[],mappings:"",file:"RoleList.vue",sourceRoot:""}])},XoIB:function(n,e,t){var i=t("/VOE");"string"==typeof i&&(i=[[n.i,i,""]]),i.locals&&(n.exports=i.locals);t("rjj0")("484572ea",i,!0)},y0F4:function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=t("woOf"),a=t.n(i),o=t("eOoE"),s=t("Y81h"),r=t.n(s),l={data:function(){return{loading:!1,addEditDialogVisible:!1,isEdit:!1,menus:[],infoForm:{id:"",name:"",menus:[]},defaultProps:{children:"children",label:"label"},page:{pageNumber:1,pageSize:10,total:0},tableData:[],rules:{name:[{required:!0,message:"请输入名称",trigger:"blur"}],menus:[{required:!0,message:"请选择",trigger:"blur"}]}}},computed:{realIndex:function(){var n=this;return function(e){return(n.page.pageNumber-1)*n.page.pageSize+e+1}}},mounted:function(){this.getList(),this.getMenus()},watch:{},methods:{closeDialog:function(n){this[n]=!1,this.infoForm=[],this.$refs.tree.setCheckedKeys([]),this.$refs.infoForm&&this.$refs.infoForm.resetFields()},handleSizeChange:function(n){this.page.pageSize=n,this.getList()},handleCurrentChange:function(n){this.pageNumber=n,this.getList()},handleAdd:function(){this.isEdit=!1,this.addEditDialogVisible=!0,this.infoForm={}},handleEdit:function(n,e){var t=this;this.isEdit=!0,this.addEditDialogVisible=!0,this.infoForm=a()({},e),setTimeout(function(){t.$refs.tree.setCheckedKeys(e.menus)},100)},getList:function(){var n=this;this.loading=!0,function(n){return Object(o.a)({url:"/admin/auth/role/list",method:"post",data:n})}().then(function(e){n.tableData=e.data.list,n.loading=!1}).catch(function(e){n.loading=!1,n.$message.error(e),console.log(e)})},getMenus:function(){var n=this;Object(o.a)({url:"/admin/auth/role/getMenuList",method:"get"}).then(function(e){n.menus=e.data.list}).catch(function(e){n.$message.error(e),console.log(e)})},handleSubmitSave:function(){var n=this;this.infoForm.menus=this.$refs.tree.getCheckedKeys(),this.$refs.infoForm.validate(function(e){e&&n.$confirm("确认提交吗？","提示",{}).then(function(){r.a.start();(function(n){return Object(o.a)({url:"/admin/auth/role/save",method:"post",data:n})})(a()({},n.infoForm)).then(function(e){r.a.done(),n.$message.success(e.data.msg),n.$refs.infoForm.resetFields(),n.addEditDialogVisible=!1,n.getList()})})})},handleSubmitUpdate:function(){var n=this;this.infoForm.menus=this.$refs.tree.getCheckedKeys(),this.$refs.infoForm.validate(function(e){e&&n.$confirm("确认提交吗？","提示",{}).then(function(){r.a.start();(function(n){return Object(o.a)({url:"/admin/auth/role/update",method:"post",data:n})})(a()({},n.infoForm)).then(function(e){r.a.done(),n.$message.success(e.data.msg),n.$refs.infoForm.resetFields(),n.addEditDialogVisible=!1,n.getList()})})})},handleRemove:function(n,e){var t=this;this.$confirm("确认删除吗？").then(function(n){(function(n){return Object(o.a)({url:"/admin/auth/role/remove/"+n,method:"get"})})(e.id).then(function(n){t.$message.success(n.data.msg),t.getList()}).catch(function(n){t.$message.error(n)})}).catch(function(n){})}}},d={render:function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",[t("el-form",{staticClass:"query-form",attrs:{inline:"","label-position":"right","label-width":"60px"}},[t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:n.handleAdd}},[n._v("添加权限组")])],1)],1),n._v(" "),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:n.loading,expression:"loading"}],staticClass:"table",attrs:{data:n.tableData,stripe:"",border:""}},[t("el-table-column",{attrs:{type:"index",index:n.realIndex,label:"序号",width:"70"}}),n._v(" "),t("el-table-column",{attrs:{prop:"name",label:"权限组"}}),n._v(" "),t("el-table-column",{attrs:{prop:"roleAccount",label:"管理员数量"}}),n._v(" "),t("el-table-column",{attrs:{prop:"createAt",label:"添加时间"}}),n._v(" "),t("el-table-column",{attrs:{label:"操作"},scopedSlots:n._u([{key:"default",fn:function(e){return[t("el-button",{attrs:{size:"mini",type:"primary",plain:""},on:{click:function(t){n.handleEdit(e.$index,e.row)}}},[n._v("编辑")]),n._v(" "),t("el-button",{attrs:{size:"mini",type:"danger",plain:""},on:{click:function(t){n.handleRemove(e.$index,e.row)}}},[n._v("删除")])]}}])})],1),n._v(" "),t("el-dialog",{attrs:{title:"新增/编辑",visible:n.addEditDialogVisible,width:"800px"},on:{"update:visible":function(e){n.addEditDialogVisible=e},close:function(e){n.closeDialog("addEditDialogVisible")}}},[t("el-form",{ref:"infoForm",attrs:{model:n.infoForm,rules:n.rules,"label-width":"120px"}},[t("el-form-item",{attrs:{prop:"name",label:"权限组名称："}},[t("el-input",{model:{value:n.infoForm.name,callback:function(e){n.$set(n.infoForm,"name",e)},expression:"infoForm.name"}})],1),n._v(" "),t("el-form-item",{attrs:{prop:"menus",label:"权限："}},[t("el-tree",{ref:"tree",attrs:{data:n.menus,"show-checkbox":"","expand-on-click-node":"","node-key":"id","default-expanded-keys":n.infoForm.menus,"default-checked-keys":n.infoForm.menus,props:n.defaultProps}})],1)],1),n._v(" "),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(e){n.closeDialog("addEditDialogVisible")}}},[n._v("关闭")]),n._v(" "),n.isEdit?t("el-button",{attrs:{type:"primary"},on:{click:n.handleSubmitUpdate}},[n._v("提交")]):t("el-button",{attrs:{type:"primary"},on:{click:n.handleSubmitSave}},[n._v("确定")])],1)],1)],1)},staticRenderFns:[]},u=t("VU/8")(l,d,!1,function(n){t("XoIB")},null,null);e.default=u.exports}});
//# sourceMappingURL=10.1efe1c85ed6fcd136a8f.js.map