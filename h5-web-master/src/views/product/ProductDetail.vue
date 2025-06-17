
<template>
    <van-nav-bar title="商品详情" left-text="返回" left-arrow @click-left="onClickLeft" />
    <van-image width="100%" height="200" :src="productData.img" />
    <van-cell :title="productData.name" :value="'¥ ' + productData.price" value-class="price" />
    <div class="subName">
        {{ productData.subName }}
    </div>
    <van-cell title="商品详情" icon="description" />

    <div class="brief">
        <div v-html="productData.brief"></div>
    </div>

    <van-action-bar>
        <van-action-bar-icon icon="chat-o" text="客服" @click="onClickIcon" />
        <van-action-bar-icon icon="cart-o" text="购物车" @click="onClickIcon" />
        <van-action-bar-icon icon="shop-o" text="店铺" @click="onClickIcon" />
        <van-action-bar-button type="danger" text="加入购物车" @click="addCart" />
    </van-action-bar>

</template>
<script setup lang="ts">
import { onMounted, ref, reactive } from 'vue'
import { productApi, cartApi } from '@/api/index'
import { showToast,showSuccessToast, showFailToast } from 'vant';
import { useRoute, useRouter } from 'vue-router'
import  appStore  from '@/store/appStore'  
import {storeToRefs}  from "pinia"
let { userId,phone} =   storeToRefs(appStore())

const route = useRoute()   //不带r的是获取当前页面url相关参数
const router = useRouter()  //代r的是负责页面跳转



const productData = reactive({
    id:0,
    name: "",
    subName: "",
    brief: "",
    price: 0,
    img: ""
})

onMounted(() => {

    productData.id = Number(route.query.id)

    callProductApi()
})

const callProductApi = () => {

    productApi.select.call({ id: productData.id }).then((res: any) => {

        productData.name = res.list[0].name
        productData.subName = res.list[0].subName
        productData.brief = res.list[0].brief
        productData.price = res.list[0].price
        productData.img = res.list[0].img
    })

}
const onClickIcon = () => {

    router.push({ name: "cart" })

};
const addCart = () => {

    const params = {
        productId: productData.id,
        productName: productData.name,
        productImg: productData.img,
        productDesc:productData.subName,
        price: productData.price,
        qty: 1,
        userId: userId.value
    }

    cartApi.insert.call(params).then((res: any) => {
        if(res ===1){
            showSuccessToast('加入购物车成功');
        } else {

            showFailToast('加入购物车失败');
        }
    })

}
const onClickLeft = () => history.back();
</script>



<style>
.subName {
    color: #e4393c;
    font-size: 12px;
    padding: 0px 10px;
}

.brief {
    color: #ccc;
    font-size: 12px;
    padding: 0px 10px;
    line-height: 25px;
}

.brief img {
    width: 100%;
    height: 100%;
}

.van-action-bar {

    z-index: 2;
}
</style>