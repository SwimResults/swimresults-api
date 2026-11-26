package de.swimresults.api.meeting.apis

import de.swimresults.api.meeting.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.meeting.models.Error
import de.swimresults.api.meeting.models.MeetingSeries

interface MeetingSeriesApi {
    /**
     * POST meeting_series
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param meetingSeries 
     * @return [MeetingSeries]
     */
    @POST("meeting_series")
    suspend fun addMeetingSeries(@Body meetingSeries: MeetingSeries): Response<MeetingSeries>

    /**
     * GET meeting_series
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<MeetingSeries>]
     */
    @GET("meeting_series")
    suspend fun getMeetingSeries(): Response<kotlin.collections.List<MeetingSeries>>

    /**
     * GET meeting_series/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [MeetingSeries]
     */
    @GET("meeting_series/{id}")
    suspend fun getMeetingSeriesById(@Path("id") id: kotlin.String): Response<MeetingSeries>

    /**
     * DELETE meeting_series/{id}
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
    @DELETE("meeting_series/{id}")
    suspend fun removeMeetingSeries(@Path("id") id: kotlin.String): Response<kotlin.String>

    /**
     * PUT meeting_series
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param meetingSeries 
     * @return [MeetingSeries]
     */
    @PUT("meeting_series")
    suspend fun updateMeetingSeries(@Body meetingSeries: MeetingSeries): Response<MeetingSeries>

}
