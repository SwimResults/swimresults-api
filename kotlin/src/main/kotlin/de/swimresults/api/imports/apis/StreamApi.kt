package de.swimresults.api.imports.apis

import de.swimresults.api.imports.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName


interface StreamApi {
    /**
     * GET stream/{sessionId}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *
     * @param sessionId 
     * @return [kotlin.String]
     */
    @GET("stream/{sessionId}")
    suspend fun streamProgress(@Path("sessionId") sessionId: kotlin.String): Response<kotlin.String>

}
