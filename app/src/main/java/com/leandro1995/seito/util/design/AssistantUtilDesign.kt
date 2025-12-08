package com.leandro1995.seito.util.design

import androidx.recyclerview.widget.RecyclerView

class AssistantUtilDesign {

    companion object {
        fun insertLastChatItem(recyclerView: RecyclerView?, itemCount: Int?) {
            val lastPosition = (itemCount ?: 0) - 1

            if (lastPosition >= 0) {
                recyclerView?.scrollToPosition(lastPosition)
            }
        }
    }
}