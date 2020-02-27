import dotenv from "dotenv";
dotenv.config();

const { NODE_ENV, PORT, SECRET_KEY, SENTRY_KEY } = process.env;

const DEFAULT_NODE_ENV = "development";
const DEFAULT_PORT = 4000;
const DEFAULT_SECRET_KEY = "MY_SECRET_KEY";
const DEFAULT_SENTRY_KEY = "";

export default {
  NODE_ENV: NODE_ENV || DEFAULT_NODE_ENV,
  PORT: PORT || DEFAULT_PORT,
  SECRET_KEY: SECRET_KEY || DEFAULT_SECRET_KEY,
  SENTRY_KEY: SENTRY_KEY || DEFAULT_SENTRY_KEY,
  POSTGRES_USER: "postgres",
  POSTGRES_PASSWORD: "postgres",
  POSTGRES_DB: "discounts",
  ENV: {
    dev: "development",
    prod: "production",
    isDev: (NODE_ENV || DEFAULT_NODE_ENV) === "development",
    isProd: (NODE_ENV || DEFAULT_NODE_ENV) === "production"
  }
};
