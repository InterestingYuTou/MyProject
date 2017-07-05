package com.yutou.net;

/**
 * 描    述：服务器通用状态数据
 * 创 建 人：ZJY
 * 创建日期：2017/4/7 9:50
 * 修订历史：
 * 修 改 人：
 */

public class HttpStatus {
    private String code;
    private String hint;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
