<template>
  <div>
    <FolderToolbar class="mb-5"/>
    <v-row v-if="directories" class="mx-2">
      <v-col v-for="(directory, index) in directories" :key="index" :cols="4">
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
    <NoData/>
  </div>
</template>

<script>
import FolderCard from "@/views/Folders/FolderCard.vue";
import FolderToolbar from "@/views/Folders/FolderToolbar.vue";
import NoData from "@/components/NoData.vue"
import FolderService from "@/services/folderService.js";

export default {
  name: 'Directory',
  components: { FolderCard, FolderToolbar, NoData },

  data() {
    return {
      directories: [],
    }
  },

  methods: {
    async fetchDirectories() {
      FolderService.getAll({}).then(result => {
        console.log('RESULT::::', result)
        })
    }
  },

  async created() {
    await this.fetchDirectories()
  }
}
</script>
