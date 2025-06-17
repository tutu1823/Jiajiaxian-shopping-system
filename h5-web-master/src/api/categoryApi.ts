import http  from "@/http/index"


export default{

    select:{
        name:"分类查询",
        url:"/api/category",
        call: async function (params:any ={}) {
            return await http.get(this.url,params)
            
        }
    },

}
