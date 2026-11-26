package de.swimresults.api.user.apis

import de.swimresults.api.user.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.user.models.Error
import de.swimresults.api.user.models.Widget

interface WidgetApi {
    /**
     * POST widget
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 201: Created
     *  - 500: Internal Server Error
     *
     * @param widget 
     * @return [Widget]
     */
    @POST("widget")
    suspend fun addWidget(@Body widget: Widget): Response<Widget>

    /**
     * GET widget
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<Widget>]
     */
    @GET("widget")
    suspend fun getWidgets(): Response<kotlin.collections.List<Widget>>

    /**
     * DELETE widget/{id}
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
    @DELETE("widget/{id}")
    suspend fun removeWidget(@Path("id") id: kotlin.String): Response<Unit>

}
