import http  from "@/http/index"


export default{

    select:{
        name:"商品查询",
        url:"/api/product",
        call: async function (params:any ={}) {
            return await http.get(this.url,params)
            
        }
    },

}
