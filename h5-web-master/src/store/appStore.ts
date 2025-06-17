import { defineStore } from 'pinia'

export default defineStore({
    id: "appStore",
    state: () => {
        return {
                 userId:0,
                 phone:"",
                         
        }
    },
    getters: {},
    actions: {},
    //开启数据持久化
    persist: {
        enabled:true,
        strategies: [
            {
                key: 'jjx-app',
                storage: localStorage,
            }
        ]
    }
})

