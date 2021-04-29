package com.android.jetpacprodua.ui.detail

import androidx.lifecycle.ViewModel
import com.android.jetpacprodua.repository.TwoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val allRepository: TwoRepository) : ViewModel() {


    fun getMovieKoreaDetail(id : Int) = allRepository.getMovieKoreaDetail(id)

    fun getTvKoreaDetail(id : Int) = allRepository.getDetailTvKorea(id)
}