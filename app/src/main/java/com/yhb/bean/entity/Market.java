package com.yhb.bean.entity;

/**
 * market实体
 *
 * @author 宋昌明
 */
public class Market extends AEntity {
    private String iconUrl;
    private String modelId;
    /**
     * 市场id	String
     */
    private String marketId;
    /**
     * 市场名称	String
     */
    private String marketName;

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public String toString() {
        return "Market [marketId=" + marketId + ", marketName=" + marketName + "]";
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
