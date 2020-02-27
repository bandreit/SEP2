import { Client } from "pg";
import ENV_CONFIG from "../config/env";

const client = new Client(
  `postgres://${ENV_CONFIG.POSTGRES_USER}:${ENV_CONFIG.POSTGRES_PASSWORD}@postgres:5432/${ENV_CONFIG.POSTGRES_DB}`
);

export default async (): Promise<any> => {
  const connection = await client.connect();

  return connection;
};
