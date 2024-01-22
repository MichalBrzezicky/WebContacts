<template>
  <div>
    <FolderToolbar class="mb-5"/>
    <v-row v-if="folders" class="mx-2">
      <v-col v-for="(folder, index) in folders" :key="index" :cols="4">
        <v-badge
          :offset-x="25"
          class="d-block"
          color="error"
          content="5"
        >
          <FolderCard class="w-100"/>
        </v-badge>

      </v-col>
    </v-row>
    <NoData v-else/>
  </div>
</template>

<script>
import FolderCard from "@/views/Folders/FolderCard.vue";
import FolderToolbar from "@/views/Folders/FolderToolbar.vue";
import NoData from "@/components/NoData.vue"
import FolderService from "@/services/folderService.js";
import FolderDialog from "@/views/Folders/dialogs/FolderDialog.vue";

export default {
  name: 'Directory',
  components: {FolderDialog, FolderCard, FolderToolbar, NoData },

  data() {
    return {
      folderDialog: false,
      folders: [],
    }
  },

  methods: {
    fetchDirectories() {
      FolderService.getAll({}).then(result => {
          if (result?.data) {
            this.folders = result.data
          }
        })
    }
  },

  created() {
    this.fetchDirectories()
  }
}
</script>
