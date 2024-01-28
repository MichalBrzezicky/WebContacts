<template>
  <div>
    <v-list-item :value="phoneNumber">
      <template v-slot:prepend>
        <v-icon icon="mdi-phone" />
      </template>
      <v-list-item-title>+ ({{phoneNumber.codeArea}}) {{phoneNumber.number}}</v-list-item-title>
      <template v-slot:append>
        <v-icon @click="openDialog" class="px-4">mdi-pencil</v-icon>
        <v-icon @click="removePhoneNumber(phoneNumber.id)" class="px-4">mdi-delete</v-icon>
      </template>
    </v-list-item>
    <SinglePhoneNumberDialog :value="dialog" :phone-number="phoneNumber" @onClose="closeDialog" @onSubmit="$emit('OnPhoneNumberChange')" />
  </div>
</template>

<script>
  import PhoneNumberService from "@/services/phoneNumberService.js";
  import DialogMixin from "@/mixins/DialogMixin.js";
  import SinglePhoneNumberDialog from "@/views/PhoneNumbers/dialogs/SinglePhoneNumberDialog.vue";
  import ContactDialog from "@/views/Contacts/dialogs/ContactDialog.vue";

  export default {
    name: 'PhoneNumberListItem',
    components: {ContactDialog, SinglePhoneNumberDialog},
    mixins: [DialogMixin],
    props: {
      phoneNumber: {
        type: Object,
        required: true,
      }
    },

    methods: {
      removePhoneNumber() {
        PhoneNumberService.delete(this.phoneNumber.id).then((result) => {
          this.$emit('onPhoneNumberDelete')
        })
      }
    }
  }
</script>
