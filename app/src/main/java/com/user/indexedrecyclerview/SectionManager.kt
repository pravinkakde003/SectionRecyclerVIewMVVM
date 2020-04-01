package com.user.indexedrecyclerview

import androidx.annotation.IntRange
import com.user.indexedrecyclerview.Notifier.Companion.createAllDataChanged
import java.util.*

internal class SectionManager(private val sectionProvider: SectionProvider) {
    val sections: MutableList<Section> =
        ArrayList()

    fun init() {
        sections.clear()
        if (sectionProvider.itemSize != 0) {
            sections.add(Section(0))
        } else {
            return
        }
        var lastSectionItemCount = sectionProvider.itemSize
        for (i in 1 until sectionProvider.itemSize) {
            if (sectionProvider.onPlaceSubheaderBetweenItems(i - 1)) {
                val section =
                    Section(i + sections.size)
                val previousSection = lastSection
                val sectionItemCount =
                    section.subheaderPosition - previousSection.subheaderPosition - 1
                previousSection.itemCount = sectionItemCount
                sections.add(section)
                lastSectionItemCount -= sectionItemCount
            }
        }
        val lastSection = lastSection
        lastSection.itemCount = lastSectionItemCount
    }

    fun isSectionSubheaderAtPosition(
        @IntRange(
            from = 0,
            to = Int.MAX_VALUE.toLong()
        ) position: Int
    ): Boolean {
        for (section in sections) {
            if (section.subheaderPosition == position) {
                return true
            }
        }
        return false
    }

    fun getItemPositionForSubheaderViewHolder(
        @IntRange(
            from = 0,
            to = Int.MAX_VALUE.toLong()
        ) subheaderPosition: Int
    ): Int {
        var itemPosition = 0
        val sectionIndex = sectionIndex(subheaderPosition)
        for (i in 0 until sectionIndex) {
            itemPosition += sections[i].itemCount
        }
        return itemPosition
    }

    fun getItemPositionForItemViewHolder(
        @IntRange(
            from = 0,
            to = Int.MAX_VALUE.toLong()
        ) itemHolderPosition: Int
    ): Int {
        var itemHolderPosition = itemHolderPosition
        val sectionIndex = sectionIndex(itemHolderPosition)
        itemHolderPosition -= sectionIndex + 1
        for (i in 0 until sectionIndex) {
            val section = sections[i]
            if (!section.isExpanded) {
                itemHolderPosition += section.itemCount
            }
        }
        return itemHolderPosition
    }

    fun onDataChanged(): NotifyResult {
        init()
        return NotifyResult.create(createAllDataChanged())
    }

    fun sectionIndex(
        @IntRange(
            from = 0,
            to = Int.MAX_VALUE.toLong()
        ) adapterPosition: Int
    ): Int {
        var sectionIndex = 0
        for (i in sections.indices) {
            val section = sections[i]
            if (adapterPosition == section.subheaderPosition) {
                return i
            } else if (adapterPosition > section.subheaderPosition) {
                sectionIndex = i
            }
        }
        return sectionIndex
    }

    val itemCount: Int
        get() {
            var itemCount = 0
            for (section in sections) {
                itemCount += 1
                if (section.isExpanded) {
                    itemCount += section.itemCount
                }
            }
            return itemCount
        }

    fun isFirstItemInSection(adapterPosition: Int): Boolean {
        return positionInSection(adapterPosition) == 0
    }

    fun isLastItemInSection(adapterPosition: Int): Boolean {
        val section =
            getSection(sectionIndex(adapterPosition))
        return positionInSection(adapterPosition) == section.itemCount - 1
    }

    fun positionInSection(
        @IntRange(
            from = 0,
            to = Int.MAX_VALUE.toLong()
        ) itemAdapterPosition: Int
    ): Int {
        val section =
            getSection(sectionIndex(itemAdapterPosition))
        return itemAdapterPosition - section.subheaderPosition - 1
    }

    fun sectionSize(sectionIndex: Int): Int {
        return getSection(sectionIndex).itemCount
    }

    fun getSectionSubheaderPosition(sectionIndex: Int): Int {
        return getSection(sectionIndex).subheaderPosition
    }

    val sectionsCount: Int
        get() = sections.size

    fun clear() {
        sections.clear()
    }

    private fun getSection(
        @IntRange(
            from = 0,
            to = Int.MAX_VALUE.toLong()
        ) sectionIndex: Int
    ): Section {
        return sections[sectionIndex]
    }

    private val lastSection: Section
        private get() = sections[sections.size - 1]

}