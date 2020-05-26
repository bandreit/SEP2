import $ from "cheerio";
import ScrapperConfig from "../config/scrapping";
import ScrapperService from "../services/ScrapperService";

class Rema1000Scrapper {
  public getDiscountsCount = () => {
    return new ScrapperService(ScrapperConfig.rema1000.discounts)
      .advancedFetch()
      .then((html) => {
        const itemContainers = $(".product-wrap", html);
        return itemContainers.length;
      });
  };

  public getDiscounts = () => {
    return new ScrapperService(ScrapperConfig.rema1000.discounts)
      .advancedFetch()
      .then((html) => {
        const discountsList = $(".product", html);
        const discounts: any = [];

        $(discountsList).map((index) => {
          const item = $(discountsList[index]).html();
          if (!item) return;

          const discount = this.getDiscountInformation(item);
          return discounts.push(discount);
        });

        return discounts;
      });
  };

  public getDiscountInformation = (html: string) => {
    const image = $(".image img", html).attr("src");
    const title = $(".title", html).text();
    const details = $(".extra", html).children().first().text();

    // The UI shows wrong classes
    const normalPrice = $(".price-discount", html).html();
    const discountPrice = $(".price-normal", html).html();

    /**
     * The img looks something like
     * https://cphapp.rema1000.dk/api/v1/catalog/store/1/item/61114/image/1529484494/170.jpg
     * and the uid is 61114
     */

    const uid = image.split("item/")[1].split("/")[0];
    const link = `${ScrapperConfig.rema1000.base}/varer/${uid}`;

    return {
      title,
      details,
      image,
      discountPrice: this.htmlPriceToValue(discountPrice),
      normalPrice: this.htmlPriceToValue(normalPrice),
      link,
    };
  };

  private htmlPriceToValue = (html: string | null) => {
    if (!html) return html;

    return html.replace("<span>", ".").replace("</span>", "").replace(",", ".");
  };
}

export default Rema1000Scrapper;
