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
              <h1>系统运行情况</h1>
            </span>
          </div>
          <el-tabs v-model="activeName"
            tabPosition="right"
            class="ehcarts-tabs">
            <el-tab-pane label="CPU使用情況"
              name="first">
              <ve-pie v-if="activeName ==='first'"
                :data="chartData"
                :settings="chartSettings"></ve-pie>
            </el-tab-pane>
            <el-tab-pane label="内存使用情況"
              name="second">
              <ve-pie v-if="activeName ==='second'"
                :data="chartData"
                :settings="chartSettings"></ve-pie>
            </el-tab-pane>
          </el-tabs>
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
      chartSettings: {},
      platform: {
        userCount: 0,
        projectCount: 0,
        collectionCount: 0,
        advCount: 0,
        infoCount: 0
      },
      activeName: "",
      chartData: {
        columns: ["name", "present"],
        rows: [{ name: "空闲率", present: 90 }, { name: "使用率", present: 10 }]
      }
    };
  },
  methods: {
    reFindPlatForm() {
      console.log('哈哈哈');

      platformCount().then(res => {
        console.log(res);
        this.platform = res.data
      })
    },
    reFindHistoryOnline() {
      // 未实现
      historyOnline().then(res => {
        console.log(res);
      })
    }
  },
  mounted() {
    //放到这里是因为渲染问题，如果直接在data默认，css有问题
    this.activeName = "first";
    this.reFindPlatForm()
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