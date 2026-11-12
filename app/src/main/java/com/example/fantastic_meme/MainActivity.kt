package com.example.fantastic_meme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fantastic_meme.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MovieAdapter
    var movies: List<Response> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = "Star Wars: Episode IV - A New Hope"
        val id = "tt0076759"
        val api = Api.get()
        adapter = MovieAdapter(movies) {
            val movie = movies[it]
            val intent = Intent(this, Moviee::class.java)
            intent.putExtra("ID", movie.imdbID)
            startActivity(intent)
        }
        binding.recycler.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.name(t=name)
            //val response = api.id(i=id)
            CoroutineScope(Dispatchers.Main).launch {
                movies = movies + response
                update(movies)
            }
        }
    }
    fun update(movies: List<Response>) {
        adapter.update(movies)
    }
}