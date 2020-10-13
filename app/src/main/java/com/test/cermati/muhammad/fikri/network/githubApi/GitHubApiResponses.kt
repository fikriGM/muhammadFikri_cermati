package com.test.cermati.muhammad.fikri.githubApi

import com.google.gson.annotations.SerializedName
import com.test.cermati.muhammad.fikri.model.User

/*
* Created by Muhammad Fikri on 11/10/2020
* */
data class SearchUserResponses(
    @SerializedName("total_count")
    var total_count : Int? = null,
    @SerializedName("incomplete_results")
    var incomplete_results : Boolean? = null,
    @SerializedName("items")
    var users : List<User>? = null
) : BasicErrorResponse()

data class RateLimitResponse(
    @SerializedName("rate")
    var rate : Limit? = null,
    @SerializedName("resources")
    var resources : ResourcesLimit? = null
)

data class ResourcesLimit(
    @SerializedName("core")
    var core : Limit? = null,
    @SerializedName("search")
    var search : Limit? = null,
    @SerializedName("graphql")
    var graphql : Limit? = null,
    @SerializedName("integration_manifest")
    var integration_manifest : Limit? = null
)

data class Limit(
    @SerializedName("limit")
    var limit : Int? = null,
    @SerializedName("remaining")
    var remaining : Int? = null,
    @SerializedName("reset")
    var reset : Long? = null
)

open class BasicErrorResponse {
    @SerializedName("message")
    var message : String? = null
    @SerializedName("documentation_url")
    var documentation_url : String? = null
}