package de.swimresults.api.user.apis

import de.swimresults.api.user.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.user.models.Error
import de.swimresults.api.user.models.User

interface UsersApi {
    /**
     * GET users
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<User>]
     */
    @GET("users")
    suspend fun getUsers(): Response<kotlin.collections.List<User>>

}
