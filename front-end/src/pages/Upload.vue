<template>
  <div>
    <MySteps :text="text" :active="active" />
    <!-- 第一步 -->
    <div class="center" v-show="active == 0">
      <el-link type="primary" icon="el-icon-download" href="http://101.200.234.105:9080/file/template.xlsx"
        >点此下载Excel模板</el-link
      >
      <div style="margin: 40px 0"></div>
      <el-input
        type="text"
        placeholder="请输入题库名称，最少4字，最多20字"
        v-model="bankname"
        maxlength="20"
        show-word-limit
      >
      </el-input>
      <div style="margin: 40px 0"></div>

      <el-button type="primary" class="button" @click="createBank"
        >下 一 步</el-button
      >
    </div>

    <!-- 第二步 -->
    <div v-show="active == 1">
      <el-button @click="back">上 一 步</el-button>
      <el-button type="primary" @click="getTest()" :loading="loading"
        >上传题库</el-button
      >
      <div class="container">
        {{
          upload_file
            ? upload_file + "-->若有错误，可更改后，点此重新上传"
            : "导入Excel"
        }}
        <input
          type="file"
          accept=".xls,.xlsx"
          class="upload_file"
          @change="readExcel($event)"
        />
      </div>

      <el-table :data="lists" style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="题干：" v-show="props.row.tabout">
                <span>{{ props.row.tabout }}</span>
              </el-form-item>
              <el-form-item label="题型：" v-show="props.row.ttype">
                <span>{{ props.row.ttype }}</span>
              </el-form-item>
              <el-form-item label="选项A：" v-show="props.row.optionA">
                <span>{{ props.row.optionA }}</span>
              </el-form-item>
              <el-form-item label="选项B：" v-show="props.row.optionB">
                <span>{{ props.row.optionB }}</span>
              </el-form-item>
              <el-form-item label="选项C：" v-show="props.row.optionC">
                <span>{{ props.row.optionC }}</span>
              </el-form-item>
              <el-form-item label="选项D：" v-show="props.row.optionD">
                <span>{{ props.row.optionD }}</span>
              </el-form-item>
              <el-form-item label="选项E：" v-show="props.row.optionE">
                <span>{{ props.row.optionE }}</span>
              </el-form-item>
              <el-form-item label="选项F：" v-show="props.row.optionF">
                <span>{{ props.row.optionF }}</span>
              </el-form-item>
              <el-form-item label="选项G：" v-show="props.row.optionG">
                <span>{{ props.row.optionG }}</span>
              </el-form-item>
              <el-form-item label="选项H：" v-show="props.row.optionH">
                <span>{{ props.row.optionH }}</span>
              </el-form-item>
              <el-form-item label="正确答案：" v-show="props.row.trueABCD">
                <span>{{ props.row.trueABCD }}</span>
              </el-form-item>
              <el-form-item label="解析：" v-show="props.row.analysis">
                <span>{{ props.row.analysis }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="题干" prop="tabout"> </el-table-column>
        <el-table-column label="题型" prop="ttype"> </el-table-column>
        <el-table-column label="正确答案" prop="trueABCD"> </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { reqAddbank, reqAddtimu, reqTest } from "../api/index";
import XLSX from "xlsx";
export default {
  data() {
    return {
      active: 0,
      text: ["使用模板创建题库", "上传模板并检查", "上传成功"],
      bankname: "",
      upload_file: "",
      lists: [],
      loading: false,
    };
  },
  methods: {
    back() {
      // 返回上一步
      this.active = this.active - 1;
    },
    createBank() {
      // 题库名称创建完成
      if (this.bankname.length < 21 && this.bankname.length > 3) {
        this.active = 1;
        this.$message({
          message: `题库名称创建成功，请上传文件`,
          type: "success",
        });

        console.log("eee");
      } else {
        this.$message({
          message: `格式不正确，请重新输入`,
          type: "error",
        });
      }
    },

    readExcel(e) {
      // 读取表格文件
      let that = this;
      const files = e.target.files;
      if (files.length <= 0) {
        return false;
      } else if (!/\.(xls|xlsx)$/.test(files[0].name.toLowerCase())) {
        this.$message({
          message: "上传格式不正确，请上传xls或者xlsx格式",
          type: "warning",
        });
        return false;
      } else {
        // 更新获取文件名
        that.upload_file = files[0].name;
      }

      const fileReader = new FileReader();
      fileReader.onload = (ev) => {
        try {
          const data = ev.target.result;
          const workbook = XLSX.read(data, {
            type: "binary",
          });
          const wsname = workbook.SheetNames[0]; //取第一张表
          const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]); //生成json表格内容
          console.log(ws);
          that.lists = [];
          // 从解析出来的数据中提取相应的数据
          ws.forEach((item) => {
            that.lists.push({
              tabout: item["题干(必填)"] || "",
              ttype: item["题型(必填)"] || "",
              optionA: item["选项A"] || "",
              optionB: item["选项B"] || "",
              optionC: item["选项C"] || "",
              optionD: item["选项D"] || "",
              optionE: item["选项E(勿删)"] || "",
              optionF: item["选项F(勿删)"] || "",
              optionG: item["选项G(勿删)"] || "",
              optionH: item["选项H(勿删)"] || "",
              trueABCD: item["正确答案(必填)"] || "",
              analysis: item["解析"] || "",
              bankid: "",
            });
          });
          console.log(that.lists);
          // 给后端发请求
        } catch (e) {
          return false;
        }
      };
      fileReader.readAsBinaryString(files[0]);
    },
    time_transform() {
      let strDate = "";
      const date = new Date();
      const Y = date.getFullYear() + "-";
      const M =
        (date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1) + "-";
      const D =
        (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " ";
      const h =
        (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
      const m =
        (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
        ":";
      const s =
        date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      strDate = Y + M + D + h + m + s;
      return strDate;
    },
    async getTest() {
      this.loading = true;
      if (this.lists[0]) {
        const data = {
          bankname: this.bankname,
          uuid: this.$store.state.uuid,
          banktime: this.time_transform(),
          tinum: this.lists.length,
        };
        const r1 = await reqAddbank(data);
        if (r1.code == 200) {
          this.lists.forEach((item) => {
            item.bankid = r1.data;
          });
          const r2 = await reqAddtimu({
            timudata: this.lists,
            tinum: this.lists.length,
          });
          if (r2.code == 200) {
            this.$message({
              message: `上传成功:${r2.data}个题目`,
              type: "success",
            });
            this.active=2
setTimeout(()=>{
        this.$router.push('/questionbank')
      },1000)
          }
        }
      } else {
        this.$message({
          message: `请上传后再提交`,
          type: "error",
        });
      }
      this.loading = false;
      
    },
  },
};
</script>

<style>
.button {
  width: 300px;
}
.center {
  text-align: center;
  margin: 10%;
}
.container {
  text-align: center;
  border: none;
  border-radius: 4px;
  background-color: #b6bbc0;
  height: 40px;
  width: 600px;
  margin-top: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 15px;
  min-width: 80px;
  *zoom: 1;
}

.upload_file {
  font-size: 15px;

  opacity: 0;
  position: absolute;
  filter: alpha(opacity=0);
  width: 100%;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>