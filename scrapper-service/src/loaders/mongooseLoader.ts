import mongoose from "mongoose";
import ENV_CONFIG from "../config/env";

export default async (): Promise<any> => {
  const connection = await mongoose.connect(ENV_CONFIG.MONGODB_URI, {
    useNewUrlParser: true
  });

  return connection.connection.db;
};
