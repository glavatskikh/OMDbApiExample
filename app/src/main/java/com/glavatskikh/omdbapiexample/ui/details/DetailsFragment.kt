package com.glavatskikh.omdbapiexample.ui.details

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.glavatskikh.omdbapiexample.R
import com.glavatskikh.omdbapiexample.api.response.Movie
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private var mViewModel: DetailsViewModel? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val movie: Movie = arguments?.getParcelable(ARG_MOVIE)
            ?: throw IllegalArgumentException("missing argument!")
        with(movie) {
            f_details_poster.load(poster)
            f_details_title.text = title
            f_details_years.text = year
        }
    }

    companion object {

        private const val ARG_MOVIE = "arg.movie"

        fun newInstance(movie: Movie): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(ARG_MOVIE to movie)
            }
        }
    }
}