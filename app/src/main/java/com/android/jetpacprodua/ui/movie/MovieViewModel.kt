package com.android.jetpacprodua.ui.movie

import androidx.lifecycle.ViewModel
import com.android.jetpacprodua.repository.TwoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val allRepository: TwoRepository) : ViewModel(){
    fun getMovieKorea() = allRepository.getMovieKoreaPopular()
}