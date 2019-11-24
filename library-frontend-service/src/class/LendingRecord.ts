export default class LendingRecord {
  isbn: string;

  title: string;

  userId: string;

  constructor(isbn: string, userId: string) {
    this.isbn = isbn;
    this.userId = userId;

    this.title = '';
  }

  /**
   * openBd用のisbn整形ロジック。
   * 本来であれば、腐敗防止層の持ち物だが、他のAPIでも使用する可能性を考慮してこちらのドメインに持たせる。
   */
  getFormattedIsbn() : string {
    return `${this.isbn.substr(0, 3)}-${this.isbn.substr(3, 1)}-${this.isbn.substr(4, 4)}-${this.isbn.substr(8, 4)}-${this.isbn.substr(12, 1)}`;
  }
}
