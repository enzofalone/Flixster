package com.efalone.flixster

import android.util.Log
import org.json.JSONArray

data class Movie(
    val movieId: Int,
    private val posterPath: String,
    val title: String,
    val overview: String,
    ) {
    val posterImageURL = "https://image.tmdb.org/t/p/w342/$posterPath"
    val placeholderImageURL = "https://github.com/enzofalone/Flixster/blob/main/placeholder.gif"

    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)

                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview")
                    )
                )
            }
            return movies
        }
    }

}