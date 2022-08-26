<template>
  <span>
    <el-table :data="examData" stripe style="width: 100%">
      <el-table-column type="index" label="序号" align="center">
      </el-table-column>

      <el-table-column prop="bankname" label="题库名称" align="center">
      </el-table-column>
      <el-table-column
        prop="bankid"
        label="题库编号"
        width="150"
        align="center"
      >
      </el-table-column>

      <el-table-column prop="examtime" label="更新日期" align="center" width="200">
      </el-table-column>
      <el-table-column prop="dandui" label="单选题正确数" align="center">
      </el-table-column>
      <el-table-column prop="danzong" label="单选题个数" align="center">
      </el-table-column>
        <el-table-column prop="duodui" label="多选题正确数" align="center">
      </el-table-column>
      <el-table-column prop="duozong" label="多选题个数" align="center">
      </el-table-column>
      <el-table-column prop="zong" label="总得分" align="center">
      </el-table-column>
    <el-table-column label="查看详情" align="center">

    </el-table-column>

      

    </el-table>

    <el-pagination
      background
      hide-on-single-page
      layout="prev, pager, next"
      :total="total"
      @current-change="changePage"
      class="pagination"
    >
    </el-pagination>
  </span>
</template>

<script>
import { reqExamLog } from '../api';
export default {
  data() {
    return {
      examData: [],
      total: 1,
      page: 1,
    };
  },
  mounted(){
this.getExamLog()
  },
methods:{
  async getExamLog() {
      const req = await reqExamLog({
        uuid: this.$store.state.uuid,
        page: this.page,
      });
      // console.log(req);
      if (req.code == 200) {
        this.examData = req.data;
        this.total = req.total;
        // console.log(this.BankData);
        // console.log(this.total);
      }
    },
    changePage(res) {
      // console.log(res);
      this.page = res;
      this.getExamLog();
    },
}
}
</script>

<style>
.pagination {
  text-align: center;
  margin-top: 20px;
}



</style>