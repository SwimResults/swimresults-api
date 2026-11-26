package de.swimresults.api.meeting.apis

import de.swimresults.api.meeting.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.meeting.models.AgeGroup
import de.swimresults.api.meeting.models.Error
import de.swimresults.api.meeting.models.ImportAgeGroupRequestDto

interface AgeGroupApi {
    /**
     * POST age_group
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param ageGroup 
     * @return [AgeGroup]
     */
    @POST("age_group")
    suspend fun addAgeGroup(@Body ageGroup: AgeGroup): Response<AgeGroup>

    /**
     * GET age_group/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [AgeGroup]
     */
    @GET("age_group/{id}")
    suspend fun getAgeGroup(@Path("id") id: kotlin.String): Response<AgeGroup>

    /**
     * GET age_group/meet/{meet_id}/event/{event_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @return [kotlin.collections.List<AgeGroup>]
     */
    @GET("age_group/meet/{meet_id}/event/{event_id}")
    suspend fun getAgeGroupByMeetingAndEvent(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<kotlin.collections.List<AgeGroup>>

    /**
     * GET age_group
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<AgeGroup>]
     */
    @GET("age_group")
    suspend fun getAgeGroups(): Response<kotlin.collections.List<AgeGroup>>

    /**
     * GET age_group/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @return [kotlin.collections.List<AgeGroup>]
     */
    @GET("age_group/meet/{meet_id}")
    suspend fun getAgeGroupsByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<AgeGroup>>

    /**
     * POST age_group/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 201: Created
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param importAgeGroupRequestDto 
     * @return [AgeGroup]
     */
    @POST("age_group/import")
    suspend fun importAgeGroup(@Body importAgeGroupRequestDto: ImportAgeGroupRequestDto): Response<AgeGroup>

    /**
     * DELETE age_group/{id}
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
    @DELETE("age_group/{id}")
    suspend fun removeAgeGroup(@Path("id") id: kotlin.String): Response<kotlin.String>

    /**
     * PUT age_group
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param ageGroup 
     * @return [AgeGroup]
     */
    @PUT("age_group")
    suspend fun updateAgeGroup(@Body ageGroup: AgeGroup): Response<AgeGroup>

}
