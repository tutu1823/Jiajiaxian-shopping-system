import { createApp } from 'vue'

import App from '@/App.vue'

//加入路由
import router from '@/router/index';

//使用vant
import 'vant/lib/index.css';
import vant from 'vant'
//使用pinia
import store  from '@/store/index'

const app = createApp(App)
//使用vant
app.use(vant);
app.use(router)
//使用store存储
app.use(store)

app.mount('#app')

