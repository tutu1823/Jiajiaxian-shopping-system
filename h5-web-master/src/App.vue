

<template>

  <div class="content">
    <router-view>

    </router-view>
  </div>



  <van-tabbar v-model="active" route>
    <van-tabbar-item icon="home-o" to="/">首页</van-tabbar-item>
    <van-tabbar-item icon="cluster-o" to="/category">分类</van-tabbar-item>
    <van-tabbar-item icon="aim" to="/finder" dot>发现</van-tabbar-item>
    <van-tabbar-item icon="shopping-cart-o" badge="改" to="/cart">购物车</van-tabbar-item>
    <van-tabbar-item icon="user-circle-o" to="/my">我的</van-tabbar-item>
  </van-tabbar>

</template>
<script setup lang="ts">
import { ref } from 'vue'
const active = ref(0);
</script>
<style scoped>
.content{

   margin-bottom: 50px;
   padding-left: 8px;
   padding-right: 8px;
   padding-top: 8px;
}

</style>