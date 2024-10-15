package com.rickaban.android.getrandomusers.feature.random.domain.use_case
       
import com.rickaban.android.getrandomusers.R
import retrofit2.HttpException
import com.rickaban.android.getrandomusers.app.domain.ResultError
import com.rickaban.android.getrandomusers.app.domain.Result
import com.rickaban.android.getrandomusers.app.domain.apiError
import com.rickaban.android.getrandomusers.feature.random.data.api.RandomApi
import com.rickaban.android.getrandomusers.feature.random.data.api.action.GetRandomUserList
import com.rickaban.android.getrandomusers.feature.random.data.db.RandomDao
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser

object GetRandomUserList {
    suspend operator fun invoke(
        results: Int,
        randomApi: RandomApi,
        randomDao: RandomDao
    ): Result<List<RandomUser>, ResultError> {
        return try {
            val localizedRandomUserList = randomApi.getRandomUserList(
                results
            ).results.map { it.localize() }

            val randomUserList = localizedRandomUserList.map {
                it.model(
                    it.nationality.getFullNationality()
                )
            }

            randomDao.upsertRandomUserList(localizedRandomUserList)
            Result.Success(randomUserList)
        } catch (e: HttpException) {
            Result.Failure(e.code().apiError())
        } catch (e: Exception) {
            Result.Failure(ResultError.Generic)
        }
    }                          
}

fun String.getFullNationality(): Int {
    return when (this.uppercase()) {
        "AU" -> R.string.nationality_au
        "BR" -> R.string.nationality_br
        "CA" -> R.string.nationality_ca
        "CH" -> R.string.nationality_ch
        "DE" -> R.string.nationality_de
        "DK" -> R.string.nationality_dk
        "ES" -> R.string.nationality_es
        "FI" -> R.string.nationality_fi
        "FR" -> R.string.nationality_fr
        "GB" -> R.string.nationality_gb
        "IE" -> R.string.nationality_ie
        "IR" -> R.string.nationality_ir
        "NL" -> R.string.nationality_nl
        "NZ" -> R.string.nationality_nz
        "TR" -> R.string.nationality_tr
        "US" -> R.string.nationality_us
        else -> R.string.unknown_nationality
    }
}
