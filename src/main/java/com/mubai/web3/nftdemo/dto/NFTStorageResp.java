package com.mubai.web3.nftdemo.dto;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class NFTStorageResp implements Serializable {

    private Boolean ok;

    private StorageValue value;

    @Data
    public static class StorageValue implements Serializable {
        private String ipnft;
        private String url;
        private ValueData data;
    }

    @Data
    private static class ValueData implements Serializable {
        private String image;
        private String name;
        private String description;
        private JSONObject properties;
    }

}
