<template>
  <v-container>
    <RankingList/>
  </v-container>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

import RankingList from '@/components/RankingList.vue';

@Component({
  components: {
    RankingList,
  },
})
export default class RankingView extends Vue {
}
</script>
