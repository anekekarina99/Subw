package com.android.jetpacprodua.ui.tv

import androidx.lifecycle.ViewModel
import com.android.jetpacprodua.repository.TwoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val allRepository: TwoRepository) : ViewModel(){
    fun getTvKorea() = allRepository.getTvKoreaPopular()
}