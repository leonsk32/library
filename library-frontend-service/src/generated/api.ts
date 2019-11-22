// tslint:disable
/// <reference path="./custom.d.ts" />
/**
 * libraryApplication
 * 図書館アプリのライブラリ
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import * as globalImportUrl from 'url';
import { Configuration } from './configuration';
import globalAxios, { AxiosPromise, AxiosInstance } from 'axios';
// Some imports not used depending on template conditions
// @ts-ignore
import { BASE_PATH, COLLECTION_FORMATS, RequestArgs, BaseAPI, RequiredError } from './base';

/**
 * 
 * @export
 * @interface Bookaction
 */
export interface Bookaction {
    /**
     * 
     * @type {string}
     * @memberof Bookaction
     */
    type?: string;
    /**
     * 
     * @type {string}
     * @memberof Bookaction
     */
    userId?: string;
}
/**
 * 本の検索結果オブジェクト
 * @export
 * @interface Books
 */
export interface Books {
    /**
     * 
     * @type {string}
     * @memberof Books
     */
    isbn?: string;
    /**
     * 
     * @type {string}
     * @memberof Books
     */
    userId?: string;
}
/**
 * 
 * @export
 * @interface InlineResponse200
 */
export interface InlineResponse200 {
    /**
     * 
     * @type {Array | books}
     * @memberof InlineResponse200
     */
    books?: Array | books;
}
/**
 * 貸出帳の検索結果オブジェクト
 * @export
 * @interface LendingRecordDto
 */
export interface LendingRecordDto {
    /**
     * 
     * @type {string}
     * @memberof LendingRecordDto
     */
    isbn?: string;
    /**
     * 
     * @type {string}
     * @memberof LendingRecordDto
     */
    userId?: string;
    /**
     * 
     * @type {string}
     * @memberof LendingRecordDto
     */
    namae?: string;
    /**
     * 
     * @type {string}
     * @memberof LendingRecordDto
     */
    simei?: string;
}
/**
 * 貸出帳のオブジェクト
 * @export
 * @interface LendingRecordsDto
 */
export interface LendingRecordsDto {
    /**
     * 
     * @type {Array<LendingRecordDto>}
     * @memberof LendingRecordsDto
     */
    lendingRecords?: Array<LendingRecordDto>;
}

/**
 * BooksApi - axios parameter creator
 * @export
 */
export const BooksApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 本を検索する
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksGet(options: any = {}): RequestArgs {
            const localVarPath = `/books`;
            const localVarUrlObj = globalImportUrl.parse(localVarPath, true);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarUrlObj.query = {...localVarUrlObj.query, ...localVarQueryParameter, ...options.query};
            // fix override query string Detail: https://stackoverflow.com/a/7517673/1077943
            delete localVarUrlObj.search;
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...options.headers};

            return {
                url: globalImportUrl.format(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * BooksApi - functional programming interface
 * @export
 */
export const BooksApiFp = function(configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 本を検索する
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksGet(options?: any): (axios?: AxiosInstance, basePath?: string) => AxiosPromise<InlineResponse200> {
            const localVarAxiosArgs = BooksApiAxiosParamCreator(configuration).booksGet(options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
    }
};

/**
 * BooksApi - factory interface
 * @export
 */
export const BooksApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    return {
        /**
         * 
         * @summary 本を検索する
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksGet(options?: any) {
            return BooksApiFp(configuration).booksGet(options)(axios, basePath);
        },
    };
};

/**
 * BooksApi - object-oriented interface
 * @export
 * @class BooksApi
 * @extends {BaseAPI}
 */
export class BooksApi extends BaseAPI {
    /**
     * 
     * @summary 本を検索する
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof BooksApi
     */
    public booksGet(options?: any) {
        return BooksApiFp(this.configuration).booksGet(options)(this.axios, this.basePath);
    }

}


/**
 * BooksIsbnApi - axios parameter creator
 * @export
 */
export const BooksIsbnApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 本を登録する
         * @param {string} isbn 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksIsbnPut(isbn: string, options: any = {}): RequestArgs {
            // verify required parameter 'isbn' is not null or undefined
            if (isbn === null || isbn === undefined) {
                throw new RequiredError('isbn','Required parameter isbn was null or undefined when calling booksIsbnPut.');
            }
            const localVarPath = `/books/{isbn}`
                .replace(`{${"isbn"}}`, encodeURIComponent(String(isbn)));
            const localVarUrlObj = globalImportUrl.parse(localVarPath, true);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'PUT', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarUrlObj.query = {...localVarUrlObj.query, ...localVarQueryParameter, ...options.query};
            // fix override query string Detail: https://stackoverflow.com/a/7517673/1077943
            delete localVarUrlObj.search;
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...options.headers};

            return {
                url: globalImportUrl.format(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * BooksIsbnApi - functional programming interface
 * @export
 */
export const BooksIsbnApiFp = function(configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 本を登録する
         * @param {string} isbn 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksIsbnPut(isbn: string, options?: any): (axios?: AxiosInstance, basePath?: string) => AxiosPromise<void> {
            const localVarAxiosArgs = BooksIsbnApiAxiosParamCreator(configuration).booksIsbnPut(isbn, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
    }
};

/**
 * BooksIsbnApi - factory interface
 * @export
 */
export const BooksIsbnApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    return {
        /**
         * 
         * @summary 本を登録する
         * @param {string} isbn 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksIsbnPut(isbn: string, options?: any) {
            return BooksIsbnApiFp(configuration).booksIsbnPut(isbn, options)(axios, basePath);
        },
    };
};

/**
 * BooksIsbnApi - object-oriented interface
 * @export
 * @class BooksIsbnApi
 * @extends {BaseAPI}
 */
export class BooksIsbnApi extends BaseAPI {
    /**
     * 
     * @summary 本を登録する
     * @param {string} isbn 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof BooksIsbnApi
     */
    public booksIsbnPut(isbn: string, options?: any) {
        return BooksIsbnApiFp(this.configuration).booksIsbnPut(isbn, options)(this.axios, this.basePath);
    }

}


/**
 * BooksIsbnActionsApi - axios parameter creator
 * @export
 */
export const BooksIsbnActionsApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 本に対する行動
         * @param {string} isbn 
         * @param {Bookaction} [bookaction] パラメータのISBNの
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksIsbnActionsPost(isbn: string, bookaction?: Bookaction, options: any = {}): RequestArgs {
            // verify required parameter 'isbn' is not null or undefined
            if (isbn === null || isbn === undefined) {
                throw new RequiredError('isbn','Required parameter isbn was null or undefined when calling booksIsbnActionsPost.');
            }
            const localVarPath = `/books/{isbn}/actions`
                .replace(`{${"isbn"}}`, encodeURIComponent(String(isbn)));
            const localVarUrlObj = globalImportUrl.parse(localVarPath, true);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            localVarUrlObj.query = {...localVarUrlObj.query, ...localVarQueryParameter, ...options.query};
            // fix override query string Detail: https://stackoverflow.com/a/7517673/1077943
            delete localVarUrlObj.search;
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...options.headers};
            const needsSerialization = (<any>"Bookaction" !== "string") || localVarRequestOptions.headers['Content-Type'] === 'application/json';
            localVarRequestOptions.data =  needsSerialization ? JSON.stringify(bookaction !== undefined ? bookaction : {}) : (bookaction || "");

            return {
                url: globalImportUrl.format(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * BooksIsbnActionsApi - functional programming interface
 * @export
 */
export const BooksIsbnActionsApiFp = function(configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 本に対する行動
         * @param {string} isbn 
         * @param {Bookaction} [bookaction] パラメータのISBNの
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksIsbnActionsPost(isbn: string, bookaction?: Bookaction, options?: any): (axios?: AxiosInstance, basePath?: string) => AxiosPromise<void> {
            const localVarAxiosArgs = BooksIsbnActionsApiAxiosParamCreator(configuration).booksIsbnActionsPost(isbn, bookaction, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
    }
};

/**
 * BooksIsbnActionsApi - factory interface
 * @export
 */
export const BooksIsbnActionsApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    return {
        /**
         * 
         * @summary 本に対する行動
         * @param {string} isbn 
         * @param {Bookaction} [bookaction] パラメータのISBNの
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksIsbnActionsPost(isbn: string, bookaction?: Bookaction, options?: any) {
            return BooksIsbnActionsApiFp(configuration).booksIsbnActionsPost(isbn, bookaction, options)(axios, basePath);
        },
    };
};

/**
 * BooksIsbnActionsApi - object-oriented interface
 * @export
 * @class BooksIsbnActionsApi
 * @extends {BaseAPI}
 */
export class BooksIsbnActionsApi extends BaseAPI {
    /**
     * 
     * @summary 本に対する行動
     * @param {string} isbn 
     * @param {Bookaction} [bookaction] パラメータのISBNの
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof BooksIsbnActionsApi
     */
    public booksIsbnActionsPost(isbn: string, bookaction?: Bookaction, options?: any) {
        return BooksIsbnActionsApiFp(this.configuration).booksIsbnActionsPost(isbn, bookaction, options)(this.axios, this.basePath);
    }

}


/**
 * DefaultApi - axios parameter creator
 * @export
 */
export const DefaultApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 
         * @param {string} isbn 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksIsbnDelete(isbn: string, options: any = {}): RequestArgs {
            // verify required parameter 'isbn' is not null or undefined
            if (isbn === null || isbn === undefined) {
                throw new RequiredError('isbn','Required parameter isbn was null or undefined when calling booksIsbnDelete.');
            }
            const localVarPath = `/books/{isbn}`
                .replace(`{${"isbn"}}`, encodeURIComponent(String(isbn)));
            const localVarUrlObj = globalImportUrl.parse(localVarPath, true);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'DELETE', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarUrlObj.query = {...localVarUrlObj.query, ...localVarQueryParameter, ...options.query};
            // fix override query string Detail: https://stackoverflow.com/a/7517673/1077943
            delete localVarUrlObj.search;
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...options.headers};

            return {
                url: globalImportUrl.format(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * DefaultApi - functional programming interface
 * @export
 */
export const DefaultApiFp = function(configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 
         * @param {string} isbn 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksIsbnDelete(isbn: string, options?: any): (axios?: AxiosInstance, basePath?: string) => AxiosPromise<void> {
            const localVarAxiosArgs = DefaultApiAxiosParamCreator(configuration).booksIsbnDelete(isbn, options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
    }
};

/**
 * DefaultApi - factory interface
 * @export
 */
export const DefaultApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    return {
        /**
         * 
         * @summary 
         * @param {string} isbn 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        booksIsbnDelete(isbn: string, options?: any) {
            return DefaultApiFp(configuration).booksIsbnDelete(isbn, options)(axios, basePath);
        },
    };
};

/**
 * DefaultApi - object-oriented interface
 * @export
 * @class DefaultApi
 * @extends {BaseAPI}
 */
export class DefaultApi extends BaseAPI {
    /**
     * 
     * @summary 
     * @param {string} isbn 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof DefaultApi
     */
    public booksIsbnDelete(isbn: string, options?: any) {
        return DefaultApiFp(this.configuration).booksIsbnDelete(isbn, options)(this.axios, this.basePath);
    }

}


/**
 * LendingRecordsApi - axios parameter creator
 * @export
 */
export const LendingRecordsApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 貸出帳を検索する
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        lendingRecordsGet(options: any = {}): RequestArgs {
            const localVarPath = `/lendingRecords`;
            const localVarUrlObj = globalImportUrl.parse(localVarPath, true);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }
            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarUrlObj.query = {...localVarUrlObj.query, ...localVarQueryParameter, ...options.query};
            // fix override query string Detail: https://stackoverflow.com/a/7517673/1077943
            delete localVarUrlObj.search;
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...options.headers};

            return {
                url: globalImportUrl.format(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * LendingRecordsApi - functional programming interface
 * @export
 */
export const LendingRecordsApiFp = function(configuration?: Configuration) {
    return {
        /**
         * 
         * @summary 貸出帳を検索する
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        lendingRecordsGet(options?: any): (axios?: AxiosInstance, basePath?: string) => AxiosPromise<LendingRecordsDto> {
            const localVarAxiosArgs = LendingRecordsApiAxiosParamCreator(configuration).lendingRecordsGet(options);
            return (axios: AxiosInstance = globalAxios, basePath: string = BASE_PATH) => {
                const axiosRequestArgs = {...localVarAxiosArgs.options, url: basePath + localVarAxiosArgs.url};
                return axios.request(axiosRequestArgs);
            };
        },
    }
};

/**
 * LendingRecordsApi - factory interface
 * @export
 */
export const LendingRecordsApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    return {
        /**
         * 
         * @summary 貸出帳を検索する
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        lendingRecordsGet(options?: any) {
            return LendingRecordsApiFp(configuration).lendingRecordsGet(options)(axios, basePath);
        },
    };
};

/**
 * LendingRecordsApi - object-oriented interface
 * @export
 * @class LendingRecordsApi
 * @extends {BaseAPI}
 */
export class LendingRecordsApi extends BaseAPI {
    /**
     * 
     * @summary 貸出帳を検索する
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof LendingRecordsApi
     */
    public lendingRecordsGet(options?: any) {
        return LendingRecordsApiFp(this.configuration).lendingRecordsGet(options)(this.axios, this.basePath);
    }

}


