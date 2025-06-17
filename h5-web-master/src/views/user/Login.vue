<template>
    <van-nav-bar title="用户登录" left-text="返回" left-arrow @click-left="onClickLeft" />

    <van-form @submit="onSubmit">
        <van-cell-group inset>
            <van-field v-model="formData.phone" name="手机号" label="手机号" placeholder="请输入手机号" :rules="[
                { required: true, message: '请填写手机号' },
                { validator: myValidator, message: '请输入11位手机号' }
            ]" />

            <van-field v-model="formData.password" type="password" name="密码" label="密码" placeholder="密码" :rules="[
                { required: true, message: '请填写密码' },
                { pattern: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{6,10}$/, message: '必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间' }
            ]" />

        </van-cell-group>
        <div style="margin: 16px;">
            <van-button round block type="primary" native-type="submit">
                登录
            </van-button>
        </div>
    </van-form>
    <van-cell value="去注册" is-link :to="{ name: 'user-regist' }">
        <template #title>
            <span class="custom-title">没有账号</span>
            <van-icon name="question-o" />
        </template>
    </van-cell>
</template>
<script setup lang="ts">
import { onMounted, ref, reactive } from 'vue'
import { userApi } from '@/api/index'
import { showToast, showSuccessToast, showFailToast } from 'vant';
import { useRoute, useRouter } from 'vue-router'
import  appStore  from '@/store/appStore'
import {storeToRefs}  from "pinia"
let { userId,phone  } =   storeToRefs(appStore())



const route = useRoute()   //不带r的是获取当前页面url相关参数
const router = useRouter()  //代r的是负责页面跳转

const formData = reactive({
    phone: '',
    password: "",
})
const myValidator = (val: any) => {

    if (val.length === 11) {
        return true
    } else {
        return false;
    }


};
const onSubmit = (values: any) => {
 
    //{  //用户登录成功
    // "id": 5,
    // "phone": "13275568968",
    // }

    //  { //登录失败，手机号或密码错误
    // "id": 0,
    // "phone": null,
    // }
    userApi.login.call({ phone: formData.phone, password: formData.password }).then((res: any) => {

        if (res.id === 0) {

            showFailToast("手机号或密码错误")

        } else {

            showSuccessToast("登录成功")
            //必须把登录之后的用户信息存某一个位置 {"id":6,"phone":"15936138701"}

            userId.value = res.id
            phone.value = res.phone

            router.push({name:"home"})

        }

    })


};
onMounted(() => {


})


const onClickLeft = () => history.back();
</script>
<style scoped>

</style>