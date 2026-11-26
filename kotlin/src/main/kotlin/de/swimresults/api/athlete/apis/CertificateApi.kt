package de.swimresults.api.athlete.apis

import de.swimresults.api.athlete.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.athlete.models.Certificate
import de.swimresults.api.athlete.models.Error
import de.swimresults.api.athlete.models.ImportCertificateRequestDto

interface CertificateApi {
    /**
     * POST certificate
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 201: Created
     *  - 500: Internal Server Error
     *
     * @param certificate 
     * @return [Certificate]
     */
    @POST("certificate")
    suspend fun addCertificate(@Body certificate: Certificate): Response<Certificate>

    /**
     * GET certificate/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Certificate]
     */
    @GET("certificate/{id}")
    suspend fun getCertificate(@Path("id") id: kotlin.String): Response<Certificate>

    /**
     * GET certificate
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<Certificate>]
     */
    @GET("certificate")
    suspend fun getCertificates(): Response<kotlin.collections.List<Certificate>>

    /**
     * GET certificate/amount
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.Int]
     */
    @GET("certificate/amount")
    suspend fun getCertificatesAmount(): Response<kotlin.Int>

    /**
     * GET certificate/meet/{meet_id}/amount
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meetId 
     * @return [kotlin.Int]
     */
    @GET("certificate/meet/{meet_id}/amount")
    suspend fun getCertificatesAmountByMeeting(@Path("meet_id") meetId: kotlin.String): Response<kotlin.Int>

    /**
     * GET certificate/athlete/{athlete_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param athleteId 
     * @return [kotlin.collections.List<Certificate>]
     */
    @GET("certificate/athlete/{athlete_id}")
    suspend fun getCertificatesByAthlete(@Path("athlete_id") athleteId: kotlin.String): Response<kotlin.collections.List<Certificate>>

    /**
     * GET certificate/athlete/{athlete_id}/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param athleteId 
     * @param meetId 
     * @return [kotlin.collections.List<Certificate>]
     */
    @GET("certificate/athlete/{athlete_id}/meet/{meet_id}")
    suspend fun getCertificatesByAthleteAndMeeting(@Path("athlete_id") athleteId: kotlin.String, @Path("meet_id") meetId: kotlin.String): Response<kotlin.collections.List<Certificate>>

    /**
     * POST certificate/import
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 201: Created
     *  - 500: Internal Server Error
     *
     * @param importCertificateRequestDto 
     * @return [Certificate]
     */
    @POST("certificate/import")
    suspend fun importCertificate(@Body importCertificateRequestDto: ImportCertificateRequestDto): Response<Certificate>

    /**
     * DELETE certificate/{id}
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
    @DELETE("certificate/{id}")
    suspend fun removeCertificate(@Path("id") id: kotlin.String): Response<kotlin.String>

    /**
     * PUT certificate
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param certificate 
     * @return [Certificate]
     */
    @PUT("certificate")
    suspend fun updateCertificate(@Body certificate: Certificate): Response<Certificate>

}
