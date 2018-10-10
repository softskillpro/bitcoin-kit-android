package bitcoin.wallet.kit.network

import bitcoin.wallet.kit.models.Block

class RegTest : TestNet() {

    override var port: Int = 18444

    override var dnsSeeds: Array<String> = arrayOf(
            "blocknode01.grouvi.org",
            "blocknode02.grouvi.org",
            "blocknode03.grouvi.org",
            "blocknode04.grouvi.org"
    )

    override val checkpointBlock = Block()

    override fun validate(block: Block, previousBlock: Block) {
        validator.validateHeader(block, previousBlock)
    }
}
