import { Configuration } from '@/generated';

export module Constant {

  // eslint-disable-next-line import/prefer-default-export
  export const customConfiguration: Configuration = {
    baseOptions: {
      headers: {
        'Access-Control-Allow-Origin': '*',
      },
    },
    basePath: process.env.VUE_APP_DEV_SERVER_URL,
  };
}
