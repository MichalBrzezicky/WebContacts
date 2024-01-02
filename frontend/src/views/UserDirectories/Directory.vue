<template>
  <div>
    <DirectoryToolbar class="mb-5"/>
    <!--
    <v-row class="mx-2">
      <v-col v-for="(card, index) in 12" :key="index" :cols="4">
        <v-badge
          :offset-x="25"
          class="d-block"
          color="error"
          content="5"
        >
          <DirectoryCard class="w-100"/>
        </v-badge>

      </v-col>
    </v-row>
    -->
    <NoData/>
  </div>

</template>

<script setup>

import DirectoryCard from "@/views/UserDirectories/DirectoryCard.vue";
import DirectoryToolbar from "@/views/UserDirectories/DirectoryToolbar.vue";
import NoData from "@/components/NoData.vue"
</script>
