package com.example.jokeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.jokeapplication.databinding.ActivityMainBinding
import com.example.jokeapplication.view_model.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.button.setOnClickListener {
            viewModel.getJoke()
        }
        viewModel.observeState().observe(this, Observer{state ->
            when(state){
                is MainViewModel.State.Success -> {
                    binding.progressBar.visibility = GONE
                    binding.joke.text = state.joke.joke
                    binding.type.text = state.joke.type
                }
                is MainViewModel.State.Failure -> {
                    binding.progressBar.visibility = GONE
                    Toast.makeText(this, "An error has occured!", Toast.LENGTH_LONG).show()
                }
                is MainViewModel.State.Loading -> {
                    binding.progressBar.visibility = VISIBLE
                }
                is MainViewModel.State.Empty -> {
                    binding.progressBar.visibility = GONE
                    binding.joke.text = ""
                    binding.type.text = ""
                }
            }
        })
    }
}

