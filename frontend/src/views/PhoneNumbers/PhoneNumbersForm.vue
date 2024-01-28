<template>
  <div>
    <div v-for="(phoneNumber, index) in phoneNumbersVal" :key="index" class="pt-2 d-flex align-center">
      <v-text-field v-model="phoneNumber.codeArea" hide-details type="text" label="Předčíslí" prepend-inner-icon="mdi-plus" variant="outlined" class="pr-2" style="max-width: 150px" placeholder="420" required />
      <v-text-field v-model="phoneNumber.number" hide-details type="text" label="Telefonní číslo" placeholder="72648953" variant="outlined" />

      <v-icon v-if="index > 0" class="px-4 ml-2" @click="removePhoneNum(index)">mdi-close</v-icon>
    </div>

    <v-btn class="mt-3" size="small" variant="text" @click="addNextPhoneNum">
      Přidat další tel. číslo
    </v-btn>
  </div>
</template>

<script>
  import _ from 'lodash'

  const DEFAULT_PHONE_NUMBER = {id: null, codeArea: '', number: ''}
  export default {
    name: 'PhoneNumbersForm',
    props: {
      phoneNumbers: {
        type: Array,
        required: true,
      },
    },

    data() {
      return {
        phoneNumbersVal: [],
      }
    },

    watch: {
      phoneNumbersVal: {
        handler(newValue, oldValue) {
          this.$emit('onChange', newValue)
        },

        deep: true,
      }
    },

    methods: {
      removePhoneNum(index) {
        this.phoneNumbersVal.splice(index, 1);
      },

      addNextPhoneNum() {
        this.phoneNumbersVal.push(_.cloneDeep(DEFAULT_PHONE_NUMBER))
      },
    },

    created() {
      if (this.phoneNumbers?.length) this.phoneNumbersVal = _.cloneDeep(this.phoneNumbers)
      else this.addNextPhoneNum()
    }
  }
</script>
