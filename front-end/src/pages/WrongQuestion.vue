<template>
  <div>
    <div class="container">
      {{ upload_file || "导入" }}
      <input
        type="file"
        accept=".xls,.xlsx"
        class="upload_file"
        @change="readExcel($event)"
      />
    </div>
  </div>
</template>

<script>
import XLSX from "xlsx";

export default {
  data() {
    return {
      upload_file: "",
      lists: [],
    };
  },
  methods: {
    submit_form() {
      // 给后端发送请求，更新数据
      console.log("假装给后端发了个请求...");
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
          // console.log(ws);
          that.lists = [];
          // 从解析出来的数据中提取相应的数据
          ws.forEach((item) => {
            that.lists.push({
              tabout: item["题干(必填)"],
              ttype: item["题型(必填)"],
              optionA: item["选项A"],
              optionB: item["选项B"],
              optionC: item["选项C"],
              optionD: item["选项D"],
              optionE: item["选项E(勿删)"],
              optionF: item["选项F(勿删)"],
              optionG: item["选项G(勿删)"],
              optionH: item["选项H(勿删)"],
              true: item["正确答案(必填)"],
              analysis:item["解析"]
            });
          });
          console.log(that.lists);
          // 给后端发请求
          this.submit_form();
        } catch (e) {
          return false;
        }
      };
      fileReader.readAsBinaryString(files[0]);
    },
  },
};
</script>
<style scoped>
.container {
  border: none;
  border-radius: 4px;
  background-color: #409eff;
  height: 40px;
  width: 100px;
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
  width: 100px;
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