import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import '@mdi/font/css/materialdesignicons.css'; // Pro Material Design Icons
import '@fortawesome/fontawesome-free/css/all.css'; // Pro FontAwesome

import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import router from "@/router/index.js";
import * as colors from "vuetify/util/colors";
import store from "@/store/index.js";
import axios from "axios";
import VueAxios from "vue-axios";

const vuetify = createVuetify({
  theme: {
    themes: {
      light: {
        dark: false,
        colors: {
          primary: colors.teal.darken3,
          secondary: colors.teal.lighten2,
        }
      },
    },
  },
  components,
  directives
})

createApp(App)
  .use(vuetify)
  .use(router)
  .use(store)
  .use(VueAxios, axios)
  .mount('#app')
