import Isbn from '@/class/Isbn';

describe('openBd用のisbn整形ロジック', () => {
  it('エリックエバンスのDDD本を目的通りに成形する', () => {
    const expected: string = '978-4-7981-2196-3';
    const isbn = new Isbn('9784798121963');
    expect(isbn.getFormattedIsbn()).toMatch(expected);
  });

  it('実践DDDの本を目的通りに成形する', () => {
    const expected: string = '978-4-7981-3161-0';
    const isbn = new Isbn('9784798131610');
    expect(isbn.getFormattedIsbn()).toMatch(expected);
  });
});
