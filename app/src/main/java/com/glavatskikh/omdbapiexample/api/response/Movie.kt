package com.glavatskikh.omdbapiexample.api.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Movie(
	@SerializedName("Title")
	val title: String,
	@SerializedName("Year")
	val year: String,
	@SerializedName("imdbID")
	val imdbID: String,
	@SerializedName("Type")
	val type: String,
	@SerializedName("Poster")
	val poster: String
) : Parcelable {

    constructor(source: Parcel) : this(
		source.readString().orEmpty(),
		source.readString().orEmpty(),
		source.readString().orEmpty(),
		source.readString().orEmpty(),
		source.readString().orEmpty(),
	)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(year)
        writeString(imdbID)
        writeString(type)
        writeString(poster)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}