package de.swimresults.api.start.apis

import de.swimresults.api.start.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.start.models.Error
import de.swimresults.api.start.models.ImportResultRequestDto
import de.swimresults.api.start.models.Result

interface ResultApi {
    /**
     * POST result/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 201: Created
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param importResultRequestDto 
     * @return [Result]
     */
    @POST("result/import")
    suspend fun importResult(@Body importResultRequestDto: ImportResultRequestDto): Response<Result>

}
