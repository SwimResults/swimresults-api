package de.swimresults.api.start.apis

import de.swimresults.api.start.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.start.models.Error
import de.swimresults.api.start.models.ImportRankRequestDto
import de.swimresults.api.start.models.Rank

interface RankApi {
    /**
     * POST rank/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 201: Created
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param importRankRequestDto 
     * @return [Rank]
     */
    @POST("rank/import")
    suspend fun importRank(@Body importRankRequestDto: ImportRankRequestDto): Response<Rank>

}
