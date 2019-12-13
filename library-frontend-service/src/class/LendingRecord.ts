import Isbn from '@/class/Isbn';

export default class LendingRecord {
  isbn: Isbn;

  title: string;

  userId: string;

  familyName: string;

  givenName: string;

  fullName: string;

  constructor(isbn: Isbn, userId: string, title: string, familyName: string, givenName: string) {
    this.isbn = isbn;
    this.userId = userId;
    this.title = title;
    this.familyName = familyName;
    this.givenName = givenName;
    this.fullName = this.getFullName();
  }

  private getFullName() : string {
    return this.familyName + this.givenName;
  }
}
