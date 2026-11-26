package de.swimresults.api.athlete.apis

import de.swimresults.api.athlete.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.athlete.models.AddParticipationRequestDto
import de.swimresults.api.athlete.models.Athlete
import de.swimresults.api.athlete.models.AthleteIdList
import de.swimresults.api.athlete.models.Error
import de.swimresults.api.athlete.models.ImportAthleteRequestDto

interface AthleteApi {
    /**
     * POST athlete
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 201: Created
     *  - 500: Internal Server Error
     *
     * @param athlete 
     * @return [Athlete]
     */
    @POST("athlete")
    suspend fun addAthlete(@Body athlete: Athlete): Response<Athlete>

    /**
     * POST athlete/participation
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param addParticipationRequestDto 
     * @return [Athlete]
     */
    @POST("athlete/participation")
    suspend fun addParticipation(@Body addParticipationRequestDto: AddParticipationRequestDto): Response<Athlete>

    /**
     * GET athlete/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Athlete]
     */
    @GET("athlete/{id}")
    suspend fun getAthlete(@Path("id") id: kotlin.String): Response<Athlete>

    /**
     * GET athlete/alias_year
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param alias  (optional)
     * @param year  (optional)
     * @return [Athlete]
     */
    @GET("athlete/alias_year")
    suspend fun getAthleteByAliasAndYear(@Query("alias") alias: kotlin.String? = null, @Query("year") year: kotlin.Int? = null): Response<Athlete>

    /**
     * GET athlete/name_year
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param name  (optional)
     * @param year  (optional)
     * @return [Athlete]
     */
    @GET("athlete/name_year")
    suspend fun getAthleteByNameAndYear(@Query("name") name: kotlin.String? = null, @Query("year") year: kotlin.Int? = null): Response<Athlete>

    /**
     * HEAD athlete/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Athlete]
     */
    @HEAD("athlete/{id}")
    suspend fun getAthleteHead(@Path("id") id: kotlin.String): Response<Athlete>

    /**
     * GET athlete
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param limit  (optional)
     * @param offset  (optional)
     * @param query  (optional)
     * @return [kotlin.collections.List<Athlete>]
     */
    @GET("athlete")
    suspend fun getAthletes(@Query("limit") limit: kotlin.Int? = null, @Query("offset") offset: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Athlete>>

    /**
     * GET athlete/amount
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.Long]
     */
    @GET("athlete/amount")
    suspend fun getAthletesAmount(): Response<kotlin.Long>

    /**
     * GET athlete/meet/{meet_id}/amount
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @return [kotlin.Long]
     */
    @GET("athlete/meet/{meet_id}/amount")
    suspend fun getAthletesAmountByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.Long>

    /**
     * GET athlete/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @param limit  (optional)
     * @param offset  (optional)
     * @param query  (optional)
     * @return [kotlin.collections.List<Athlete>]
     */
    @GET("athlete/meet/{meet_id}")
    suspend fun getAthletesByMeeting(@Path("meet_id") meetId: kotlin.String, @Query("limit") limit: kotlin.Int? = null, @Query("offset") offset: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Athlete>>

    /**
     * POST athlete/meet/{meet_id}/id_list
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @param athleteIdList 
     * @param limit  (optional)
     * @param offset  (optional)
     * @param query  (optional)
     * @return [kotlin.collections.List<Athlete>]
     */
    @POST("athlete/meet/{meet_id}/id_list")
    suspend fun getAthletesByMeetingAndIdList(@Path("meet_id") meetId: kotlin.String, @Body athleteIdList: AthleteIdList, @Query("limit") limit: kotlin.Int? = null, @Query("offset") offset: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Athlete>>

    /**
     * GET athlete/team/{team_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param teamId 
     * @param limit  (optional)
     * @param offset  (optional)
     * @param query  (optional)
     * @return [kotlin.collections.List<Athlete>]
     */
    @GET("athlete/team/{team_id}")
    suspend fun getAthletesByTeam(@Path("team_id") teamId: kotlin.String, @Query("limit") limit: kotlin.Int? = null, @Query("offset") offset: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Athlete>>

    /**
     * GET athlete/team/{team_id}/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param teamId 
     * @param meetId 
     * @param limit  (optional)
     * @param offset  (optional)
     * @param query  (optional)
     * @return [kotlin.collections.List<Athlete>]
     */
    @GET("athlete/team/{team_id}/meet/{meet_id}")
    suspend fun getAthletesByTeamAndMeeting(@Path("team_id") teamId: kotlin.String, @Path("meet_id") meetId: kotlin.String, @Query("limit") limit: kotlin.Int? = null, @Query("offset") offset: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Athlete>>

    /**
     * HEAD athlete
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param limit  (optional)
     * @param offset  (optional)
     * @param query  (optional)
     * @return [kotlin.collections.List<Athlete>]
     */
    @HEAD("athlete")
    suspend fun getAthletesHead(@Query("limit") limit: kotlin.Int? = null, @Query("offset") offset: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Athlete>>

    /**
     * POST athlete/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 201: Created
     *  - 500: Internal Server Error
     *
     * @param importAthleteRequestDto 
     * @return [Athlete]
     */
    @POST("athlete/import")
    suspend fun importAthlete(@Body importAthleteRequestDto: ImportAthleteRequestDto): Response<Athlete>

    /**
     * DELETE athlete/{id}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param id 
     * @return [kotlin.String]
     */
    @DELETE("athlete/{id}")
    suspend fun removeAthlete(@Path("id") id: kotlin.String): Response<kotlin.String>

    /**
     * PUT athlete
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param athlete 
     * @return [Athlete]
     */
    @PUT("athlete")
    suspend fun updateAthlete(@Body athlete: Athlete): Response<Athlete>

}
