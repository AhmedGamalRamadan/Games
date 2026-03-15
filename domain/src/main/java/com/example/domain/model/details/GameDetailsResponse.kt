package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsResponse(
    @SerialName("achievements_count")
    var achievementsCount: Int? = 0,
    @SerialName("added")
    var added: Int? = 0,
    @SerialName("added_by_status")
    var addedByStatus: AddedByStatus? = AddedByStatus(),
    @SerialName("additions_count")
    var additionsCount: Int? = 0,
    @SerialName("alternative_names")
    var alternativeNames: List<String>? = listOf(),
    @SerialName("background_image")
    var backgroundImage: String? = "",
    @SerialName("background_image_additional")
    var backgroundImageAdditional: String? = "",
    @SerialName("clip")
    var clip: String? = null,
    @SerialName("creators_count")
    var creatorsCount: Int? = 0,
    @SerialName("description")
    var description: String? = "",
    @SerialName("description_raw")
    var descriptionRaw: String? = "",
    @SerialName("developers")
    var developers: List<Developer>? = listOf(),
    @SerialName("dominant_color")
    var dominantColor: String? = "",
    @SerialName("esrb_rating")
    var esrbRating: EsrbRating? = EsrbRating(),
    @SerialName("game_series_count")
    var gameSeriesCount: Int? = 0,
    @SerialName("genres")
    var genres: List<Genre>? = listOf(),
    @SerialName("id")
    var id: Int? = 0,
    @SerialName("metacritic")
    var metacritic: Int? = 0,
    @SerialName("metacritic_platforms")
    var metacriticPlatforms: List<MetacriticPlatform>? = listOf(),
    @SerialName("metacritic_url")
    var metacriticUrl: String? = "",
    @SerialName("movies_count")
    var moviesCount: Int? = 0,
    @SerialName("name")
    var name: String? = "",
    @SerialName("name_original")
    var nameOriginal: String? = "",
    @SerialName("parent_achievements_count")
    var parentAchievementsCount: Int? = 0,
    @SerialName("parent_platforms")
    var parentPlatforms: List<ParentPlatform>? = listOf(),
    @SerialName("parents_count")
    var parentsCount: Int? = 0,
    @SerialName("platforms")
    var platforms: List<PlatformXX>? = listOf(),
    @SerialName("playtime")
    var playtime: Int? = 0,
    @SerialName("publishers")
    var publishers: List<Publisher>? = listOf(),
    @SerialName("rating")
    var rating: Double? = 0.0,
    @SerialName("rating_top")
    var ratingTop: Int? = 0,
    @SerialName("ratings")
    var ratings: List<Rating>? = listOf(),
    @SerialName("ratings_count")
    var ratingsCount: Int? = 0,
    @SerialName("reactions")
    var reactions: Reactions? = Reactions(),
    @SerialName("reddit_count")
    var redditCount: Int? = 0,
    @SerialName("reddit_description")
    var redditDescription: String? = "",
    @SerialName("reddit_logo")
    var redditLogo: String? = "",
    @SerialName("reddit_name")
    var redditName: String? = "",
    @SerialName("reddit_url")
    var redditUrl: String? = "",
    @SerialName("released")
    var released: String? = "",
    @SerialName("reviews_count")
    var reviewsCount: Int? = 0,
    @SerialName("reviews_text_count")
    var reviewsTextCount: Int? = 0,
    @SerialName("saturated_color")
    var saturatedColor: String? = "",
    @SerialName("screenshots_count")
    var screenshotsCount: Int? = 0,
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
    @SerialName("twitch_count")
    var twitchCount: Int? = 0,
    @SerialName("updated")
    var updated: String? = "",
    @SerialName("user_game")
    var userGame: String? = null,
    @SerialName("website")
    var website: String? = "",
    @SerialName("youtube_count")
    var youtubeCount: Int? = 0
)