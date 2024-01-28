<template>
  <v-dialog v-model="visible" width="500">
      <v-card>
        <v-card-title>
          <template v-if="isEditation">Editace složky</template>
          <template v-else>Nová složka</template>
        </v-card-title>
        <v-divider />
        <v-card-text>
          <v-text-field :disabled="loading" v-model="folderVal.name" type="text" label="Název" placeholder="Pracovní složka" required />
          <v-textarea :disabled="loading" v-model="folderVal.title" type="textarea" label="Popisek" required />
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
  import FolderService from "@/services/folderService.js"
  import _ from 'lodash'

  const DEFAULT_FOLDER = { id: null, name: '', title: '' }
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
      },

      isEditation() {
        return !!this.folderVal.id
      }
    },

    methods: {
      closeDialog() {
        this.visible = false
      },

      submit() {
        if (!this.isFormFilled) return
        if (this.isEditation) {
          this.editFolder()
        } else {
          this.saveFolder()
        }
      },

      editFolder() {
        this.loading = true
        FolderService.update(this.folderVal?.id, this.folderVal).then((res) => {
          this.loading = false
          this.closeDialog()
          this.$emit('onSubmit')
        })
      },

      saveFolder() {
        this.loading = true
        FolderService.add(this.folderVal).then((res) => {
          this.loading = false
          this.closeDialog()
          this.$emit('onSubmit')
        })
      }
    },

    created() {
      this.folderVal = _.cloneDeep(this.folder) || {...DEFAULT_FOLDER}
    }
  }
</script>
