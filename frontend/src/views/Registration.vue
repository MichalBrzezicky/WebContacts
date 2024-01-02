<template>
  <div class="mt-16">
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="5" lg="3" align-self="center" >
        <v-card>
          <v-card-title class="text-h6 mb-6">Registrace</v-card-title>

          <v-card-text class="text-right">
            <v-form>
              <v-text-field label="Jméno" placeholder="Jan" required></v-text-field>
              <v-text-field  label="Příjmení" placeholder="Novák" required></v-text-field>
              <v-text-field label="Email" type="email" placeholder="mail@example.com" required></v-text-field>
              <v-text-field  label="Heslo" type="password" required></v-text-field>
            </v-form>

            <v-btn class="ml-2" color="primary">Potvrdit</v-btn>
          </v-card-text>

          <v-card-actions class="justify-center">
            <router-link to="/login">
              <span class="text-decoration-underline">Zpět na přihlášení</span>.
            </router-link>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
  export default {
    name: 'Registration',
  }
</script>
