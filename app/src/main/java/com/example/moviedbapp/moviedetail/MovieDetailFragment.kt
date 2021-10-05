package com.example.moviedbapp.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moviedbapp.base.BaseFragment
import com.example.moviedbapp.bindImage
import com.example.moviedbapp.database.MovieDataRepository
import com.example.moviedbapp.database.MovieDatabase
import com.example.moviedbapp.databinding.FragmentMovieDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>() {

    override lateinit var viewModel: MovieDetailViewModel
    lateinit var movieDatabase: MovieDatabase
    lateinit var movieDataRepository: MovieDataRepository
    lateinit var factory: MovieDetailViewModelFactory
    override lateinit var dataBinding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentMovieDetailBinding.inflate(inflater)

        movieDatabase = MovieDatabase(requireContext())
        movieDataRepository = MovieDataRepository(movieDatabase)
        factory = MovieDetailViewModelFactory(movieDataRepository)

        viewModel = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        dataBinding.lifecycleOwner = this

        dataBinding.viewModel = viewModel

        val navController = this.findNavController()

        val id = MovieDetailFragmentArgs.fromBundle(requireArguments()).videoId

        var modelData: MovieDataModel? = null

        viewModel.getMovieDetailResponse(id).observe(viewLifecycleOwner, {
            val posterPath = "https://image.tmdb.org/t/p/original/" + it.posterPath
            dataBinding.movieDetailTitleText.text = it.title
            dataBinding.movieDetailOverviewText.text = it.overview
            dataBinding.movieDetailDateText.text = it.releaseDate
            dataBinding.movieDetailRunTimeText.text = it.runTime
            dataBinding.movieDetailVoteText.text = it.voteAverage
            bindImage(dataBinding.movieDetailPosterImage, posterPath)
            modelData = MovieDataModel(1, it.id, it.title)
        })

        dataBinding.movieDetailSaveButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.insertMovie(modelData!!)
            }
        }

        return dataBinding.root
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }
}