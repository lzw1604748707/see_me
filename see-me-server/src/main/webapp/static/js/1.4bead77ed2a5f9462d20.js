webpackJsonp([1],{IBO3:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=i("woOf"),n=i.n(a),r=i("Y81h"),o=i.n(r),s=i("q4js"),l=i("eOoE"),c={data:function(){return{query:{title:"",author:""},addEditDialogVisible:!1,isShowImgPreview:!1,previewImageUrl:"",isEdit:!1,page:{pageNumber:1,pageSize:10,total:1},fieldList:[],cover:"",linkImageList:[],extImageList:[],infoForm:{},rules:{title:[{required:!0,message:"请输入项目标题",trigger:"blur"}],cover:[{required:!0,message:"请上传封面",trigger:"change"}]},tableData:[]}},created:function(){},mounted:function(){this.getList()},computed:{realIndex:function(){var e=this;return function(t){return(e.page.pageNumber-1)*e.page.pageSize+t+1}},headers:function(){return{jxtAdminSessionId:this.$store.state.token}}},methods:{closeDialog:function(e){this[e]=!1,this.infoForm=[],this.cover="",this.extImageList=[],this.$refs.infoForm&&this.$refs.infoForm.resetFields(),console.log(this.$refs),this.$refs.upload.clearFiles()},handleCoverCardPreview:function(e){this.previewImageUrl=e,this.isShowImgPreview=!0},handleSizeChange:function(e){this.page.pageSize=e,this.page.pageNumber=1,this.getList()},handleCurrentChange:function(e){console.log("当前页",e),this.page.pageNumber=e,this.getList()},handleSearch:function(){this.getList()},handleAdd:function(){this.isEdit=!1,this.cover="",this.linkImageList=[],this.infoForm=n()({},null),this.addEditDialogVisible=!0,this.reFindCreateFieldList()},handleEdit:function(e,t){this.isEdit=!0,this.linkImageList=[],this.reFindCreateFieldList(),this.reFindDetailByid(t.id)},reFindDetailByid:function(e){var t=this;(function(e){return Object(l.a)({url:"/admin/user/project/findById",method:"post",data:e})})({id:e}).then(function(e){t.infoForm=e.data,t.cover=t.infoForm.cover;var i=[];t.infoForm.imagesPath&&t.infoForm.imagesPath.split(",").forEach(function(e){i.push({url:e}),t.linkImageList.push({url:e})}),t.extImageList=i,t.addEditDialogVisible=!0})},handlePictureCardPreview:function(e){this.previewImageUrl=e.url,this.isShowImgPreview=!0},handleExtCoverSuccess:function(e){this.linkImageList.push(e.file)},handleExtCoverRemove:function(e){var t=0;e.response?(t=this.linkImageList.findIndex(function(t){return t.name===e.response.file.name}),this.linkImageList.splice(t,1)):(t=this.linkImageList.findIndex(function(t){return t.uid===e.uid}),this.linkImageList.splice(t,1))},handleCoverRemove:function(e,t){this.infoForm.cover=""},handleCoverSuccess:function(e,t){this.cover=e.file.url,this.infoForm.cover=e.file.url},handleDownShelf:function(e,t){var i=this,a=1===t.status?0:1;(function(e){return Object(l.a)({url:"/admin/user/project/changeStatus",data:e,method:"post"})})({id:t.id,status:a}).then(function(t){i.$message.success(t.data.msg),i.$refs.projectTable.data[e].status=a})},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type||"image/png",i=e.size/1024/1024<2;return t||this.$message.error("上传图片只能是 JPG或者 png 格式!"),i||this.$message.error("上传图片大小不能超过 2MB!"),t&&i},handleImageAdded:function(e,t,i){Object(s.a)(e,"/admin/upload").then(function(e){var a=e.data.file.url;-1===a.indexOf("http")&&(a="http://127.0.0.1:80"+a),t.insertEmbed(i,"image",a)}).catch(function(e){console.log(e)})},handleSubmitSave:function(){var e=this;this.$refs.infoForm.validate(function(t){t&&e.$confirm("确认提交吗？","提示",{}).then(function(){o.a.start(),e.addEditDialogVisible=!1,e.infoForm.createrId=-1,e.infoForm.imagesPath=e.linkImageList.map(function(e){return e.url}).join(",");(function(e){return Object(l.a)({url:"/admin/user/project/save",method:"post",data:e})})(n()({},e.infoForm)).then(function(t){o.a.done(),e.$message.success(t.data.msg),e.$refs.infoForm.resetFields(),e.getList()})})})},handleSubmitUpdate:function(){var e=this;this.$refs.infoForm.validate(function(t){t&&e.$confirm("确认提交吗？","提示",{}).then(function(){o.a.start(),e.addEditDialogVisible=!1,e.infoForm.imagesPath=e.linkImageList.map(function(e){return e.url}).join(",");(function(e){return Object(l.a)({url:"/admin/user/project/update",method:"post",data:e})})(n()({},e.infoForm)).then(function(t){o.a.done(),e.$message.success(t.data.msg),e.$refs.infoForm.resetFields(),e.getList()})})})},handleRemove:function(e,t){var i=this;this.$confirm("确定删除吗？").then(function(e){(function(e){return Object(l.a)({url:"/admin/user/project/remove/"+e,method:"get"})})(t.id).then(function(e){i.$message.success(e.data.msg),i.getList()}).catch(function(e){i.$message.error(e)})}).catch(function(e){})},getList:function(){var e=this;(function(e){return Object(l.a)({url:"/admin/user/project/list",method:"post",data:e})})({title:this.query.title,author:this.query.author,pageNumber:this.page.pageNumber,pageSize:this.page.pageSize}).then(function(t){e.tableData=t.data.page.list,e.page.total=t.data.page.totalRow}).catch(function(t){e.$message.error(t),console.log(t)})},reFindCreateFieldList:function(){var e=this;Object(l.a)({url:"/admin/user/project/findCreateFileldList",method:"get"}).then(function(t){e.fieldList=t.data})}}},u={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("el-form",{staticClass:"query-form",attrs:{inline:"",model:e.query,"label-position":"right","label-width":"70px"}},[i("el-input",{staticStyle:{width:"150px"},attrs:{placeholder:"请输入项目名称"},model:{value:e.query.title,callback:function(t){e.$set(e.query,"title",t)},expression:"query.title"}}),e._v(" "),i("el-input",{staticStyle:{width:"150px"},attrs:{placeholder:"请输入作者名称"},model:{value:e.query.author,callback:function(t){e.$set(e.query,"author",t)},expression:"query.author"}}),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:e.handleSearch}},[e._v("搜索")]),e._v(" "),i("el-button",{attrs:{type:"primary"},on:{click:e.handleAdd}},[e._v("添加项目")])],1)],1),e._v(" "),i("el-table",{ref:"projectTable",attrs:{data:e.tableData,height:"580","header-cell-style":{"text-align":"center"},"cell-style":{"text-align":"center"},stripe:"",border:""}},[i("el-table-column",{attrs:{type:"index",index:e.realIndex,label:"序号",width:"70"}}),e._v(" "),i("el-table-column",{attrs:{prop:"title",label:"名称"}}),e._v(" "),i("el-table-column",{attrs:{prop:"cover",label:"封面图",width:"70"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("img",{staticStyle:{width:"30px",height:"30px",cursor:"pointer"},attrs:{src:t.row.cover},on:{click:function(i){e.handleCoverCardPreview(t.row.cover)}}})]}}])}),e._v(" "),i("el-table-column",{attrs:{prop:"author",label:"作者"}}),e._v(" "),i("el-table-column",{attrs:{prop:"viewCount",label:"浏览数"}}),e._v(" "),i("el-table-column",{attrs:{prop:"praiseCount",sortable:"",label:"好评数"}}),e._v(" "),i("el-table-column",{attrs:{prop:"status",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.status?i("span",[e._v("上架")]):i("span",[e._v("下架")])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"操作",width:"230"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{size:"mini",type:"warning",plain:""},on:{click:function(i){e.handleDownShelf(t.$index,t.row)}}},[e._v(e._s(t.row.status?"下架":"上架"))]),e._v(" "),i("el-button",{attrs:{size:"mini"},on:{click:function(i){e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),i("el-button",{attrs:{size:"mini",type:"danger",plain:""},on:{click:function(i){e.handleRemove(t.$index,t.row)}}},[e._v("刪除")])]}}])})],1),e._v(" "),i("el-pagination",{staticClass:"table__pagination",attrs:{background:"","current-page":e.page.pageNumber,"page-sizes":[10,20,30,40],"page-size":e.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.page.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),i("el-dialog",{attrs:{title:e.isEdit?"编辑":"新增",visible:e.addEditDialogVisible,width:"50%"},on:{"update:visible":function(t){e.addEditDialogVisible=t},close:function(t){e.closeDialog("addEditDialogVisible")}}},[i("el-form",{ref:"infoForm",attrs:{model:e.infoForm,rules:e.rules,"label-width":"110px"}},[i("el-form-item",{attrs:{prop:"title",label:"标题："}},[i("el-input",{staticStyle:{width:"300px"},model:{value:e.infoForm.title,callback:function(t){e.$set(e.infoForm,"title",t)},expression:"infoForm.title"}})],1),e._v(" "),i("el-form-item",{attrs:{prop:"createField",label:"创作领域："}},[i("el-select",{staticStyle:{width:"300px"},attrs:{placeholder:"请选择创作领域"},model:{value:e.infoForm.createFields,callback:function(t){e.$set(e.infoForm,"createFields",t)},expression:"infoForm.createFields"}},e._l(e.fieldList,function(e){return i("el-option",{key:e.id,attrs:{label:e.fieldName,value:e.id}})}))],1),e._v(" "),i("el-form-item",{attrs:{prop:"cover",label:"封面："}},[i("el-upload",{staticClass:"img-uploader",attrs:{action:"/admin/upload",headers:e.headers,"show-file-list":!1,"on-success":e.handleCoverSuccess,"on-remove":e.handleCoverRemove,"before-upload":e.beforeAvatarUpload}},[e.cover?i("img",{staticClass:"img",attrs:{src:e.cover}}):i("i",{staticClass:"el-icon-plus img-uploader-icon"})])],1),e._v(" "),i("el-form-item",{attrs:{prop:"extCover",label:"配图："}},[i("el-upload",{ref:"upload",attrs:{action:"/admin/upload","list-type":"picture-card",headers:e.headers,"file-list":e.extImageList,"on-success":e.handleExtCoverSuccess,"on-remove":e.handleExtCoverRemove,"on-preview":e.handlePictureCardPreview,"before-upload":e.beforeAvatarUpload}},[i("i",{staticClass:"el-icon-plus"})])],1)],1),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(t){e.closeDialog("addEditDialogVisible")}}},[e._v("关闭")]),e._v(" "),e.isEdit?i("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmitUpdate}},[e._v("提交")]):i("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmitSave}},[e._v("确定")])],1)],1),e._v(" "),i("div",{directives:[{name:"show",rawName:"v-show",value:e.isShowImgPreview,expression:"isShowImgPreview"}],staticClass:"pircture__preview",on:{click:function(t){e.isShowImgPreview=!1}}},[i("img",{staticClass:"preview__img",attrs:{src:e.previewImageUrl,alt:""}})])],1)},staticRenderFns:[]},d=i("VU/8")(c,u,!1,function(e){i("W0BK")},null,null);t.default=d.exports},W0BK:function(e,t,i){var a=i("aWr9");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);i("rjj0")("03eb78ce",a,!0)},aWr9:function(e,t,i){(e.exports=i("FZ+f")(!0)).push([e.i,"\n.school-item {\r\n  margin-bottom: 1px;\n}\n.school-name {\r\n  font-weight: 700;\n}\r\n","",{version:3,sources:["E:/JavaWork/see_me/see-me-backend/src/views/user/projectList.vue"],names:[],mappings:";AACA;EACE,mBAAmB;CACpB;AACD;EACE,iBAAiB;CAClB",file:"projectList.vue",sourcesContent:["\n.school-item {\r\n  margin-bottom: 1px;\n}\n.school-name {\r\n  font-weight: 700;\n}\r\n"],sourceRoot:""}])},q4js:function(e,t,i){"use strict";t.a=function(e,t,i){var a=new FormData;a.append("file",e);var r={headers:{"Content-Type":"multipart/form-data",jxt_admin_session_id:i}};return n.post(t,a,r)};var a=i("OMN4"),n=i.n(a).a.create()}});
//# sourceMappingURL=1.4bead77ed2a5f9462d20.js.map