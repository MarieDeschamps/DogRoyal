import { IhmPage } from './app.po';

describe('ihm App', () => {
  let page: IhmPage;

  beforeEach(() => {
    page = new IhmPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
