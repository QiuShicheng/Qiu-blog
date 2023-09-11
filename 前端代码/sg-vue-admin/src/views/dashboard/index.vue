<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      行秋博客管理系统
    </div>
    <div>
      欢迎关注行秋的CSDN账号：<a href="https://blog.csdn.net/weixin_43042683?spm=1000.2115.3001.5343">https://blog.csdn.net/weixin_43042683?spm=1000.2115.3001.5343</a>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
