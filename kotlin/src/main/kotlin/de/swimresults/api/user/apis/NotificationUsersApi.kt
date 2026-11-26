package de.swimresults.api.user.apis

import de.swimresults.api.user.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.user.models.Error
import de.swimresults.api.user.models.NotificationUser

interface NotificationUsersApi {
    /**
     * GET notification_users
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<NotificationUser>]
     */
    @GET("notification_users")
    suspend fun getNotificationUsers(): Response<kotlin.collections.List<NotificationUser>>

}
