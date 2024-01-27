<template>
  <div>
    <FolderToolbar class="mb-5" @onSubmit="refreshDirectories"/>
    <v-row v-if="folders" class="mx-2">
      <v-col v-for="(folder, index) in folders" :key="index" :cols="4">
          <FolderCard class="w-100" :folder="folder" @onFolderEdit="refreshDirectories" />
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
  name: 'FolderList',
  components: {FolderDialog, FolderCard, FolderToolbar, NoData },

  data() {
    return {
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
    },

    refreshDirectories() {
      this.fetchDirectories()
    }
  },

  created() {
    this.fetchDirectories()
  }
}
</script>
