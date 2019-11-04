import Vue from 'vue';
import App from './App.vue';
import router from './router';
import vuetify from './plugins/vuetify';
import Firebase from '@/firebase';

Vue.config.productionTip = false;
Firebase.init();

new Vue({
  router,
  vuetify,
  render: h => h(App),
}).$mount('#app');
