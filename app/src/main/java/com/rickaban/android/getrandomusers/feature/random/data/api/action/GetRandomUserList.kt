package com.rickaban.android.getrandomusers.feature.random.data.api.action
       
import com.google.gson.annotations.SerializedName
import com.rickaban.android.getrandomusers.feature.random.data.db.local.RandomUserLocal
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class GetRandomUserList {
    data class Request(
        @SerializedName("q") val query: String
    )

    data class Response(
        @SerializedName("results") val results: List<RandomUserRemote>,
        @SerializedName("info") val info: Info
    ) {
        data class RandomUserRemote(
            @SerializedName("gender") val gender: String,
            @SerializedName("name") val name: Name,
            @SerializedName("location") val location: Location,
            @SerializedName("email") val email: String,
            @SerializedName("login") val login: Login,
            @SerializedName("dob") val dob: Dob,
            @SerializedName("registered") val registered: Registered,
            @SerializedName("phone") val phone: String,
            @SerializedName("cell") val cell: String,
            @SerializedName("id") val id: Id,
            @SerializedName("picture") val picture: Picture,
            @SerializedName("nat") val nat: String
        ) {
            fun localize() = RandomUserLocal(
                randomUserID = login.username,
                firstName = name.first,
                lastName = name.last,
                address = "${location.street.number} ${location.street.name}, ${location.city}, ${location.state}, ${location.country}, ${location.postcode}",
                profilePictureUrl = picture.large,
                username = login.username,
                email = email,
                phone = phone,
                cell = cell,
                nationality = nat,
                birthday = ZonedDateTime.parse(dob.date).format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
            )


            data class Name(
                @SerializedName("title") val title: String,
                @SerializedName("first") val first: String,
                @SerializedName("last") val last: String
            )

            data class Location(
                @SerializedName("street") val street: Street,
                @SerializedName("city") val city: String,
                @SerializedName("state") val state: String,
                @SerializedName("country") val country: String,
                @SerializedName("postcode") val postcode: String,
                @SerializedName("coordinates") val coordinates: Coordinates,
                @SerializedName("timezone") val timezone: Timezone
            )

            data class Street(
                @SerializedName("number") val number: Int,
                @SerializedName("name") val name: String
            )

            data class Coordinates(
                @SerializedName("latitude") val latitude: String,
                @SerializedName("longitude") val longitude: String
            )

            data class Timezone(
                @SerializedName("offset") val offset: String,
                @SerializedName("description") val description: String
            )

            data class Login(
                @SerializedName("uuid") val uuid: String,
                @SerializedName("username") val username: String,
                @SerializedName("password") val password: String,
                @SerializedName("salt") val salt: String,
                @SerializedName("md5") val md5: String,
                @SerializedName("sha1") val sha1: String,
                @SerializedName("sha256") val sha256: String
            )

            data class Dob(
                @SerializedName("date") val date: String,
                @SerializedName("age") val age: Int
            )

            data class Registered(
                @SerializedName("date") val date: String,
                @SerializedName("age") val age: Int
            )

            data class Id(
                @SerializedName("name") val name: String,
                @SerializedName("value") val value: String
            )

            data class Picture(
                @SerializedName("large") val large: String,
                @SerializedName("medium") val medium: String,
                @SerializedName("thumbnail") val thumbnail: String
            )
        }

        data class Info(
            @SerializedName("seed") val seed: String,
            @SerializedName("results") val results: Int,
            @SerializedName("page") val page: Int,
            @SerializedName("version") val version: String
        )

    }
}
       