webpackJsonp([6],{"6VkS":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=a("woOf"),o=a.n(i),s=a("Y81h"),n=a.n(s),r=a("eOoE"),l={data:function(){return{query:{name:""},infoDialogVisible:!1,addDialogVisible:!1,editDialogVisible:!1,isShowImgPreview:!1,previewImageUrl:"",isEdit:!1,page:{pageNumber:1,pageSize:10,total:0},avatar:"",infoForm:{},rules:{name:[{required:!0,message:"请输入用户昵称",trigger:"blur"}],area:[{required:!0,message:"请输入所在地",trigger:"blur"}],email:[{required:!0,message:"请输入电子邮箱",trigger:"blur"}],moblie:[{required:!0,message:"请输入手机号",trigger:"blur"},{min:6,max:11,message:"长度在 6 到 11 个字符",trigger:"blur"}],account:[{required:!0,message:"请输入登陆账户",trigger:"blur"}]},tableData:[]}},created:function(){},mounted:function(){this.getList()},methods:{handleCoverCardPreview:function(e){this.previewImageUrl=e,this.isShowImgPreview=!0},getStatusName:function(e){return["正常","停用","冻结"][e]},handleCoverSuccess:function(e,t){this.avatar=e.file.url,this.infoForm.avatar=e.file.url},handleCoverRemove:function(e,t){this.infoForm.avatar=""},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type||"image/png",a=e.size/1024/1024<2;return t||this.$message.error("上传图片只能是 JPG或者 png 格式!"),a||this.$message.error("上传图片大小不能超过 2MB!"),t&&a},handleSizeChange:function(e){this.page.pageSize=e,this.getList()},handleCurrentChange:function(e){this.pageNumber=e,this.getList()},formatDateJoinAt:function(e,t){return this.$moment(e.joinAt).format("YYYY-MM-DD")},formatDateNextPayAt:function(e,t){return this.$moment(e.nextPayAt).format("YYYY-MM-DD")},handleRead:function(e,t){this.infoDialogVisible=!0,this.infoForm=o()({},t)},handleEdit:function(e,t){this.editDialogVisible=!0,this.infoForm=o()({},t)},handleAdd:function(){this.addDialogVisible=!0,this.infoForm=o()({},null)},ChangeStatus:function(e){var t=this;this.$confirm("确认提交吗？","提示",{}).then(function(){n.a.start(),2===e.status?e.status=1:e.status=2;(function(e){return Object(r.a)({url:"/admin/user/user/changeFreeze",data:e,method:"post"})})({id:e.id,status:e.status}).then(function(e){t.$message.success(e.data.msg),t.getList()})})},handleSearch:function(){this.getList()},handleSubmitSave:function(){var e=this;this.$refs.infoForm.validate(function(t){t&&e.$confirm("确认提交吗？","提示",{}).then(function(){n.a.start();(function(e){return Object(r.a)({url:"/admin/user/user/save",method:"post",data:e})})(o()({},e.infoForm)).then(function(t){n.a.done(),e.$message.success(t.data.msg),e.$refs.infoForm.resetFields(),e.addDialogVisible=!1,e.getList()})})})},handleSubmitUpdate:function(){var e=this;this.$refs.infoForm.validate(function(t){t&&e.$confirm("确认提交吗？","提示",{}).then(function(){n.a.start();var t=o()({},e.infoForm);console.log(t),function(e){return Object(r.a)({url:"/admin/user/user/update",method:"post",data:e})}(t).then(function(t){n.a.done(),e.$message.success(t.data.msg),e.$refs.infoForm.resetFields(),e.editDialogVisible=!1,e.getList()})})})},handleRemove:function(e,t){var a=this;this.$confirm("确定刪除吗？").then(function(e){(function(e){return Object(r.a)({url:"/admin/user/user/remove/"+e,method:"get"})})(t.id).then(function(e){a.$message.success(e.data.msg),a.getList()}).catch(function(e){a.$message.error(e)})}).catch(function(e){})},handleChangeStop:function(){var e=this,t=1===this.infoForm.status?"启用":"停用";this.$confirm("确定"+t+"吗？").then(function(t){e.infoForm.status=1===e.infoForm.status?0:1;(function(e){return Object(r.a)({url:"/admin/user/user/changeStop",data:e,method:"post"})})({id:e.infoForm.id,status:e.infoForm.status}).then(function(t){e.getList(),e.$message.success(t.data.msg)}).catch(function(t){e.$message.error(t)})}).catch(function(e){})},getList:function(){var e=this;(function(e){return Object(r.a)({url:"/admin/user/user/list",method:"post",data:e})})({name:this.query.name,pageNumber:this.page.pageNumber,pageSize:this.page.pageSize}).then(function(t){e.tableData=t.data.page.list,e.page.pageNumber*e.page.pageSize<t.data.page.totalRow&&e.page.pageNumber++,e.page.total=t.data.page.totalRow}).catch(function(t){e.$message.error(t),console.log(t)})},reFindFollowerslist:function(){var e=this;this.page.pageNumber=1,this.page.pageSize=10,this.query.name="";(function(e){return Object(r.a)({url:"/admin/user/user/followerslist",data:e,method:"post"})})({id:this.infoForm.id,name:this.query.name,pageNumber:this.page.pageNumber,pageSize:this.page.pageSize}).then(function(t){e.tableData=t.data.page.list,e.page.pageNumber*e.page.pageSize<t.data.page.totalRow&&e.page.pageNumber++,e.infoDialogVisible=!1,e.page.total=t.data.page.totalRow}).catch(function(t){e.$message.error(t),console.log(t)})},reFindFollowingsList:function(){var e=this;this.page.pageNumber=1,this.page.pageSize=10,this.query.name="";(function(e){return Object(r.a)({url:"/admin/user/user/followingslist",data:e,method:"post"})})({id:this.infoForm.id,name:this.query.name,pageNumber:this.page.pageNumber,pageSize:this.page.pageSize}).then(function(t){e.tableData=t.data.page.list,e.page.pageNumber*e.page.pageSize<t.data.page.totalRow&&e.page.pageNumber++,e.infoDialogVisible=!1,e.page.total=t.data.page.totalRow}).catch(function(t){e.$message.error(t),console.log(t)})}}},m={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-form",{staticClass:"query-form",attrs:{inline:"",model:e.query,"label-position":"right","label-width":"70px"}},[a("el-input",{staticStyle:{width:"150px"},attrs:{placeholder:"请输入用户名称"},model:{value:e.query.name,callback:function(t){e.$set(e.query,"name",t)},expression:"query.name"}}),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.handleSearch}},[e._v("搜索")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.handleAdd}},[e._v("添加用户")])],1)],1),e._v(" "),a("el-table",{staticClass:"table",attrs:{data:e.tableData,stripe:"",border:""}},[a("el-table-column",{attrs:{align:"center",type:"index",label:"序号",width:"70"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"avatar",label:"头像",width:"70"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.avatar?a("img",{staticStyle:{width:"40px",height:"40px",cursor:"pointer"},attrs:{src:t.row.avatar},on:{click:function(a){e.handleCoverCardPreview(t.row.avatar)}}}):a("img",{staticStyle:{width:"40px",height:"40px",cursor:"pointer"},attrs:{src:"/static/img/system.jpg"},on:{click:function(a){e.handleCoverCardPreview(t.row.avatar)}}})]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"name",label:"用户昵称"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"account",label:"账号"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"userType",label:"用户类型"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"status",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.getStatusName(t.row.status)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"lastLoginDate",label:"上次登录时间",sortable:"",width:"140",formatter:e.formatDateJoinAt}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"nextPayAt",label:"冻结时间",width:"120",formatter:e.formatDateNextPayAt}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"230"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"info"},on:{click:function(a){e.handleRead(t.$index,t.row)}}},[e._v("查看")]),e._v(" "),a("el-button",{attrs:{size:"mini"},on:{click:function(a){e.ChangeStatus(t.row)}}},[e._v(e._s(2===t.row.status?"解冻":"冻结"))]),e._v(" "),a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){e.handleEdit(t.$index,t.row)}}},[e._v("编辑")])]}}])})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.page.pageNumber,"page-sizes":[10,20,30,40],"page-size":e.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.page.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),a("el-dialog",{attrs:{title:"查看",visible:e.infoDialogVisible,width:"800px"},on:{"update:visible":function(t){e.infoDialogVisible=t}}},[a("el-form",{staticStyle:{position:"relative"},attrs:{"label-width":"120px"}},[a("div",{staticClass:"user__avatar"},[a("div",{staticClass:"user__avatar--img"},[e.infoForm.avatar?a("img",{staticStyle:{width:"150px",height:"150px"},attrs:{src:e.infoForm.avatar}}):a("img",{staticStyle:{width:"150px",height:"150px","vertical-align":"bottom"},attrs:{src:"/static/img/system.jpg",alt:""}})]),e._v(" "),a("span",[e._v("用户头像")])]),e._v(" "),a("el-form-item",{staticClass:"\n          school-item",attrs:{label:"用户昵称："}},[a("span",{staticClass:"school-name"},[e._v(e._s(e.infoForm.name))])]),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"账号："}},[a("span",[e._v(e._s(e.infoForm.account))])]),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"性别："}},[a("span",[e._v(e._s(e.infoForm.sex))])]),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"职业："}},[a("span",[e._v(e._s(e.infoForm.career))])]),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"地区："}},[a("span",[e._v(e._s(e.infoForm.area))])]),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"用户类型："}},[a("span",[e._v(e._s(e.infoForm.userType))])]),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"手机号码："}},[a("span",[e._v(e._s(e.infoForm.mobile))])]),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"关注者数量："}},[a("span",[e._v(e._s(e.infoForm.followers))]),e._v(" "),a("span",{staticStyle:{"margin-left":"40px"}},[a("el-button",{attrs:{type:"primary",size:"mini",round:""},on:{click:e.reFindFollowerslist}},[e._v("关注者列表")])],1)]),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"正在关注数量："}},[a("span",[e._v(e._s(e.infoForm.followings))]),e._v(" "),a("span",{staticStyle:{"margin-left":"40px"}},[a("el-button",{attrs:{type:"primary",size:"mini",round:""},on:{click:e.reFindFollowingsList}},[e._v("正在关注列表")])],1)]),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"状态："}},[a("span",[e._v(e._s(e.getStatusName(e.infoForm.status)))])]),e._v(" "),0!==e.infoForm.status?a("el-form-item",{staticClass:"school-item",attrs:{label:"冻结时间:"}},[a("span",[e._v(e._s(e.infoForm.freezeDate))])]):e._e(),e._v(" "),0!==e.infoForm.status?a("el-form-item",{staticClass:"school-item",attrs:{label:"冻结原因："}},[a("span",[e._v(e._s(e.infoForm.freezeCause))])]):e._e(),e._v(" "),a("el-form-item",{staticClass:"school-item",attrs:{label:"入驻时间："}},[a("span",[e._v(e._s(e.infoForm.createDate))])]),e._v(" "),a("el-form-item",[1!==e.infoForm.status?a("el-button",{attrs:{type:"primary"},on:{click:e.handleChangeStop}},[e._v("停用用户")]):a("el-button",{attrs:{type:"success"},on:{click:e.handleChangeStop}},[e._v("启用用户")]),e._v(" "),a("el-button",{attrs:{type:"danger"},on:{click:e.handleRemove}},[e._v("删除用户")])],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.infoDialogVisible=!1}}},[e._v("关闭")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"编辑",visible:e.editDialogVisible,width:"800px"},on:{"update:visible":function(t){e.editDialogVisible=t}}},[a("el-form",{ref:"infoForm",staticStyle:{position:"relative"},attrs:{inline:"true",model:e.infoForm,rules:e.rules,"label-width":"120px"}},[a("el-form-item",{staticClass:"user__avatar",attrs:{prop:"cover",label:"头像："}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"/admin/upload",headers:e.headers,"show-file-list":!1,"on-success":e.handleCoverSuccess,"on-remove":e.handleCoverRemove,"with-credentials":"true","before-upload":e.beforeAvatarUpload}},[e.avatar?a("img",{staticClass:"avatar",attrs:{src:e.avatar}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),e._v(" "),a("el-form-item",{staticStyle:{display:"block"},attrs:{prop:"name",label:"用户昵称："}},[a("el-input",{model:{value:e.infoForm.name,callback:function(t){e.$set(e.infoForm,"name",t)},expression:"infoForm.name"}})],1),e._v(" "),a("el-form-item",{staticStyle:{display:"block"},attrs:{prop:"account",label:"登陆账号："}},[a("span",[e._v(e._s(e.infoForm.account))])]),e._v(" "),a("el-form-item",{staticStyle:{display:"block"},attrs:{prop:"password",label:"密码："}},[a("el-input",{model:{value:e.infoForm.password,callback:function(t){e.$set(e.infoForm,"password",t)},expression:"infoForm.password"}})],1),e._v(" "),a("el-form-item",{attrs:{prop:"career",label:"职业："}},[a("el-input",{model:{value:e.infoForm.career,callback:function(t){e.$set(e.infoForm,"career",t)},expression:"infoForm.career"}})],1),e._v(" "),a("el-form-item",{attrs:{prop:"sex",label:"性别："}},[a("el-radio-group",{model:{value:e.infoForm.sex,callback:function(t){e.$set(e.infoForm,"sex",t)},expression:"infoForm.sex"}},[a("el-radio",{attrs:{label:"男"}}),e._v(" "),a("el-radio",{attrs:{label:"女"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{prop:"area",label:"地区："}},[a("el-input",{model:{value:e.infoForm.area,callback:function(t){e.$set(e.infoForm,"area",t)},expression:"infoForm.area"}})],1),e._v(" "),a("el-form-item",{attrs:{prop:"mobile",label:"手机号码："}},[a("el-input",{model:{value:e.infoForm.mobile,callback:function(t){e.$set(e.infoForm,"mobile",t)},expression:"infoForm.mobile"}})],1),e._v(" "),a("el-form-item",{attrs:{prop:"email",label:"电子邮箱："}},[a("el-input",{model:{value:e.infoForm.email,callback:function(t){e.$set(e.infoForm,"email",t)},expression:"infoForm.email"}})],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.editDialogVisible=!1}}},[e._v("关闭")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmitUpdate}},[e._v("提交")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"新增",visible:e.addDialogVisible,width:"500px"},on:{"update:visible":function(t){e.addDialogVisible=t}}},[a("el-form",{ref:"infoForm",attrs:{model:e.infoForm,rules:e.rules,"label-width":"120px"}},[a("el-form-item",{attrs:{prop:"name",label:"用户昵称："}},[a("el-input",{model:{value:e.infoForm.name,callback:function(t){e.$set(e.infoForm,"name",t)},expression:"infoForm.name"}})],1),e._v(" "),a("el-form-item",{attrs:{prop:"account",label:"登陆账号："}},[a("el-input",{model:{value:e.infoForm.account,callback:function(t){e.$set(e.infoForm,"account",t)},expression:"infoForm.account"}})],1),e._v(" "),a("el-form-item",{attrs:{prop:"password",label:"密码："}},[a("el-input",{model:{value:e.infoForm.password,callback:function(t){e.$set(e.infoForm,"password",t)},expression:"infoForm.password"}})],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.addDialogVisible=!1}}},[e._v("关闭")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmitSave}},[e._v("确定")])],1)],1),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:e.isShowImgPreview,expression:"isShowImgPreview"}],staticClass:"pircture__preview",on:{click:function(t){e.isShowImgPreview=!1}}},[a("img",{staticClass:"preview__img",attrs:{src:e.previewImageUrl||"/static/img/system.jpg",alt:""}})])],1)},staticRenderFns:[]},c=a("VU/8")(l,m,!1,function(e){a("mu8e")},"data-v-7e223bd8",null);t.default=c.exports},"j/JH":function(e,t,a){(e.exports=a("FZ+f")(!0)).push([e.i,"\n.school-item[data-v-7e223bd8] {\n  margin-bottom: 1px;\n}\n.school-name[data-v-7e223bd8] {\n  font-weight: 700;\n}\n.user__avatar[data-v-7e223bd8] {\n  position: absolute;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n  right: 115px;\n  top: 0;\n  text-align: center;\n}\n.user__avatar--img[data-v-7e223bd8] {\n    border-radius: 15px;\n    overflow: hidden;\n    -webkit-box-shadow: 0 0 5px #999;\n            box-shadow: 0 0 5px #999;\n    margin-bottom: 5px;\n}\n","",{version:3,sources:["E:/JavaWork/see_me/see-me-backend/src/views/user/AccountList.vue"],names:[],mappings:";AACA;EACE,mBAAmB;CACpB;AACD;EACE,iBAAiB;CAClB;AACD;EACE,mBAAmB;EACnB,0BAA0B;MACtB,uBAAuB;UACnB,oBAAoB;EAC5B,aAAa;EACb,OAAO;EACP,mBAAmB;CACpB;AACD;IACI,oBAAoB;IACpB,iBAAiB;IACjB,iCAAiC;YACzB,yBAAyB;IACjC,mBAAmB;CACtB",file:"AccountList.vue",sourcesContent:["\n.school-item[data-v-7e223bd8] {\n  margin-bottom: 1px;\n}\n.school-name[data-v-7e223bd8] {\n  font-weight: 700;\n}\n.user__avatar[data-v-7e223bd8] {\n  position: absolute;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n  right: 115px;\n  top: 0;\n  text-align: center;\n}\n.user__avatar--img[data-v-7e223bd8] {\n    border-radius: 15px;\n    overflow: hidden;\n    -webkit-box-shadow: 0 0 5px #999;\n            box-shadow: 0 0 5px #999;\n    margin-bottom: 5px;\n}\n"],sourceRoot:""}])},mu8e:function(e,t,a){var i=a("j/JH");"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);a("rjj0")("390589e2",i,!0)}});
//# sourceMappingURL=6.7975c239b43f858f8903.js.map