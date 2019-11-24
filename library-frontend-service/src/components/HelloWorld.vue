<template>
  <v-container>
    <v-layout
      text-center
      wrap
    >
      <v-flex xs12>
      <v-toolbar flat color="white">
        <v-toolbar-title>読書ランキング(累計)</v-toolbar-title>
        <v-divider class="mx-2" inset vertical></v-divider>
        <v-spacer></v-spacer>
      </v-toolbar>
      <v-data-table :headers="headers" :items="lendingRecords" class="elevation-1">
        <template v-slot:items="props">
          <td>{{ props.item.title }}</td>
          <td>{{ props.item.userId }}</td>
        </template>
        <template v-slot:no-data> </template>
      </v-data-table>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class Home extends Vue {
    headers: any = [
      { text: 'ランキング', value: 'rank' },
      { text: '読書', value: 'id' },
      { text: '冊数', value: 'num' },
    ];
}
</script>
