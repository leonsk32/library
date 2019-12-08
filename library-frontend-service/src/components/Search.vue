<template>
  <v-container>
    <v-toolbar flat color="white">
      <v-toolbar-title>本一覧</v-toolbar-title>
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
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
// eslint-disable-next-line import/named
import { BooksApi } from '@/generated';
import LendingRecord from '@/class/LendingRecord';
import { Constant } from '@/class/Constant';
import Isbn from '@/class/Isbn';

import customConfiguration = Constant.customConfiguration;

@Component
export default class Search extends Vue {
    lendingRecords: Array<LendingRecord> = [];

    headers: any = [
      { text: 'タイトル', value: 'title' },
      { text: '貸出者', value: 'userId' },
    ];

    bookApi: BooksApi = new BooksApi(customConfiguration);

    getBooks(): void {
      this.bookApi.booksGet()
        .then(async (res) => {
          const { isbns } = res.data;

          if (typeof isbns === 'undefined') {
            return;
          }

          // Promiseの配列を生成
          const b = isbns.map(item => this.createBook(item));

          // 配列bに入っている処理がすべて終わるまで、await
          // 並列処理を行う。
          await Promise.all(b);
        });
    }

    mounted(): void {
      this.getBooks();
    }

    async createBook(isbnDto: string): Promise<void> {
      const isbn = new Isbn(isbnDto);
      const lendingRecord = new LendingRecord(isbn, isbnDto, await isbn.getTitle());
      this.lendingRecords.push(lendingRecord);
    }
}
</script>
