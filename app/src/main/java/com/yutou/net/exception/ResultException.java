package com.yutou.net.exception;

/**
 *描    述：接收异常 来自服务器数据code值不为40000的情况
 *创 建 人：ZJY
 *创建日期：2017/4/6 17:26
 *修订历史：
 *修 改 人：
 */
public class ResultException extends RuntimeException {
    private String errCode;

    public ResultException(String errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }
}
