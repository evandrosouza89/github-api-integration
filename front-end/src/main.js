// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

//Vue.config.productionTip = false

const vuetify = createVuetify({
  components,
  directives
})

const app = createApp(App)

app.use(router)
app.use(vuetify)

app.mount('#app')
