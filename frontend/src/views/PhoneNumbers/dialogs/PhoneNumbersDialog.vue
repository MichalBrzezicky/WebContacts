<template>
  <v-dialog v-model="visible" width="500">
    <v-card>
      <v-card-title>Nové telefonní číslo</v-card-title>

      <v-divider />

      <v-card-text>
        <PhoneNumbersForm :phone-numbers="[]" @onChange="handlePhoneNumbersChange" />
      </v-card-text>

      <v-divider />

      <v-card-actions>
        <v-spacer />
        <v-btn @click="closeDialog">Zrušit</v-btn>
        <v-btn color="primary" :disabled="loading || !isFormFilled" :loading="loading" @click="submit">Potvrdit</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<script>
  import PhoneNumbersForm from "@/views/PhoneNumbers/PhoneNumbersForm.vue";
  import _ from "lodash";
  import PhoneNumberService from "@/services/phoneNumberService.js";

  export default {
    name: 'PhoneNumbersDialog',
    components: {PhoneNumbersForm},
    props: {
      value: {
        type: Boolean,
        default: false,
      },

      contactId: {
        type: Number,
        required: true,
      }

    },

    data() {
      return {
        loading: false,
        phoneNumbers: [],
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
        return this.isEachPhoneNumberFilled
      },

      isEachPhoneNumberFilled() {
        let isTrue = this.phoneNumbers.length > 0
        this.phoneNumbers?.forEach((phoneNumber) => {
          if (_.isEmpty(phoneNumber.number) || _.isEmpty(phoneNumber.codeArea)) isTrue = false
        })

        return isTrue
      },
    },

    methods: {
      handlePhoneNumbersChange(phoneNumbers) {
        this.phoneNumbers = phoneNumbers
      },

      closeDialog() {
        this.visible = false
      },

      submit() {
        if (!this.isFormFilled) return
        this.addPhoneNumbers()
      },

      addPhoneNumbers() {
        this.loading = true
        PhoneNumberService.addAll(this.phoneNumbers, this.contactId).then((res) => {
          this.loading = false
          this.closeDialog()
          this.$emit('onSubmit')
        })
      }
    }
  }

</script>
