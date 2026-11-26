package de.swimresults.api.meeting.apis

import de.swimresults.api.meeting.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName


interface ActuatorApi {
    /**
     * GET actuator
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *
     * @return [kotlin.String]
     */
    @GET("actuator")
    suspend fun actuator(): Response<kotlin.String>

}
