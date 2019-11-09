<template>
  <v-container>
    <v-toolbar flat color="white">
      <v-toolbar-title>貸出中一覧</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-data-table :headers="headers" :items="books" class="elevation-1">
      <template v-slot:items="props">
        <td>{{ props.item.title }}</td>
        <td>{{ props.item.max }}</td>
        <td>{{ props.item.users }}</td>
      </template>
      <template v-slot:no-data> </template>
    </v-data-table>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { BooksApi, Configuration } from '@/generated';
import { DefaultApi } from '@/generated/external';

@Component
export default class BookList extends Vue {
  books: any = [];

  headers: any = [
    { text: 'タイトル', value: 'title' },
    { text: '在庫数', value: 'max' },
    { text: '貸出者', value: 'users' },
  ];

  configuration: Configuration =
      {
        baseOptions: {
          headers: {
            'Access-Control-Allow-Origin': '*',
          },
        },
        basePath: process.env.VUE_APP_DEV_SERVER_URL,

      };

    bookApi: BooksApi = new BooksApi(this.configuration);

    bookInfoapi: DefaultApi = new DefaultApi();

    getBooks(): any {
      const hoge: Array<string> = ['978-4-7981-2196-3'];

      this.bookApi.booksGet()
        .then((res) => {
          const books = res.data;
          // booksを加工してisbnに。
          this.bookInfoapi.getGet(hoge)
            .then((res2) => {
              this.books = res2.data;
            });
        });


      const hoge2 = [
        { title: 'DDD', max: '2', users: 'きり丸' },
        { title: 'vue', max: '0', users: '乱太郎,きり丸,新兵衛' },
        { title: 'Nuxt', max: '0', users: 'きり丸' },
        { title: 'よくわからないDDD', max: '1', users: '' },
        { title: 'アジャイルサムライ', max: '10', users: '' },
      ];
      this.books = hoge2;
    }

    mounted(): void {
      this.getBooks();
    }
}
</script>
