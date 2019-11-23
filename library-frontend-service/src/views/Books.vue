<template>
    <BookList/>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import BookList from '@/components/BookList.vue';
@Component({
  components: { BookList },
})
export default class BooksView extends Vue {
}
</script>
