package de.swimresults.api.meeting.apis

import de.swimresults.api.meeting.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.meeting.models.Error
import de.swimresults.api.meeting.models.StorageFile

interface FileApi {
    /**
     * POST file
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param storageFile 
     * @return [StorageFile]
     */
    @POST("file")
    suspend fun addFile(@Body storageFile: StorageFile): Response<StorageFile>

    /**
     * GET file/{id}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [StorageFile]
     */
    @GET("file/{id}")
    suspend fun getFile(@Path("id") id: kotlin.String): Response<StorageFile>

    /**
     * GET file/meeting/{meeting}/name/{name}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param meeting 
     * @param name 
     * @return [StorageFile]
     */
    @GET("file/meeting/{meeting}/name/{name}")
    suspend fun getFileByNameAndMeeting(@Path("meeting") meeting: kotlin.String, @Path("name") name: kotlin.String): Response<StorageFile>

    /**
     * GET file/meeting/list/{meeting}
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 400: Bad Request
     *  - 500: Internal Server Error
     *
     * @param meeting 
     * @return [kotlin.collections.List<StorageFile>]
     */
    @GET("file/meeting/list/{meeting}")
    suspend fun getFileListByMeeting(@Path("meeting") meeting: kotlin.String): Response<kotlin.collections.List<StorageFile>>

    /**
     * GET file
     * 
     * Public — no authentication required.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.collections.List<StorageFile>]
     */
    @GET("file")
    suspend fun getFiles(): Response<kotlin.collections.List<StorageFile>>

    /**
     * POST file/increment
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param body 
     * @return [Unit]
     */
    @POST("file/increment")
    suspend fun incrementDownloads(@Body body: kotlin.String): Response<Unit>

    /**
     * DELETE file/{id}
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 204: No Content
     *  - 400: Bad Request
     *  - 404: Not Found
     *
     * @param id 
     * @return [Unit]
     */
    @DELETE("file/{id}")
    suspend fun removeFile(@Path("id") id: kotlin.String): Response<Unit>

    /**
     * PUT file
     * 
     * Requires realm role &#x60;manager&#x60; or &#x60;admin&#x60; (any meeting), or the service key.
     * Responses:
     *  - 200: OK
     *  - 404: Not Found
     *  - 500: Internal Server Error
     *
     * @param storageFile 
     * @return [StorageFile]
     */
    @PUT("file")
    suspend fun updateFile(@Body storageFile: StorageFile): Response<StorageFile>

}
