<template>
  <v-dialog v-model="visible" width="500">
    <v-card>
      <v-card-title>Editace telefonního čísla</v-card-title>

      <v-divider />

      <v-card-text class="pt-2 d-flex align-center">
        <v-text-field v-model="phoneNumberVal.codeArea" hide-details type="text" label="Předčíslí" prepend-inner-icon="mdi-plus" variant="outlined" class="pr-2" style="max-width: 150px" placeholder="420" required />
        <v-text-field v-model="phoneNumberVal.number" hide-details type="text" label="Telefonní číslo" placeholder="72648953" variant="outlined" />
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
  import _ from 'lodash'
  import PhoneNumberService from "@/services/phoneNumberService.js";

  export default {
    name: 'SinglePhoneNumberDialog',
    props: {
      value: {
        type: Boolean,
        default: false,
      },
      phoneNumber: {
        type: Object,
        required: true,
      }
    },

    data() {
      return {
        loading: false,
        phoneNumberVal: {},
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
        return this.phoneNumberVal.codeArea && this.phoneNumberVal.number
      },

      areValuesChanged() {
        return !_.isEqual(this.phoneNumber, this.phoneNumberVal)
      },
    },

    methods: {
      submit() {
        if (!this.isFormFilled) return
        this.editPhoneNumber()
      },

      editPhoneNumber() {
        this.loading = true
        PhoneNumberService.update(this.phoneNumberVal?.id, this.phoneNumberVal).then((res) => {
          this.loading = false
          this.closeDialog()
          this.$emit('onSubmit')
        })
      },

      closeDialog() {
        this.visible = false
      },
    },

    created() {
      this.phoneNumberVal = _.cloneDeep(this.phoneNumber)
    }
  }
</script>
