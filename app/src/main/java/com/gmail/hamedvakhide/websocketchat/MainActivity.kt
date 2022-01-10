package com.gmail.hamedvakhide.websocketchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.hamedvakhide.websocketchat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUserName.text.toString()
            if(username.isNotEmpty()){
                val intent = Intent(this,ChatRoomActivity::class.java)
                intent.putExtra("username",binding.etUserName.text.toString())
                startActivity(intent)
            }
        }
    }
}