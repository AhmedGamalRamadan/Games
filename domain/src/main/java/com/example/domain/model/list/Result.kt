package com.example.domain.model.list


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("added")
    var added: Int? = 0,
    @SerialName("added_by_status")
    var addedByStatus: AddedByStatus? = AddedByStatus(),
    @SerialName("background_image")
    var backgroundImage: String? = "",
    @SerialName("clip")
    var clip: String? = null,
    @SerialName("dominant_color")
    var dominantColor: String? = "",
    @SerialName("esrb_rating")
    var esrbRating: EsrbRating? = EsrbRating(),
    @SerialName("genres")
    var genres: List<Genre>? = listOf(),
    @SerialName("id")
    var id: Int? = 0,
    @SerialName("metacritic")
    var metacritic: Int? = 0,
    @SerialName("name")
    var name: String? = "",
    @SerialName("parent_platforms")
    var parentPlatforms: List<ParentPlatform>? = listOf(),
    @SerialName("platforms")
    var platforms: List<PlatformX>? = listOf(),
    @SerialName("playtime")
    var playtime: Int? = 0,
    @SerialName("rating")
    var rating: Double? = 0.0,
    @SerialName("rating_top")
    var ratingTop: Int? = 0,
    @SerialName("ratings")
    var ratings: List<Rating>? = listOf(),
    @SerialName("ratings_count")
    var ratingsCount: Int? = 0,
    @SerialName("released")
    var released: String? = "",
    @SerialName("reviews_count")
    var reviewsCount: Int? = 0,
    @SerialName("reviews_text_count")
    var reviewsTextCount: Int? = 0,
    @SerialName("saturated_color")
    var saturatedColor: String? = "",
    @SerialName("score")
    var score: String? = null,
    @SerialName("short_screenshots")
    var shortScreenshots: List<ShortScreenshot>? = listOf(),
    @SerialName("slug")
    var slug: String? = "",
    @SerialName("stores")
    var stores: List<Store>? = listOf(),
    @SerialName("suggestions_count")
    var suggestionsCount: Int? = 0,
    @SerialName("tags")
    var tags: List<Tag>? = listOf(),
    @SerialName("tba")
    var tba: Boolean? = false,
    @SerialName("updated")
    var updated: String? = "",
    @SerialName("user_game")
    var userGame: String? = null
)