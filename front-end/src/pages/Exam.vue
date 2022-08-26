<template>
  <div class="box-card">
    <!-- 以下为单选 -->
    <div v-if="danTi.length!=0">
    <span v-for="(ti,index) in danTi" :key="ti.tid">
      <el-card >
  <div slot="header" class="clearfix">
    <span>{{index+1}}：({{ti.ttype}})  {{ti.tabout}}  </span>
    <span class="true " style="float: right; padding: 3px" v-show="isTijiao">
      <!-- 对勾 -->
      <i class="el-icon-check" style="font-size: 45px; color: red" v-if="danJiao[index]"></i>
      <!-- 叉号 -->
      <i class="el-icon-close" style="font-size: 45px; color: red" v-else></i>
    </span>
 <span class="true " style="float: right; padding: 3px 0">{{ti.ttype=="单选题"?"1":"2"}}分</span>
     
  </div>
 <span>
  <div class="text item" v-show="ti.optionA"><el-radio v-model="danZuo[index]" label="A" border>A：{{ti.optionA}}</el-radio></div>
  <div class="text item" v-show="ti.optionB"><el-radio v-model="danZuo[index]" label="B" border>B：{{ti.optionB}}</el-radio></div>
  <div class="text item" v-show="ti.optionC"><el-radio v-model="danZuo[index]" label="C" border>C：{{ti.optionC}}</el-radio></div>
  <div class="text item" v-show="ti.optionD"><el-radio v-model="danZuo[index]" label="D" border>D：{{ti.optionD}}</el-radio></div>
  <div class="text item" v-show="ti.optionE"><el-radio v-model="danZuo[index]" label="E" border>E：{{ti.optionE}}</el-radio></div>
  <div class="text item" v-show="ti.optionF"><el-radio v-model="danZuo[index]" label="F" border>F：{{ti.optionF}}</el-radio></div>
  <div class="text item" v-show="ti.optionG"><el-radio v-model="danZuo[index]" label="G" border>G：{{ti.optionG}}</el-radio></div>
  <div class="text item" v-show="ti.optionH"><el-radio v-model="danZuo[index]" label="H" border>H：{{ti.optionH}}</el-radio></div>
</span>

<!-- 以下为答案 -->
<span>
  <!-- 答案 -->
 <span v-show="isTijiao">
  <el-divider content-position="left"></el-divider>

<p>正确答案：{{ti.trueABCD}}</p>
    


  <span v-show="ti.analysis">
  <el-divider content-position="left"><i class="el-icon-info"></i>解析</el-divider>

<p>{{ti.analysis}}</p>
    </span>
    </span>
  </span>
</el-card>
 <div style="margin: 20px 0"></div>
    </span>
</div>


<!-- 以下为多选 -->
<span v-if="duoTi.length!=0">
<span v-for="(ti,index) in duoTi" :key="ti.tid">
      <el-card >
  <div slot="header" class="clearfix">
    <span>{{danTi.length+index+1}}：({{ti.ttype}})  {{ti.tabout}}  </span>
        <span class="true " style="float: right; padding: 3px" v-show="isTijiao">
      <!-- 对勾 -->
      <i class="el-icon-check" style="font-size: 45px; color: red" v-if="duoJiao"></i>
      <!-- 叉号 -->
      <i class="el-icon-close" style="font-size: 45px; color: red" v-else></i>
    </span>
 <span class="true " style="float: right; padding: 3px 0">{{ti.ttype=="单选题"?"1":"2"}}分</span>
     
  </div>
 <span >

  <div class="text item" v-show="ti.optionA"><el-checkbox v-model="duoZuo[index][0]" :label="'A：'+ti.optionA" border></el-checkbox>  </div>
  <div class="text item" v-show="ti.optionB"><el-checkbox v-model="duoZuo[index][1]" :label="'B：'+ti.optionB" border></el-checkbox>  </div>
  <div class="text item" v-show="ti.optionC"><el-checkbox v-model="duoZuo[index][2]" :label="'C：'+ti.optionC" border></el-checkbox>  </div>
  <div class="text item" v-show="ti.optionD"><el-checkbox v-model="duoZuo[index][3]" :label="'D：'+ti.optionD" border></el-checkbox>  </div>
  <div class="text item" v-show="ti.optionE"><el-checkbox v-model="duoZuo[index][4]" :label="'E：'+ti.optionE" border></el-checkbox>  </div>
  <div class="text item" v-show="ti.optionF"><el-checkbox v-model="duoZuo[index][5]" :label="'F：'+ti.optionF" border></el-checkbox>  </div>
  <div class="text item" v-show="ti.optionG"><el-checkbox v-model="duoZuo[index][6]" :label="'G：'+ti.optionG" border></el-checkbox>  </div>
  <div class="text item" v-show="ti.optionH"><el-checkbox v-model="duoZuo[index][7]" :label="'H：'+ti.optionH" border></el-checkbox>  </div>

</span>

<!-- 以下为答案 -->
<span>
  <!-- 答案 -->
 <span v-show="isTijiao">
  <el-divider content-position="left"></el-divider>

<p>正确答案：{{ti.trueABCD}}</p>
    


  <span v-show="ti.analysis">
  <el-divider content-position="left"><i class="el-icon-info"></i>解析</el-divider>

<p>{{ti.analysis}}</p>
    </span></span>
  </span>
</el-card>
 <div style="margin: 20px 0"></div>
    </span>

</span>
<el-button type="primary" @click="tijiao()" v-if="!tijiaoYes">提交</el-button>

<el-descriptions title="成绩信息" v-show="isTijiao">

    <el-descriptions-item label="总分">{{zong}}/{{danTi.length*1+duoTi.length*2}}</el-descriptions-item>
    <el-descriptions-item label="单选题正确个数">{{danDui}}/{{danTi.length}}</el-descriptions-item>
    <el-descriptions-item label="多选题正确个数">{{duoDui}}/{{duoTi.length}}</el-descriptions-item>
</el-descriptions>


  </div>
</template>

<script>
import { reqPractice,reqAddExam } from "../api";
import {time_transform} from '../utils/time'
export default {
  data() {
    return {
      danTi:[],
      danDa:[],
      danZuo:[],
      danJiao:[],
      duoTi:[],
      duoDa:[],
      duoZuo:[],
      duoJiao:[],
      isTijiao:false,
      zong:0,
      danDui:0,
      duoDui:0,
      tijiaoYes:false
      
    };
  },
  mounted() {
    this.getPractice();
  },
  methods: {
    async getPractice() {
      console.log(this.$route.params.bankid);
      const r =await reqPractice({ bankid:this.$route.params.bankid});

      if (r.code === 200) {
        console.log(r);
        const list =r.data
        list.forEach((item)=>{
          if(item.ttype=="单选题"){
            this.danTi.push(item)
            this.danZuo.push("")
            this.danDa.push(item.trueABCD)
          }else{
            this.duoTi.push(item)
            this.duoZuo.push([false,false,false,false,false,false,false,false])
           const isTrue=(val,string)=>{return string.indexOf(val)==-1?false:true}
            this.duoDa.push([
              isTrue("A",item.trueABCD),
              isTrue("B",item.trueABCD),
              isTrue("C",item.trueABCD),
              isTrue("D",item.trueABCD),
              isTrue("E",item.trueABCD),
              isTrue("F",item.trueABCD),
              isTrue("G",item.trueABCD),
              isTrue("H",item.trueABCD),
              ])
          }
        })
        // console.log(this.allTimu);
      }
    },
    tijiao(){
      this.danDa.forEach((item,index)=>{
        if(item==this.danZuo[index]){
          this.danJiao.push(true)
          this.zong=this.zong+1
          this.danDui++
        }else{
          this.danJiao.push(false)
        }
      })
      this.duoDa.forEach((item,index)=>{
        if(
          item[0]==this.duoZuo[index][0]&&
          item[1]==this.duoZuo[index][1]&&
          item[2]==this.duoZuo[index][2]&&
          item[3]==this.duoZuo[index][3]&&
          item[4]==this.duoZuo[index][4]&&
          item[5]==this.duoZuo[index][5]&&
          item[6]==this.duoZuo[index][6]&&
          item[7]==this.duoZuo[index][7]
        ){
          this.duoJiao.push(true)
          this.zong=this.zong+2
          this.duoDui++
        }else{
          this.duoJiao.push(false)
        }
      })
      this.reqtijiao()
   this.isTijiao=true
    },
   
   
   
   async reqtijiao(){
     const data={
      bankid:this.$route.params.bankid,
      uuid:this.$store.state.uuid,
      
      zong:this.zong,
      dandui:this.danDui,
      duodui:this.duoDui,
      danzong:this.danTi.length,
      duozong:this.duoTi.length,
      dan:this.danZuo.toString(),
      duo:this.duoZuo.toString(),
      examtime:time_transform()
     }
     const r=await reqAddExam(data)
     console.log("提交")
     if(r.code===200){
       this.tijiaoYes=true
       console.log("成功");
     }
   }
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
  color: #409eff;
}
</style>