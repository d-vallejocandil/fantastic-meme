package com.example.fantastic_meme

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fantastic_meme.databinding.ActivityMainBinding
import com.example.fantastic_meme.databinding.ActivityMovieeBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Moviee : AppCompatActivity() {
    lateinit var binding: ActivityMovieeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMovieeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = intent.getStringExtra("ID")!!
        val api = Api.get()
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.id(i = id)
            CoroutineScope(Dispatchers.Main).launch {
                binding.name.text = response.Title
                binding.year.text = response.Year.toString()
                Picasso.get().load(response.Poster).into(binding.poster)
                binding.plot.text = response.Plot
                binding.runtime.text = response.Runtime
                binding.director.text = response.Director
                binding.genre.text = response.Genre
                binding.country.text = response.Country
            }
        }
    }
}