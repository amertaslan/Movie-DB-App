package com.example.moviedbapp.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.R
import com.example.moviedbapp.base.BaseFragment
import com.example.moviedbapp.databinding.FragmentMovieListBinding

class MovieListFragment : BaseFragment<MovieListViewModel, FragmentMovieListBinding>(), MovieListListener {

    override val viewModel: MovieListViewModel by viewModels()
    override lateinit var dataBinding: FragmentMovieListBinding
    var isScrolling: Boolean = false
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var pastVisibleItems: Int = 0
    var page: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentMovieListBinding.inflate(inflater)

        dataBinding.lifecycleOwner = this

        dataBinding.viewModel = viewModel

        dataBinding.movieList.adapter = MovieListAdapter(this)

        //interface'e eklenip clean architecture ile daha iyi yapılabilirdi yetiştirmek için burada bırakıyorum
        dataBinding.movieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 ) {
                    visibleItemCount = recyclerView.layoutManager!!.childCount
                    totalItemCount = viewModel.itemCount
                    pastVisibleItems = (recyclerView.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()

                    if (isScrolling &&  pastVisibleItems + 1 >= totalItemCount) {
                        page++
                        viewModel.getNextPagesResponses(page)
                        isScrolling = false
                    }
                }
            }
        })

        return dataBinding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_movie_list
    }

    override fun onClick(id: String) {
        this.findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(id))
    }
}