package de.swimresults.api.user.apis

import de.swimresults.api.user.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.user.models.Error
import de.swimresults.api.user.models.ReportSubjectType
import de.swimresults.api.user.models.UserReport
import de.swimresults.api.user.models.UserReportSubmission

interface ReportApi {
    /**
     * POST report/{id}/acknowledge
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 201: Created
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param id 
     * @return [UserReport]
     */
    @POST("report/{id}/acknowledge")
    suspend fun acknowledgeReport(@Path("id") id: kotlin.String): Response<UserReport>

    /**
     * POST report
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 201: Created
     *  - 500: Internal Server Error
     *
     * @param userReport 
     * @return [UserReport]
     */
    @POST("report")
    suspend fun addReport(@Body userReport: UserReport): Response<UserReport>

    /**
     * POST report/{id}/complete
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 201: Created
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param id 
     * @return [UserReport]
     */
    @POST("report/{id}/complete")
    suspend fun completeReport(@Path("id") id: kotlin.String): Response<UserReport>

    /**
     * GET report
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<UserReport>]
     */
    @GET("report")
    suspend fun getReports(): Response<kotlin.collections.List<UserReport>>

    /**
     * GET report/meet/{meeting}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meeting 
     * @return [kotlin.collections.List<UserReport>]
     */
    @GET("report/meet/{meeting}")
    suspend fun getReportsByMeeting(@Path("meeting") meeting: kotlin.String): Response<kotlin.collections.List<UserReport>>

    /**
     * GET report/subject-types
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *
     * @return [kotlin.collections.List<ReportSubjectType>]
     */
    @GET("report/subject-types")
    suspend fun getSubjectTypes(): Response<kotlin.collections.List<ReportSubjectType>>

    /**
     * DELETE report/{id}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Unit]
     */
    @DELETE("report/{id}")
    suspend fun removeReport(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * POST report/submit
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 201: Created
     *  - 401: Unauthorized
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param userReportSubmission 
     * @return [UserReport]
     */
    @POST("report/submit")
    suspend fun submitReport(@Body userReportSubmission: UserReportSubmission): Response<UserReport>

}
