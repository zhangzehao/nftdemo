// SPDX-License-Identifier: MIT
pragma solidity >=0.7.0 <0.9.0;

import "@openzeppelin/contracts/token/ERC1155/ERC1155.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

contract Minty is ERC1155 {
    using Counters for Counters.Counter;
    Counters.Counter private _tokenId;

    mapping(uint256 => string) private _tokenURIs;

    constructor() ERC1155("") {}

    function mint(
        address _recipient,
        uint256 _amount,
        uint256 _tid,
        string memory _tokenUrl
    ) public returns (uint256 _mintTokenId) {
        require(bytes(_tokenUrl).length > 0, "The _tokenUrl must be have");
        require(_amount > 0, "The _amount must be have");
        _mint(_recipient, _tid, _amount, "");
        _tokenURIs[_tid] = _tokenUrl;
        return _tid;
    }

    function mint2(
        address _recipient,
        uint256 _amount,
        string memory _tokenUrl
    ) public returns (uint256 _mintTokenId) {
        require(bytes(_tokenUrl).length > 0, "The _tokenUrl must be have");
        require(_amount > 0, "The _amount must be have");
        _tokenId.increment();
        uint256 newTokenId = _tokenId.current();
        _mint(_recipient, newTokenId, _amount, "");
        _tokenURIs[newTokenId] = _tokenUrl;
        return newTokenId;
    }

    function uri(uint256 _id)
    public
    view
    override
    returns (string memory _tokenUrl)
    {
        return _tokenURIs[_id];
    }
}
