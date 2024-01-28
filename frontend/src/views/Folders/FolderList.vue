<template>
  <div class="text-center">
    <FolderToolbar class="mb-5" @onSubmit="refreshDirectories" @onSearch="refreshDirectories" />
    <v-progress-circular class="mt-10" v-if="loading" :size="70" :width="7" indeterminate color="primary" />
    <v-row v-else-if="!emptyFolders" class="mx-2 text-left">
      <v-col v-for="(folder, index) in folders" :key="index" :cols="4">
          <FolderCard @click="goToContacts(folder.id)" class="w-100" :folder="folder" @onFolderEdit="refreshDirectories" @onFolderDelete="refreshDirectories" />
      </v-col>
    </v-row>
    <NoData folders v-else/>
  </div>
</template>

<script>
import FolderCard from "@/views/Folders/FolderCard.vue";
import FolderToolbar from "@/views/Folders/FolderToolbar.vue";
import NoData from "@/components/NoData.vue"
import FolderService from "@/services/folderService.js";
export default {
  name: 'FolderList',
  components: { FolderCard, FolderToolbar, NoData },

  data() {
    return {
      loading: false,
      folders: [],
    }
  },

  computed: {
    emptyFolders() {
      return this.folders?.length === 0
    }
  },

  methods: {
    fetchDirectories(params) {
      this.loading = true
      FolderService.getAll(params).then(result => {
        if (result?.data) {
          this.folders = result.data
        }
      }).finally(() => {
          this.loading = false
      })
    },

    refreshDirectories(params = {}) {
      this.fetchDirectories(params)
    },



    goToContacts(id) {
      this.$router.push({ name: 'FolderDetail', params: {id: id}})
    }
  },

  created() {
    this.fetchDirectories()
  }
}
</script>
