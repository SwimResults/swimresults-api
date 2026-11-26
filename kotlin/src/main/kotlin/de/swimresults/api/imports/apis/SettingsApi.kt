package de.swimresults.api.imports.apis

import de.swimresults.api.imports.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.imports.models.Error
import de.swimresults.api.imports.models.ImportSetting

interface SettingsApi {
    /**
     * POST settings
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param importSetting 
     * @return [ImportSetting]
     */
    @POST("settings")
    suspend fun addImportSetting(@Body importSetting: ImportSetting): Response<ImportSetting>

    /**
     * GET settings/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [ImportSetting]
     */
    @GET("settings/{id}")
    suspend fun getImportSetting(@Path("id") id: kotlin.String): Response<ImportSetting>

    /**
     * GET settings/meet/{meet_id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meetId 
     * @return [ImportSetting]
     */
    @GET("settings/meet/{meet_id}")
    suspend fun getImportSettingByMeetId(@Path("meet_id") meetId: kotlin.String): Response<ImportSetting>

    /**
     * GET settings
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<ImportSetting>]
     */
    @GET("settings")
    suspend fun getImportSettings(): Response<kotlin.collections.List<ImportSetting>>

    /**
     * DELETE settings/{id}
     * 
     * Requires Keycloak realm role &#x60;admin&#x60; (or the service key).
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [kotlin.String]
     */
    @DELETE("settings/{id}")
    suspend fun removeImportSetting(@Path("id") id: kotlin.String): Response<kotlin.String>

}
