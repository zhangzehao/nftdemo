package com.mubai.web3.nftdemo.util;

import cn.hutool.core.lang.Assert;
import com.mubai.web3.nftdemo.smartContract.Minty;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;

@Slf4j
public class ContractCallUtil {

    private final static String nodeUrl = "https://goerli.infura.io/v3/cf2a345446014268a2a5bdcaa8dd3f1d";
    private final static String contractAddress = "0x2f746B19C78115C2Df182159d170b3F3ab6e4591";
    private final static String privateKey = "64563d9751625d10449831a844f244dfdd257aa4df6a9b736c967e39d6acecf4";
    private static Web3j web3j;
    private static Minty mintNFT;

    static {
        HttpService httpService = new HttpService(nodeUrl);
        web3j = Web3j.build(httpService);
        mintNFT = Minty.load(contractAddress, web3j, Credentials.create(privateKey), new DefaultGasProvider());
        try {
            Assert.isTrue(mintNFT.isValid(), "合约调用初始化失败");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                if (web3j != null) {
                    web3j.shutdown();
                }
            }
        }));
    }

    public static TransactionReceipt mint(String recipient, Integer amount, String tid, String tokenUrl) throws Exception {
        TransactionReceipt receipt = mintNFT.mint(recipient, new BigInteger(amount.toString()), new BigInteger(tid), tokenUrl).send();
        return receipt;
    }
}
