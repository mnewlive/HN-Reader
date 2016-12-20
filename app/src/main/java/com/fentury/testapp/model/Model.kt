package com.fentury.testapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Model : Serializable {

    @SerializedName("by") var by: String? = null
    @SerializedName("descendants") var descendants: Int? = null
    @SerializedName("id") var id: Int? = null
    @SerializedName("kids") var kids: List<Int>? = ArrayList()
    @SerializedName("score") var score: Int? = null
    @SerializedName("time") var time: Int? = null
    @SerializedName("title") var title: String? = null
    @SerializedName("type") var type: String? = null
    @SerializedName("url") var url: String? = null

    override fun toString(): String {
        return "Model(by=$by, descendants=$descendants, id=$id, kids=$kids, score=$score," +
                " time=$time, title=$title, type=$type, url=$url)"
    }

    val kidsAsString: String
        get() {
            if (kids == null || kids!!.isEmpty()) {
                return ""
            } else {
                val stringBuilder = StringBuilder()
                for (i in kids!!.indices) {
                    stringBuilder.append(kids!![i])
                    if (i + 1 != kids!!.size) {
                        stringBuilder.append("\n")
                    }
                }
                return stringBuilder.toString()
            }
        }
}