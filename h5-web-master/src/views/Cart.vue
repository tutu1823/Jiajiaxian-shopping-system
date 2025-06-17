<template>
    <van-nav-bar title="购物车" left-text="返回" left-arrow @click-left="onClickLeft" />

    <van-checkbox-group v-model="selectProducts" ref="checkboxGroup">
        <van-swipe-cell v-for="(item,index) in products">
            <van-row>
                <van-col span="2" class="leftbox">
                    <van-checkbox :name="item"></van-checkbox>
                </van-col>
                <van-col span="22">

                    <van-card :price="item.price" :desc="item.productDesc" :title="item.productName"
                        :thumb="item.productImg">

                        <template #num>
                            <van-stepper v-model="item.qty" @change="changeQty(item)" />
                        </template>

                    </van-card>
                </van-col>
            </van-row>
            <template #right>
                <van-button square text="删除" type="danger" class="delete-button" @click="deleteCart(item,index)" />
            </template>
        </van-swipe-cell>
    </van-checkbox-group>
    <van-empty description="购物车空空如也" v-if="products.length === 0" />
    <van-submit-bar :price="totalAmount" button-text="提交订单" @submit="onSubmit">
        <van-checkbox v-model="selectAll" @change="checkAll">全选</van-checkbox>

    </van-submit-bar>

</template>

<script setup lang="ts">
import { onMounted, ref, watch } from 'vue'
import { cartApi } from '@/api/index'
import { showToast } from 'vant';
import { useRoute, useRouter } from 'vue-router'
import  appStore  from '@/store/appStore'  
import {storeToRefs}  from "pinia"
let { userId,phone} =   storeToRefs(appStore())

const route = useRoute()   //不带r的是获取当前页面url相关参数
const router = useRouter()  //代r的是负责页面跳转
const selectProducts = ref([])
const selectAll = ref(false)
const totalAmount = ref(0)
const products = ref([])
const checkboxGroup = ref();
onMounted(() => {

    callCartApi()
})
const callCartApi = () => {
    cartApi.select.call({ userId: userId.value }).then((res: any) => {
        products.value = res
        selectProducts.value = res        
    })
}
const  deleteCart = (item:any,index:number)=>{

   cartApi.delete.call({id:item.id}).then((res:any)=>{
         if(res === 1){
            showToast("删除成功")
            products.value.splice(index,1)
            computeTotalAmount()
         }
   })
}
const changeQty = (item: any) => {

    cartApi.updateQty.call({id:item.id,qty:item.qty}).then((res:any)=>{
       if(res===1){
          computeTotalAmount()
       }
    })

      
}
const computeTotalAmount = () => {

    totalAmount.value = 0

    selectProducts.value.forEach((item: any) => {

        totalAmount.value += item.price * item.qty * 100
    })
}

watch(() => selectProducts.value, (newValue, oldValue) => {

  
    selectAll.value = selectProducts.value.length === products.value.length

    computeTotalAmount()

}, { immediate: true })



const onSubmit = () => {

    console.log(selectProducts)

}






const checkAll = (b: boolean) => {

    if (b === true) {
        checkboxGroup.value.toggleAll(true);
    }
}

const onClickLeft = () => history.back();
</script>
<style scoped>
.leftbox {
    height: 100px;
    display: flex;
    align-items: center;
    justify-self: center;
}

.delete-button {
    height: 100%;
}
</style>