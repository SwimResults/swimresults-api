package de.swimresults.api.athlete.apis

import de.swimresults.api.athlete.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.athlete.models.Error
import de.swimresults.api.athlete.models.ImportTeamRequestDto
import de.swimresults.api.athlete.models.Team

interface TeamApi {
    /**
     * POST team
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 201: Created
     *  - 500: Internal Server Error
     *
     * @param team 
     * @return [Team]
     */
    @POST("team")
    suspend fun addTeam(@Body team: Team): Response<Team>

    /**
     * GET team/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Team]
     */
    @GET("team/{id}")
    suspend fun getTeam(@Path("id") id: kotlin.String): Response<Team>

    /**
     * GET team/alias
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param alias  (optional)
     * @return [Team]
     */
    @GET("team/alias")
    suspend fun getTeamByAlias(@Query("alias") alias: kotlin.String? = null): Response<Team>

    /**
     * GET team/name
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param name  (optional)
     * @return [Team]
     */
    @GET("team/name")
    suspend fun getTeamByName(@Query("name") name: kotlin.String? = null): Response<Team>

    /**
     * HEAD team/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Team]
     */
    @HEAD("team/{id}")
    suspend fun getTeamHead(@Path("id") id: kotlin.String): Response<Team>

    /**
     * GET team
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param limit  (optional)
     * @param offset  (optional)
     * @param query  (optional)
     * @return [kotlin.collections.List<Team>]
     */
    @GET("team")
    suspend fun getTeams(@Query("limit") limit: kotlin.Int? = null, @Query("offset") offset: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Team>>

    /**
     * GET team/amount
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.Long]
     */
    @GET("team/amount")
    suspend fun getTeamsAmount(): Response<kotlin.Long>

    /**
     * GET team/meet/{meet_id}/amount
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
    @GET("team/meet/{meet_id}/amount")
    suspend fun getTeamsAmountByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.Long>

    /**
     * GET team/meet/{meet_id}
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
     * @return [kotlin.collections.List<Team>]
     */
    @GET("team/meet/{meet_id}")
    suspend fun getTeamsByMeeting(@Path("meet_id") meetId: kotlin.String, @Query("limit") limit: kotlin.Int? = null, @Query("offset") offset: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Team>>

    /**
     * HEAD team
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param limit  (optional)
     * @param offset  (optional)
     * @param query  (optional)
     * @return [kotlin.collections.List<Team>]
     */
    @HEAD("team")
    suspend fun getTeamsHead(@Query("limit") limit: kotlin.Int? = null, @Query("offset") offset: kotlin.Int? = null, @Query("query") query: kotlin.String? = null): Response<kotlin.collections.List<Team>>

    /**
     * POST team/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 201: Created
     *  - 500: Internal Server Error
     *
     * @param importTeamRequestDto 
     * @return [Team]
     */
    @POST("team/import")
    suspend fun importTeam(@Body importTeamRequestDto: ImportTeamRequestDto): Response<Team>

}
