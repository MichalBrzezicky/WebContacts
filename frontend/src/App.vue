<template>
  <v-app class="d-flex">
    <Navigation/>
    <v-main>
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
