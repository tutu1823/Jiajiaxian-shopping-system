<template>
    <van-nav-bar title="我的" left-text="返回" left-arrow @click-left="onClickLeft" />
    <van-row>
        <van-col>
            <van-image round width="3rem" height="3rem" :src="userFace" />
        </van-col>
        <van-col class="user" @click="login"> {{ loginInfo }} </van-col>
    </van-row>
    <van-image width="100%" height="120px"
        src="https://m15.360buyimg.com/mobilecms/jfs/t1/20859/15/20135/125872/63a8ff9eE1f72b91d/734cfd665232b31c.jpg!cr_1053x420_4_0!q70.jpg" />

    <van-grid :column-num="3">
        <van-grid-item icon="peer-pay" text="待支付" badge="1" />
        <van-grid-item icon="logistics" text="待收货" badge="2" />
        <van-grid-item icon="orders-o" text="全部订单" />
    </van-grid>
    <van-image width="100%" height="80px"
        src="https://m15.360buyimg.com/mobilecms/s1062x420_jfs/t1/19535/26/20349/38863/63aa4f80Fa0f42c16/e8006c8573dcf124.jpg!cr_1053x420_4_0!q70.jpg" />

    <van-grid :column-num="5">
        <van-grid-item icon="coupon-o" text="优惠券" />
        <van-grid-item icon="send-gift-o" text="京豆" />
        <van-grid-item icon="wap-home-o" text="地址管理" />
        <van-grid-item icon="other-pay" text="积分" />
        <van-grid-item icon="photo-o" text="优惠券" />
        <van-grid-item icon="photo-o" text="优惠券" />
        <van-grid-item icon="photo-o" text="优惠券" />
        <van-grid-item icon="photo-o" text="优惠券" />
        <van-grid-item icon="photo-o" text="优惠券" />
        <van-grid-item icon="photo-o" text="优惠券" />
    </van-grid>

    <van-cell title="猜你喜欢" value="更多" />

    <van-grid square :column-num="3" icon-size="80px">
        <van-grid-item v-for="product in products" :key="product.id" :icon="product.img" :text="product.name"
            :to="{ name: 'product-detail', query: { id: product.id } }" />
    </van-grid>


</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { productApi } from '@/api/index'
import { useRoute, useRouter } from 'vue-router'
import appStore from '@/store/appStore'
import { storeToRefs } from "pinia"
import { showToast, showConfirmDialog } from 'vant'
let { userId, phone } = storeToRefs(appStore())
const products = ref([])
const route = useRoute()   //不带r的是获取当前页面url相关参数
const router = useRouter()  //代r的是负责页面跳转

const userFace = ref("https://fastly.jsdelivr.net/npm/@vant/assets/icon-demo.png")
const loginInfo = ref("登录/注册")

onMounted(() => {

    if (phone.value != "") {
        loginInfo.value = phone.value
        userFace.value = "https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg"
    }
    callProductApi()
})
const login = () => {

    if (userId.value != 0) {

        showConfirmDialog({
            title: '退出',
            message: '确定退出登录？',
        })
            .then(() => {
                userId.value = 0
                phone.value = ""
                showToast("退出成功")
                router.push({ name: "home" })
            })
            .catch(() => {

            });


        return
    }

    router.push({ name: "user-login" })

}


const callProductApi = () => {

    productApi.select.call({ pageNum: 1, pageSize: 6 }).then((res: any) => {

        products.value = res.list

    })
}

const onClickLeft = () => history.back();
</script>
<style scoped>
.user {
    line-height: 50px;
    color: tomato;
    margin-left: 10px;
}
</style>