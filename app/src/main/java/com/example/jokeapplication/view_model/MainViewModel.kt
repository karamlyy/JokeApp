package com.example.jokeapplication.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapplication.api.JokeApi
import com.example.jokeapplication.model.JokeDTO
import com.example.jokeapplication.network.RetrofitClient
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    sealed class State{
        data class Success(val joke: JokeDTO): State()
        object Failure: State()
        object Loading: State()
        object Empty: State()

    }


    private val state: MutableLiveData<State> = MutableLiveData(State.Empty)
    private val jokeApi: JokeApi = RetrofitClient.jokeApi

    fun observeState(): LiveData<State> = state

    fun getJoke(){
         this.viewModelScope.launch {
             state.value = State.Loading
             try {
                 val joke = jokeApi.getRandomJoke()
                 state.value = State.Success(joke)
             }
             catch (e:Throwable){
                state.value = State.Failure
             }
         }
    }
}

