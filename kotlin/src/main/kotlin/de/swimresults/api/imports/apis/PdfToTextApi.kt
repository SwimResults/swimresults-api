package de.swimresults.api.imports.apis

import de.swimresults.api.imports.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.imports.models.Error
import de.swimresults.api.imports.models.ImportFileRequest

interface PdfToTextApi {
    /**
     * POST pdf_to_text
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param importFileRequest 
     * @return [ImportFileRequest]
     */
    @POST("pdf_to_text")
    suspend fun readPdfToText(@Body importFileRequest: ImportFileRequest): Response<ImportFileRequest>

}
