<template>
  <div style="width: 60%">
    <div class="switch">
      <el-switch v-model="isLogin" active-text="登录" inactive-text="注册">
      </el-switch>

      <el-divider>{{
        isLogin ? "登录:若忘记密码,可在反馈页面找回" : "注册"
      }}</el-divider>
    </div>
    <!-- 登录 -->
    <el-form ref="login" :model="login" label-width="120px" v-show="isLogin">
      <el-form-item label="手机号：">
        <el-input
          v-model="login.phone"
          maxlength="11"
          show-word-limit
          placeholder="请输入手机号"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码：">
        <el-input
          placeholder="请输入密码"
          v-model="login.password"
          show-password
        ></el-input>

        <div style="margin: 20px 0"></div>
        <div><el-checkbox v-model="login.antoLogin">自动登录</el-checkbox></div>
      </el-form-item>

      <el-form-item>
        <!-- <el-tooltip
          class="item"
          effect="dark"
          content="请再次检查无误后点击"
          placement="bottom-start"
        > -->
        <el-button type="primary" @click="onLogin" :loading="login.loading"
          >立即登录</el-button
        >
        <!-- </el-tooltip> -->
      </el-form-item>
    </el-form>
    <!-- 注册 -->
    <el-form
      ref="register"
      :model="register"
      label-width="120px"
      v-show="!isLogin"
    >
      <el-form-item label="手机号：">
        <el-input
          v-model="register.phone"
          maxlength="11"
          show-word-limit
          placeholder="请输入手机号"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码：">
        <el-input
          placeholder="请输入密码(6-20位)"
          v-model="register.password"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码：">
        <el-input
          placeholder="请再次输入密码(6-20位)"
          v-model="register.passwordTwo"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onRegister" :loading="register.loading">立即注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { reqLogin, reqRegister } from "../api/index";
import { getStorage, setStorage } from "../utils/Storage";
import { v4 as uuidv4 } from "uuid";
export default {
  data() {
    return {
      isLogin: true,
      login: {
        phone: "",
        password: "",
        antoLogin: false,
        loading: false,
      },
      register: {
        phone: "",
        password: "",
        passwordTwo: "",
        loading: false,
      },
    };
  },
  methods: {
    // 登录业务
    async onLogin() {
      // console.log("登录");
      if (
        5 < this.login.password.length &&
        this.login.password.length < 21 &&
        /^1[3|4|5|7|8]\d{9}$/.test(this.login.phone)
      ) {
        this.login.loading = true;
        let { phone, password } = this.login;
        const result = await reqLogin({ phone, password });
        // console.log(result);
        if (result.code == 200) {
          if (this.login.antoLogin) {
            // 自动登录，保存到缓存
            setStorage("uuid", result.data.uuid);
            setStorage("phone", result.data.phone);
          }
          // console.log(result.data);
          sessionStorage.setItem('uuid',result.data.uuid)
          sessionStorage.setItem('phone',result.data.phone)
          this.$store.dispatch("changeLogin", {
            // isLogin: true,
            uuid: result.data.uuid,
            phone: result.data.phone,
          });

          this.$router.push("/home");

          this.$message({
            message: `欢迎您：${phone}登录成功`,
            type: "success",
          });
        } else {
          this.login.loading = false;
          this.$message({
            message: `密码错误或未注册`,
            type: "error",
          });
        }
      } else {
        this.$message({
          message: "请检查输入是否正确",
          type: "warning",
        });
      }
    },

    // 注册业务
    async onRegister() {
      // console.log("注册");
      this.register.loading=true
      if (
        5 < this.register.password.length &&
        this.register.password.length < 21 &&
        /^1[3|4|5|7|8]\d{9}$/.test(this.register.phone) &&
        this.register.password == this.register.passwordTwo
      ) {
        this.register.loading = true;
        let { phone, password } = this.register;
        const uuid = uuidv4();
        const result = await reqRegister({ uuid, phone, password });
        // console.log(result);
        if (result.code == 200) {
          // console.log(result.data);
          this.isLogin = true;
          this.$message({
            message: `欢迎您：${phone}注册成功`,
            type: "success",
          });
        } else {
          this.login.loading = false;
          this.$message({
            message: `手机号已注册或其他原因，请重试`,
            type: "error",
          });
        }
      } else {
        this.$message({
          message: "请检查输入是否正确",
          type: "warning",
        });
      }
      this.register.loading=false
    },
  },
};
</script>

<style>
.switch {
  margin: 30px;
}
.antoLogin {
  margin-left: 60px;
}
</style>