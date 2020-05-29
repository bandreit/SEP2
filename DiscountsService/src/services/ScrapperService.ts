import rp from "request-promise";
import puppeteer from "puppeteer";

class ScrapperService {
  url = "";

  constructor(url: string) {
    this.url = url;
  }

  public fetch = () => {
    return rp(this.url);
  };

  public advancedFetch = () => {
    return puppeteer
      .launch({ args: ["--no-sandbox", "--disable-setuid-sandbox"] })
      .then(browser => {
        return browser.newPage();
      })
      .then(page => {
        return page.goto(this.url).then(async () => {
          await page.waitFor(5000);
          return page.content();
        });
      });
  };
}

export default ScrapperService;
