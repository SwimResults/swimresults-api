package de.swimresults.api.start.apis

import de.swimresults.api.start.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.start.models.CurrentNextHeatDto
import de.swimresults.api.start.models.Error
import de.swimresults.api.start.models.EventHeatInfoDto
import de.swimresults.api.start.models.GetHeatsByMeetingAndEvent400Response
import de.swimresults.api.start.models.Heat
import de.swimresults.api.start.models.HeatEstimationDateRequest
import de.swimresults.api.start.models.HeatTimesRequestDto
import de.swimresults.api.start.models.ImportHeatRequestDto
import de.swimresults.api.start.models.MeetingHeatInfoDto
import de.swimresults.api.start.models.MeetingHeatsEventListDto

interface HeatApi {
    /**
     * POST heat
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param heat 
     * @return [Heat]
     */
    @POST("heat")
    suspend fun addHeat(@Body heat: Heat): Response<Heat>

    /**
     * DELETE heat/meet/{meet_id}/event/{event_id}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @return [kotlin.String]
     */
    @DELETE("heat/meet/{meet_id}/event/{event_id}")
    suspend fun deleteHeatsByMeetingAndEvent(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<kotlin.String>

    /**
     * GET heat/meet/{meet_id}/current
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [Heat]
     */
    @GET("heat/meet/{meet_id}/current")
    suspend fun getCurrentHeat(@Path("meet_id") meetId: kotlin.String): Response<Heat>

    /**
     * GET heat/meet/{meet_id}/current_next
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [CurrentNextHeatDto]
     */
    @GET("heat/meet/{meet_id}/current_next")
    suspend fun getCurrentNextHeat(@Path("meet_id") meetId: kotlin.String): Response<CurrentNextHeatDto>

    /**
     * GET heat/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Heat]
     */
    @GET("heat/{id}")
    suspend fun getHeat(@Path("id") id: kotlin.String): Response<Heat>

    /**
     * GET heat/meet/{meet_id}/info
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [MeetingHeatInfoDto]
     */
    @GET("heat/meet/{meet_id}/info")
    suspend fun getHeatInfoByMeeting(@Path("meet_id") meetId: kotlin.String): Response<MeetingHeatInfoDto>

    /**
     * GET heat/meet/{meet_id}/event/{event_id}/info
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @return [EventHeatInfoDto]
     */
    @GET("heat/meet/{meet_id}/event/{event_id}/info")
    suspend fun getHeatInfoByMeetingAndEvent(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<EventHeatInfoDto>

    /**
     * GET heat
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<Heat>]
     */
    @GET("heat")
    suspend fun getHeats(): Response<kotlin.collections.List<Heat>>

    /**
     * GET heat/amount
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.Int]
     */
    @GET("heat/amount")
    suspend fun getHeatsAmount(): Response<kotlin.Int>

    /**
     * GET heat/meet/{meet_id}/amount
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @return [kotlin.Int]
     */
    @GET("heat/meet/{meet_id}/amount")
    suspend fun getHeatsAmountByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.Int>

    /**
     * GET heat/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [kotlin.collections.List<Heat>]
     */
    @GET("heat/meet/{meet_id}")
    suspend fun getHeatsByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<Heat>>

    /**
     * GET heat/meet/{meet_id}/event/{event_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @return [kotlin.collections.List<Heat>]
     */
    @GET("heat/meet/{meet_id}/event/{event_id}")
    suspend fun getHeatsByMeetingAndEvent(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<kotlin.collections.List<Heat>>

    /**
     * GET heat/meet/{meet_id}/event_list
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [MeetingHeatsEventListDto]
     */
    @GET("heat/meet/{meet_id}/event_list")
    suspend fun getHeatsByMeetingForEventList(@Path("meet_id") meetId: kotlin.String): Response<MeetingHeatsEventListDto>

    /**
     * POST heat/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 201: Created
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param importHeatRequestDto 
     * @return [Heat]
     */
    @POST("heat/import")
    suspend fun importHeat(@Body importHeatRequestDto: ImportHeatRequestDto): Response<Heat>

    /**
     * DELETE heat/{id}
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
    @DELETE("heat/{id}")
    suspend fun removeHeat(@Path("id") id: kotlin.String): Response<kotlin.String>

    /**
     * POST heat/meet/{meet_id}/event/{event_id}/heat/{heat}/start
     * 
     * Requires &#x60;admin&#x60;, or &#x60;manager&#x60; with the meeting id in the token&#39;s &#x60;events&#x60; claim; or the service key. The meeting id is read from &#x60;meet_id&#x60;/&#x60;meetid&#x60;/&#x60;meeting&#x60; in path, query or JSON body.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @param heat 
     * @return [Heat]
     */
    @POST("heat/meet/{meet_id}/event/{event_id}/heat/{heat}/start")
    suspend fun triggerHeatStart(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int, @Path("heat") heat: kotlin.Int): Response<Heat>

    /**
     * PUT heat
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param heat 
     * @return [Heat]
     */
    @PUT("heat")
    suspend fun updateHeat(@Body heat: Heat): Response<Heat>

    /**
     * POST heat/{id}/time
     * 
     * Requires &#x60;admin&#x60;, or &#x60;manager&#x60; with the meeting id in the token&#39;s &#x60;events&#x60; claim; or the service key. The meeting id is read from &#x60;meet_id&#x60;/&#x60;meetid&#x60;/&#x60;meeting&#x60; in path, query or JSON body.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param id 
     * @param heatTimesRequestDto 
     * @return [Heat]
     */
    @POST("heat/{id}/time")
    suspend fun updateHeatTime(@Path("id") id: kotlin.String, @Body heatTimesRequestDto: HeatTimesRequestDto): Response<Heat>

    /**
     * POST heat/meet/{meet_id}/events/start_estimation_date
     * 
     * Requires &#x60;admin&#x60;, or &#x60;manager&#x60; with the meeting id in the token&#39;s &#x60;events&#x60; claim; or the service key. The meeting id is read from &#x60;meet_id&#x60;/&#x60;meetid&#x60;/&#x60;meeting&#x60; in path, query or JSON body.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @param heatEstimationDateRequest 
     * @return [kotlin.collections.List<Heat>]
     */
    @POST("heat/meet/{meet_id}/events/start_estimation_date")
    suspend fun updateHeatsStartEstimationDate(@Path("meet_id") meetId: kotlin.String, @Body heatEstimationDateRequest: HeatEstimationDateRequest): Response<kotlin.collections.List<Heat>>

}
