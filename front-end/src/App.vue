<template>
  <div>
    <el-container>
      <el-aside width="200px" v-if="!$route.meta.showAside">
        <Aside></Aside>
      </el-aside>

      <el-container>
        <!-- 头部组件 -->
        <el-header style="text-align: right; font-size: 12px">
          <Header></Header>
        </el-header>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from "./components/Aside.vue";
import Header from "./components/MyHeader.vue";
export default {
  name: "App",
  components: { Aside, Header },
};
</script>

<style>
.el-aside {
  color: #566;
}
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}
.footer {
  margin-trim: 2rpx;
  text-align: center;
}
</style>
