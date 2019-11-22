<template>
  <v-container>
    <v-toolbar flat color="white">
      <v-toolbar-title>貸出中一覧</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-data-table :headers="headers" :items="lendingRecords" class="elevation-1">
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
import {
  LendingRecordsApi, Configuration, LendingRecordsDto, LendingRecordDto,
} from '@/generated';
import { DefaultApi } from '@/generated/external';

@Component
export default class BookList extends Vue {
    lendingRecords: any = [];
    // lendingRecords: LendingRecordsDto = [];

  // headers: any = [
  //   { text: 'タイトル', value: 'title' },
  //   { text: '在庫数', value: 'max' },
  //   { text: '貸出者', value: 'users' },
  // ];
  headers: any = [
    { text: 'タイトル', value: 'isbn' },
    { text: '名前', value: 'namae' },
    { text: '氏名', value: 'simei' },
    { text: 'Id', value: 'userId' },
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

    bookApi: LendingRecordsApi = new LendingRecordsApi(this.configuration);

    bookInfoapi: DefaultApi = new DefaultApi();

    getBooks(): any {
      // const hoge: Array<string> = ['978-4-7981-2196-3'];
      // const hoge3: Array<string> = [];

      this.bookApi.lendingRecordsGet()
        .then((res) => {
          this.lendingRecords = res.data.lendingRecords;
          // let isbn = books.isbn;
          // const isbn : string = '9784798121963';
          // const formatIsbn = `${isbn.substr(0, 3)}-${isbn.substr(3, 1)}-${isbn.substr(4, 4)}
          // -${isbn.substr(8, 4)}-${isbn.substr(12, 1)}`;
          // hoge3.push(formatIsbn);
          // this.bookInfoapi.getGet(hoge3)
          //   .then((res2) => {
          //     this.books = res2.data;
          //   });
        });


      // const hoge2 = [
      //   { title: 'DDD', max: '2', users: 'きり丸' },
      //   { title: 'vue', max: '0', users: '乱太郎,きり丸,新兵衛' },
      //   { title: 'Nuxt', max: '0', users: 'きり丸' },
      //   { title: 'よくわからないDDD', max: '1', users: '' },
      //   { title: 'アジャイルサムライ', max: '10', users: '' },
      // ];
      // this.books = hoge2;
    }

    mounted(): void {
      this.getBooks();
    }
}
</script>
