<template>
  <span>
    <el-dropdown @command="handleCommand">
      <span class="el-dropdown-link">
        {{ phone ? "欢迎：" + phone : "我要登录"
        }}<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="removeLogin" v-if="phone"
          >退出登录</el-dropdown-item
        >
        <el-dropdown-item command="login" v-else>登录or注册</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </span>
</template>

<script>
import { getStorage, removeStorage } from "../utils/Storage";
import { mapState } from "vuex";
export default {
  // data() {
  //   return {
  //     phone: '',
  //   };
  // },
  methods: {
    handleCommand(command) {
      if (command == "removeLogin") {
        this.$message("退出登录成功 ");
        console.log("清除");
        removeStorage("phone");
        removeStorage("uuid");
        sessionStorage.removeItem("phone");
        sessionStorage.removeItem("uuid");
        this.$store.dispatch("changeLogin", { uuid: "", phone: "" });
        this.$router.push("/home");
      } else {
        this.$router.push("/login");
      }
    },
  },
  computed: {
    ...mapState(["phone"]),
  },
  // watch:{
  //  $route:{
  //     handler(newdata,olddata){
  //       console.log('监视',newdata,olddata);

  //     }
  //   }
  // }
};
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>

