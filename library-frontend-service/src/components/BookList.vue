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
  LendingRecordsApi, Configuration,
} from '@/generated';
import { DefaultApi } from '@/generated/external';
import LendingRecord from '@/class/LendingRecord';

@Component
export default class BookList extends Vue {
    lendingRecords: Array<LendingRecord> = [];

  headers: any = [
    { text: 'タイトル', value: 'title' },
    { text: '貸出者', value: 'userId' },
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
      this.bookApi.lendingRecordsGet()
        .then((res) => {
          const ledingRecords: any = res.data.lendingRecords;

          let lendinfRecordDto : any;
          /* eslint no-restricted-syntax : 0 */
          for (lendinfRecordDto of ledingRecords) {
            const { isbn } = lendinfRecordDto;
            const formatIsbn = `${isbn.substr(0, 3)}-${isbn.substr(3, 1)}-${isbn.substr(4, 4)}-${isbn.substr(8, 4)}-${isbn.substr(12, 1)}`;

            const formatIsbnList: Array<string> = [];
            let title: string = '';
            formatIsbnList.push(formatIsbn);
            /* eslint no-loop-func:0 */
            this.bookInfoapi.getGet(formatIsbnList)
              .then((res2) => {
                /* eslint prefer-destructuring:0 */
                title = res2.data[0].summary.title;
              }).finally(() => {
                this.lendingRecords.push(
                  new LendingRecord(title, lendinfRecordDto.userId),
                );
              });
          }
        });
    }

    mounted(): void {
      this.getBooks();
    }
}
</script>
