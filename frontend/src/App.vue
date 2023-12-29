<template>
  <div id="app" class="wrapper">
    Here should be navbar
    <div class="main_content">
      <router-view/>
    </div>
  </div>
</template>

<script>
  import HelloWorld from './components/HelloWorld.vue'
  import TheWelcome from './components/TheWelcome.vue'
  export default {
    name: "App",
  }
</script>
