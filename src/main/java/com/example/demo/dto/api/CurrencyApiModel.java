package com.example.demo.dto.api;

import java.util.Map;

public class CurrencyApiModel {
    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, Currency> bpi; // 動態貨幣資料可以用 Map 儲存

    // Getters & Setters (可以用 Lombok @Getter/@Setter 簡化)
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public String getDisclaimer() {
        return disclaimer;
    }
    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }
    public String getChartName() {
        return chartName;
    }
    public void setChartName(String chartName) {
        this.chartName = chartName;
    }
    public Map<String, Currency> getBpi() {
        return bpi;
    }
    public void setBpi(Map<String, Currency> bpi) {
        this.bpi = bpi;
    }

}

class Time {
    private String updated;
    private String updatedISO;
    private String updateduk;

    // Getters & Setters
    public String getUpdated() {
        return updated;
    }
    public void setUpdated(String updated) {
        this.updated = updated;
    }
    public String getUpdatedISO() {
        return updatedISO;
    }
    public void setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
    }
    public String getUpdateduk() {
        return updateduk;
    }
    public void setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
    }
}


