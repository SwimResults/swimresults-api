package de.swimresults.api.user.apis

import de.swimresults.api.user.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.user.models.Config
import de.swimresults.api.user.models.Error

interface ConfigApi {
    /**
     * POST config
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param config 
     * @return [Config]
     */
    @POST("config")
    suspend fun addConfig(@Body config: Config): Response<Config>

    /**
     * GET config
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<Config>]
     */
    @GET("config")
    suspend fun getConfigs(): Response<kotlin.collections.List<Config>>

    /**
     * POST config/enable/{meeting}/{enable}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meeting 
     * @param enable 
     * @return [Config]
     */
    @POST("config/enable/{meeting}/{enable}")
    suspend fun setEnable(@Path("meeting") meeting: kotlin.String, @Path("enable") enable: kotlin.String): Response<Config>

}
