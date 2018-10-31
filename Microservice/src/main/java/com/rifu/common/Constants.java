package com.rifu.common;

/**
 * @Author Rifu
 * @Date 2018/10/13  15:15
 */
public class Constants {
    public static final String CURRENT_USER="currentUser";

    public static final String ARTICLE="article";

    public static final String ERROR_MSG="errorMsg";

    public static final String SERVER_IMG="http://img.zava.vip/img/";

    public static final String ES_INDEX="zava_eyu";

    public static final String ES_ARTICLE_TYPE="article";

    public static final Integer TOP=1;

    public static final Integer RECOMMEND=2;

    public enum ArticleStatus{
        NORMAL(1,"NORMAL"),
        DRAFT(2,"DRAFT"),
        SHIELD(3,"SHIELD"),
        DELETE(4,"DELETE"),
        RECOMMEND(5,"RECOMMEND");
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        ArticleStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
