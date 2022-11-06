package com.mubai.web3.nftdemo.util;

import cn.hutool.core.io.resource.MultiResource;
import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSONObject;
import com.mubai.web3.nftdemo.dto.MetaInfo;
import com.mubai.web3.nftdemo.dto.NFTStorageResp;

import java.io.File;

public class NFTStorageUtil {

    private static HttpRequest request = HttpRequest.post("https://api.nft.storage/store")
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJkaWQ6ZXRocjoweDQxZTk5MDA2ZmJCZTUxOTI1NjcxN0FiNDQ0YmNGMGU0NjhmOUFBNDQiLCJpc3MiOiJuZnQtc3RvcmFnZSIsImlhdCI6MTY2NzcxNTE3ODE3NSwibmFtZSI6InpoYW5nemVoYW8ifQ.5VtUZvvzVuVlblnw0IfZauX2opIM6cjXsbgAIwDoge4");

    public static NFTStorageResp store(MultiResource multiResource, MetaInfo metaInfo) {
        HttpResponse httpResponse = request.form("image", multiResource, "meta", JSONObject.toJSONString(metaInfo)).execute();
        return handleResp(httpResponse);
    }

    public static NFTStorageResp store(File file, MetaInfo metaInfo) {
        HttpResponse httpResponse = request.form("image", file, "meta", JSONObject.toJSONString(metaInfo)).execute();
        return handleResp(httpResponse);
    }

    private static NFTStorageResp handleResp(HttpResponse httpResponse) {
        Assert.isTrue(httpResponse.getStatus() == 200, "http状态不正确");
        String body = httpResponse.body();
        if (body != null) {
            NFTStorageResp resp = JSONObject.parseObject(body, NFTStorageResp.class);
            if (resp.getOk() != null && resp.getOk()) {
                return resp;
            }
            throw new RuntimeException(body);
        }
        return null;
    }

}
