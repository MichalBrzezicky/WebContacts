<template>
  <v-card class="ma-0 pa-0">
    <div class="mx-6 my-2 d-flex align-center justify-space-between">
      <v-text-field
        :bg-color="'teal-lighten-5'"
        rounded
        flat
        clearable
        variant="solo"
        hide-details
        class="mr-6"
        prepend-inner-icon="mdi-magnify"
        persistent-placeholder
        placeholder="Vyhledat složku nebo tel. číslo..."
      />

      <v-btn @click="openDialog" icon color="primary" title="Vytvořir novou složku">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </div>

    <FolderDialog :value="folderDialog" @onClose="closeDialog" />
  </v-card>
</template>

<script>
  import FolderDialog from "@/views/Folders/dialogs/FolderDialog.vue";

  export default {
    name: 'FolderToolbar',
    components: { FolderDialog },
    data() {
      return {
        folderDialog: false,
      }
    },

    methods: {
      openDialog() {
        this.folderDialog = true
      },

      closeDialog() {
        this.folderDialog = false
      }
    }
  }
</script>

<style scoped>
  .v-text-field {
    max-width: 750px;
  }
</style>
