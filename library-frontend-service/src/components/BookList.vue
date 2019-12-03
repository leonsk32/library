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
        <td>{{ props.item.userId }}</td>
      </template>
      <template v-slot:no-data> </template>
    </v-data-table>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { LendingRecordsApi } from '@/generated';
import { DefaultApi } from '@/generated/external';
import LendingRecord from '@/class/LendingRecord';
import { Constant } from '@/class/Constant';

import customConfiguration = Constant.customConfiguration;

    @Component
export default class BookList extends Vue {
    lendingRecords: Array<LendingRecord> = [];

  headers: any = [
    { text: 'タイトル', value: 'title' },
    { text: '貸出者', value: 'userId' },
  ];

    bookApi: LendingRecordsApi = new LendingRecordsApi(customConfiguration);

    bookInfoapi: DefaultApi = new DefaultApi();

    getBooks(): void {
      this.bookApi.lendingRecordsGet()
        .then((res) => {
          const lendingRecordsDto: any = res.data.lendingRecords;

          /* eslint no-restricted-syntax : 0 */
          for (const lendingRecordDto of lendingRecordsDto) {
            const lendingRecord = new LendingRecord(lendingRecordDto.isbn, lendingRecordDto.userId);

            const formatIsbnList: Array<string> = [];
            formatIsbnList.push(lendingRecord.getFormattedIsbn());
            /* eslint no-loop-func:0 */
            this.bookInfoapi.getGet(formatIsbnList)
              .then((res2) => {
                /* eslint prefer-destructuring:0 */
                lendingRecord.title = res2.data[0].summary.title;
              }).finally(() => {
                this.lendingRecords.push(lendingRecord);
              });
          }
        });
    }

    mounted(): void {
      this.getBooks();
    }
}
</script>
