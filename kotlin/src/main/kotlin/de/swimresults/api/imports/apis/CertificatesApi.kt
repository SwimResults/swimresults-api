package de.swimresults.api.imports.apis

import de.swimresults.api.imports.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.imports.models.Error
import de.swimresults.api.imports.models.ImportCertificatesRequestDto

interface CertificatesApi {
    /**
     * POST certificates
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param importCertificatesRequestDto 
     * @return [Unit]
     */
    @POST("certificates")
    suspend fun importCertificates(@Body importCertificatesRequestDto: ImportCertificatesRequestDto): Response<Unit>

}
