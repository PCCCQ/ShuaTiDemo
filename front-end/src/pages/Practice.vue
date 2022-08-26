<template>
  <div class="box-card">
    <span v-for="(ti,index) in allTimu" :key="ti.tid">
      <el-card >
  <div slot="header" class="clearfix">
    <span>{{index+1}}：({{ti.ttype}})  {{ti.tabout}}  </span>
     
  </div>
  <div class="text item" v-show="ti.optionA">A：{{ti.optionA}}</div>
  <div class="text item" v-show="ti.optionB">B：{{ti.optionB}}</div>
  <div class="text item" v-show="ti.optionC">C：{{ti.optionC}}</div>
  <div class="text item" v-show="ti.optionD">D：{{ti.optionD}}</div>
  <div class="text item" v-show="ti.optionE">E：{{ti.optionE}}</div>
  <div class="text item" v-show="ti.optionF">F：{{ti.optionF}}</div>
  <div class="text item" v-show="ti.optionG">G：{{ti.optionG}}</div>
  <div class="text item" v-show="ti.optionH">H：{{ti.optionH}}</div>
<span class="true " >正确答案：{{ti.trueABCD}}</span>
  <span v-show="ti.analysis">
  <el-divider content-position="left"><i class="el-icon-info"></i>解析</el-divider>

<p>{{ti.analysis}}</p>
    </span>
  
</el-card>
 <div style="margin: 20px 0"></div>
    </span>

  </div>
</template>

<script>
import { reqPractice } from "../api";
export default {
  data() {
    return {
      allTimu: "",
    };
  },
  mounted() {
    this.getPractice();
  },
  methods: {
    async getPractice() {
      console.log(this.$route.params.bankid);
      const r =await reqPractice({ bankid: this.$route.params.bankid});

      if (r.code === 200) {
        console.log(r);
        this.allTimu = r.data;
        console.log(this.allTimu);
      }
    },
  },
};
</script>

<style>
.text {
  font-size: 16px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 70%;
margin-left: 15%;
margin-right: 15%;
}
.true{
  font-size: 17px;
  color:red;
}
</style>