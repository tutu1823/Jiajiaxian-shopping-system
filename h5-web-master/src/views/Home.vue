<template>
    <van-search v-model="keyword" shape="round" background="#089305" placeholder="请输入搜索关键词" @search="search" />
    <van-swipe :autoplay="3000" lazy-render>
        <van-swipe-item v-for="(image, index) in images" :key="image" @click="swipeClick(index)">
            <img :src="image" class="img" />
        </van-swipe-item>
    </van-swipe>
    <van-notice-bar left-icon="volume-o" :text="noticeText" />
    <van-cell title="热卖商品" icon="hot-o" color="#ee0a24" is-link value="更多" @click="hotClick" />
    <van-grid square :column-num="3" icon-size="80px">
        <van-grid-item v-for="product in products" :key="product.id" :icon="product.img" :text="product.name"
            :to="{ name: 'product-detail', query: { id: product.id } }" />
    </van-grid>
    <van-cell title="新品推荐" icon="new-arrival-o" color="#ee0a24" is-link value="更多" @click="hotClick" />
    <van-grid square :column-num="3" icon-size="80px">
        <van-grid-item v-for="product in products" :key="product.id" :icon="product.img" :text="product.name"
            :to="{ name: 'product-detail', query: { id: product.id } }" />
    </van-grid>
</template>

<script setup lang="ts">

import { onMounted, ref } from 'vue'
import { productApi } from '@/api/index'
import {useRouter}  from 'vue-router'
const router = useRouter()

const keyword = ref('')
const images = ref(['https://img0.baidu.com/it/u=1806166729,553656438&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=282', 'https://img1.baidu.com/it/u=3980359106,2584408124&fm=253&fmt=auto&app=138&f=JPEG?w=640&h=324','https://img1.baidu.com/it/u=495120107,1201199302&fm=253&fmt=auto&app=138&f=JPEG?w=655&h=410','https://img0.baidu.com/it/u=4109665872,1675557004&fm=253&fmt=auto&app=120&f=JPEG?w=889&h=500','https://img2.baidu.com/it/u=2740400046,2604963121&fm=253&fmt=auto&app=138&f=PNG?w=800&h=500']);
const noticeText = ref("  假面骑士 W  ")
const products = ref([])
onMounted(() => {

    callProductApi()
})

const callProductApi = () => {
    productApi.select.call({ pageNum: 1, pageSize: 6 }).then((res: any) => {

        products.value = res.list

    })
}
const search = (value: string) => {
    console.log(value)
    router.push({name:"product-list",query:{name:value}})
}
const swipeClick = (index: any) => {

    console.log(index)
}
const hotClick = () => {

    console.log("您点击了热卖商品")
}
</script>

<style>
.img {
    width: 100%;
    height: 200px;
}
</style>