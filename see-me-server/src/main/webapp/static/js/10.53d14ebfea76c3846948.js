webpackJsonp([10],{ARoL:function(t,n,i){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var s=i("eOoE"),a={name:"list",components:{VePie:window.VeLine},data:function(){return{chartSettings:{},dayNumber:7,platform:{userCount:0,projectCount:0,collectionCount:0,advCount:0,infoCount:0},activeName:"",chartData:{columns:["date","在线人数"],rows:[]}}},methods:{reFindPlatForm:function(){var t=this;Object(s.a)({url:"/admin/dashBoard/platformCount",method:"get"}).then(function(n){t.platform=n.data})},reFindHistoryOnline:function(){var t=this;(function(t){return Object(s.a)({url:"/admin/dashBoard/historyOnline",method:"post",data:t})})({dayNumber:this.dayNumber}).then(function(n){t.chartData.rows=n.data.map(function(t){return{date:t.date,"在线人数":t.personNumber}})})}},mounted:function(){this.activeName="first",this.reFindPlatForm(),this.reFindHistoryOnline()}},e={render:function(){var t=this.$createElement,n=this._self._c||t;return n("div",{staticClass:"dashboard"},[n("el-row",{attrs:{gutter:20}},[n("el-col",{attrs:{span:12}},[n("el-card",{staticClass:"box-card"},[n("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[n("span",[n("h1",[this._v("平台运营情况")])])]),this._v(" "),n("ul",{staticClass:"platform-li"},[n("li",[this._v("已入驻用户总数 | "),n("span",[this._v(this._s(this.platform.userCount))])]),this._v(" "),n("li",[this._v("平台累计产生的项目数量 | "),n("span",[this._v(this._s(this.platform.projectCount))])]),this._v(" "),n("li",[this._v("平台累计产生的作品集数量 | "),n("span",[this._v(this._s(this.platform.collectionCount))])]),this._v(" "),n("li",[this._v("广告数量 | "),n("span",[this._v(this._s(this.platform.advCount))])]),this._v(" "),n("li",[this._v("发布的资讯数量 | "),n("span",[this._v(this._s(this.platform.infoCount))])])])])],1),this._v(" "),n("el-col",{attrs:{span:12}},[n("el-card",{staticClass:"box-card"},[n("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[n("span",[n("h1",[this._v("近7天在线人数")])])]),this._v(" "),n("ve-pie",{attrs:{data:this.chartData,settings:this.chartSettings}})],1)],1)],1)],1)},staticRenderFns:[]},r=i("VU/8")(a,e,!1,function(t){i("kAaw")},null,null);n.default=r.exports},"F+Zz":function(t,n,i){(t.exports=i("FZ+f")(!0)).push([t.i,"\n.dashboard {\r\n  margin-top: 20px;\n}\n.box-card {\r\n  height: 510px;\n}\n.clearfix h1 {\r\n  font-weight: 700;\n}\n.platform-li {\r\n  list-style-type: none;\n}\n.platform-li li {\r\n  padding: 10px 0;\r\n  border-bottom: 1px solid #f0f2f7;\n}\n.platform-li li span {\r\n  font-size: 16px;\r\n  font-weight: 600;\n}\r\n","",{version:3,sources:["E:/JavaWork/see_me/see-me-backend/src/views/dashboard/index.vue"],names:[],mappings:";AACA;EACE,iBAAiB;CAClB;AACD;EACE,cAAc;CACf;AACD;EACE,iBAAiB;CAClB;AACD;EACE,sBAAsB;CACvB;AACD;EACE,gBAAgB;EAChB,iCAAiC;CAClC;AACD;EACE,gBAAgB;EAChB,iBAAiB;CAClB",file:"index.vue",sourcesContent:["\n.dashboard {\r\n  margin-top: 20px;\n}\n.box-card {\r\n  height: 510px;\n}\n.clearfix h1 {\r\n  font-weight: 700;\n}\n.platform-li {\r\n  list-style-type: none;\n}\n.platform-li li {\r\n  padding: 10px 0;\r\n  border-bottom: 1px solid #f0f2f7;\n}\n.platform-li li span {\r\n  font-size: 16px;\r\n  font-weight: 600;\n}\r\n"],sourceRoot:""}])},kAaw:function(t,n,i){var s=i("F+Zz");"string"==typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);i("rjj0")("b4b26e34",s,!0)}});
//# sourceMappingURL=10.53d14ebfea76c3846948.js.map