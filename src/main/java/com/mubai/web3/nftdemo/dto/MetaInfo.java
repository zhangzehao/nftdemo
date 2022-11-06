package com.mubai.web3.nftdemo.dto;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MetaInfo implements Serializable {

    private String image;

    private String name;

    private String description;

    private MetaProperties properties;

    @Data
    public static class MetaProperties implements Serializable {
        private String type;
        private List<Authors> authors;
        private JSONObject content;
    }

    @Data
    public static class Authors implements Serializable {
        private String name;
    }
}
