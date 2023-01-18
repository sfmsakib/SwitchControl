package com.bitopi.switchcontrol.model.utils

import com.bitopi.switchcontrol.model.Movie

object ValidationUtil {

    fun validateMovie(movie: Movie) : Boolean {
        if (movie.name.isNotEmpty() && movie.category.isNotEmpty()) {
            return true
        }
        return false
    }
}