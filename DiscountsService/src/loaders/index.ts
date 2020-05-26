import { Express } from "express";

import expressLoader from "./expressLoader";
import databaseLoader from "./databaseLoader";
import cronLoader from "./cronLoader";

interface IProps {
  expressApp: Express;
}

export default async ({ expressApp }: IProps) => {
  await databaseLoader();
  console.log("✅ Database Intialized");

  await expressLoader({ expressApp });
  console.log("✅ Express Intialized");

  await cronLoader();
  console.log("✅ Cron Intialized");

  return true;
};
