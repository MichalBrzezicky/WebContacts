<template>
  <div class="mt-16">
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="5" lg="3" align-self="center" >
        <v-card>
          <v-card-title class="text-h6 mb-6">Přihlášení</v-card-title>

          <v-card-text class="text-right">
            <v-form>
              <v-text-field label="Email" placeholder="mail@example.com" required></v-text-field>
              <v-text-field  label="Heslo" type="password" required></v-text-field>
            </v-form>
            <v-btn class="ml-2" color="primary">Přihlásit se</v-btn>
          </v-card-text>

          <v-divider />

          <v-card-actions class="justify-center">
            <router-link to="/registration">
              Ještě nemáte účet? <span class="text-decoration-underline">Zaregistrujte se</span>.
            </router-link>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
  export default {
    name: 'Login',
  }
</script>
