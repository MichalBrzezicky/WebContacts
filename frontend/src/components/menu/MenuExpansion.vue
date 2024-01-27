<template>
  <v-menu transition="scale-transition">
    <template v-slot:activator="{ props }">
      <v-btn v-bind="props" x-small variant="text" color="primary" title="Zobrazit možnosti" icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
    </template>
    <v-list density="compact">
      <v-list-item v-for="(option, index) in options" :key="index" @click="option.action">
        <template v-slot:prepend>
          <v-icon :icon="option.icon"></v-icon>
        </template>
        <v-list-item-title v-text="option.title" />
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
  export default {
    name: 'MenuExpansion',
    props: {
      options: {
        type: Array,
        required: true,
      }
    }
  }
</script>
