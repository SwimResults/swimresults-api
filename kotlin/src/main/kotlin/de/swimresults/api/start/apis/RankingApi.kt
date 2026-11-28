package de.swimresults.api.start.apis

import de.swimresults.api.start.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.start.models.Error
import de.swimresults.api.start.models.ImportRankingRequestDto
import de.swimresults.api.start.models.Ranking

interface RankingApi {
    /**
     * POST ranking
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param ranking 
     * @return [Ranking]
     */
    @POST("ranking")
    suspend fun addRanking(@Body ranking: Ranking): Response<Ranking>

    /**
     * GET ranking/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Ranking]
     */
    @GET("ranking/{id}")
    suspend fun getRanking(@Path("id") id: kotlin.String): Response<Ranking>

    /**
     * GET ranking/meet/{meet_id}/event/{event_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @param eventId 
     * @return [kotlin.collections.List<Ranking>]
     */
    @GET("ranking/meet/{meet_id}/event/{event_id}")
    suspend fun getRankingByMeetingAndEvent(@Path("meet_id") meetId: kotlin.String, @Path("event_id") eventId: kotlin.Int): Response<kotlin.collections.List<Ranking>>

    /**
     * GET ranking
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<Ranking>]
     */
    @GET("ranking")
    suspend fun getRankings(): Response<kotlin.collections.List<Ranking>>

    /**
     * GET ranking/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @return [kotlin.collections.List<Ranking>]
     */
    @GET("ranking/meet/{meet_id}")
    suspend fun getRankingsByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<Ranking>>

    /**
     * POST ranking/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 201: Created
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param importRankingRequestDto 
     * @return [Ranking]
     */
    @POST("ranking/import")
    suspend fun importRanking(@Body importRankingRequestDto: ImportRankingRequestDto): Response<Ranking>

    /**
     * DELETE ranking/{id}
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
    @DELETE("ranking/{id}")
    suspend fun removeRanking(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * PUT ranking
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param ranking 
     * @return [Ranking]
     */
    @PUT("ranking")
    suspend fun updateRanking(@Body ranking: Ranking): Response<Ranking>

}
