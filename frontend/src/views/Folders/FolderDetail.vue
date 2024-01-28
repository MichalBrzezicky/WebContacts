<template>
  <div>
    <v-card class="elevation-0">
      <div class="d-flex align-center mx-4 mt-2">
        <v-btn class="mt-2" @click="goToFolders" variant="text" icon title="Zpět na přehled složek">
          <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <div>
          <v-card-title>{{ folder.name }}</v-card-title>
          <v-card-subtitle>{{ folder.title }}</v-card-subtitle>
        </div>
        <v-spacer />
        <v-btn class="mt-2" @click="openDialog" icon color="primary" title="Vytvořit nový kontakt">
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </div>
      <v-card-text>
        <v-expansion-panels v-if="!emptyContacts" multiple>
          <v-expansion-panel
            v-for="(contact, index) in contacts"
            :key="index"
            :title="contact.name"></v-expansion-panel>
        </v-expansion-panels>
        <NoData contacts v-else />
      </v-card-text>
    </v-card>

    <ContactDialog v-if="dialog" :folder-id="id" :value="dialog" @onClose="closeDialog" @onSubmit="refreshContacts" />
  </div>
</template>

<script>
  import FolderService from "@/services/folderService.js";
  import DialogMixin from "@/mixins/DialogMixin.js";
  import ContactDialog from "@/views/Contacts/dialogs/ContactDialog.vue";
  import ContactService from "@/services/contactService.js";
  import NoData from "@/components/NoData.vue";

  export default {
    name: 'FolderDetail',
    mixins: [DialogMixin],
    components: {NoData, ContactDialog},
    data() {
      return {
        id: null,
        folder: {
          name: '',
          title: '',
          contacts: [],
        },
      }
    },

    methods: {
      fetchFolder() {
        FolderService.getById(this.id).then(result => {
          if (result?.data) this.folder = result?.data
        })
      },

      refreshContacts() {
        ContactService.getAll({id: this.id}).then((result) => {
          if (result?.data) {
            this.folder.contacts = result.data
          }
        })
      },

      goToFolders() {
        this.$router.push({ name: 'FolderList'})
      }
    },

    computed: {
      contacts() {
        return this.folder?.contacts
      },

      emptyContacts() {
        return this.contacts?.length === 0
      },
    },

    created() {
      this.id = this.$route?.params?.id
      if (this.id) {
        this.fetchFolder()
      }
    }
  }

</script>
