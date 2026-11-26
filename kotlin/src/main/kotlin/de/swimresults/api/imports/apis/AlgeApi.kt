package de.swimresults.api.imports.apis

import de.swimresults.api.imports.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.imports.models.AlgeActionRequest
import de.swimresults.api.imports.models.AlgeLivetimingData500Response

interface AlgeApi {
    /**
     * POST alge
     * 
     * Not registered with a permission: service-core applies the meeting rule if a meeting id is present, otherwise requires &#x60;admin&#x60;.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param algeActionRequest 
     * @return [kotlin.String]
     */
    @POST("alge")
    suspend fun algeLivetimingData(@Body algeActionRequest: AlgeActionRequest): Response<kotlin.String>

    /**
     * POST alge/meet/{meeting}
     * 
     * Requires &#x60;admin&#x60;, or &#x60;manager&#x60; with the meeting id in the token&#39;s &#x60;events&#x60; claim; or the service key. The meeting id is read from &#x60;meet_id&#x60;/&#x60;meetid&#x60;/&#x60;meeting&#x60; in path, query or JSON body.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param meeting 
     * @param algeActionRequest 
     * @return [kotlin.String]
     */
    @POST("alge/meet/{meeting}")
    suspend fun algeLivetimingDataWithAuth(@Path("meeting") meeting: kotlin.String, @Body algeActionRequest: AlgeActionRequest): Response<kotlin.String>

}
