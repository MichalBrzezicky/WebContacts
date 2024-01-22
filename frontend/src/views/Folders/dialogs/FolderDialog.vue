<template>
  <v-dialog v-model="visible" width="500">
      <v-card>
        <v-card-title>
          <template v-if="folderVal.id">Editace složky</template>
          <template v-else>Nová složka</template>
        </v-card-title>
        <v-divider />
        <v-card-text>
          <v-text-field :disabled="loading" v-model="folderVal.name" type="text" label="Název" placeholder="Pracovní složka" required />
          <v-text-field :disabled="loading" v-model="folderVal.title" label="text" type="Popisek" required />
        </v-card-text>

        <v-divider />
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn @click="closeDialog">Zrušit</v-btn>
          <v-btn color="primary" :disabled="loading" :loading="loading" @click="submit">Potvrdit</v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>
</template>

<script lang="js" type="text/javascript">
  import FolderService from "@/services/folderService.js";

  const DEFAULT_FOLDER = { name: '', title: '' }
  export default {
    name: 'FolderDialog',
    props: {
      value: {
        type: Boolean,
        default: false,
      },
      folder: { type: Object }
    },

    data() {
      return {
        loading: false,
        folderVal: {}
      }
    },

    watch: {
      folderVal: {
        handler(newVal, oldVal) {
          console.log('NEW, OLD', newVal, oldVal)
        },
        deep: true
      },
    },

    computed: {
      visible: {
        get() {
          return this.value;
        },
        set(val) {
          this.$emit('onClose', val)
        }
      },

      isFormFilled() {
        return this.folderVal.name && this.folderVal.title
      }
    },

    methods: {
      closeDialog() {
        this.visible = false
      },

      submit() {
        if (this.isFormFilled) this.saveFolder()
      },

      saveFolder() {
        this.loading = true
        FolderService.add(this.folderVal).then((res) => {
          console.log(res)
          this.loading = false
        })
      }
    },

    created() {
      this.folderVal = this.folder || {...DEFAULT_FOLDER}
    }
  }
</script>
