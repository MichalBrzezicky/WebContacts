<template>
  <v-app-bar :elevation="8" color="primary">
    <v-app-bar-nav-icon>
      <v-icon variant="text" icon="mdi-phone-log"></v-icon>
    </v-app-bar-nav-icon>

    <v-app-bar-title>Moje webové kontakty</v-app-bar-title>

    <div class="text-right">
      <v-btn variant="text" icon="mdi-account"/>

      <v-btn variant="text" icon="mdi-logout"/>

      <v-btn variant="text" icon="mdi-login"/>
    </div>

  </v-app-bar>
</template>

<script>
  export default {
    name: 'Navigation'
  }

</script>
