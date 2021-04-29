package com.android.jetpacprodua.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.jetpacprodua.data.source.local.entity.MovieKorea
import com.android.jetpacprodua.data.source.local.entity.TvKorea
import com.android.jetpacprodua.data.source.remote.response.MovieRemoteResponse
import com.android.jetpacprodua.data.source.remote.response.TvRemoteResponse

interface MovieTvDataSource {

    fun getMovieKoreaPopular(): LiveData<ArrayList<MovieKorea>>

    fun getMovieKoreaDetail(id: Int): LiveData<MovieRemoteResponse>

    fun getTvKoreaPopular(): LiveData<ArrayList<TvKorea>>

    fun getDetailTvKorea(id: Int): LiveData<TvRemoteResponse>
}