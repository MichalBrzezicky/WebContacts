<template>
  <v-app-bar :elevation="8" color="primary">
    <v-app-bar-nav-icon>
      <router-link :to="'/'" >
        <v-icon variant="text" color="white" icon="mdi-phone-log"></v-icon>
      </router-link>
    </v-app-bar-nav-icon>

    <v-app-bar-title>Moje webové kontakty</v-app-bar-title>

    <div class="text-right mr-5">
      <v-btn v-if="isUserLogged" variant="text" :title="'Zobrazit profil'" icon="mdi-account"/>

      <v-btn v-if="isUserLogged" variant="text" :title="'Odhlásit se'" icon="mdi-logout" @click="logout"/>

      <v-btn v-if="!isUserLogged" variant="text" :title="'Přejít k přihlášení'" icon="mdi-login" @click="goOnLogin"/>
    </div>

  </v-app-bar>
</template>

<script>
  import UserMixin from "@/mixins/UserMixin.js";

  export default {
    name: 'Navigation',
    mixins: [UserMixin],
    methods: {
      goOnLogin() {
        this.$router.push('/login')
      }
    }
  }

</script>
