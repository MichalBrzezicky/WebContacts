export default {
  name: "UserMixin",
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
      this.$router.push('/login')
    }
  },

  computed: {
    isUserLogged () {
      return this.$store.getters["auth/isLoggedIn"];
    },

    getLoggedUser () {
      return this.$store.getters["auth/loggedUser"];
    },
  }

}
