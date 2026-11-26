package de.swimresults.api.meeting.apis

import de.swimresults.api.meeting.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.meeting.models.Error
import de.swimresults.api.meeting.models.Event
import de.swimresults.api.meeting.models.EventCertificationRequestDto
import de.swimresults.api.meeting.models.EventLivetimingDto
import de.swimresults.api.meeting.models.ImportEventRequestDto
import de.swimresults.api.meeting.models.MeetingPart

interface EventApi {
    /**
     * POST event
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param event 
     * @return [Event]
     */
    @POST("event")
    suspend fun addEvent(@Body event: Event): Response<Event>

    /**
     * GET event/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Event]
     */
    @GET("event/{id}")
    suspend fun getEvent(@Path("id") id: kotlin.String): Response<Event>

    /**
     * GET event/meet/{meet_id}/event/{event_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @return [Event]
     */
    @GET("event/meet/{meet_id}/event/{event_id}")
    suspend fun getEventByMeetingAndNumber(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<Event>

    /**
     * GET event/meet/{meet_id}/event/{event_id}/livetiming
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @return [EventLivetimingDto]
     */
    @GET("event/meet/{meet_id}/event/{event_id}/livetiming")
    suspend fun getEventByMeetingAndNumberForLivetiming(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<EventLivetimingDto>

    /**
     * GET event
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<Event>]
     */
    @GET("event")
    suspend fun getEvents(): Response<kotlin.collections.List<Event>>

    /**
     * GET event/meet/{meet_id}/parts
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @return [kotlin.collections.List<MeetingPart>]
     */
    @GET("event/meet/{meet_id}/parts")
    suspend fun getEventsAsPartsByMeetId(@Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<MeetingPart>>

    /**
     * GET event/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @return [kotlin.collections.List<Event>]
     */
    @GET("event/meet/{meet_id}")
    suspend fun getEventsByMeetId(@Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<Event>>

    /**
     * POST event/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 201: Created
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param importEventRequestDto 
     * @return [Event]
     */
    @POST("event/import")
    suspend fun importEvent(@Body importEventRequestDto: ImportEventRequestDto): Response<Event>

    /**
     * DELETE event/{id}
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [kotlin.String]
     */
    @DELETE("event/{id}")
    suspend fun removeEvent(@Path("id") id: kotlin.String): Response<kotlin.String>

    /**
     * PUT event
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param event 
     * @return [Event]
     */
    @PUT("event")
    suspend fun updateEvent(@Body event: Event): Response<Event>

    /**
     * POST event/meet/{meet_id}/event/{event_id}/certification
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @param eventId 
     * @param eventCertificationRequestDto 
     * @return [Event]
     */
    @POST("event/meet/{meet_id}/event/{event_id}/certification")
    suspend fun updateEventCertification(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int, @Body eventCertificationRequestDto: EventCertificationRequestDto): Response<Event>

}
