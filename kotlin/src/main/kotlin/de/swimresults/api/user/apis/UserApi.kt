package de.swimresults.api.user.apis

import de.swimresults.api.user.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.user.models.Error
import de.swimresults.api.user.models.FollowAthleteRequestDto
import de.swimresults.api.user.models.SetLanguageRequestDto
import de.swimresults.api.user.models.SetMeRequestDto
import de.swimresults.api.user.models.SetThemeRequestDto
import de.swimresults.api.user.models.SubscribeMeetingRequestDto
import de.swimresults.api.user.models.User

interface UserApi {
    /**
     * POST user
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param user 
     * @return [User]
     */
    @POST("user")
    suspend fun addUser(@Body user: User): Response<User>

    /**
     * POST user/athlete
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 200: OK
     *  - 401: Unauthorized
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param followAthleteRequestDto 
     * @return [User]
     */
    @POST("user/athlete")
    suspend fun changeFollowerForUser(@Body followAthleteRequestDto: FollowAthleteRequestDto): Response<User>

    /**
     * POST user/me
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 200: OK
     *  - 401: Unauthorized
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param setMeRequestDto 
     * @return [User]
     */
    @POST("user/me")
    suspend fun changeMe(@Body setMeRequestDto: SetMeRequestDto): Response<User>

    /**
     * GET user
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 200: OK
     *  - 401: Unauthorized
     *  - 404: Not Found
     *
     * @return [User]
     */
    @GET("user")
    suspend fun getUser(): Response<User>

    /**
     * GET user/{id}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [User]
     */
    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: kotlin.String): Response<User>

    /**
     * DELETE user/{id}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [kotlin.String]
     */
    @DELETE("user/{id}")
    suspend fun removeUser(@Path("id") id: kotlin.String): Response<kotlin.String>

    /**
     * POST user/meeting
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 200: OK
     *  - 401: Unauthorized
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param subscribeMeetingRequestDto 
     * @return [User]
     */
    @POST("user/meeting")
    suspend fun updateMeetings(@Body subscribeMeetingRequestDto: SubscribeMeetingRequestDto): Response<User>

    /**
     * PUT user
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param user 
     * @return [User]
     */
    @PUT("user")
    suspend fun updateUser(@Body user: User): Response<User>

    /**
     * POST user/language
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 200: OK
     *  - 401: Unauthorized
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param setLanguageRequestDto 
     * @return [User]
     */
    @POST("user/language")
    suspend fun updateUserLanguage(@Body setLanguageRequestDto: SetLanguageRequestDto): Response<User>

    /**
     * POST user/theme
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 200: OK
     *  - 401: Unauthorized
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param setThemeRequestDto 
     * @return [User]
     */
    @POST("user/theme")
    suspend fun updateUserTheme(@Body setThemeRequestDto: SetThemeRequestDto): Response<User>

}
