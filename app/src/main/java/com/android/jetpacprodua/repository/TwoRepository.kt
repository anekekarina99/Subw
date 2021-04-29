package com.android.jetpacprodua.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.jetpacprodua.data.source.local.entity.MovieKorea
import com.android.jetpacprodua.data.source.local.entity.TvKorea
import com.android.jetpacprodua.data.source.remote.MovieTvDataSource
import com.android.jetpacprodua.data.source.remote.api.ApiService
import com.android.jetpacprodua.data.source.remote.response.MovieRemoteResponse
import com.android.jetpacprodua.data.source.remote.response.TvRemoteResponse
import com.android.jetpacprodua.utils.Benefit.Companion.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TwoRepository @Inject constructor(
    private val api: ApiService,
) : MovieTvDataSource {

    companion object{
        const val TAG = "TwoRepository"
    }

    override fun getMovieKoreaPopular(): LiveData<ArrayList<MovieKorea>> {
        val mKorea = MutableLiveData<ArrayList<MovieKorea>>()

        CoroutineScope(Dispatchers.IO).launch {
            val resMovie = api.getMovieKoreaPopular(API_KEY)
            if(resMovie.isSuccessful){
                mKorea.postValue(resMovie.body()?.hasil as ArrayList<MovieKorea>?)
                Log.d(this@TwoRepository.toString(), "get Movie Korea Berhasil")
            }else{
                    Log.e(TAG,"Maaf belum berhasil getMovieKorea")
            }
        }
        return  mKorea
    }

    override fun getMovieKoreaDetail(id: Int): LiveData<MovieRemoteResponse> {
        val detailM = MutableLiveData<MovieRemoteResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            val resDetailM = api.getMovieKoreaDetail(id, API_KEY)
            if (resDetailM.isSuccessful){
                    detailM.postValue(resDetailM.body())
                Log.d(this@TwoRepository.toString(), "get Movie Korea Berhasil")
            }else{
                Log.e(TAG,"Error pada getMovieKoreaDetail")
            }
        }
        return  detailM
    }

    override fun getTvKoreaPopular(): LiveData<ArrayList<TvKorea>> {
        val mTv  =   MutableLiveData<ArrayList<TvKorea>>()

        CoroutineScope(Dispatchers.IO).launch {
            val resTv  = api.getTvKoreaPopular(API_KEY)
            if(resTv.isSuccessful){
                mTv.postValue(resTv.body()?.hasil as ArrayList<TvKorea>?)
                Log.d(this@TwoRepository.toString(), "get Movie Korea Berhasil")
            }else{
                Log.e(TAG,"Maaf belum berhasil getTvKorea")
            }
        }
        return  mTv
    }

    override fun getDetailTvKorea(id: Int): LiveData<TvRemoteResponse> {
        val detailT= MutableLiveData<TvRemoteResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            val resDetailT= api.getTvKoreaDetail(id, API_KEY)
            if (resDetailT.isSuccessful){
                detailT.postValue(resDetailT.body())
                Log.d(this@TwoRepository.toString(), "get Movie Korea Berhasil")
            }else{
                Log.e(TAG,"Error pada getMovieKoreaDetail")
            }
        }
        return  detailT
    }
}


