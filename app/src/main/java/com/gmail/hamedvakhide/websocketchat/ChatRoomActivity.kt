package com.gmail.hamedvakhide.websocketchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.hamedvakhide.websocketchat.databinding.ActivityChatRoomBinding

class ChatRoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatRoomBinding

    private var name: String? = null
    private var chatList: MutableList<Chat> = mutableListOf()

    private lateinit var chatAdapter: ChatAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        name = intent.getStringExtra("username")// logged in username

        chatAdapter = ChatAdapter()
        binding.rvMessages.apply {
            this.adapter = chatAdapter
            this.layoutManager = LinearLayoutManager(context)
        }

        SocketHandler.setSocket()
        val mSocket = SocketHandler.getSocket()
        mSocket.connect()
        mSocket.emit("user", name) // emit name of connected user to server

        binding.topAppBar.setNavigationOnClickListener {
            mSocket.disconnect()
            finish()
        }

        mSocket.on("online") { args ->
            if (args[0] != null) {
                val count = args[0] as Int
                runOnUiThread {
                    binding.topAppBar.title = if (count>1){
                        "$count online users"
                    } else{
                        "$count online user"
                    }
                }
            }
        }

        mSocket.on("msg") { args ->
            if (args[0] != null) {
                val uName = args[0] as String
                val message = args[1] as String

                chatList.add(Chat(uName, message))
                runOnUiThread {
                    chatAdapter.submitList(chatList)
                    binding.rvMessages.scrollToPosition(binding.rvMessages.adapter!!.itemCount-1)
                }

            }
        }


        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString()
            binding.etMessage.setText("")
            mSocket.emit("msg", text)
            chatList.add(Chat("", text))
            chatAdapter.submitList(chatList)
            binding.rvMessages.scrollToPosition(binding.rvMessages.adapter!!.itemCount-1)

        }

    }

}