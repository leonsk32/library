<template>
  <v-container>
    <v-toolbar flat color="white">
      <v-toolbar-title>本一覧</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-card-title>
      検索
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table :headers="headers" :items="books" class="elevation-1" :search="search">
      <template v-slot:items="props">
        <td>{{ props.item.isbn.isbn }}</td>
        <td>{{ props.item.title }}</td>
      </template>
      <template v-slot:no-data> </template>
    </v-data-table>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
// eslint-disable-next-line import/named
import { BooksApi } from '@/generated';
import { Constant } from '@/class/Constant';
import Isbn from '@/class/Isbn';
import Book from '@/class/Book';

import customConfiguration = Constant.customConfiguration;

@Component
export default class Search extends Vue {
    search: string = '';

    books: Array<Book> = [];

    headers: Array<any> = [
      { text: 'isbn', value: 'isbn.isbn' },
      { text: 'タイトル', value: 'title' },
    ];

    bookApi: BooksApi = new BooksApi(customConfiguration);

    getBooks(): void {
      this.bookApi.booksGet()
        .then(async (res) => {
          const { isbns } = res.data;

          if (typeof isbns === 'undefined') {
            return;
          }

          const b = isbns.map(item => this.createBook(item));
          await Promise.all(b);
        });
    }

    mounted(): void {
      this.getBooks();
    }

    async createBook(isbnDto: string): Promise<void> {
      const isbn = new Isbn(isbnDto);
      const info = await isbn.getBookInfo();
      {
        let book;
        if (typeof info === 'undefined') {
          book = new Book(isbn, '');
        } else {
          book = new Book(isbn, info.summary.title);
        }
        this.books.push(book);
      }
    }
}
</script>
