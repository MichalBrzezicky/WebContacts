<template>
  <div class="mt-16">
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="5" lg="3" align-self="center" >
        <v-card>
          <v-card-title class="text-h6 mb-6">Přihlášení</v-card-title>

          <v-card-text class="text-right">
            <v-alert v-if="error"
              class="text-left"
              density="compact"
              type="error"
              variant="tonal"
              :text="error"
            ></v-alert>

            <v-text-field v-model="user.email" type="email" label="Email" placeholder="mail@example.com" required />
            <v-text-field v-model="user.password" label="Heslo" type="password" required />
            <v-btn :disabled="!isFormFilled" @click="handleSubmit" class="ml-2" color="primary">Přihlásit se</v-btn>
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
    data() {
      return {
        submitted: false,
        loading: false,
        returnUrl: '',
        error: '',
        user: {
          email: '',
          password: '',
        }
      }
    },

    created () {
      this.$store.dispatch('auth/logout');
      this.returnUrl = this.$route.query.returnUrl || '/';
    },

    computed: {
      isFormFilled() {
        return this.user?.email && this.user.password
      }
    },

    methods: {
      handleSubmit() {
        this.submitted = true;

        if (!this.isFormFilled) {
          this.error = 'Pro pokračování zadejte email a heslo'
          return;
        }

        this.loading = true;
        this.$store.dispatch("auth/login", this.user).then(
          data => this.$router.push(this.returnUrl),
          error => {
            console.log(error)
            this.error = error?.response?.data?.message || 'Nastala neočekávaná chyba';
            this.loading = false;
          }
        )
      },
    }
  }
</script>
