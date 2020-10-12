package io.horizontalsystems.bitcoinxkit

import io.horizontalsystems.bitcoincore.network.Network

class MainNetBitcoinx : Network() {

    override var port: Int = 19296

    override var magic: Long = 0xd0d6cecdL
    override var bip32HeaderPub: Int = 0x0488B21E   // The 4 byte header that serializes in base58 to "xpub".
    override var bip32HeaderPriv: Int = 0x0488ADE4  // The 4 byte header that serializes in base58 to "xprv"
    override var addressVersion: Int = 76
    override var addressSegwitHrp: String = "btcx"
    override var addressScriptVersion: Int = 75
    override var coinType: Int = 0

    override val maxBlockSize = 1_000_000
    override val dustRelayTxFee = 3000 // https://github.com/bitcoin/bitcoin/blob/c536dfbcb00fb15963bf5d507b7017c241718bf6/src/policy/policy.h#L50

    override var dnsSeeds = listOf(
            "node1.walletbuilders.com"
    )
}
