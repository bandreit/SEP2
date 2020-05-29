import express from "express";

import loaders from "./loaders";
import ENV_CONFIG from "./config/env";

const startServer = async () => {
  const app = express();

  await loaders({ expressApp: app });

  app.listen({ port: ENV_CONFIG.PORT }, () =>
    console.log(`🚀 Server ready at http://localhost:${ENV_CONFIG.PORT}`)
  );
};

startServer();
