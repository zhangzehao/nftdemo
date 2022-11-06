## 一个不知道是否正确的生成ERC1155 NFT例子

solidity智能合约开发环境：https://remix.ethereum.org/
<br/>
钱包工具：metamask
<br/>
合约部署测试网络：goerli

http请求例子：
<br/>

curl --location --request POST 'http://localhost:8080/nft/upload' \
--form 'file=@"/C:/Users/zhang/Pictures/20221103153554.jpg"' \
--form 'mintAmount="1"' \
--form 'metaData="{\"image\":null,\"name\":\"zhangzehao-wutongshan-02\",\"description\":\"张泽豪制作的梧桐山景色NFT\",\"properties\":{\"type\":\"wutongshan\",\"authors\":[{\"name\":\"zhangzehao\"}],\"content\":{\"text/markdown\":\"张泽豪制作的梧桐山景色NFT\"}}}"'

返回结果：
<br/>

{
    "code": 200,
    "tokenId": "1589207316103327744",
    "tokenUrl": "ipfs://bafyreidpzhzuqvhpbs65a45gfewjqcwhe3yw6vbhsn5bzgcb5vdqqepg4u/metadata.json",
    "data": {
        "transactionHash": "0xd655b4f3018b1b056f17eb779e56806d81795b5e4a957219dc45f70c9604c014",
        "transactionIndex": 78,
        "blockHash": "0xc453bb6c2a694e3dcde84cf8d8bfa07b7fc46912203e3c88e9a9c5b184a8c91d",
        "blockNumber": 7902399,
        "cumulativeGasUsed": 10500559,
        "gasUsed": 140617,
        "contractAddress": null,
        "root": null,
        "status": "0x1",
        "from": "0x1586b55ad81153585f8c2be336497071edc9e54a",
        "to": "0x2f746b19c78115c2df182159d170b3f3ab6e4591",
        "logs": [
            {
                "removed": false,
                "logIndex": 516,
                "transactionIndex": 78,
                "transactionHash": "0xd655b4f3018b1b056f17eb779e56806d81795b5e4a957219dc45f70c9604c014",
                "blockHash": "0xc453bb6c2a694e3dcde84cf8d8bfa07b7fc46912203e3c88e9a9c5b184a8c91d",
                "blockNumber": 7902399,
                "address": "0x2f746b19c78115c2df182159d170b3f3ab6e4591",
                "data": "0x000000000000000000000000000000000000000000000000160dffa24eca70000000000000000000000000000000000000000000000000000000000000000001",
                "type": null,
                "topics": [
                    "0xc3d58168c5ae7397731d063d5bbf3d657854427343f4c083240f7aacaa2d0f62",
                    "0x0000000000000000000000001586b55ad81153585f8c2be336497071edc9e54a",
                    "0x0000000000000000000000000000000000000000000000000000000000000000",
                    "0x0000000000000000000000001586b55ad81153585f8c2be336497071edc9e54a"
                ],
                "logIndexRaw": "0x204",
                "transactionIndexRaw": "0x4e",
                "blockNumberRaw": "0x7894bf"
            }
        ],
        "logsBloom": "0x00000000000000000000000080000000000000400000000000000000000000000000000000000000000000000000000000000000000000000000080000002000000000000000000000000000000000000000000000000000000000000000000000000000020000000000000000000800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000080000000000000000000000000000000000100000000000000000000000000000000000000000000008000000000000000020000000000000000000000000000008000000000000000000000000080000000000",
        "revertReason": null,
        "type": "0x0",
        "effectiveGasPrice": "0xf4610900",
        "statusOK": true,
        "gasUsedRaw": "0x22549",
        "transactionIndexRaw": "0x4e",
        "cumulativeGasUsedRaw": "0xa039cf",
        "blockNumberRaw": "0x7894bf"
    }
}
