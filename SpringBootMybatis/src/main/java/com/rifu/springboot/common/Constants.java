package com.rifu.springboot.common;

/**
 * @Author Rifu
 * @Date 2018/9/28  0:48
 */
public class Constants {

    public enum ProductCode{
        SUCCESS(100,"SUCCESS"),
        ERROR(200,"ERROR"),
        NO_EXIST(300,"NO_EXIST"),
        HAS_EXIST(400,"HAS_EXIST"),
        OP_FAIL(500,"OP_FAIL");
        int code;
        String desc;

        ProductCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum CategoryCode{
        SUCCESS(100,"SUCCESS"),
        ERROR(200,"ERROR"),
        NO_EXIST(300,"NO_EXIST"),
        HAS_EXIST(400,"HAS_EXIST"),
        OP_FAIL(500,"OP_FAIL");
        int code;
        String desc;

        CategoryCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
