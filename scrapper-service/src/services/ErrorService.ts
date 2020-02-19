import raven from "raven";
import ENV_CONFIG from "../config/env";

class ErrorService {
  constructor() {
    return raven
      .config(ENV_CONFIG.ENV.isProd && ENV_CONFIG.SENTRY_KEY, {
        environment: ENV_CONFIG.NODE_ENV
      })
      .install();
  }

  public captureException(error: any) {
    return raven.captureException(error);
  }
}

export default ErrorService;
