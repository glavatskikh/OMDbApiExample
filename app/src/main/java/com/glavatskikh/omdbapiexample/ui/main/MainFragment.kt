package com.glavatskikh.omdbapiexample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.glavatskikh.omdbapiexample.R
import com.glavatskikh.omdbapiexample.ui.details.DetailsFragment
import com.glavatskikh.omdbapiexample.ui.main.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var adapter: MoviesAdapter
    private lateinit var viewModel: MainViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = MoviesAdapter { movie ->
            activity?.run {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, DetailsFragment.newInstance(movie))
                    .addToBackStack(null)
                    .commit()
            }
        }
        f_main_movies_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        f_main_movies_recycler_view.adapter = adapter
        initViewModelObservers()
    }

    private fun initViewModelObservers() {
        viewModel.progressBarVisibleLiveData.observe(viewLifecycleOwner, { isVisible ->
            f_main_progressbar.visibility = if (isVisible) View.VISIBLE else View.GONE
        })
        viewModel.moviesLiveData.observe(viewLifecycleOwner, { movies ->
            adapter.setMovies(movies)
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        })
    }
}