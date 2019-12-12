/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.apple.model.to;

import java.util.List;

/**
 * Desc 验证Receipt返回信息
 *
 * @author sourcod
 * @version 1.0
 * @className VerifyReceiptTo
 * @date 2019/2/27 5:29 PM
 */
public class VerifyReceiptTo {

    /**
     * 状态码
     */
    private int status;

    /**
     * 收据
     */
    private Receipt receipt;

    /**
     * 环境
     */
    private String environment;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }



    public static class Receipt{
        private String receipt_type;
        private int adam_id;
        private int app_item_id;
        private String bundle_id;
        private String application_version;
        private int download_id;
        private int version_external_identifier;
        private String receipt_creation_date;
        private String receipt_creation_date_ms;
        private String receipt_creation_date_pst;
        private String request_date;
        private String request_date_ms;
        private String request_date_pst;
        private String original_purchase_date;
        private String original_purchase_date_ms;
        private String original_purchase_date_pst;
        private String original_application_version;

        /**
         * in_app
         */
        private List<InApp> in_app;

        public String getReceipt_type() {
            return receipt_type;
        }

        public void setReceipt_type(String receipt_type) {
            this.receipt_type = receipt_type;
        }

        public int getAdam_id() {
            return adam_id;
        }

        public void setAdam_id(int adam_id) {
            this.adam_id = adam_id;
        }

        public int getApp_item_id() {
            return app_item_id;
        }

        public void setApp_item_id(int app_item_id) {
            this.app_item_id = app_item_id;
        }

        public String getBundle_id() {
            return bundle_id;
        }

        public void setBundle_id(String bundle_id) {
            this.bundle_id = bundle_id;
        }

        public String getApplication_version() {
            return application_version;
        }

        public void setApplication_version(String application_version) {
            this.application_version = application_version;
        }

        public int getDownload_id() {
            return download_id;
        }

        public void setDownload_id(int download_id) {
            this.download_id = download_id;
        }

        public int getVersion_external_identifier() {
            return version_external_identifier;
        }

        public void setVersion_external_identifier(int version_external_identifier) {
            this.version_external_identifier = version_external_identifier;
        }

        public String getReceipt_creation_date() {
            return receipt_creation_date;
        }

        public void setReceipt_creation_date(String receipt_creation_date) {
            this.receipt_creation_date = receipt_creation_date;
        }

        public String getReceipt_creation_date_ms() {
            return receipt_creation_date_ms;
        }

        public void setReceipt_creation_date_ms(String receipt_creation_date_ms) {
            this.receipt_creation_date_ms = receipt_creation_date_ms;
        }

        public String getReceipt_creation_date_pst() {
            return receipt_creation_date_pst;
        }

        public void setReceipt_creation_date_pst(String receipt_creation_date_pst) {
            this.receipt_creation_date_pst = receipt_creation_date_pst;
        }

        public String getRequest_date() {
            return request_date;
        }

        public void setRequest_date(String request_date) {
            this.request_date = request_date;
        }

        public String getRequest_date_ms() {
            return request_date_ms;
        }

        public void setRequest_date_ms(String request_date_ms) {
            this.request_date_ms = request_date_ms;
        }

        public String getRequest_date_pst() {
            return request_date_pst;
        }

        public void setRequest_date_pst(String request_date_pst) {
            this.request_date_pst = request_date_pst;
        }

        public String getOriginal_purchase_date() {
            return original_purchase_date;
        }

        public void setOriginal_purchase_date(String original_purchase_date) {
            this.original_purchase_date = original_purchase_date;
        }

        public String getOriginal_purchase_date_ms() {
            return original_purchase_date_ms;
        }

        public void setOriginal_purchase_date_ms(String original_purchase_date_ms) {
            this.original_purchase_date_ms = original_purchase_date_ms;
        }

        public String getOriginal_purchase_date_pst() {
            return original_purchase_date_pst;
        }

        public void setOriginal_purchase_date_pst(String original_purchase_date_pst) {
            this.original_purchase_date_pst = original_purchase_date_pst;
        }

        public String getOriginal_application_version() {
            return original_application_version;
        }

        public void setOriginal_application_version(String original_application_version) {
            this.original_application_version = original_application_version;
        }

        public List<InApp> getIn_app() {
            return in_app;
        }

        public void setIn_app(List<InApp> in_app) {
            this.in_app = in_app;
        }

        public static class InApp{

            /**
             * 数量
             */
            private String quantity;

            /**
             * 产品Id
             */
            private String product_id;

            /**
             * 事物Id
             */
            private String transaction_id;

            /**
             * 原始事物Id
             */
            private String original_transaction_id;

            /**
             * 购买时间
             */
            private String purchase_date;

            /**
             * 购买时间毫秒
             */
            private String purchase_date_ms;

            /**
             * 购买日期
             */
            private String purchase_date_pst;

            /**
             * 原始购买日期
             */
            private String original_purchase_date;

            /**
             * 原始购买日期毫秒
             */
            private String original_purchase_date_ms;

            /**
             * 原始购买日期(pst)
             */
            private String original_purchase_date_pst;

            /**
             * 是否是试用期
             */
            private String is_trial_period;

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getTransaction_id() {
                return transaction_id;
            }

            public void setTransaction_id(String transaction_id) {
                this.transaction_id = transaction_id;
            }

            public String getOriginal_transaction_id() {
                return original_transaction_id;
            }

            public void setOriginal_transaction_id(String original_transaction_id) {
                this.original_transaction_id = original_transaction_id;
            }

            public String getPurchase_date() {
                return purchase_date;
            }

            public void setPurchase_date(String purchase_date) {
                this.purchase_date = purchase_date;
            }

            public String getPurchase_date_ms() {
                return purchase_date_ms;
            }

            public void setPurchase_date_ms(String purchase_date_ms) {
                this.purchase_date_ms = purchase_date_ms;
            }

            public String getPurchase_date_pst() {
                return purchase_date_pst;
            }

            public void setPurchase_date_pst(String purchase_date_pst) {
                this.purchase_date_pst = purchase_date_pst;
            }

            public String getOriginal_purchase_date() {
                return original_purchase_date;
            }

            public void setOriginal_purchase_date(String original_purchase_date) {
                this.original_purchase_date = original_purchase_date;
            }

            public String getOriginal_purchase_date_ms() {
                return original_purchase_date_ms;
            }

            public void setOriginal_purchase_date_ms(String original_purchase_date_ms) {
                this.original_purchase_date_ms = original_purchase_date_ms;
            }

            public String getOriginal_purchase_date_pst() {
                return original_purchase_date_pst;
            }

            public void setOriginal_purchase_date_pst(String original_purchase_date_pst) {
                this.original_purchase_date_pst = original_purchase_date_pst;
            }

            public String getIs_trial_period() {
                return is_trial_period;
            }

            public void setIs_trial_period(String is_trial_period) {
                this.is_trial_period = is_trial_period;
            }
        }
    }
}
