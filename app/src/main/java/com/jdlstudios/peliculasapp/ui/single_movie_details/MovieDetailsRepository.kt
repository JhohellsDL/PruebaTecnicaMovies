package com.jdlstudios.peliculasapp.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.jdlstudios.peliculasapp.data.api.TheMovieDBInterface
import com.jdlstudios.peliculasapp.data.repository.MovieDetailsNetworkDataSource
import com.jdlstudios.peliculasapp.data.repository.NetworkState
import com.jdlstudios.peliculasapp.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService : TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }



}