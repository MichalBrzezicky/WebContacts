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

      <v-btn icon color="primary">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </div>
  </v-card>
</template>

<script>
  export default {
    name: 'FolderToolbar'
  }
</script>

<style scoped>
  .v-text-field {
    max-width: 750px;
  }
</style>
