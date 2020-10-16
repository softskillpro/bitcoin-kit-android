package io.horizontalsystems.xcoinkit

import io.horizontalsystems.bitcoincore.network.Network

class TestNetXcoin : Network() {

    override val protocolVersion = 70215
    override val noBloomVersion = 70201

    override var port: Int = 29746

    override var magic: Long = 0xbbb9d1a1
    override var bip32HeaderPub: Int = 0x0488B21E   // The 4 byte header that serializes in base58 to "xpub".
    override var bip32HeaderPriv: Int = 0x0488ADE4  // The 4 byte header that serializes in base58 to "xprv"
    override var addressVersion: Int = 65
    override var addressSegwitHrp: String = "bc"
    override var addressScriptVersion: Int = 66
    override var coinType: Int = 1

    override val maxBlockSize = 1_000_000
    override val dustRelayTxFee = 1000 // https://github.com/dashpay/dash/blob/master/src/policy/policy.h#L36

    override var dnsSeeds = listOf(
            "node1.walletbuilders.com"
    )
}
