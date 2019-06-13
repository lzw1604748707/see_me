<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header"
            class="clearfix">
            <span>
              <h1>平台运营情况</h1>
            </span>
          </div>
          <ul class="platform-li">
            <li>已入驻用户总数 | <span>{{platform.userCount}}</span></li>
            <li>平台累计产生的项目数量 | <span>{{platform.projectCount}}</span></li>
            <li>平台累计产生的作品集数量 | <span>{{platform.collectionCount}}</span></li>
            <li>广告数量 | <span>{{platform.advCount}}</span></li>
            <li>发布的资讯数量 | <span>{{platform.infoCount}}</span></li>
          </ul>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header"
            class="clearfix">
            <span>
              <h1>近7天在线人数</h1>
            </span>
          </div>
          <ve-pie :data="chartData"
            :settings="chartSettings"></ve-pie>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
import { platformCount, historyOnline } from "@/api/dashBoard";
export default {
  name: "list",
  components: {
    VePie: window.VeLine
  },
  data() {
    return {
      chartSettings: {
      },
      dayNumber: 7,
      platform: {
        userCount: 0,
        projectCount: 0,
        collectionCount: 0,
        advCount: 0,
        infoCount: 0
      },
      activeName: "",
      chartData: {
        columns: ["date", "在线人数"],
        rows: []
      }
    };
  },
  methods: {
    reFindPlatForm() {
      platformCount().then(res => {
        this.platform = res.data
      })
    },
    reFindHistoryOnline() {
      const params = { dayNumber: this.dayNumber }
      historyOnline(params).then(res => {
        this.chartData.rows = res.data.map(item => ({
          date: item.date,
          在线人数: item.personNumber
        }))
      })
    }
  },
  mounted() {
    //放到这里是因为渲染问题，如果直接在data默认，css有问题
    this.activeName = "first";
    this.reFindPlatForm()
    this.reFindHistoryOnline()
  }
};
</script>

<style>
.dashboard {
  margin-top: 20px;
}
.box-card {
  height: 510px;
}
.clearfix h1 {
  font-weight: 700;
}

.platform-li {
  list-style-type: none;
}

.platform-li li {
  padding: 10px 0;
  border-bottom: 1px solid #f0f2f7;
}
.platform-li li span {
  font-size: 16px;
  font-weight: 600;
}
</style>