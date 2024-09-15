package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().baseUrl("https://api.quotable.io/").addConverterFactory(GsonConverterFactory.create()).build()
        val servis = retrofit.create(servis::class.java)

        binding.button.setOnClickListener{
            GlobalScope.launch(Dispatchers.Main)
            {
                val data= withContext(Dispatchers.IO) {
                    return@withContext servis.get_data()
                }
                binding.textView.text = data.content
            }
        }

    }
}