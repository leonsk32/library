export default class LendingRecord {
  isbn: string;

  title: string;

  userId: string;

  constructor(isbn: string, userId: string) {
    this.isbn = isbn;
    this.userId = userId;

    this.title = '';
  }

  getFormattedIsbn() : string {
    return `${this.isbn.substr(0, 3)}-${this.isbn.substr(3, 1)}-${this.isbn.substr(4, 4)}-${this.isbn.substr(8, 4)}-${this.isbn.substr(12, 1)}`;
  }
}
