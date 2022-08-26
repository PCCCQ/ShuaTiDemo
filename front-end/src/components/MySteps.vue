<template>
    <el-steps :active='active' finish-status="success" align-center index>

  <el-step v-for="(t,index) in text" :key='index' :title="t"></el-step>

</el-steps>
</template>

<script>
export default {
props:['active','text']
}
</script>

<style>

</style>