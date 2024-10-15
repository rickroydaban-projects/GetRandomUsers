package com.rickaban.android.getrandomusers.feature.random.domain

import com.rickaban.android.getrandomusers.feature.random.data.api.action.GetRandomUserList

object MockResponseData {
    fun randomUserResponse(): GetRandomUserList.Response {
        return GetRandomUserList.Response(
            results = listOf(
                GetRandomUserList.Response.RandomUserRemote(
                    gender = "male",
                    name = GetRandomUserList.Response.RandomUserRemote.Name(
                        title = "Mr",
                        first = "John",
                        last = "Doe"
                    ),
                    location = GetRandomUserList.Response.RandomUserRemote.Location(
                        street = GetRandomUserList.Response.RandomUserRemote.Street(
                            number = 123,
                            name = "Main Street"
                        ),
                        city = "",
                        state = "",
                        country = "",
                        postcode = "",
                        coordinates = GetRandomUserList.Response.RandomUserRemote.Coordinates(
                            latitude = "",
                            longitude = ""
                        ),
                        timezone = GetRandomUserList.Response.RandomUserRemote.Timezone(
                            offset = "",
                            description = ""
                        )
                    ),
                    email = "william.henry.moody@my-own-personal-domain.com",
                    login = GetRandomUserList.Response.RandomUserRemote.Login(
                        uuid = "",
                        username = "",
                        password = "",
                        salt = "",
                        md5 = "",
                        sha1 = "",
                        sha256 = ""
                    ),
                    dob = GetRandomUserList.Response.RandomUserRemote.Dob(
                        date = "1948-03-23T21:14:56.099Z",
                        age = 0
                    ),
                    registered = GetRandomUserList.Response.RandomUserRemote.Registered(
                        date = "",
                        age = 0
                    ),
                    phone = "123-456-7890",
                    cell = "987-654-3210",
                    id = GetRandomUserList.Response.RandomUserRemote.Id(
                        name = "",
                        value = ""
                    ),
                    picture = GetRandomUserList.Response.RandomUserRemote.Picture(
                        large = "",
                        medium = "",
                        thumbnail = ""
                    ),
                    nat = "US",
                )
            ),
            info = GetRandomUserList.Response.Info(
                seed = "seed",
                results = 1,
                page = 1,
                version = "1.0"
            )
        )
    }
}