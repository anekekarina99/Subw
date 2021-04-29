package com.android.jetpacprodua.ui.movie

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
import com.android.jetpacprodua.databinding.FragmentMovieBinding
import com.android.jetpacprodua.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()
    private var fragmentMovieBindingN: FragmentMovieBinding? = null
    private val fragmentMovieBinding get() = fragmentMovieBindingN!!
    private lateinit var mAdapter: MovieAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentMovieBindingN = FragmentMovieBinding.inflate(inflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel.getMovieKorea().observe(viewLifecycleOwner, {
                mAdapter = MovieAdapter()
                mAdapter.notifyDataSetChanged()
                fragmentMovieBinding.recyclerViewMovie.adapter = mAdapter
                fragmentMovieBinding.recyclerViewMovie.visibility = View.VISIBLE
                fragmentMovieBinding.recyclerViewMovie.layoutManager = LinearLayoutManager(context)
                fragmentMovieBinding.recyclerViewMovie.setHasFixedSize(true)
                mAdapter.setOnItemClickCallback(
                    object : MovieAdapter.OnItemClickCallback {
                        override fun onItemClicked(data: MovieKorea) {
                            showSelectedMovie(data)
                        }
                    }
                )
            })


        }
    }

    private fun showSelectedMovie(data: MovieKorea) {

        Toast.makeText(requireActivity(), "Kamu memilih ${data.title}", Toast.LENGTH_SHORT).show()
        val user = MovieKorea(
            data.id,
            data.poster_path,
            data.overview,
            data.title,
            data.vote_average

        )
        val i = Intent(context, DetailActivity::class.java)
        i.putExtra(DetailActivity.EXTRA_DATA, user)
        startActivity(i)


    }


}