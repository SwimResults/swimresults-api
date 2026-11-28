package de.swimresults.api.meeting.apis

import de.swimresults.api.meeting.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.meeting.models.Error
import de.swimresults.api.meeting.models.Incident
import de.swimresults.api.meeting.models.IncidentDateRequest

interface IncidentApi {
    /**
     * POST incident
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param incident 
     * @return [Incident]
     */
    @POST("incident")
    suspend fun addIncident(@Body incident: Incident): Response<Incident>

    /**
     * GET incident/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Incident]
     */
    @GET("incident/{id}")
    suspend fun getIncident(@Path("id") id: kotlin.String): Response<Incident>

    /**
     * GET incident/meet/{meeting}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meeting 
     * @return [kotlin.collections.List<Incident>]
     */
    @GET("incident/meet/{meeting}")
    suspend fun getIncidentByMeeting(@Path("meeting") meeting: kotlin.String): Response<kotlin.collections.List<Incident>>

    /**
     * DELETE incident/{id}
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Unit]
     */
    @DELETE("incident/{id}")
    suspend fun removeIncident(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * PUT incident
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param incident 
     * @return [Incident]
     */
    @PUT("incident")
    suspend fun updateIncident(@Body incident: Incident): Response<Incident>

    /**
     * POST incident/meet/{meet_id}/change_date
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @param incidentDateRequest 
     * @return [kotlin.collections.List<Incident>]
     */
    @POST("incident/meet/{meet_id}/change_date")
    suspend fun updateIncidentDatesByMeeting(@Path("meet_id") meetId: kotlin.String, @Body incidentDateRequest: IncidentDateRequest): Response<kotlin.collections.List<Incident>>

}
