package de.swimresults.api.start.apis

import de.swimresults.api.start.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.start.models.Error
import de.swimresults.api.start.models.GetHeatsByMeetingAndEvent400Response
import de.swimresults.api.start.models.Registration

interface RegistrationApi {
    /**
     * POST registration
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param registration 
     * @return [Registration]
     */
    @POST("registration")
    suspend fun addRegistration(@Body registration: Registration): Response<Registration>

    /**
     * GET registration/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Registration]
     */
    @GET("registration/{id}")
    suspend fun getRegistration(@Path("id") id: kotlin.String): Response<Registration>

    /**
     * GET registration/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [kotlin.collections.List<Registration>]
     */
    @GET("registration/meet/{meet_id}")
    suspend fun getRegistrationsByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<Registration>>

    /**
     * GET registration/meet/{meet_id}/me
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param userId  (optional)
     * @return [Registration]
     */
    @GET("registration/meet/{meet_id}/me")
    suspend fun getRegistrationsByMeetingForMe(@Path("meet_id") meetId: kotlin.String, @Query("user_id") userId: kotlin.String? = null): Response<Registration>

    /**
     * DELETE registration/{id}
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
    @DELETE("registration/{id}")
    suspend fun removeRegistration(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * PUT registration
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param registration 
     * @return [Registration]
     */
    @PUT("registration")
    suspend fun updateRegistration(@Body registration: Registration): Response<Registration>

}
