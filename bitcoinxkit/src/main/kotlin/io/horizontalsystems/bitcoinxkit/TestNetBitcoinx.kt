package io.horizontalsystems.bitcoinxkit

import io.horizontalsystems.bitcoincore.network.Network

class TestNetBitcoinx : Network() {

    override var port: Int = 29296

    override var magic: Long = 0x2255073f
    override var bip32HeaderPub: Int = 0x043587CF
    override var bip32HeaderPriv: Int = 0x04358394
    override var addressVersion: Int = 66
    override var addressSegwitHrp: String = "tbtcx"
    override var addressScriptVersion: Int = 65
    override var coinType: Int = 1

    override val maxBlockSize = 1_000_000
    override val dustRelayTxFee = 3000 // https://github.com/bitcoin/bitcoin/blob/c536dfbcb00fb15963bf5d507b7017c241718bf6/src/policy/policy.h#L50

    override var dnsSeeds = listOf(
            "bitcoin-testnet.bloqseeds.net"          // Bloq
    )
}
