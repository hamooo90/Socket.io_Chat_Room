package com.gmail.hamedvakhide.websocketchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter : ListAdapter<Chat, ChatAdapter.ChatViewHolder>(COMPARATOR) {
    class ChatViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val msgContentReceived: TextView =
            itemView.findViewById(R.id.list_item_text_message_received)
        private val msgUser: TextView = itemView.findViewById(R.id.list_item_text_name_received)
        private val msgContentSent: TextView =
            itemView.findViewById(R.id.list_item_text_message_sent)


        private val cardViewReceived: CardView = itemView.findViewById(R.id.list_item_card_received)
        private val cardViewSent: CardView = itemView.findViewById(R.id.list_item_card_sent)

        fun bind(chat: Chat) {
            if (chat.uName.isEmpty()) {
                cardViewReceived.visibility = View.GONE
                cardViewSent.visibility = View.VISIBLE
                msgContentSent.text = chat.message
            } else{
                cardViewReceived.visibility = View.VISIBLE
                cardViewSent.visibility = View.GONE
                msgContentReceived.text = chat.message
                msgUser.text = chat.uName

            }
        }

        companion object {
            fun from(parent: ViewGroup): ChatViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.list_item_received, parent, false)
                return ChatViewHolder(itemView)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Chat>() {
            override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val msg = getItem(position)
        msg.apply {
            holder.bind(this)
        }
    }

    override fun submitList(list: List<Chat>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

}