package com.example.startandroidacademy.data

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val poster: String?,
    val backdrop: String?,
    var ratings: Float,
    var numberOfRatings: Int,
    var minimumAge: Int?,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        TODO("genres"),
        TODO("actors")
    ) {
    }

    fun getReview(): String {
        return "$ Reviews"   //не чего поэтому поводу не нашел в лодере хз дге их брать
    }

    fun getRuntime(): String {
        return "$runtime min"
    }


    fun getRating(): Float {
        return if (ratings <= 0) 0F else ratings / 5
    }

    fun getTag(): String {
        return genres.joinToString(separator = ", ", transform = { genreItem -> genreItem.name })
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(poster)
        parcel.writeString(backdrop)
        parcel.writeFloat(ratings)
        parcel.writeInt(numberOfRatings)
        parcel.writeValue(minimumAge)
        parcel.writeInt(runtime)
    }

    override fun describeContents(): Int {
        return 0
    }

  /*  fun getMinimumAge(): String {
        val adult:Boolean?=false
        return if (adult<18) "18+" else "13+"  // не пойму как ему возраст установить
    }*/

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }


}