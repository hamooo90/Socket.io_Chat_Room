package com.gmail.hamedvakhide.websocketchat

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    lateinit var mSocket: Socket
    private const val SERVER_PATH = "http://10.0.2.2:3000" // local server address

    @Synchronized
    fun setSocket() {
        try {
            mSocket = IO.socket(SERVER_PATH)
        } catch (e: URISyntaxException) {

        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

//    @Synchronized
//    fun establishConnection() {
//        mSocket.connect()
//    }
//
//    @Synchronized
//    fun closeConnection() {
//        mSocket.disconnect()
//    }
}