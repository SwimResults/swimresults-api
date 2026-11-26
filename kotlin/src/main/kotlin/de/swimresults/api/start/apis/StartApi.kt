package de.swimresults.api.start.apis

import de.swimresults.api.start.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.start.models.Error
import de.swimresults.api.start.models.EventStartResultRequestDto
import de.swimresults.api.start.models.GetHeatsByMeetingAndEvent400Response
import de.swimresults.api.start.models.ImportStartRequestDto
import de.swimresults.api.start.models.LivestreamDto
import de.swimresults.api.start.models.LivestreamHeatStateDto
import de.swimresults.api.start.models.Start
import de.swimresults.api.start.models.StartsByYearAndGenderStatsDto

interface StartApi {
    /**
     * POST start
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param start 
     * @return [Start]
     */
    @POST("start")
    suspend fun addStart(@Body start: Start): Response<Start>

    /**
     * DELETE start/meet/{meet_id}/event/{event_id}
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
    @DELETE("start/meet/{meet_id}/event/{event_id}")
    suspend fun deleteStartsByMeetingAndEvent(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<kotlin.String>

    /**
     * GET start/meet/{meet_id}/current
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [kotlin.collections.List<Start>]
     */
    @GET("start/meet/{meet_id}/current")
    suspend fun getCurrentStarts(@Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<Start>>

    /**
     * GET start/meet/{meet_id}/livestream
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [LivestreamDto]
     */
    @GET("start/meet/{meet_id}/livestream")
    suspend fun getLivestreamData(@Path("meet_id") meetId: kotlin.String): Response<LivestreamDto>

    /**
     * GET start/meet/{meet_id}/livestream/state
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [LivestreamHeatStateDto]
     */
    @GET("start/meet/{meet_id}/livestream/state")
    suspend fun getLivestreamHeatState(@Path("meet_id") meetId: kotlin.String): Response<LivestreamHeatStateDto>

    /**
     * GET start/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Start]
     */
    @GET("start/{id}")
    suspend fun getStart(@Path("id") id: kotlin.String): Response<Start>

    /**
     * GET start/meet/{meet_id}/event/{event_id}/heat/{heat_id}/lane/{lane_number}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @param heatId 
     * @param laneNumber 
     * @return [Start]
     */
    @GET("start/meet/{meet_id}/event/{event_id}/heat/{heat_id}/lane/{lane_number}")
    suspend fun getStartByMeetingAndEventAndHeatAndLane(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int, @Path("heat_id") heatId: kotlin.Int, @Path("lane_number") laneNumber: kotlin.Int): Response<Start>

    /**
     * GET start/meet/{meet_id}/stats
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [kotlin.collections.List<StartsByYearAndGenderStatsDto>]
     */
    @GET("start/meet/{meet_id}/stats")
    suspend fun getStartStatsByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<StartsByYearAndGenderStatsDto>>

    /**
     * GET start
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<Start>]
     */
    @GET("start")
    suspend fun getStarts(): Response<kotlin.collections.List<Start>>

    /**
     * GET start/amount
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.Int]
     */
    @GET("start/amount")
    suspend fun getStartsAmount(): Response<kotlin.Int>

    /**
     * GET start/meet/{meet_id}/amount
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
    @GET("start/meet/{meet_id}/amount")
    suspend fun getStartsAmountByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.Int>

    /**
     * GET start/athlete/{ath_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param athId 
     * @return [kotlin.collections.List<Start>]
     */
    @GET("start/athlete/{ath_id}")
    suspend fun getStartsByAthlete(@Path("ath_id") athId: kotlin.String): Response<kotlin.collections.List<Start>>

    /**
     * GET start/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [kotlin.collections.List<Start>]
     */
    @GET("start/meet/{meet_id}")
    suspend fun getStartsByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<Start>>

    /**
     * GET start/meet/{meet_id}/athlete/{ath_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param athId 
     * @return [kotlin.collections.List<Start>]
     */
    @GET("start/meet/{meet_id}/athlete/{ath_id}")
    suspend fun getStartsByMeetingAndAthlete(@Path("meet_id") meetId: kotlin.String, @Path("ath_id") athId: kotlin.String): Response<kotlin.collections.List<Start>>

    /**
     * GET start/meet/{meet_id}/event/{event_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @return [kotlin.collections.List<Start>]
     */
    @GET("start/meet/{meet_id}/event/{event_id}")
    suspend fun getStartsByMeetingAndEvent(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<kotlin.collections.List<Start>>

    /**
     * GET start/meet/{meet_id}/event/{event_id}/heat/{heat_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @param heatId 
     * @return [kotlin.collections.List<Start>]
     */
    @GET("start/meet/{meet_id}/event/{event_id}/heat/{heat_id}")
    suspend fun getStartsByMeetingAndEventAndHeat(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int, @Path("heat_id") heatId: kotlin.Int): Response<kotlin.collections.List<Start>>

    /**
     * GET start/meet/{meet_id}/event/{event_id}/results
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @return [kotlin.collections.List<EventStartResultRequestDto>]
     */
    @GET("start/meet/{meet_id}/event/{event_id}/results")
    suspend fun getStartsByMeetingAndEventAsResult(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<kotlin.collections.List<EventStartResultRequestDto>>

    /**
     * POST start/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 201: Created
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param importStartRequestDto 
     * @return [Start]
     */
    @POST("start/import")
    suspend fun importStart(@Body importStartRequestDto: ImportStartRequestDto): Response<Start>

    /**
     * DELETE start/{id}
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
    @DELETE("start/{id}")
    suspend fun removeStart(@Path("id") id: kotlin.String): Response<kotlin.String>

    /**
     * PUT start
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param start 
     * @return [Start]
     */
    @PUT("start")
    suspend fun updateStart(@Body start: Start): Response<Start>

}
