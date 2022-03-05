package com.efalone.flixster

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

const val MOVIE_EXTRA = "MOVIE_EXTRA"

class MovieAdapter(private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        //        private val ivBackdrop = itemView.findViewById<ImageView>(R.id.ivBackdrop)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.ConstraintLayout)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview

            Glide.with(context)
                .load(movie.posterImageURL)
                .dontAnimate()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fitCenter()
                .transform(RoundedCorners(20))
                .into(ivPoster)

//TODO: fix backdrop image, code commented below does not work

//            Glide.with(context)
//                .load(movie.backdropImageURL)
//                .placeholder(R.drawable.placeholder)
//                .error(R.drawable.error)
//                    .fitCenter()
//                .into(ivBackdrop)
        }

        override fun onClick(v: View?) {
            //get notified of the particular movie which was clicked
            val movie = movies[adapterPosition]

            //use the intent system to navigate to the new activity
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)

        }

    }

}