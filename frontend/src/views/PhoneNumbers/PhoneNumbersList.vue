<template>
  <div class="text-center">
    <v-progress-circular v-if="loading" class="my-5"  :size="35" :width="4" indeterminate color="primary" />
    <v-list v-else-if="phoneNumbersLocal.length" class="pa-0 ma-0 text-left">
        <PhoneNumberListItem
          v-for="(phoneNumber, index) in phoneNumbersLocal"
          :key="index"
          :phone-number="phoneNumber"
          @onPhoneNumberDelete="refreshPhoneNumbers"
          @OnPhoneNumberChange="refreshPhoneNumbers"
        />
    </v-list>
    <NoData v-else class="text-left" phone-numbers  />
  </div>
</template>

<script>
  import NoData from "@/components/NoData.vue";
  import PhoneNumberService from "@/services/phoneNumberService.js";
  import PhoneNumberListItem from "@/views/PhoneNumbers/PhoneNumberListItem.vue";
  import _ from "lodash";

  export default {
    name: 'PhoneNumbersList',
    components: {PhoneNumberListItem, NoData},
    data() {
      return {
        loading: false,
        phoneNumbersLocal: [],
      }
    },

    props: {
      contactId: {
        type: Number,
        required: true,
      },

      phoneNumbers: {
        type: Array,
        required: true,
      },
    },

    watch: {
      phoneNumbers: {
        handler(newVal, oldVal) {
          this.phoneNumbersLocal = _.cloneDeep(this.phoneNumbers)
        },
        deep: true,
      }
    },

    methods: {
      refreshPhoneNumbers() {
        this.loading = true
        PhoneNumberService.getAll({id: this.contactId}).then((result) => {
          if (result?.data) {
            this.phoneNumbersLocal = result.data
          }
        }).finally(() => {
          this.loading = false
        })
      },
    },

    created() {
      this.phoneNumbersLocal = _.cloneDeep(this.phoneNumbers)
    }
  }
</script>
