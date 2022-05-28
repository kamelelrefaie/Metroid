package com.example.metroid.model.remote.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StationResponse(val from:String , val to:String) : Parcelable