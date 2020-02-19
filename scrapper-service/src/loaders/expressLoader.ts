import { Express } from "express";
import cors from "cors";
import bodyParser from "body-parser";

interface IProps {
  expressApp: Express;
}

export default async ({ expressApp }: IProps) => {
  expressApp.use(bodyParser.urlencoded({ extended: false }));
  expressApp.use(bodyParser.json());

  expressApp.use(
    cors({
      origin: "*"
    })
  );
};
