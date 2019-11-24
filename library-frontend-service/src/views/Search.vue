<template>
  <v-container>
    <Search />
  </v-container>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

import Search from '@/components/Search.vue';

@Component({
  components: {
    Search,
  },
})
export default class SearchView extends Vue {
}
</script>
