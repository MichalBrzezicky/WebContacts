<template>
  <v-app class="d-flex">
    <Navigation/>
    <v-main class="ma-5">
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
  import Navigation from "@/components/Navigation.vue";

  export default {
    name: "App",
    components: {Navigation},
  }
</script>
