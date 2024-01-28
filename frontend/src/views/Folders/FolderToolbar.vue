<template>
  <v-card class="ma-0 pa-0">
    <div class="mx-6 my-3 d-flex align-center justify-space-between">
      <v-text-field
        v-model="search"
        :bg-color="'teal-lighten-5'"
        rounded
        flat
        clearable
        variant="solo"
        hide-details
        class="mr-6"
        prepend-inner-icon="mdi-magnify"
        persistent-placeholder
        placeholder="Vyhledat podle názvu nebo titulku složky..."
      />

      <v-btn @click="openDialog" icon color="primary" title="Vytvořit novou složku">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </div>

    <FolderDialog v-if="dialog" :value="dialog" @onClose="closeDialog" @onSubmit="$emit('onSubmit')" />
  </v-card>
</template>

<script>
  import FolderDialog from "@/views/Folders/dialogs/FolderDialog.vue";
  import DialogMixin from "@/mixins/DialogMixin.js";
  import _ from 'lodash'

  export default {
    name: 'FolderToolbar',
    mixins: [DialogMixin],
    components: { FolderDialog },

    data() {
      return {
        search: '',
      }
    },

    watch: {
      search: {
        handler(newVal, oldVal) {
          this.handleOnSearch()
        },
        deep: true,
      }
    },

    methods: {
      handleOnSearch: _.debounce(function () {
        this.$emit('onSearch', {search: this.search})
      }, 1000)
    }
  }
</script>

<style scoped>
  .v-text-field {
    max-width: 750px;
  }
</style>
