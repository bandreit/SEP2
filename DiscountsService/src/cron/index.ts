import cron from "node-cron";
import DiscountService from "../services/DiscountService";
import fs from "fs";

import Rema1000Scrapper from "../scrappers/Rema1000Scrapper";

// # ┌────────────── second (optional)
// # │ ┌──────────── minute
// # │ │ ┌────────── hour
// # │ │ │ ┌──────── day of month
// # │ │ │ │ ┌────── month
// # │ │ │ │ │ ┌──── day of week
// # │ │ │ │ │ │
// # │ │ │ │ │ │
// # * * * * * *

const run = async () => {
  cron.schedule("10 * * * * *", async () => {
    const discounts = await new Rema1000Scrapper().getDiscounts();

    if (discounts.length < 1) {
      return;
    }

    fs.writeFileSync("discounts.json", JSON.stringify(discounts));

    await new DiscountService().create(discounts);
    console.log(`🕒 Rema1000 job has been executed`);
  });

  cron.schedule("* * * 8 * *", async () => {
    await new DiscountService().deleteExpired(7);
    console.log("🧹 Removed all the expired discounts");
  });
};

export default run;
