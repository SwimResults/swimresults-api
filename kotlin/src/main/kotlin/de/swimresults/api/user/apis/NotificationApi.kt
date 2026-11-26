package de.swimresults.api.user.apis

import de.swimresults.api.user.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.user.models.BroadcastResponseDto
import de.swimresults.api.user.models.Error
import de.swimresults.api.user.models.MeetingNotificationRequestDto
import de.swimresults.api.user.models.NotificationRequestDto
import de.swimresults.api.user.models.NotificationResponseDto
import de.swimresults.api.user.models.NotificationsResponseDto

interface NotificationApi {
    /**
     * POST notification/broadcast/{channel}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 500: Internal Server Error
     *  - 0: response
     *
     * @param channel 
     * @param body 
     * @return [BroadcastResponseDto]
     */
    @POST("notification/broadcast/{channel}")
    suspend fun sendBroadcast(@Path("channel") channel: kotlin.String, @Body body: kotlin.Any): Response<BroadcastResponseDto>

    /**
     * POST notification/broadcast/meeting/{meeting}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 500: Internal Server Error
     *  - 0: response
     *
     * @param meeting 
     * @param body 
     * @return [BroadcastResponseDto]
     */
    @POST("notification/broadcast/meeting/{meeting}")
    suspend fun sendMeetingBroadcast(@Path("meeting") meeting: kotlin.String, @Body body: kotlin.Any): Response<BroadcastResponseDto>

    /**
     * POST notification/{device}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 500: Internal Server Error
     *  - 0: response
     *
     * @param device 
     * @param notificationRequestDto 
     * @return [NotificationResponseDto]
     */
    @POST("notification/{device}")
    suspend fun sendNotification(@Path("device") device: kotlin.String, @Body notificationRequestDto: NotificationRequestDto): Response<NotificationResponseDto>

    /**
     * POST notification/meet/{meeting}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param meeting 
     * @param meetingNotificationRequestDto 
     * @return [NotificationsResponseDto]
     */
    @POST("notification/meet/{meeting}")
    suspend fun sendNotificationForMeeting(@Path("meeting") meeting: kotlin.String, @Body meetingNotificationRequestDto: MeetingNotificationRequestDto): Response<NotificationsResponseDto>

    /**
     * POST notification/meet/{meeting}/athlete/{athlete}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param meeting 
     * @param athlete 
     * @param meetingNotificationRequestDto 
     * @return [NotificationsResponseDto]
     */
    @POST("notification/meet/{meeting}/athlete/{athlete}")
    suspend fun sendNotificationForMeetingAndAthlete(@Path("meeting") meeting: kotlin.String, @Path("athlete") athlete: kotlin.String, @Body meetingNotificationRequestDto: MeetingNotificationRequestDto): Response<NotificationsResponseDto>

    /**
     * POST notification/test/{device}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param device 
     * @return [Unit]
     */
    @POST("notification/test/{device}")
    suspend fun sendTestNotification(@Path("device") device: kotlin.String): Response<Unit>

}
