<template>
  <v-card :color="'primary'" :variant="'tonal'" class="pb-4 elevation-2">
    <v-card-title class="pt-2 pb-0 pr-0 d-flex align-center">
      <v-badge
        :offset-y="-2"
        :offset-x="2"
        color="primary"
        :content="folder.contacts.length.toString()"
      >
        <v-icon size="small" icon="mdi-folder-open" />
      </v-badge>
      <span class="pl-2">{{ folder.name }}</span>
      <v-spacer />
      <MenuExpansion :options="folderMenuOptions" />
    </v-card-title>
    <v-card-subtitle>{{ folder.title }}</v-card-subtitle>

    <!-- Dialogs -->
    <FolderDialog v-if="dialog" :value="dialog" :folder="folder" @onClose="closeDialog" @onSubmit="$emit('onFolderEdit')" />
  </v-card>
</template>

<script>
  import FolderDialog from "@/views/Folders/dialogs/FolderDialog.vue";
  import FolderService from "@/services/folderService.js";
  import DialogMixin from "@/mixins/DialogMixin.js";
  import MenuExpansion from "@/components/menu/MenuExpansion.vue";

  export default {
    name: 'FolderCard',
    mixins: [DialogMixin],
    components: {MenuExpansion, FolderDialog},
    props: {
      folder: {
        type: Object,
        required: true,
      }
    },

    computed: {
      folderMenuOptions() {
        return [
          {title: 'Editovat složku', action: this.openDialog, icon: 'mdi-pencil'},
          {title: 'Smazat složku', action: this.deleteFolder, icon: 'mdi-delete'},
          {title: 'Přejít ke kontaktům', action: this.goToContacts, icon: 'mdi-contacts'}
        ]
      },
    },

    methods: {
      deleteFolder() {
        FolderService.delete(this.folder.id).then(result => {
          this.$emit('onFolderDelete')
        })
      },

      goToContacts() {
        this.$router.push({ name: 'FolderDetail', params: {id: this.folder.id}})
      }
    }
  }
</script>
