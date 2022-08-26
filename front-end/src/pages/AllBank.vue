<template>
  <span>
    <el-table :data="AllBankData" stripe style="width: 100%">
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

      <el-table-column prop="banktime" label="更新日期" align="center">
      </el-table-column>
      <el-table-column prop="tinum" label="题目数量" align="center">
      </el-table-column>

      <el-table-column label="操作" align="center" v-if="$store.state.uuid">
        <!-- <el-link icon="el-icon-plus" >加入我的题库</el-link> -->
        <span slot-scope="scope">
          <el-popconfirm
            @confirm="clickAllToSelf(scope.row)"
            title="确定要加入您的题库吗？"
          >
            <el-link slot="reference" type="mini" icon="el-icon-plus"
              >加入我的题库</el-link
            >
          </el-popconfirm>
        </span>
      </el-table-column>
    </el-table>
    <el-divider v-if="!$store.state.uuid"
      ><h3>登录后可加入个人题库</h3></el-divider
    >
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
import { reqAllBank, reqAllToSelf } from "../api/index";
export default {
  data() {
    return {
      AllBankData: [],
      total: 1,
      page: 1,
      tips: "登录后可加入题库",
    };
  },
  mounted() {
    this.getAllBankData();
  },

  methods: {
    // 获取全部题库数据
    async getAllBankData() {
      const p = {
        page: this.page,
        uuid: this.$store.state.uuid || "",
      };
      const req = await reqAllBank(p);
      console.log(this.page);
      // console.log(req);
      if (req.code === 200) {
        this.AllBankData = req.data;
        this.total = req.total;
        // console.log(this.AllBankData);
        // console.log(this.total);
      }
    },
    // 改变页数
    changePage(res) {
      // console.log(res);
      this.page = res;
      this.getAllBankData();
    },

    //登录后点击加入题库确认按钮
    async clickAllToSelf(row) {
      // console.log("que",row);
      const data = {
        uuid: this.$store.state.uuid,
        bankid: row.bankid,
        bankname: row.bankname,
        banktime: row.banktime,
        tinum: row.tinum,
      };
      console.log(data);
      const req = await reqAllToSelf(data);
      switch (req.code) {
        case 200:
          this.$message({
            message: `加入题库成功`,
            type: "success",
          });

          break;
        case 350:
          this.$message({
            message: `在您的题库中已存在，请勿重复添加`,
            type: "error",
          });
          break;
        default:
          this.$message({
            message: `添加错误，请重试`,
            type: "error",
          });
          break;
      }
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