<template>
  <div>
    <el-menu
      style="height: 100vh"
      :default-active="$route.path"
      class="el-menu-vertical-demo"
      router
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#409eff"
    >
      <el-menu-item index="/home">
        <i class="el-icon-menu"></i>
        <span slot="title">刷题小帮手</span>
      </el-menu-item>
<el-menu-item index="/login" :disabled="uuid ? true : false">
        <i class="el-icon-user"></i>
        <span slot="title">登录or注册</span>
      </el-menu-item>
      <el-menu-item index="/allbank">
        <i class="el-icon-user"></i>
        <span slot="title">全部题库</span>
      </el-menu-item>
      

      <el-menu-item index="/questionbank" :disabled="!uuid ? true : false">
        <i class="el-icon-folder-opened"></i>
        <span slot="title">我的个人题库</span>
      </el-menu-item>

      <el-menu-item index="/upload" :disabled="!uuid ? true : false"
        ><i class="el-icon-upload"></i>题库导入</el-menu-item
      >

      <!-- <el-menu-item index="/collection" :disabled="!uuid ? true : false">
        <i class="el-icon-collection"></i>
        <span slot="title">我的收藏</span>
      </el-menu-item> -->

      <el-menu-item index="/examination" :disabled="!uuid ? true : false">
        <i class="el-icon-reading"></i>
        <span slot="title">考试记录</span>
      </el-menu-item>
      <el-menu-item index="/callbug">
        <i class="el-icon-chat-dot-round"></i>
        <span slot="title">反馈问题(暂停)</span>
      </el-menu-item>

      <!-- <el-menu-item index="/exam">
        <i class="el-icon-error"></i>
        <span slot="title">测试</span>
      </el-menu-item> -->
    </el-menu>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["uuid"]),
  },
};
</script>

<style>
</style>