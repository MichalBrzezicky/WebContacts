<template>
  <v-expansion-panel>
    <v-expansion-panel-title>
      {{ contactLocal.name }}
      <v-spacer />
      <MenuExpansion :options="contactOptions" />
    </v-expansion-panel-title>
    <v-expansion-panel-text>
      <PhoneNumbersList :contactId="contactLocal.id" :phone-numbers="contactLocal.phoneNumbers" />
    </v-expansion-panel-text>

    <ContactNameDialog :value="dialog" :contact="contactLocal" @onClose="closeDialog" @onSubmit="refreshContact" />
    <PhoneNumbersDialog :value="phoneNumbersDialog" :contact-id="contactLocal.id" @onClose="closePhoneNumbersDialog" @onSubmit="refreshContact" />
  </v-expansion-panel>
</template>

<script>
  import NoData from "@/components/NoData.vue";
  import PhoneNumbersList from "@/views/PhoneNumbers/PhoneNumbersList.vue";
  import MenuExpansion from "@/components/menu/MenuExpansion.vue";
  import ContactService from "@/services/contactService.js";
  import DialogMixin from "@/mixins/DialogMixin.js";
  import PhoneNumbersDialog from "@/views/PhoneNumbers/dialogs/PhoneNumbersDialog.vue";
  import _ from 'lodash';
  import ContactNameDialog from "@/views/Contacts/dialogs/ContactNameDialog.vue";

  export default {
    name: 'ContactExpansionPanel',
    mixins: [DialogMixin],
    components: {ContactNameDialog, PhoneNumbersDialog, MenuExpansion, PhoneNumbersList, NoData},
    props: {
      contact: {
        type: Object,
        required: true,
      }
    },

    data() {
      return {
        phoneNumbersDialog: false,
        contactLocal: {}
      }
    },

    computed: {
      contactOptions() {
        return [
          {title: 'Přejmenovat kontakt', icon: 'mdi-pencil', action: this.openDialog},
          {title: 'Přidat telefonní čísla', icon: 'mdi-plus', action: this.openPhoneNumbersDialog},
          {title: 'Odstranit kontakt', icon: 'mdi-delete', action: this.removeContact},
        ]
      },
    },

    methods: {
      refreshContact() {
        this.loading = true
        ContactService.getById(this.contactLocal.id).then((res) => {
          if (res?.data) this.contactLocal = res.data
        }).finally(() => {
          this.loading = false
        })
      },

      removeContact() {
        ContactService.delete(this.contactLocal.id).then(result => {
          this.$emit('onContactDelete')
        })
      },

      openPhoneNumbersDialog() {
        this.phoneNumbersDialog = true
      },

      closePhoneNumbersDialog() {
        this.phoneNumbersDialog = false
      },
    },

    created() {
      this.contactLocal = _.cloneDeep(this.contact)
    }
  }
</script>

<style scoped>
  .v-expansion-panel-text >>> .v-expansion-panel-text__wrapper {
    margin: 0 !important;
    padding: 0 !important;
  }
</style>
