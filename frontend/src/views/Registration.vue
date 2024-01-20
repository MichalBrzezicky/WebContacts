<template>
  <div class="mt-16">
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="5" lg="3" align-self="center" >
        <v-card>
          <v-card-title class="text-h6 mb-6">Registrace</v-card-title>

          <v-card-text class="text-right">
            <v-alert v-if="error"
                     class="text-left"
                     density="compact"
                     type="error"
                     variant="tonal"
                     :text="error"
            ></v-alert>

            <v-text-field v-model="user.name" label="Jméno" placeholder="Jan" required></v-text-field>
            <v-text-field v-model="user.surname" label="Příjmení" placeholder="Novák" required></v-text-field>
            <v-text-field v-model="user.email" label="Email" type="email" placeholder="mail@example.com" required></v-text-field>
            <v-text-field v-model="user.password" label="Heslo" type="password" required></v-text-field>
            <v-btn :disabled="!isFormFilled" @click="handleSubmit" class="ml-2" color="primary">Potvrdit</v-btn>
          </v-card-text>

          <v-divider />

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
    data() {
      return {
        returnUrl: '',
        error: '',
        submitted: false,
        loading: false,
        user: {
          name: '',
          surname: '',
          password: '',
          email: '',
        }
      }
    },

    created() {
      this.$store.dispatch("auth/logout")
      this.returnUrl = this.$route?.query?.returnUrl || '/'
    },

    computed: {
      isFormFilled() {
        return this.user.name && this.user.surname && this.user.email && this.user.password
      }
    },

    methods: {
      handleSubmit() {
        this.submitted = true
        if (!this.isFormFilled) {
          this.error = 'Vyplňte celý formulář včetně jména, příjmení, emailu a hesla'
          return
        }

        this.loading = true
        this.$store.dispatch("auth/register",this.user).then(
          data => this.$router.push(this.returnUrl),
          error => {
            this.error = error
            this.loading = false
          }
        )
      },
    },
  }
</script>
