package de.swimresults.api.start.apis

import de.swimresults.api.start.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.start.models.Disqualification
import de.swimresults.api.start.models.Error
import de.swimresults.api.start.models.ImportDisqualificationRequestDto

interface DisqualificationApi {
    /**
     * POST disqualification/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param importDisqualificationRequestDto 
     * @return [Disqualification]
     */
    @POST("disqualification/import")
    suspend fun importDisqualification(@Body importDisqualificationRequestDto: ImportDisqualificationRequestDto): Response<Disqualification>

}
