import { Express } from "express";

import expressLoader from "./expressLoader";
import mongooseLoader from "./mongooseLoader";
import cronLoader from "./cronLoader";

interface IProps {
  expressApp: Express;
}

export default async ({ expressApp }: IProps) => {
  await mongooseLoader();
  console.log("✅ MongoDB Intialized");

  await expressLoader({ expressApp });
  console.log("✅ Express Intialized");

  await cronLoader();
  console.log("✅ Cron Intialized");

  return true;
};
