package com.android.jetpacprodua.ui.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.jetpacprodua.data.source.local.entity.MovieKorea
import com.android.jetpacprodua.data.source.local.entity.TvKorea
import com.android.jetpacprodua.databinding.FragmentTvBinding
import com.android.jetpacprodua.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment() {
    private var fragmentTvBindingN: FragmentTvBinding? = null
    private lateinit var tAdapter: TvAdapter
    private val viewModel: TvViewModel by activityViewModels()
    private val fragmentTvBinding get() = fragmentTvBindingN!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentTvBindingN = FragmentTvBinding.inflate(inflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            viewModel.getTvKorea().observe(viewLifecycleOwner, {
                tAdapter = TvAdapter()
                tAdapter.notifyDataSetChanged()
                fragmentTvBinding.recyclerViewTv.adapter = tAdapter
                fragmentTvBinding.recyclerViewTv.visibility = View.VISIBLE
                fragmentTvBinding.recyclerViewTv.layoutManager = LinearLayoutManager(context)
                fragmentTvBinding.recyclerViewTv.setHasFixedSize(true)
                tAdapter.setOnItemClickCallback(
                    object : TvAdapter.OnItemClickCallback {
                        override fun onItemClicked(data: TvKorea) {
                            showSelectedMovie(data)
                        }
                    }
                )
            })


        }
    }

    private fun showSelectedMovie(data: TvKorea) {

        Toast.makeText(requireActivity(), "Kamu memilih ${data.name}", Toast.LENGTH_SHORT).show()
        val user = MovieKorea(
            data.id,
            data.poster_path,
            data.overview,
            data.name,
            data.vote_average

        )
        val i = Intent(context, DetailActivity::class.java)
        i.putExtra(DetailActivity.EXTRA_DATA, user)
        startActivity(i)


    }


}