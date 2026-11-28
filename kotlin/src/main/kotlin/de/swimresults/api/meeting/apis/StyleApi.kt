package de.swimresults.api.meeting.apis

import de.swimresults.api.meeting.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.meeting.models.Error
import de.swimresults.api.meeting.models.Style

interface StyleApi {
    /**
     * POST style
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param style 
     * @return [Style]
     */
    @POST("style")
    suspend fun addStyle(@Body style: Style): Response<Style>

    /**
     * GET style/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Style]
     */
    @GET("style/{id}")
    suspend fun getStyle(@Path("id") id: kotlin.String): Response<Style>

    /**
     * GET style/name/{name}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param name 
     * @return [Style]
     */
    @GET("style/name/{name}")
    suspend fun getStyleByName(@Path("name") name: kotlin.String): Response<Style>

    /**
     * GET style
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<Style>]
     */
    @GET("style")
    suspend fun getStyles(): Response<kotlin.collections.List<Style>>

    /**
     * DELETE style/{id}
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
    @DELETE("style/{id}")
    suspend fun removeStyle(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * PUT style
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param style 
     * @return [Style]
     */
    @PUT("style")
    suspend fun updateStyle(@Body style: Style): Response<Style>

}
