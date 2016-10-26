/**
 *
 */
package com.yhb.bean.response;

import com.yhb.bean.entity.Order;

import java.util.List;

/**
 * @author 宋昌明
 *         委托列表
 */
public class OrderResult extends AResponse {
    /**
     * 响应状态码	Integer	系统常量，具体数值意义见code码表。
     */
    private String code;
    public String msg;
    /**
     * 查询条件-时间区间起
     */
    private String startDate;
    /**
     * 查询条件-时间区间止
     */
    private String endDate;
    /**
     * 分页参数-页码
     */
    private String pageNo;
    /**
     * 分页参数-数据量
     */
    private String pageSize;
    /**
     * 分页参数-数据总量
     */
    private String total;
    /**
     * 当前条件下所有的委托数据
     */
    private List<Order> datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getData() {
        return datas;
    }

    public void setData(List<Order> data) {
        this.datas = data;
    }

    @Override
    public String toString() {
        return "OrderResult [code=" + code + ", startDate=" + startDate + ", endDate=" + endDate + ", pageNo=" + pageNo
                + ", pageSize=" + pageSize + ", total=" + total + ", data=" + data + "]";
    }


}
