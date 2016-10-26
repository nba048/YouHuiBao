/**
 *
 */
package com.yhb.bean.response;

import com.yhb.bean.entity.Frequent;

import java.util.List;

/**
 * @author 宋昌明
 */
public class FrequentResult extends AResponse {
    private String code;
    private List<Frequent> datas;

    @Override
    public String toString() {
        return "FrequentResult [Code=" + code + ", data=" + data + "]";
    }

    public List<Frequent> getData() {
        return datas;
    }

    public String getCode() {
        return code;
    }
}
