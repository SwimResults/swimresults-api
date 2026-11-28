package de.swimresults.api.meeting.apis

import de.swimresults.api.meeting.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.meeting.models.Error
import de.swimresults.api.meeting.models.Meeting

interface MeetingApi {
    /**
     * POST meeting
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param meeting 
     * @return [Meeting]
     */
    @POST("meeting")
    suspend fun addMeeting(@Body meeting: Meeting): Response<Meeting>

    /**
     * GET meeting/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Meeting]
     */
    @GET("meeting/{id}")
    suspend fun getMeeting(@Path("id") id: kotlin.String): Response<Meeting>

    /**
     * GET meeting/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [Meeting]
     */
    @GET("meeting/meet/{meet_id}")
    suspend fun getMeetingByMeetId(@Path("meet_id") meetId: kotlin.String): Response<Meeting>

    /**
     * GET meeting/between/{date_start}/{date_end}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 501: Not Implemented
     *
     * @param dateStart 
     * @param dateEnd 
     * @return [Unit]
     */
    @GET("meeting/between/{date_start}/{date_end}")
    suspend fun getMeetingWithDateBetween(@Path("date_start") dateStart: kotlin.String, @Path("date_end") dateEnd: kotlin.String): Response<Unit>

    /**
     * GET meeting
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param search  (optional)
     * @return [kotlin.collections.List<Meeting>]
     */
    @GET("meeting")
    suspend fun getMeetings(@Query("search") search: kotlin.String? = null): Response<kotlin.collections.List<Meeting>>

    /**
     * DELETE meeting/{id}
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
    @DELETE("meeting/{id}")
    suspend fun removeMeeting(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * PUT meeting
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param meeting 
     * @return [Meeting]
     */
    @PUT("meeting")
    suspend fun updateMeeting(@Body meeting: Meeting): Response<Meeting>

}
