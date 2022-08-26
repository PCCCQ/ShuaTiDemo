<template>
  <span>
    <el-table :data="BankData" stripe style="width: 100%">
      <el-table-column type="index" label="序号" align="center">
      </el-table-column>
      <el-table-column
        prop="bankid"
        label="题库编号"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column prop="bankname" label="题库名称" align="center">
      </el-table-column>

      <el-table-column prop="banktime" label="上传日期" align="center">
      </el-table-column>
      <el-table-column prop="tinum" label="题目数量" align="center">
      </el-table-column>

      <el-table-column align="center" width="100">
        <span slot-scope="scope">
        <el-link icon="el-icon-reading" @click="toPractice(scope.row.bankid)">背题</el-link>
        </span>
      </el-table-column>
      <el-table-column align="center" width="100" label="操作">
        <span slot-scope="scope">
        <el-link icon="el-icon-s-data" @click="toExam(scope.row.bankid)">去考试</el-link>
        </span>
      </el-table-column>
      <el-table-column align="center" width="100">
        <!-- <el-link icon="el-icon-delete">删除</el-link> -->
        <span slot-scope="scope">
          <el-popconfirm
            @confirm="clickDelete(scope.row)"
            title="确认删除此题库吗？"
          >
            <el-link slot="reference" type="mini" icon="el-icon-delete"
              >删除</el-link
            >
          </el-popconfirm>
        </span>
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
import { reqSelfBank, reqDeleteselfdata } from "../api/index";
export default {
  data() {
    return {
      BankData: [],
      total: 1,
      page: 1,
    };
  },
  mounted() {
    this.reqBankData();
  },
  methods: {
    // 获取题库数据
    async reqBankData() {
      const req = await reqSelfBank({
        uuid: this.$store.state.uuid,
        page: this.page,
      });
      // console.log(req);
      if (req.code == 200) {
        this.BankData = req.data;
        this.total = req.total;
        // console.log(this.BankData);
        // console.log(this.total);
      }
    },
    changePage(res) {
      // console.log(res);
      this.page = res;
      this.reqBankData();
    },
    async clickDelete(row) {
      //删除数据
      const req = await reqDeleteselfdata({ firstid: row.firstid });
      if (req.code == 200) {
        this.$message({
          message: `删除成功`,
          type: "success",
        });
        this.reqBankData();
      }else{
        this.$message({
          message: `删除失败，请重试`,
          type: "error",
        });
        this.reqBankData();
        
      }
    },
    toPractice(bankid){

      // 去背题
console.log(bankid);
// this.$store.dispatch("changeBankid",bankid)
this.$router.push("/practice/"+bankid)
    },
    toExam(bankid){
      // 去考试
      this.$router.push("/exam/"+bankid)

    },
  },
};
</script>

<style>
.pagination {
  text-align: center;
  margin-top: 20px;
}
.link {
  margin-left: 60px;
}
</style>