webpackJsonp([9],{"8jSr":function(n,e,t){(n.exports=t("FZ+f")(!0)).push([n.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n","",{version:3,sources:[],names:[],mappings:"",file:"AdvList.vue",sourceRoot:""}])},Jbpx:function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=t("woOf"),a=t.n(i),o=t("Y81h"),l=t.n(o),r=t("eOoE"),s={data:function(){return{query:{title:"",advName:""},addEditDialogVisible:!1,isEdit:!1,isShowImgPreview:!1,previewImageUrl:"",page:{pageNumber:1,pageSize:10,total:0},fieldList:[],cover:"",infoForm:{cover:""},rules:{title:[{required:!0,message:"请输入广告标题",trigger:"blur"}],cover:[{required:!0,message:"请上传封面",trigger:"change"}],schoolIdList:[{required:!0,message:"请选择展示领域",trigger:"change"}]},tableData:[]}},computed:{realIndex:function(){var n=this;return function(e){return(n.page.pageNumber-1)*n.page.pageSize+e+1}},statusName:function(){return function(n){return["草稿","展示中","已到期"][n]}},headers:function(){return{jxtAdminSessionId:this.$store.state.token}}},methods:{closeDialog:function(n){this[n]=!1,this.infoForm=[],this.$refs.infoForm&&this.$refs.infoForm.resetFields()},handleSizeChange:function(n){this.page.pageSize=n,this.getList()},handleCurrentChange:function(n){this.pageNumber=n,this.getList()},handleCoverCardPreview:function(n){this.previewImageUrl=n,this.isShowImgPreview=!0},formatDate:function(){return this.$moment(arguments.length<=2?void 0:arguments[2]).format("YYYY-MM-DD")},handleSearch:function(){this.getList()},handleAdd:function(){this.isEdit=!1,this.addEditDialogVisible=!0,this.cover="",this.infoForm=a()({},null)},handleEdit:function(n,e){this.cover="",this.isEdit=!0,this.infoForm=a()({},e),this.cover=this.infoForm.cover,this.addEditDialogVisible=!0},handlePictureCardPreview:function(n){this.previewImageUrl=n.url,this.isShowImgPreview=!0},handleCoverRemove:function(n,e){this.infoForm.cover=""},handleCoverSuccess:function(n,e){this.cover=n.file.url,this.infoForm.cover=n.file.url},beforeAvatarUpload:function(n){var e="image/jpeg"===n.type||"image/png",t=n.size/1024/1024<2;return e||this.$message.error("上传图片只能是 JPG或者 png 格式!"),t||this.$message.error("上传图片大小不能超过 2MB!"),e&&t},handleSubmitSave:function(){var n=this;this.$refs.infoForm.validate(function(e){e&&n.$confirm("确认提交吗？","提示",{}).then(function(){l.a.start();(function(n){return Object(r.a)({url:"/admin/info/adv/save",method:"post",data:n})})(a()({},n.infoForm)).then(function(e){l.a.done(),n.$message.success(e.data.msg),n.$refs.infoForm.resetFields(),n.addEditDialogVisible=!1,n.getList()})})})},handleSubmitUpdate:function(){var n=this;this.$refs.infoForm.validate(function(e){e&&n.$confirm("确认提交吗？","提示",{}).then(function(){l.a.start();(function(n){return Object(r.a)({url:"/admin/info/adv/update",method:"post",data:n})})(a()({},n.infoForm)).then(function(e){l.a.done(),n.$message.success(e.data.msg),n.$refs.infoForm.resetFields(),n.addEditDialogVisible=!1,n.getList()})})})},handleRemove:function(n,e){var t=this;this.$confirm("确认删除吗？").then(function(n){(function(n){return Object(r.a)({url:"/admin/info/adv/remove/"+n,method:"get"})})(e.id).then(function(n){t.$message.success(n.data.msg),t.getList()}).catch(function(n){t.$message.error(n)})}).catch(function(n){})},getList:function(){var n=this;(function(n){return Object(r.a)({url:"/admin/info/adv/list",method:"post",data:n})})({title:this.query.title,advName:this.query.advName,pageNumber:this.page.pageNumber,pageSize:this.page.pageSize}).then(function(e){n.tableData=e.data.page.list,n.page.total=e.data.page.totalRow}).catch(function(e){n.$message.error(e),console.log(e)})},reFindCreateFieldList:function(){var n=this;Object(r.a)({url:"/admin/user/project/findCreateFileldList",method:"get"}).then(function(e){n.fieldList=e.data,console.log("参数",n.fieldList)})}},mounted:function(){this.getList(),console.log("触发"),this.reFindCreateFieldList()}},c={render:function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",[t("el-form",{staticClass:"query-form",attrs:{inline:"",model:n.query,"label-position":"right","label-width":"70px"}},[t("el-input",{staticStyle:{width:"150px"},attrs:{placeholder:"请输入广告标题"},model:{value:n.query.title,callback:function(e){n.$set(n.query,"title",e)},expression:"query.title"}}),n._v(" "),t("el-input",{staticStyle:{width:"150px"},attrs:{placeholder:"请输入广告商名称"},model:{value:n.query.advName,callback:function(e){n.$set(n.query,"advName",e)},expression:"query.advName"}}),n._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:n.handleSearch}},[n._v("搜索")]),n._v(" "),t("el-button",{attrs:{type:"primary"},on:{click:n.handleAdd}},[n._v("添加广告")])],1)],1),n._v(" "),t("el-table",{staticClass:"table",attrs:{data:n.tableData,"header-cell-style":{"text-align":"center"},"cell-style":{"text-align":"center"},stripe:"",border:""}},[t("el-table-column",{attrs:{type:"index",label:"序号",index:n.realIndex,width:"70"}}),n._v(" "),t("el-table-column",{attrs:{prop:"cover",label:"封面图",width:"100"},scopedSlots:n._u([{key:"default",fn:function(e){return[t("img",{staticStyle:{width:"30px",height:"30px",cursor:"pointer"},attrs:{src:e.row.cover},on:{click:function(t){n.handleCoverCardPreview(e.row.cover)}}})]}}])}),n._v(" "),t("el-table-column",{attrs:{prop:"title",label:"广告标题"}}),n._v(" "),t("el-table-column",{attrs:{prop:"advName",label:"广告商"}}),n._v(" "),t("el-table-column",{attrs:{prop:"clickCount",label:"点击量"}}),n._v(" "),t("el-table-column",{attrs:{prop:"adminName",label:"发布人"}}),n._v(" "),t("el-table-column",{attrs:{prop:"fieldName",label:"展示领域"}}),n._v(" "),t("el-table-column",{attrs:{prop:"createDate",sortable:"",label:"创建时间",formatter:n.formatDate}}),n._v(" "),t("el-table-column",{attrs:{prop:"status",label:"状态"},scopedSlots:n._u([{key:"default",fn:function(e){return[t("span",[n._v(n._s(n.statusName(e.row.status)))])]}}])}),n._v(" "),t("el-table-column",{attrs:{label:"操作",width:"230"},scopedSlots:n._u([{key:"default",fn:function(e){return[t("el-button",{attrs:{size:"mini",type:"primary",plain:""},on:{click:function(t){n.handleEdit(e.$index,e.row)}}},[n._v("编辑")]),n._v(" "),t("el-button",{attrs:{size:"mini",type:"danger",plain:""},on:{click:function(t){n.handleRemove(e.$index,e.row)}}},[n._v("删除")])]}}])})],1),n._v(" "),t("el-pagination",{staticClass:"table__pagination",attrs:{background:"","current-page":n.page.pageNumber,"page-sizes":[10,20,30,40],"page-size":n.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:n.page.total},on:{"size-change":n.handleSizeChange,"current-change":n.handleCurrentChange}}),n._v(" "),t("el-dialog",{attrs:{top:"2vh",title:n.isEdit?"编辑":"新增",visible:n.addEditDialogVisible,width:"800px"},on:{"update:visible":function(e){n.addEditDialogVisible=e},close:function(e){n.closeDialog("addEditDialogVisible")}}},[t("el-form",{ref:"infoForm",attrs:{model:n.infoForm,rules:n.rules,"label-width":"120px"}},[t("el-form-item",{staticStyle:{display:"block"},attrs:{prop:"title",label:"标题："}},[t("el-input",{attrs:{placeholder:"请输入标题"},model:{value:n.infoForm.title,callback:function(e){n.$set(n.infoForm,"title",e)},expression:"infoForm.title"}})],1),n._v(" "),t("div",{staticStyle:{position:"relative"}},[t("el-form-item",{staticStyle:{display:"block"},attrs:{prop:"advName",label:"广告商："}},[t("el-input",{staticStyle:{width:"300px"},attrs:{placeholder:"请输入广告商名称"},model:{value:n.infoForm.advName,callback:function(e){n.$set(n.infoForm,"advName",e)},expression:"infoForm.advName"}})],1),n._v(" "),t("el-form-item",{staticStyle:{display:"block"},attrs:{prop:"createField",label:"展示领域："}},[t("el-select",{staticStyle:{width:"300px"},attrs:{filterable:"",placeholder:"请选择创作领域"},model:{value:n.infoForm.fieldId,callback:function(e){n.$set(n.infoForm,"fieldId",e)},expression:"infoForm.fieldId"}},n._l(n.fieldList,function(n){return t("el-option",{key:n.id,attrs:{label:n.fieldName,value:n.id}})}))],1),n._v(" "),t("el-form-item",{staticStyle:{display:"block"},attrs:{prop:"status",label:"状态："}},[t("el-select",{staticStyle:{width:"300px"},attrs:{placeholder:"请选择状态"},model:{value:n.infoForm.status,callback:function(e){n.$set(n.infoForm,"status",e)},expression:"infoForm.status"}},[t("el-option",{key:0,attrs:{label:"草稿",value:0}}),n._v(" "),t("el-option",{key:1,attrs:{label:"展示中",value:1}}),n._v(" "),t("el-option",{key:2,attrs:{label:"过期",value:2}})],1)],1),n._v(" "),t("el-form-item",{staticStyle:{position:"absolute",right:"30px",top:"0"},attrs:{prop:"cover",label:"封面："}},[t("el-upload",{staticClass:"img-uploader",staticStyle:{width:"165px",height:"165px"},attrs:{action:"/admin/upload",headers:n.headers,"show-file-list":!1,"on-success":n.handleCoverSuccess,"on-remove":n.handleCoverRemove,"before-upload":n.beforeAvatarUpload}},[n.cover?t("img",{staticClass:"img",staticStyle:{"max-width":"165px",height:"165px"},attrs:{src:n.cover}}):t("i",{staticClass:"el-icon-plus img-uploader-icon",staticStyle:{"max-width":"165px",height:"165px"}})])],1)],1),n._v(" "),t("el-form-item",{attrs:{prop:"url",label:"外链："}},[t("el-input",{staticStyle:{width:"615px"},model:{value:n.infoForm.url,callback:function(e){n.$set(n.infoForm,"url",e)},expression:"infoForm.url"}})],1)],1),n._v(" "),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(e){n.closeDialog("addEditDialogVisible")}}},[n._v("关闭")]),n._v(" "),n.isEdit?t("el-button",{attrs:{type:"primary"},on:{click:n.handleSubmitUpdate}},[n._v("更新")]):t("el-button",{attrs:{type:"primary"},on:{click:n.handleSubmitSave}},[n._v("提交")])],1)],1),n._v(" "),t("div",{directives:[{name:"show",rawName:"v-show",value:n.isShowImgPreview,expression:"isShowImgPreview"}],staticClass:"pircture__preview",on:{click:function(e){n.isShowImgPreview=!1}}},[t("img",{staticClass:"preview__img",attrs:{src:n.previewImageUrl,alt:""}})])],1)},staticRenderFns:[]},d=t("VU/8")(s,c,!1,function(n){t("s8ez")},null,null);e.default=d.exports},s8ez:function(n,e,t){var i=t("8jSr");"string"==typeof i&&(i=[[n.i,i,""]]),i.locals&&(n.exports=i.locals);t("rjj0")("14cab8db",i,!0)}});
//# sourceMappingURL=9.956ef3f0ba0755f44f92.js.map