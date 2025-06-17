<template>
    <van-nav-bar title="分类" left-text="返回" left-arrow @click-left="onClickLeft" />


    <van-tree-select v-model:main-active-index="leftActiveIndex"  :items="categoryData">
        <template #content>
            <van-grid :column-num="2" icon-size="80px">
                <van-grid-item v-for="item in categoryData[leftActiveIndex]?.children" 
                :key="item.id"
                 :icon="item.img"
                 :text="item.text" 
                 :to="{name:'product-list',query:{categoryId:item.id}}"/>
            </van-grid>
        </template>
    </van-tree-select>
</template>
<script setup lang="ts">

import { onMounted, ref } from 'vue'
import { categoryApi } from '@/api/index'
const leftActiveIndex = ref(0);
const categoryData = ref([]);

onMounted(() => {

    callCategoryApi()

})
const callCategoryApi = () => {

    categoryApi.select.call().then((categorys: any) => {
        categorys.forEach((item: any) => {
            item.text = item.name
        });
     
        //使用高阶函数reduce把扁平化数据转化成树形结构数据
        const newCategorys = categorys.reduce((newArray: any, curr: any) => {
            curr.children = categorys.filter((v: any) => v.parentId === curr.id)
            if (curr.parentId == 0) {
                newArray.push(curr)
            }
            return newArray;
        }, [])

   

        categoryData.value = newCategorys

    })

}

const onClickLeft = () => history.back();

</script>