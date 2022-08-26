<template>
  <div>
    <h2>我要反馈(暂不支持)</h2>

    <el-divider></el-divider>
    
      <el-select v-model="value" placeholder="请选择反馈类型" class="type">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <el-input v-model="email" placeholder="请输入您的邮箱" class="email"></el-input>
    
    <div style="margin: 20px 0"></div>
    <el-input
      type="textarea"
      :autosize="{ minRows: 8, maxRows: 13 }"
      placeholder="请输入反馈正文，请在上方留下您的邮箱或手机号，方便联系；若忘记密码，请说明注册时填写的手机号，会在48小时内给您发送邮件"
      v-model="text"
    >
    </el-input>
    <div style="margin: 20px 0"></div>
    <el-button type="primary" @click="submit">提交反馈</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      options: [
        {
          value: "找回密码",
          label: "找回密码",
        },
        {
          value: "提出建议",
          label: "提出建议",
        },
        {
          value: "反馈bug",
          label: "反馈bug",
        },
      ],
      value: "",
      text: "",
      email: "",
    };
  },
  methods: {
    submit() {
      // 提交反馈
      if (this.text != "" && this.value != "" && this.email != "") {
        this.$message({
          message: "恭喜你，反馈成功",
          type: "success",
        });
      } else {
        this.$message({
          message: "请输入完整后再提交",
          type: "warning",
        });
      }
    },
  },
};
</script>

<style>
.email{
  width:200px;
  padding-left: 30px;
}
.type{
  width: 150px;
}
</style>