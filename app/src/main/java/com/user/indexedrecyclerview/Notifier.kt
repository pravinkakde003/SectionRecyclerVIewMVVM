package com.user.indexedrecyclerview

internal class Notifier private constructor(
    val type: Type,
    val positionStart: Int,
    val itemCount: Int
) {
    internal enum class Type {
        ALL_DATA_CHANGED, CHANGED, INSERTED, REMOVED
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val notifier = o as Notifier
        if (positionStart != notifier.positionStart) return false
        return if (itemCount != notifier.itemCount) false else type == notifier.type
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + positionStart
        result = 31 * result + itemCount
        return result
    }

    override fun toString(): String {
        return "Notifier{" +
                "type=" + type +
                ", positionStart=" + positionStart +
                ", itemCount=" + itemCount +
                '}'
    }

    companion object {
        private fun create(
            type: Type,
            positionStart: Int,
            itemCount: Int
        ): Notifier {
            return Notifier(type, positionStart, itemCount)
        }

        @JvmStatic
        fun createAllDataChanged(): Notifier {
            return create(
                Type.ALL_DATA_CHANGED,
                0,
                0
            )
        }

        @JvmOverloads
        fun createChanged(positionStart: Int, itemCount: Int = 1): Notifier {
            return create(
                Type.CHANGED,
                positionStart,
                itemCount
            )
        }

        @JvmOverloads
        fun createInserted(positionStart: Int, itemCount: Int = 1): Notifier {
            return create(
                Type.INSERTED,
                positionStart,
                itemCount
            )
        }
    }

}