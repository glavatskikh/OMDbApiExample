package com.glavatskikh.omdbapiexample.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glavatskikh.omdbapiexample.api.response.Movie
import com.glavatskikh.omdbapiexample.api.response.MoviesResult
import com.glavatskikh.omdbapiexample.usecase.GetMoviesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    private val _progressBarVisibleLiveData = MutableLiveData<Boolean>()
    val progressBarVisibleLiveData: LiveData<Boolean> = _progressBarVisibleLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    init {
        loadMovies("Batman")
    }

    private fun loadMovies(search: String) {
        viewModelScope.launch {
            try {
                _progressBarVisibleLiveData.value = true
                when (val result =
                    withContext(Dispatchers.IO) { GetMoviesUseCaseImpl().getMovies(search) }) {
                    is MoviesResult.Success -> _moviesLiveData.postValue(result.movies)
                    is MoviesResult.Error -> {
                        Log.d("MoviesApp", "Loading error: $result.message")
                        _errorLiveData.postValue("Error please try later")
                    }
                }
                _progressBarVisibleLiveData.value = false
            } catch (error: Throwable) {
                _errorLiveData.postValue("Error please try later")
                Log.d("MoviesApp", "Loading error:", error)
                error.printStackTrace()
            } finally {
                _progressBarVisibleLiveData.value = false
            }
        }
    }
}