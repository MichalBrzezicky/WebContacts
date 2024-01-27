export default {
  name: 'DialogMixin',

  data() {
    return {
      dialog: false,
    }
  },

  methods: {
    openDialog() {
      this.dialog = true
    },

    closeDialog() {
      this.dialog = false
    }
  },
}
