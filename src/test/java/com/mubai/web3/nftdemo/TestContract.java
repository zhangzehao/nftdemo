package com.mubai.web3.nftdemo;


import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson2.JSONObject;
import com.mubai.web3.nftdemo.dto.MetaInfo;
import com.mubai.web3.nftdemo.dto.NFTStorageResp;
import com.mubai.web3.nftdemo.smartContract.Minty;
import com.mubai.web3.nftdemo.util.NFTStorageUtil;
import org.junit.jupiter.api.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.math.BigInteger;

public class TestContract {

    @Test
    public void storeTest() {
        File file = new File("D:\\nft\\20221103153554.jpg");
        MetaInfo metaInfo = new MetaInfo();
        metaInfo.setImage(null);
        metaInfo.setName("nft name");
        metaInfo.setDescription("这是一个描述");
        NFTStorageResp resp = NFTStorageUtil.store(file, metaInfo);
        System.out.println(JSONObject.toJSONString(resp));
    }

    @Test
    public void contractTest() throws Exception {
        String nodeUrl = "https://goerli.infura.io/v3/cf2a345446014268a2a5bdcaa8dd3f1d";
        String contractAddress = "0x2f746B19C78115C2Df182159d170b3F3ab6e4591";
        String privateKey = "64563d9751625d10449831a844f244dfdd257aa4df6a9b736c967e39d6acecf4";

        HttpService httpService = new HttpService(nodeUrl);
        Web3j web3j = Web3j.build(httpService);
        Minty mintNFT = Minty.load(contractAddress, web3j, Credentials.create(privateKey), new DefaultGasProvider());
        System.out.println("isValid=" + mintNFT.isValid());

        Snowflake snowflake = new Snowflake();
        String tid = "1589173358921670656";//snowflake.nextIdStr();
        System.out.println("tid= " + tid);
        TransactionReceipt receipt = mintNFT.mint("0x1586b55aD81153585F8C2be336497071EdC9E54a",
                new BigInteger("1"),
                new BigInteger(tid),
                "ipfs://bafyreiaegydr7kanjixef5qirtrcyfi7pndyk3j65yntyqmninv7biw4x4/metadata.json").send();
        System.out.println(receipt);

        receipt = mintNFT.balanceOf("0x1586b55aD81153585F8C2be336497071EdC9E54a", new BigInteger(tid)).send();
        System.out.println(receipt);

        web3j.shutdown();
    }

}
