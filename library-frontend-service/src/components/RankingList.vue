<template>
  <v-container>
    <v-layout
      text-center
      wrap
    >
      <v-flex xs12>
        鋭意製作中
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class RankingList extends Vue {
    test : any;

    mounted(): void {
      this.test = '';
    }
}
</script>
