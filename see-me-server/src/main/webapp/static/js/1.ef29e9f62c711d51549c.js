webpackJsonp([1],{QKYt:function(n,e,t){(n.exports=t("FZ+f")(!0)).push([n.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n","",{version:3,sources:[],names:[],mappings:"",file:"StudentList.vue",sourceRoot:""}])},TOo8:function(n,e,t){var a=t("QKYt");"string"==typeof a&&(a=[[n.i,a,""]]),a.locals&&(n.exports=a.locals);t("rjj0")("5d25f002",a,!0)},Zaon:function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=t("eOoE"),l={data:function(){return{query:{name:"",schoolId:"",gradeId:"",studentCode:""},loading:!1,page:{pageNumber:1,pageSize:10,total:0},selectSchoolId:this.$route.query.schoolId?1*this.$route.query.schoolId:"",schoolList:[],selectGradeId:"",gradeList:[],tableData:[]}},created:function(){},mounted:function(){this.getList(),this.getSchoolList(),this.selectSchoolId&&this.getGradelList()},watch:{selectSchoolId:function(n,e){this.gradeList=[],this.selectGradeId="",n&&this.getGradelList()}},methods:{handleSizeChange:function(n){this.page.pageSize=n,this.getList()},handleCurrentChange:function(n){this.pageNumber=n,this.getList()},handleSearch:function(){this.getList()},getList:function(){var n=this,e={name:this.query.name,pageNumber:this.page.pageNumber,studentCode:this.query.studentCode,pageSize:this.page.pageSize,schoolId:this.selectSchoolId,gradeId:this.selectGradeId};this.loading=!0,function(n){return Object(a.a)({url:"/admin/user/student/list",method:"post",data:n})}(e).then(function(e){n.tableData=e.data.page.list,n.page.pageNumber=e.data.page.pageNumber,n.page.pageSize=e.data.page.pageSize,n.page.total=e.data.page.totalRow,n.loading=!1}).catch(function(e){n.loading=!1,n.$message.error(e),console.log(e)})},getSchoolList:function(){var n=this;Object(a.a)({url:"/admin/user/student/schoolList",method:"get"}).then(function(e){n.schoolList=e.data.schoolList}).catch(function(e){n.$message.error(e),console.log(e)})},getGradelList:function(){var n=this;(function(n){return Object(a.a)({url:"/admin/user/student/gradeList",method:"post",data:n})})({schoolId:this.selectSchoolId}).then(function(e){n.gradeList=e.data.gradeList}).catch(function(e){n.$message.error(e),console.log(e)})}}},o={render:function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",[t("el-form",{staticClass:"query-form",attrs:{inline:"",model:n.query,"label-position":"right","label-width":"60px"}},[t("el-select",{staticStyle:{"margin-left":"10px"},attrs:{placeholder:"请选择学校"},model:{value:n.selectSchoolId,callback:function(e){n.selectSchoolId=e},expression:"selectSchoolId"}},[t("el-option",{key:"0",attrs:{label:"请选择学校",value:""}}),n._v(" "),n._l(n.schoolList,function(n){return t("el-option",{key:n.id,attrs:{label:n.name,value:n.id}})})],2),n._v(" "),t("el-select",{attrs:{placeholder:"请选择年级"},model:{value:n.selectGradeId,callback:function(e){n.selectGradeId=e},expression:"selectGradeId"}},[t("el-option",{key:"0",attrs:{label:"请选择年级",value:""}}),n._v(" "),n._l(n.gradeList,function(n){return t("el-option",{key:n.id,attrs:{label:n.name,value:n.id}})})],2),n._v(" "),t("el-input",{staticStyle:{width:"150px"},attrs:{placeholder:"请输入学校姓名"},model:{value:n.query.name,callback:function(e){n.$set(n.query,"name",e)},expression:"query.name"}}),n._v(" "),t("el-input",{staticStyle:{width:"150px"},attrs:{placeholder:"请输入学籍号"},model:{value:n.query.studentCode,callback:function(e){n.$set(n.query,"studentCode",e)},expression:"query.studentCode"}}),n._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:n.handleSearch}},[n._v("搜索")])],1)],1),n._v(" "),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:n.loading,expression:"loading"}],staticClass:"table",attrs:{data:n.tableData,stripe:"",border:""}},[t("el-table-column",{attrs:{type:"index",label:"序号",width:"70"}}),n._v(" "),t("el-table-column",{attrs:{prop:"name",label:"名称"}}),n._v(" "),t("el-table-column",{attrs:{prop:"phone",label:"手机"}}),n._v(" "),t("el-table-column",{attrs:{prop:"sex",label:"性別"},scopedSlots:n._u([{key:"default",fn:function(e){return[1===e.row.sex?t("span",[n._v("男")]):t("span",[n._v("女")])]}}])}),n._v(" "),t("el-table-column",{attrs:{prop:"studentCode",label:"学籍号"}}),n._v(" "),t("el-table-column",{attrs:{prop:"schoolName",label:"所属学校"}}),n._v(" "),t("el-table-column",{attrs:{prop:"gradeName",label:"年级"}}),n._v(" "),t("el-table-column",{attrs:{prop:"className",label:"班级"}}),n._v(" "),t("el-table-column",{attrs:{prop:"parentCount",label:"家长账号数量"}})],1),n._v(" "),t("el-pagination",{attrs:{"current-page":n.page.pageNumber,"page-sizes":[10,20,30,40],"page-size":n.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:n.page.total},on:{"size-change":n.handleSizeChange,"current-change":n.handleCurrentChange}})],1)},staticRenderFns:[]},s=t("VU/8")(l,o,!1,function(n){t("TOo8")},null,null);e.default=s.exports}});
//# sourceMappingURL=1.ef29e9f62c711d51549c.js.map