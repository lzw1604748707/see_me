<template>
  <div class="wrapper">
    <!-- 顶部头部 -->
    <HeaderItem></HeaderItem>
    <!-- 中间区域 -->
    <el-container class="main">
      <!-- 左侧菜单栏 -->
      <el-aside class="menu">
        <MenuItems></MenuItems>
      </el-aside>
      <!-- 展示页-->
      <el-main class="content">
        <!-- 面包屑导航 -->
        <BreadCrumb></BreadCrumb>

        <!-- 展示页面 -->
        <router-view></router-view>

      </el-main> <!-- 展示页 结束-->
    </el-container><!-- 中间区域 -->
  </div>
</template>

<script>
import HeaderItem from './HeaderItem'
import MenuItems from './MenuItems'
import BreadCrumb from './BreadCrumb'
export default {
  components: {
    HeaderItem,
    MenuItems,
    BreadCrumb
  },
  created() {},
  mounted() {},
  methods: {}
}
</script>

<style lang="scss" scoped>
.wrapper {
  background: #fff;
  .main {
    height: auto;
  }
}
</style>
