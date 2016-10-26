package com.yhb.bean.response;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class LocationResult {
    private String resultcode;
    private String reason;
    private Result result;
    private int errorCode;

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public class Result {
        private String area;
        private String location;

        public void setArea(String area) {
            this.area = area;
        }

        public String getArea() {
            return area;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }
    }
}
