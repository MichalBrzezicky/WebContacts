<template>
  <v-card :color="'primary'" :variant="'tonal'" class="pb-4 elevation-2">
    <v-card-title class="pt-2 pb-0 pr-0 d-flex align-center" @click="openDialog">
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
      <v-btn icon x-small variant="text" color="primary" title="Otevřít další akce">
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
    </v-card-title>
    <v-card-subtitle>{{ folder.title }}</v-card-subtitle>

    <!-- Dialogs -->
    <FolderDialog v-if="dialog" :value="dialog" :folder="folder" @onClose="closeDialog" @onSubmit="$emit('onFolderEdit')" />
  </v-card>
</template>

<script>
  import FolderDialog from "@/views/Folders/dialogs/FolderDialog.vue";
  import DialogMixin from "@/mixins/DialogMixin.js";

  export default {
    name: 'FolderCard',
    mixins: [DialogMixin],
    components: {FolderDialog},
    props: {
      folder: {
        type: Object,
        required: true,
      }
    }

  }
</script>
