import Isbn from '@/class/Isbn';

export default class LendingRecord {
  isbn: Isbn;

  title: string;

  userId: string;

  constructor(isbn: Isbn, userId: string, title: string) {
    this.isbn = isbn;
    this.userId = userId;
    this.title = title;
  }
}
