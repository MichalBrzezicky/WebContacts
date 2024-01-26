<template>
  <v-card :color="'primary'" :variant="'tonal'">
    <v-card-title>{{ folder.name }}</v-card-title>
    <v-divider />
    <v-card-text>{{ folder.title }}</v-card-text>
  </v-card>
</template>

<script>
  export default {
    name: 'FolderCard',
    props: {
      folder: {
        type: Object,
        required: true,
      }
    }

  }
</script>
