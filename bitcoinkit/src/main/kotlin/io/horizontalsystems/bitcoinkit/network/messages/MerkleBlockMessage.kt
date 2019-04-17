package io.horizontalsystems.bitcoinkit.network.messages

import io.horizontalsystems.bitcoinkit.io.BitcoinInput
import io.horizontalsystems.bitcoinkit.serializers.BlockHeaderSerializer
import io.horizontalsystems.bitcoinkit.storage.BlockHeader
import io.horizontalsystems.bitcoinkit.utils.HashUtils
import java.io.ByteArrayInputStream

/**
 * MerkleBlock Message
 *
 *  Size        Field           Description
 *  ====        =====           ===========
 *  80 bytes    Header          Consists of 6 fields that are hashed to calculate the block hash
 *  VarInt      hashCount       Number of hashes
 *  Variable    hashes          Hashes in depth-first order
 *  VarInt      flagsCount      Number of bytes of flag bits
 *  Variable    flagsBits       Flag bits packed 8 per byte, least significant bit first
 */
class MerkleBlockMessage(
        var header: BlockHeader,
        var txCount: Int,
        var hashCount: Int,
        var hashes: List<ByteArray>,
        var flagsCount: Int,
        var flags: ByteArray
) : IMessage {
    override val command: String = "merkleblock"

    private val blockHash: String by lazy {
        HashUtils.toHexStringAsLE(header.hash)
    }

    override fun toString(): String {
        return "MerkleBlockMessage(blockHash=$blockHash, hashesSize=${hashes.size})"
    }
}

class MerkleBlockMessageParser : IMessageParser {
    override val command = "merkleblock"

    override fun parseMessage(payload: ByteArray): IMessage {
        BitcoinInput(ByteArrayInputStream(payload)).use { input ->
            val header = BlockHeaderSerializer.deserialize(input)
            val txCount = input.readInt()

            val hashCount = input.readVarInt().toInt()
            val hashes: MutableList<ByteArray> = mutableListOf()
            repeat(hashCount) {
                hashes.add(input.readBytes(32))
            }

            val flagsCount = input.readVarInt().toInt()
            val flags = input.readBytes(flagsCount)

            return MerkleBlockMessage(header, txCount, hashCount, hashes, flagsCount, flags)
        }
    }
}
