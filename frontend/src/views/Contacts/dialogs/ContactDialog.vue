<template>
  <v-dialog v-model="visible" width="500">
    <v-card>
      <v-card-title>
        <template v-if="isEditation">Editace kontaktu</template>
        <template v-else>Nový kontakt</template>
      </v-card-title>
      <v-divider />
      <v-card-text>
        <v-text-field variant="outlined" :disabled="loading" v-model="contactVal.name" type="text" label="Název kontaktu" placeholder="Josef Janko" required />

        <PhoneNumbersForm :phone-numbers="contactVal.phoneNumbers" @onChange="handlePhoneNumbersChange"/>
      </v-card-text>

      <v-divider />
      <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn @click="closeDialog">Zrušit</v-btn>
        <v-btn color="primary" :disabled="loading || !isFormFilled || !areValuesChanged" :loading="loading" @click="submit">Potvrdit</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
  import ContactService from "@/services/contactService.js";
  import _ from "lodash";
  import PhoneNumbersForm from "@/views/PhoneNumbers/PhoneNumbersForm.vue";

  const DEFAULT_CONTACT = {id: null, name: '', phoneNumbers: []}
  export default {
    name: 'ContactDialog',
    components: {PhoneNumbersForm},
    props: {
      value: {
        type: Boolean,
        default: false,
      },
      folderId: {
        type: String,
        required: true
      },
      contact: {
        type: Object
      }
    },

    data() {
      return {
        loading: false,
        contactVal: {}
      }
    },

    watch: {
      contactVal: {
        handler(newVal, oldVal) {
          console.log('Changed whole contactVal', newVal)
        },
        deep: true,
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
        return this.contactVal.name && this.contactVal.phoneNumbers.length && this.isEachPhoneNumberFilled
      },

      areValuesChanged() {
        return !_.isEqual(this.contact, this.contactVal)
      },

      isEachPhoneNumberFilled() {
        let isTrue = true
        this.contactVal?.phoneNumbers.forEach((phoneNumber) => {
          if (_.isEmpty(phoneNumber.number) || _.isEmpty(phoneNumber.codeArea)) isTrue = false
        })

        return isTrue
      },

      isEditation() {
        return !!this.contactVal.id
      }
    },

    methods: {
      handlePhoneNumbersChange(phoneNumbers) {
        this.contactVal.phoneNumbers = phoneNumbers
      },

      closeDialog() {
        this.visible = false
      },

      submit() {
        if (!this.isFormFilled) return
        if (this.isEditation) {
          this.editContact()
        } else {
          this.saveContact()
        }
      },

      editContact() {
        this.loading = true
        ContactService.update(this.contactVal?.id, this.contactVal).then((res) => {
          this.loading = false
          this.closeDialog()
          this.$emit('onSubmit')
        })
      },

      saveContact() {
        this.loading = true
        ContactService.add(this.contactVal, this.folderId).then((res) => {
          this.loading = false
          this.closeDialog()
          this.$emit('onSubmit')
        })
      }
    },

    created() {
      this.contactVal = _.cloneDeep(this.contact) || {...DEFAULT_CONTACT}
    }
  }
</script>
