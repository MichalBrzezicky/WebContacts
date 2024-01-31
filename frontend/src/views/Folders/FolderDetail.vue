<template>
  <div>
    <v-card class="elevation-0">
      <div class="d-flex align-center mx-4 mt-2">
        <v-btn class="mt-2" @click="goToFolders" variant="text" icon title="Zpět na přehled složek">
          <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <div>
          <v-card-title class="pt-4 pr-0 d-flex align-center">
            <v-badge :offset-y="-2" :offset-x="2" :content="folder.contacts.length.toString()" >
              <v-icon size="small" icon="mdi-folder-open" />
            </v-badge>
            <span class="pl-3">{{ folder.name }}</span>
          </v-card-title>
          <v-card-subtitle>{{ folder.title }}</v-card-subtitle>
        </div>
        <v-spacer />
        <v-btn class="mt-2" @click="openDialog" icon color="primary" title="Vytvořit nový kontakt">
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </div>
      <v-card-text class="text-center">
        <v-progress-circular v-if="loadingContacts || loading" class="mt-10"  :size="70" :width="7" indeterminate color="primary" />
        <v-expansion-panels v-else-if="!emptyContacts" class="text-left" multiple>
          <ContactExpansionPanel v-for="(contact, index) in contacts" :key="index" :contact="contact" @onContactDelete="refreshContacts"/>
        </v-expansion-panels>
        <NoData class="text-left" contacts v-else />
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
  import FolderToolbar from "@/views/Folders/FolderToolbar.vue";
  import ContactExpansionPanel from "@/views/Contacts/ContactExpansionPanel.vue";

  export default {
    name: 'FolderDetail',
    mixins: [DialogMixin],
    components: {ContactExpansionPanel, FolderToolbar, NoData, ContactDialog},
    data() {
      return {
        id: null,
        loading: false,
        loadingContacts: false,
        folder: {
          name: '',
          title: '',
          contacts: [],
        },
      }
    },

    methods: {
      fetchFolder() {
        this.loading = true
        FolderService.getById(this.id).then(result => {
          if (result?.data) this.folder = result?.data
        }).finally(() => {
          this.loading = false
        })
      },

      refreshContacts() {
        this.loadingContacts = true
        ContactService.getAll({id: this.id}).then((result) => {
          if (result?.data) {
            this.folder.contacts = result.data
          }
        }).finally(() => {
          this.loadingContacts = false
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
