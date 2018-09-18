package bitcoin.wallet.kit.scripts

import bitcoin.walllet.kit.hdwallet.Utils

object ScriptType {
    const val P2PKH = 1 // pay to pubkey hash (aka pay to address)
    const val P2PK = 2  // pay to pubkey
    const val P2SH = 3  // pay to script hash
    const val UNKNOWN = 0
}

class Script(bytes: ByteArray) {
    var chunks = listOf<ScriptChunk>()

    // Creation time of the associated keys in seconds since the epoch.
    private var creationTimeSeconds: Long

    init {
        chunks = ScriptParser.parseChunks(bytes)
        creationTimeSeconds = 0
    }

    fun getPubKeyHash(): ByteArray? {
        if (ScriptParser.isP2PKH(this))
            return chunks[2].data
        if (ScriptParser.isP2PK(this))
            return Utils.sha256Hash160(chunks[0].data)
        if (ScriptParser.isP2SH(this))
            return chunks[1].data

        return null
    }

    fun getScriptType(): Int {
        if (ScriptParser.isP2PKH(this))
            return ScriptType.P2PKH
        if (ScriptParser.isP2PK(this))
            return ScriptType.P2PK
        if (ScriptParser.isP2SH(this))
            return ScriptType.P2SH

        return ScriptType.UNKNOWN
    }

    override fun toString() = chunks.joinToString(" ")
}
