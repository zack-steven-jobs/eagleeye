package com.cmsz.eagleeye.util;



public class CommConfig {
    private String collectURL;
    private String collectURLByUpay;
    /** 同步总表URL */
    private String collectBussTotalURL;
    /** 同步明细表URL */
    private String collectBussDetailURL;
    /**
     * 开通了批扣的省代码
     */
    private String pbillProvinceCode;

    private String exportSize;// 默认导出记录数

    public String getPbillProvinceCode() {
        return pbillProvinceCode;
    }

    public void setPbillProvinceCode(String pbillProvinceCode) {
        this.pbillProvinceCode = pbillProvinceCode;
    }

    public String getCollectURL() {
        return collectURL;
    }

    public void setCollectURL(String collectURL) {
        this.collectURL = collectURL;
    }

    public String getCollectURLByUpay() {
        return collectURLByUpay;
    }

    public void setCollectURLByUpay(String collectURLByUpay) {
        this.collectURLByUpay = collectURLByUpay;
    }

    public String getCollectBussTotalURL() {
        return collectBussTotalURL;
    }

    public void setCollectBussTotalURL(String collectBussTotalURL) {
        this.collectBussTotalURL = collectBussTotalURL;
    }

    public String getCollectBussDetailURL() {
        return collectBussDetailURL;
    }

    public void setCollectBussDetailURL(String collectBussDetailURL) {
        this.collectBussDetailURL = collectBussDetailURL;
    }

    /**
     * @return the exportSize
     */
    public String getExportSize() {
        return exportSize;
    }

    /**
     * @param exportSize the exportSize to set
     */
    public void setExportSize(String exportSize) {
        this.exportSize = exportSize;
    }


}
