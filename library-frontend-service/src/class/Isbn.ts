// eslint-disable-next-line import/named
import { BookInfo, DefaultApi } from '@/generated/external';

export default class Isbn {
  isbn: string;

  private bookInfoapi: DefaultApi = new DefaultApi();

  constructor(isbn: string) {
    this.isbn = isbn;
  }

  /**
   * openBd用のisbn整形ロジック。
   * 本来であれば、腐敗防止層の持ち物だが、他のAPIでも使用する可能性を考慮してこちらのドメインに持たせる。
   */
  getFormattedIsbn() : string {
    return `${this.isbn.substr(0, 3)}-${this.isbn.substr(3, 1)}-${this.isbn.substr(4, 4)}-${this.isbn.substr(8, 4)}-${this.isbn.substr(12, 1)}`;
  }

  async getTitle(): Promise<string> {
    return new Promise((resolve) => {
      let rtn = '';

      const formatIsbnList: Array<string> = [];
      formatIsbnList.push(this.getFormattedIsbn());
      /* eslint no-loop-func:0 */
      this.bookInfoapi.getGet(formatIsbnList)
        .then((res2) => {
          rtn = res2.data[0].summary.title;
        }).finally(() => resolve(rtn));
    });
  }


  async getBookInfo(): Promise<BookInfo> {
    return new Promise((resolve) => {
      let rtn: BookInfo | PromiseLike<BookInfo> | undefined;

      const formatIsbnList: Array<string> = [];
      formatIsbnList.push(this.getFormattedIsbn());
      /* eslint no-loop-func:0 */
      this.bookInfoapi.getGet(formatIsbnList)
        .then((res2) => {
          // eslint-disable-next-line prefer-destructuring
          rtn = res2.data[0];
        }).finally(() => resolve(rtn));
    });
  }
}
