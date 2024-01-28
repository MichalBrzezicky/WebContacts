<template>
  <v-dialog v-model="visible" width="500">
    <v-card>
      <v-card-title>Editace kontaktu</v-card-title>

      <v-divider />

      <v-card-text>
        <v-text-field variant="outlined" :disabled="loading" v-model="contactVal.name" type="text" label="Název kontaktu" placeholder="Josef Janko" required />
      </v-card-text>

      <v-divider />

      <v-card-actions>
        <v-spacer />
        <v-btn @click="closeDialog">Zrušit</v-btn>
        <v-btn color="primary" :disabled="loading || !isFormFilled || !areValuesChanged" :loading="loading" @click="submit">Potvrdit</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
  import _ from "lodash";
  import ContactService from "@/services/contactService.js";
  import PhoneNumbersForm from "@/views/PhoneNumbers/PhoneNumbersForm.vue";

  export default {
    name: 'ContactNameDialog',
    components: {PhoneNumbersForm},
    props: {
      value: {
        type: Boolean,
        default: false,
      },

      contact: {
        type: Object
      }
    },

    data() {
      return {
        loading: false,
        contactVal: {},
      }
    },


    computed: {
      visible: {
        get() {
          return this.value;
        },
        set(val) {
          this.$emit('onClose', val)
        }
      },

      isFormFilled() {
        return this.contactVal.name
      },

      areValuesChanged() {
        return !_.isEqual(this.contact, this.contactVal)
      },
    },

    methods: {
      closeDialog() {
        this.visible = false
      },

      submit() {
        if (!this.isFormFilled) return
        this.editContact()
      },

      editContact() {
        this.loading = true
        ContactService.update(this.contactVal?.id, this.contactVal).then((res) => {
          this.loading = false
          this.closeDialog()
          this.$emit('onSubmit')
        })
      },
    },


    created() {
      this.contactVal = _.cloneDeep(this.contact) || {...DEFAULT_CONTACT}
    }

  }

</script>
