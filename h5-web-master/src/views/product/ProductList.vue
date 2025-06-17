<template>
    <van-nav-bar title="商品列表" left-text="返回" left-arrow @click-left="onClickLeft" />

    <van-card v-for="item in products" num="2" :price="item.price" :desc="item.subName" :title="item.name"
        :thumb="item.img" @click="itemClick(item)"/>
</template>

<script setup lang="ts">

import { onMounted, ref,reactive } from 'vue'
import { productApi } from '@/api/index'
import {useRoute,useRouter} from 'vue-router'
const route = useRoute()   //不带r的是获取当前页面url相关参数
const router = useRouter()  //代r的是负责页面跳转

const products = ref([])

const urlParams = reactive({
    categoryId:0,
    name:"",
})

onMounted(()=>{
    if(route.query.categoryId != undefined){
        urlParams.categoryId =  Number(route.query.categoryId)
    }
    if(route.query.name != undefined){
        urlParams.name =  route.query.name.toString()
    }


    callProductApi()
})
const  itemClick = (item:any)=>{

    router.push({name:"product-detail",query:{id:item.id}})

}
const callProductApi = ()=>{

    const apiParams = {}

    if(urlParams.name != ""){
        apiParams.name = urlParams.name
    }
    if(urlParams.categoryId != 0){
        apiParams.categoryId = urlParams.categoryId
    }

    productApi.select.call(apiParams).then((res:any)=>{
   
         products.value = res.list

    })


}

const onClickLeft = () => history.back();
</script>