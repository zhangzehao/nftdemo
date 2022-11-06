package com.mubai.web3.nftdemo.controller;

import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.core.io.resource.MultiResource;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson2.JSONObject;
import com.mubai.web3.nftdemo.dto.MetaInfo;
import com.mubai.web3.nftdemo.dto.NFTStorageResp;
import com.mubai.web3.nftdemo.dto.Response;
import com.mubai.web3.nftdemo.util.ContractCallUtil;
import com.mubai.web3.nftdemo.util.NFTStorageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Slf4j
@RestController
@RequestMapping("/nft")
public class MintNFTController {

    @PostMapping("/upload")
    public Response upload(MultipartFile file, Integer mintAmount, String metaData) {
        try {
            Assert.isTrue(file != null, "请上传nft图片");
            Assert.isTrue(metaData != null, "请输入nft的元数据信息");
            Assert.isTrue(mintAmount != null && mintAmount > 0, "请输入铸造的数量");
            MultiResource multiResource = new MultiResource(new InputStreamResource(file.getInputStream(), file.getOriginalFilename()));
            MetaInfo metaInfo = JSONObject.parseObject(metaData, MetaInfo.class);
            NFTStorageResp storeResp = NFTStorageUtil.store(multiResource, metaInfo);
            Snowflake snowflake = new Snowflake();
            String tid = snowflake.nextIdStr();
            String tokenUrl = storeResp.getValue().getUrl();
            TransactionReceipt mintResp = ContractCallUtil.mint("0x1586b55aD81153585F8C2be336497071EdC9E54a", mintAmount,
                    tid, tokenUrl);
            return Response.<TransactionReceipt>builder().code(200)
                    .tokenId(tid)
                    .tokenUrl(tokenUrl)
                    .data(mintResp).build();
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return Response.builder().code(500).data(e.getMessage()).build();
        }
    }

}
