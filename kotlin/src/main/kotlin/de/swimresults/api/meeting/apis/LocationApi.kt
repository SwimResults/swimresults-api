package de.swimresults.api.meeting.apis

import de.swimresults.api.meeting.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.meeting.models.Error
import de.swimresults.api.meeting.models.Location

interface LocationApi {
    /**
     * POST location
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param location 
     * @return [Location]
     */
    @POST("location")
    suspend fun addLocation(@Body location: Location): Response<Location>

    /**
     * GET location/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Location]
     */
    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: kotlin.String): Response<Location>

    /**
     * GET location
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<Location>]
     */
    @GET("location")
    suspend fun getLocations(): Response<kotlin.collections.List<Location>>

    /**
     * DELETE location/{id}
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 501: Not Implemented
     *
     * @param id 
     * @return [Unit]
     */
    @DELETE("location/{id}")
    suspend fun removeLocation(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * PUT location
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 501: Not Implemented
     *
     * @return [Unit]
     */
    @PUT("location")
    suspend fun updateLocation(): Response<Unit>

}
