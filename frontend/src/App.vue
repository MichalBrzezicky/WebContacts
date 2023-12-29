<template>
  <v-app class="d-flex">
    <Navigation/>
    <v-container class="mt-16 d-block">
      <router-view/>
    </v-container>
  </v-app>
</template>

<script>
  import Navigation from "@/components/Navigation.vue";

  export default {
    name: "App",
    components: {Navigation},
  }
</script>
