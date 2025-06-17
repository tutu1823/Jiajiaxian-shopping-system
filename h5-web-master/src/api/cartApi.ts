import http  from "@/http/index"


export default {

    insert:{
        name:"加入购物车",
        url:"/api/cart",
        call: async function (params:any ={}) {
            return await http.post(this.url,params)
            
        }
    },

    select:{
        name:"购物车查询",
        url:"/api/cart",
        call: async function (params:any ={}) {
            return await http.get(this.url,params)
            
        }
    },
    updateQty:{
        name:"更改商品数量",
        url:"/api/cart/update/qty",
        call: async function (params:any ={}) {
            return await http.get(this.url,params)
            
        }
    },
    delete:{
        name:"更改商品数量",
        url:"/api/cart",
        call: async function (params:any ={}) {
            return await http.delete(this.url,params)
            
        }
    },
}
