package de.swimresults.api.user.apis

import de.swimresults.api.user.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.user.models.Error
import de.swimresults.api.user.models.NotificationUser
import de.swimresults.api.user.models.RegisterNotificationUserRequestDto

interface NotificationUserApi {
    /**
     * POST notification_user
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param notificationUser 
     * @return [NotificationUser]
     */
    @POST("notification_user")
    suspend fun addNotificationUser(@Body notificationUser: NotificationUser): Response<NotificationUser>

    /**
     * GET notification_user
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 200: OK
     *  - 401: Unauthorized
     *  - 404: Not Found
     *
     * @return [NotificationUser]
     */
    @GET("notification_user")
    suspend fun getNotificationUser(): Response<NotificationUser>

    /**
     * GET notification_user/{id}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [NotificationUser]
     */
    @GET("notification_user/{id}")
    suspend fun getNotificationUserById(@Path("id") id: kotlin.String): Response<NotificationUser>

    /**
     * GET notification_user/public/token/{token}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *
     * @param token 
     * @return [NotificationUser]
     */
    @GET("notification_user/public/token/{token}")
    suspend fun getNotificationUserByToken(@Path("token") token: kotlin.String): Response<NotificationUser>

    /**
     * POST notification_user/register/user
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 200: OK
     *  - 401: Unauthorized
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param registerNotificationUserRequestDto 
     * @return [NotificationUser]
     */
    @POST("notification_user/register/user")
    suspend fun registerNotificationUser(@Body registerNotificationUserRequestDto: RegisterNotificationUserRequestDto): Response<NotificationUser>

    /**
     * POST notification_user/public/register
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param registerNotificationUserRequestDto 
     * @return [NotificationUser]
     */
    @POST("notification_user/public/register")
    suspend fun registerNotificationUserWithoutToken(@Body registerNotificationUserRequestDto: RegisterNotificationUserRequestDto): Response<NotificationUser>

    /**
     * POST notification_user/register
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param registerNotificationUserRequestDto 
     * @return [NotificationUser]
     */
    @POST("notification_user/register")
    suspend fun registerNotificationUserWithoutTokenPost(@Body registerNotificationUserRequestDto: RegisterNotificationUserRequestDto): Response<NotificationUser>

    /**
     * DELETE notification_user/{id}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Unit]
     */
    @DELETE("notification_user/{id}")
    suspend fun removeNotificationUser(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * PUT notification_user
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param notificationUser 
     * @return [NotificationUser]
     */
    @PUT("notification_user")
    suspend fun updateNotificationUser(@Body notificationUser: NotificationUser): Response<NotificationUser>

}
