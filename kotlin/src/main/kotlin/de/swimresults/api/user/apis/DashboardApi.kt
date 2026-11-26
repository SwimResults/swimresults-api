package de.swimresults.api.user.apis

import de.swimresults.api.user.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.user.models.Dashboard
import de.swimresults.api.user.models.Error

interface DashboardApi {
    /**
     * POST dashboard
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 201: Created
     *  - 401: Unauthorized
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param dashboard 
     * @return [Dashboard]
     */
    @POST("dashboard")
    suspend fun addUserDashboard(@Body dashboard: Dashboard): Response<Dashboard>

    /**
     * GET dashboard/default
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param meetingState  (optional)
     * @return [Dashboard]
     */
    @GET("dashboard/default")
    suspend fun getDefaultDashboard(@Query("meeting_state") meetingState: kotlin.String? = null): Response<Dashboard>

    /**
     * GET dashboard
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 200: OK
     *  - 401: Unauthorized
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param meetingState  (optional)
     * @return [Dashboard]
     */
    @GET("dashboard")
    suspend fun getUserDashboard(@Query("meeting_state") meetingState: kotlin.String? = null): Response<Dashboard>

    /**
     * DELETE dashboard/{id}
     * 
     * Public — no authentication required. The handler reads the user from the bearer token, so a token is required in practice.
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 401: Unauthorized
     *  - 404: Not Found
     *
     * @param id 
     * @return [Unit]
     */
    @DELETE("dashboard/{id}")
    suspend fun removeUserDashboard(@Path("id") id: kotlin.String): Response<Unit>

}
