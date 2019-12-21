import Isbn from '@/class/Isbn';

export default class Books {
  isbn: Isbn;

  title: string;

  constructor(isbn: Isbn, title: string) {
    this.isbn = isbn;
    this.title = title;
  }
}
