package de.swimresults.api.imports.apis

import de.swimresults.api.imports.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import de.swimresults.api.imports.models.AlgeLivetimingData500Response
import de.swimresults.api.imports.models.EasyWkAction
import de.swimresults.api.imports.models.EasyWkActionV3Request

interface EasywkApi {
    /**
     * POST easywk/livework.php
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param pwd  (optional)
     * @param action  (optional)
     * @param keepsum  (optional)
     * @param firstlane  (optional)
     * @param lanecount  (optional)
     * @param vername  (optional)
     * @param event  (optional)
     * @param heat  (optional)
     * @param maxheat  (optional)
     * @param name  (optional)
     * @param lane  (optional)
     * @param meter  (optional)
     * @param time  (optional)
     * @param finished  (optional)
     * @param content  (optional)
     * @param srw0  (optional)
     * @param yob0  (optional)
     * @param club0  (optional)
     * @param srw1  (optional)
     * @param yob1  (optional)
     * @param club1  (optional)
     * @param srw2  (optional)
     * @param yob2  (optional)
     * @param club2  (optional)
     * @param srw3  (optional)
     * @param yob3  (optional)
     * @param club3  (optional)
     * @param srw4  (optional)
     * @param yob4  (optional)
     * @param club4  (optional)
     * @param srw5  (optional)
     * @param yob5  (optional)
     * @param club5  (optional)
     * @param srw6  (optional)
     * @param yob6  (optional)
     * @param club6  (optional)
     * @param srw7  (optional)
     * @param yob7  (optional)
     * @param club7  (optional)
     * @param srw8  (optional)
     * @param yob8  (optional)
     * @param club8  (optional)
     * @param srw9  (optional)
     * @param yob9  (optional)
     * @param club9  (optional)
     * @param srw10  (optional)
     * @param yob10  (optional)
     * @param club10  (optional)
     * @return [kotlin.String]
     */
    @POST("easywk/livework.php")
    suspend fun easyWkLivetiming(@Query("pwd") pwd: kotlin.String? = null, @Query("action") action: kotlin.String? = null, @Query("keepsum") keepsum: kotlin.String? = null, @Query("firstlane") firstlane: kotlin.Int? = null, @Query("lanecount") lanecount: kotlin.Int? = null, @Query("vername") vername: kotlin.String? = null, @Query("event") event: kotlin.Int? = null, @Query("heat") heat: kotlin.Int? = null, @Query("maxheat") maxheat: kotlin.Int? = null, @Query("name") name: kotlin.String? = null, @Query("lane") lane: kotlin.Int? = null, @Query("meter") meter: kotlin.String? = null, @Query("time") time: kotlin.Int? = null, @Query("finished") finished: kotlin.String? = null, @Query("content") content: kotlin.String? = null, @Query("srw0") srw0: kotlin.String? = null, @Query("yob0") yob0: kotlin.String? = null, @Query("club0") club0: kotlin.String? = null, @Query("srw1") srw1: kotlin.String? = null, @Query("yob1") yob1: kotlin.String? = null, @Query("club1") club1: kotlin.String? = null, @Query("srw2") srw2: kotlin.String? = null, @Query("yob2") yob2: kotlin.String? = null, @Query("club2") club2: kotlin.String? = null, @Query("srw3") srw3: kotlin.String? = null, @Query("yob3") yob3: kotlin.String? = null, @Query("club3") club3: kotlin.String? = null, @Query("srw4") srw4: kotlin.String? = null, @Query("yob4") yob4: kotlin.String? = null, @Query("club4") club4: kotlin.String? = null, @Query("srw5") srw5: kotlin.String? = null, @Query("yob5") yob5: kotlin.String? = null, @Query("club5") club5: kotlin.String? = null, @Query("srw6") srw6: kotlin.String? = null, @Query("yob6") yob6: kotlin.String? = null, @Query("club6") club6: kotlin.String? = null, @Query("srw7") srw7: kotlin.String? = null, @Query("yob7") yob7: kotlin.String? = null, @Query("club7") club7: kotlin.String? = null, @Query("srw8") srw8: kotlin.String? = null, @Query("yob8") yob8: kotlin.String? = null, @Query("club8") club8: kotlin.String? = null, @Query("srw9") srw9: kotlin.String? = null, @Query("yob9") yob9: kotlin.String? = null, @Query("club9") club9: kotlin.String? = null, @Query("srw10") srw10: kotlin.String? = null, @Query("yob10") yob10: kotlin.String? = null, @Query("club10") club10: kotlin.String? = null): Response<kotlin.String>

    /**
     * GET easywk/any/livework.php
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param pwd  (optional)
     * @param action  (optional)
     * @param keepsum  (optional)
     * @param firstlane  (optional)
     * @param lanecount  (optional)
     * @param vername  (optional)
     * @param event  (optional)
     * @param heat  (optional)
     * @param maxheat  (optional)
     * @param name  (optional)
     * @param lane  (optional)
     * @param meter  (optional)
     * @param time  (optional)
     * @param finished  (optional)
     * @param content  (optional)
     * @param srw0  (optional)
     * @param yob0  (optional)
     * @param club0  (optional)
     * @param srw1  (optional)
     * @param yob1  (optional)
     * @param club1  (optional)
     * @param srw2  (optional)
     * @param yob2  (optional)
     * @param club2  (optional)
     * @param srw3  (optional)
     * @param yob3  (optional)
     * @param club3  (optional)
     * @param srw4  (optional)
     * @param yob4  (optional)
     * @param club4  (optional)
     * @param srw5  (optional)
     * @param yob5  (optional)
     * @param club5  (optional)
     * @param srw6  (optional)
     * @param yob6  (optional)
     * @param club6  (optional)
     * @param srw7  (optional)
     * @param yob7  (optional)
     * @param club7  (optional)
     * @param srw8  (optional)
     * @param yob8  (optional)
     * @param club8  (optional)
     * @param srw9  (optional)
     * @param yob9  (optional)
     * @param club9  (optional)
     * @param srw10  (optional)
     * @param yob10  (optional)
     * @param club10  (optional)
     * @return [kotlin.String]
     */
    @GET("easywk/any/livework.php")
    suspend fun easyWkLivetimingGet(@Query("pwd") pwd: kotlin.String? = null, @Query("action") action: kotlin.String? = null, @Query("keepsum") keepsum: kotlin.String? = null, @Query("firstlane") firstlane: kotlin.Int? = null, @Query("lanecount") lanecount: kotlin.Int? = null, @Query("vername") vername: kotlin.String? = null, @Query("event") event: kotlin.Int? = null, @Query("heat") heat: kotlin.Int? = null, @Query("maxheat") maxheat: kotlin.Int? = null, @Query("name") name: kotlin.String? = null, @Query("lane") lane: kotlin.Int? = null, @Query("meter") meter: kotlin.String? = null, @Query("time") time: kotlin.Int? = null, @Query("finished") finished: kotlin.String? = null, @Query("content") content: kotlin.String? = null, @Query("srw0") srw0: kotlin.String? = null, @Query("yob0") yob0: kotlin.String? = null, @Query("club0") club0: kotlin.String? = null, @Query("srw1") srw1: kotlin.String? = null, @Query("yob1") yob1: kotlin.String? = null, @Query("club1") club1: kotlin.String? = null, @Query("srw2") srw2: kotlin.String? = null, @Query("yob2") yob2: kotlin.String? = null, @Query("club2") club2: kotlin.String? = null, @Query("srw3") srw3: kotlin.String? = null, @Query("yob3") yob3: kotlin.String? = null, @Query("club3") club3: kotlin.String? = null, @Query("srw4") srw4: kotlin.String? = null, @Query("yob4") yob4: kotlin.String? = null, @Query("club4") club4: kotlin.String? = null, @Query("srw5") srw5: kotlin.String? = null, @Query("yob5") yob5: kotlin.String? = null, @Query("club5") club5: kotlin.String? = null, @Query("srw6") srw6: kotlin.String? = null, @Query("yob6") yob6: kotlin.String? = null, @Query("club6") club6: kotlin.String? = null, @Query("srw7") srw7: kotlin.String? = null, @Query("yob7") yob7: kotlin.String? = null, @Query("club7") club7: kotlin.String? = null, @Query("srw8") srw8: kotlin.String? = null, @Query("yob8") yob8: kotlin.String? = null, @Query("club8") club8: kotlin.String? = null, @Query("srw9") srw9: kotlin.String? = null, @Query("yob9") yob9: kotlin.String? = null, @Query("club9") club9: kotlin.String? = null, @Query("srw10") srw10: kotlin.String? = null, @Query("yob10") yob10: kotlin.String? = null, @Query("club10") club10: kotlin.String? = null): Response<kotlin.String>

    /**
     * DELETE easywk/any/livework.php
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param pwd  (optional)
     * @param action  (optional)
     * @param keepsum  (optional)
     * @param firstlane  (optional)
     * @param lanecount  (optional)
     * @param vername  (optional)
     * @param event  (optional)
     * @param heat  (optional)
     * @param maxheat  (optional)
     * @param name  (optional)
     * @param lane  (optional)
     * @param meter  (optional)
     * @param time  (optional)
     * @param finished  (optional)
     * @param content  (optional)
     * @param srw0  (optional)
     * @param yob0  (optional)
     * @param club0  (optional)
     * @param srw1  (optional)
     * @param yob1  (optional)
     * @param club1  (optional)
     * @param srw2  (optional)
     * @param yob2  (optional)
     * @param club2  (optional)
     * @param srw3  (optional)
     * @param yob3  (optional)
     * @param club3  (optional)
     * @param srw4  (optional)
     * @param yob4  (optional)
     * @param club4  (optional)
     * @param srw5  (optional)
     * @param yob5  (optional)
     * @param club5  (optional)
     * @param srw6  (optional)
     * @param yob6  (optional)
     * @param club6  (optional)
     * @param srw7  (optional)
     * @param yob7  (optional)
     * @param club7  (optional)
     * @param srw8  (optional)
     * @param yob8  (optional)
     * @param club8  (optional)
     * @param srw9  (optional)
     * @param yob9  (optional)
     * @param club9  (optional)
     * @param srw10  (optional)
     * @param yob10  (optional)
     * @param club10  (optional)
     * @return [kotlin.String]
     */
    @DELETE("easywk/any/livework.php")
    suspend fun easyWkLivetimingGetDelete(@Query("pwd") pwd: kotlin.String? = null, @Query("action") action: kotlin.String? = null, @Query("keepsum") keepsum: kotlin.String? = null, @Query("firstlane") firstlane: kotlin.Int? = null, @Query("lanecount") lanecount: kotlin.Int? = null, @Query("vername") vername: kotlin.String? = null, @Query("event") event: kotlin.Int? = null, @Query("heat") heat: kotlin.Int? = null, @Query("maxheat") maxheat: kotlin.Int? = null, @Query("name") name: kotlin.String? = null, @Query("lane") lane: kotlin.Int? = null, @Query("meter") meter: kotlin.String? = null, @Query("time") time: kotlin.Int? = null, @Query("finished") finished: kotlin.String? = null, @Query("content") content: kotlin.String? = null, @Query("srw0") srw0: kotlin.String? = null, @Query("yob0") yob0: kotlin.String? = null, @Query("club0") club0: kotlin.String? = null, @Query("srw1") srw1: kotlin.String? = null, @Query("yob1") yob1: kotlin.String? = null, @Query("club1") club1: kotlin.String? = null, @Query("srw2") srw2: kotlin.String? = null, @Query("yob2") yob2: kotlin.String? = null, @Query("club2") club2: kotlin.String? = null, @Query("srw3") srw3: kotlin.String? = null, @Query("yob3") yob3: kotlin.String? = null, @Query("club3") club3: kotlin.String? = null, @Query("srw4") srw4: kotlin.String? = null, @Query("yob4") yob4: kotlin.String? = null, @Query("club4") club4: kotlin.String? = null, @Query("srw5") srw5: kotlin.String? = null, @Query("yob5") yob5: kotlin.String? = null, @Query("club5") club5: kotlin.String? = null, @Query("srw6") srw6: kotlin.String? = null, @Query("yob6") yob6: kotlin.String? = null, @Query("club6") club6: kotlin.String? = null, @Query("srw7") srw7: kotlin.String? = null, @Query("yob7") yob7: kotlin.String? = null, @Query("club7") club7: kotlin.String? = null, @Query("srw8") srw8: kotlin.String? = null, @Query("yob8") yob8: kotlin.String? = null, @Query("club8") club8: kotlin.String? = null, @Query("srw9") srw9: kotlin.String? = null, @Query("yob9") yob9: kotlin.String? = null, @Query("club9") club9: kotlin.String? = null, @Query("srw10") srw10: kotlin.String? = null, @Query("yob10") yob10: kotlin.String? = null, @Query("club10") club10: kotlin.String? = null): Response<kotlin.String>

    /**
     * GET easywk/get/livework.php
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param pwd  (optional)
     * @param action  (optional)
     * @param keepsum  (optional)
     * @param firstlane  (optional)
     * @param lanecount  (optional)
     * @param vername  (optional)
     * @param event  (optional)
     * @param heat  (optional)
     * @param maxheat  (optional)
     * @param name  (optional)
     * @param lane  (optional)
     * @param meter  (optional)
     * @param time  (optional)
     * @param finished  (optional)
     * @param content  (optional)
     * @param srw0  (optional)
     * @param yob0  (optional)
     * @param club0  (optional)
     * @param srw1  (optional)
     * @param yob1  (optional)
     * @param club1  (optional)
     * @param srw2  (optional)
     * @param yob2  (optional)
     * @param club2  (optional)
     * @param srw3  (optional)
     * @param yob3  (optional)
     * @param club3  (optional)
     * @param srw4  (optional)
     * @param yob4  (optional)
     * @param club4  (optional)
     * @param srw5  (optional)
     * @param yob5  (optional)
     * @param club5  (optional)
     * @param srw6  (optional)
     * @param yob6  (optional)
     * @param club6  (optional)
     * @param srw7  (optional)
     * @param yob7  (optional)
     * @param club7  (optional)
     * @param srw8  (optional)
     * @param yob8  (optional)
     * @param club8  (optional)
     * @param srw9  (optional)
     * @param yob9  (optional)
     * @param club9  (optional)
     * @param srw10  (optional)
     * @param yob10  (optional)
     * @param club10  (optional)
     * @return [kotlin.String]
     */
    @GET("easywk/get/livework.php")
    suspend fun easyWkLivetimingGetGet(@Query("pwd") pwd: kotlin.String? = null, @Query("action") action: kotlin.String? = null, @Query("keepsum") keepsum: kotlin.String? = null, @Query("firstlane") firstlane: kotlin.Int? = null, @Query("lanecount") lanecount: kotlin.Int? = null, @Query("vername") vername: kotlin.String? = null, @Query("event") event: kotlin.Int? = null, @Query("heat") heat: kotlin.Int? = null, @Query("maxheat") maxheat: kotlin.Int? = null, @Query("name") name: kotlin.String? = null, @Query("lane") lane: kotlin.Int? = null, @Query("meter") meter: kotlin.String? = null, @Query("time") time: kotlin.Int? = null, @Query("finished") finished: kotlin.String? = null, @Query("content") content: kotlin.String? = null, @Query("srw0") srw0: kotlin.String? = null, @Query("yob0") yob0: kotlin.String? = null, @Query("club0") club0: kotlin.String? = null, @Query("srw1") srw1: kotlin.String? = null, @Query("yob1") yob1: kotlin.String? = null, @Query("club1") club1: kotlin.String? = null, @Query("srw2") srw2: kotlin.String? = null, @Query("yob2") yob2: kotlin.String? = null, @Query("club2") club2: kotlin.String? = null, @Query("srw3") srw3: kotlin.String? = null, @Query("yob3") yob3: kotlin.String? = null, @Query("club3") club3: kotlin.String? = null, @Query("srw4") srw4: kotlin.String? = null, @Query("yob4") yob4: kotlin.String? = null, @Query("club4") club4: kotlin.String? = null, @Query("srw5") srw5: kotlin.String? = null, @Query("yob5") yob5: kotlin.String? = null, @Query("club5") club5: kotlin.String? = null, @Query("srw6") srw6: kotlin.String? = null, @Query("yob6") yob6: kotlin.String? = null, @Query("club6") club6: kotlin.String? = null, @Query("srw7") srw7: kotlin.String? = null, @Query("yob7") yob7: kotlin.String? = null, @Query("club7") club7: kotlin.String? = null, @Query("srw8") srw8: kotlin.String? = null, @Query("yob8") yob8: kotlin.String? = null, @Query("club8") club8: kotlin.String? = null, @Query("srw9") srw9: kotlin.String? = null, @Query("yob9") yob9: kotlin.String? = null, @Query("club9") club9: kotlin.String? = null, @Query("srw10") srw10: kotlin.String? = null, @Query("yob10") yob10: kotlin.String? = null, @Query("club10") club10: kotlin.String? = null): Response<kotlin.String>

    /**
     * PATCH easywk/any/livework.php
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param pwd  (optional)
     * @param action  (optional)
     * @param keepsum  (optional)
     * @param firstlane  (optional)
     * @param lanecount  (optional)
     * @param vername  (optional)
     * @param event  (optional)
     * @param heat  (optional)
     * @param maxheat  (optional)
     * @param name  (optional)
     * @param lane  (optional)
     * @param meter  (optional)
     * @param time  (optional)
     * @param finished  (optional)
     * @param content  (optional)
     * @param srw0  (optional)
     * @param yob0  (optional)
     * @param club0  (optional)
     * @param srw1  (optional)
     * @param yob1  (optional)
     * @param club1  (optional)
     * @param srw2  (optional)
     * @param yob2  (optional)
     * @param club2  (optional)
     * @param srw3  (optional)
     * @param yob3  (optional)
     * @param club3  (optional)
     * @param srw4  (optional)
     * @param yob4  (optional)
     * @param club4  (optional)
     * @param srw5  (optional)
     * @param yob5  (optional)
     * @param club5  (optional)
     * @param srw6  (optional)
     * @param yob6  (optional)
     * @param club6  (optional)
     * @param srw7  (optional)
     * @param yob7  (optional)
     * @param club7  (optional)
     * @param srw8  (optional)
     * @param yob8  (optional)
     * @param club8  (optional)
     * @param srw9  (optional)
     * @param yob9  (optional)
     * @param club9  (optional)
     * @param srw10  (optional)
     * @param yob10  (optional)
     * @param club10  (optional)
     * @return [kotlin.String]
     */
    @PATCH("easywk/any/livework.php")
    suspend fun easyWkLivetimingGetPatch(@Query("pwd") pwd: kotlin.String? = null, @Query("action") action: kotlin.String? = null, @Query("keepsum") keepsum: kotlin.String? = null, @Query("firstlane") firstlane: kotlin.Int? = null, @Query("lanecount") lanecount: kotlin.Int? = null, @Query("vername") vername: kotlin.String? = null, @Query("event") event: kotlin.Int? = null, @Query("heat") heat: kotlin.Int? = null, @Query("maxheat") maxheat: kotlin.Int? = null, @Query("name") name: kotlin.String? = null, @Query("lane") lane: kotlin.Int? = null, @Query("meter") meter: kotlin.String? = null, @Query("time") time: kotlin.Int? = null, @Query("finished") finished: kotlin.String? = null, @Query("content") content: kotlin.String? = null, @Query("srw0") srw0: kotlin.String? = null, @Query("yob0") yob0: kotlin.String? = null, @Query("club0") club0: kotlin.String? = null, @Query("srw1") srw1: kotlin.String? = null, @Query("yob1") yob1: kotlin.String? = null, @Query("club1") club1: kotlin.String? = null, @Query("srw2") srw2: kotlin.String? = null, @Query("yob2") yob2: kotlin.String? = null, @Query("club2") club2: kotlin.String? = null, @Query("srw3") srw3: kotlin.String? = null, @Query("yob3") yob3: kotlin.String? = null, @Query("club3") club3: kotlin.String? = null, @Query("srw4") srw4: kotlin.String? = null, @Query("yob4") yob4: kotlin.String? = null, @Query("club4") club4: kotlin.String? = null, @Query("srw5") srw5: kotlin.String? = null, @Query("yob5") yob5: kotlin.String? = null, @Query("club5") club5: kotlin.String? = null, @Query("srw6") srw6: kotlin.String? = null, @Query("yob6") yob6: kotlin.String? = null, @Query("club6") club6: kotlin.String? = null, @Query("srw7") srw7: kotlin.String? = null, @Query("yob7") yob7: kotlin.String? = null, @Query("club7") club7: kotlin.String? = null, @Query("srw8") srw8: kotlin.String? = null, @Query("yob8") yob8: kotlin.String? = null, @Query("club8") club8: kotlin.String? = null, @Query("srw9") srw9: kotlin.String? = null, @Query("yob9") yob9: kotlin.String? = null, @Query("club9") club9: kotlin.String? = null, @Query("srw10") srw10: kotlin.String? = null, @Query("yob10") yob10: kotlin.String? = null, @Query("club10") club10: kotlin.String? = null): Response<kotlin.String>

    /**
     * POST easywk/any/livework.php
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param pwd  (optional)
     * @param action  (optional)
     * @param keepsum  (optional)
     * @param firstlane  (optional)
     * @param lanecount  (optional)
     * @param vername  (optional)
     * @param event  (optional)
     * @param heat  (optional)
     * @param maxheat  (optional)
     * @param name  (optional)
     * @param lane  (optional)
     * @param meter  (optional)
     * @param time  (optional)
     * @param finished  (optional)
     * @param content  (optional)
     * @param srw0  (optional)
     * @param yob0  (optional)
     * @param club0  (optional)
     * @param srw1  (optional)
     * @param yob1  (optional)
     * @param club1  (optional)
     * @param srw2  (optional)
     * @param yob2  (optional)
     * @param club2  (optional)
     * @param srw3  (optional)
     * @param yob3  (optional)
     * @param club3  (optional)
     * @param srw4  (optional)
     * @param yob4  (optional)
     * @param club4  (optional)
     * @param srw5  (optional)
     * @param yob5  (optional)
     * @param club5  (optional)
     * @param srw6  (optional)
     * @param yob6  (optional)
     * @param club6  (optional)
     * @param srw7  (optional)
     * @param yob7  (optional)
     * @param club7  (optional)
     * @param srw8  (optional)
     * @param yob8  (optional)
     * @param club8  (optional)
     * @param srw9  (optional)
     * @param yob9  (optional)
     * @param club9  (optional)
     * @param srw10  (optional)
     * @param yob10  (optional)
     * @param club10  (optional)
     * @return [kotlin.String]
     */
    @POST("easywk/any/livework.php")
    suspend fun easyWkLivetimingGetPost(@Query("pwd") pwd: kotlin.String? = null, @Query("action") action: kotlin.String? = null, @Query("keepsum") keepsum: kotlin.String? = null, @Query("firstlane") firstlane: kotlin.Int? = null, @Query("lanecount") lanecount: kotlin.Int? = null, @Query("vername") vername: kotlin.String? = null, @Query("event") event: kotlin.Int? = null, @Query("heat") heat: kotlin.Int? = null, @Query("maxheat") maxheat: kotlin.Int? = null, @Query("name") name: kotlin.String? = null, @Query("lane") lane: kotlin.Int? = null, @Query("meter") meter: kotlin.String? = null, @Query("time") time: kotlin.Int? = null, @Query("finished") finished: kotlin.String? = null, @Query("content") content: kotlin.String? = null, @Query("srw0") srw0: kotlin.String? = null, @Query("yob0") yob0: kotlin.String? = null, @Query("club0") club0: kotlin.String? = null, @Query("srw1") srw1: kotlin.String? = null, @Query("yob1") yob1: kotlin.String? = null, @Query("club1") club1: kotlin.String? = null, @Query("srw2") srw2: kotlin.String? = null, @Query("yob2") yob2: kotlin.String? = null, @Query("club2") club2: kotlin.String? = null, @Query("srw3") srw3: kotlin.String? = null, @Query("yob3") yob3: kotlin.String? = null, @Query("club3") club3: kotlin.String? = null, @Query("srw4") srw4: kotlin.String? = null, @Query("yob4") yob4: kotlin.String? = null, @Query("club4") club4: kotlin.String? = null, @Query("srw5") srw5: kotlin.String? = null, @Query("yob5") yob5: kotlin.String? = null, @Query("club5") club5: kotlin.String? = null, @Query("srw6") srw6: kotlin.String? = null, @Query("yob6") yob6: kotlin.String? = null, @Query("club6") club6: kotlin.String? = null, @Query("srw7") srw7: kotlin.String? = null, @Query("yob7") yob7: kotlin.String? = null, @Query("club7") club7: kotlin.String? = null, @Query("srw8") srw8: kotlin.String? = null, @Query("yob8") yob8: kotlin.String? = null, @Query("club8") club8: kotlin.String? = null, @Query("srw9") srw9: kotlin.String? = null, @Query("yob9") yob9: kotlin.String? = null, @Query("club9") club9: kotlin.String? = null, @Query("srw10") srw10: kotlin.String? = null, @Query("yob10") yob10: kotlin.String? = null, @Query("club10") club10: kotlin.String? = null): Response<kotlin.String>

    /**
     * PUT easywk/any/livework.php
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param pwd  (optional)
     * @param action  (optional)
     * @param keepsum  (optional)
     * @param firstlane  (optional)
     * @param lanecount  (optional)
     * @param vername  (optional)
     * @param event  (optional)
     * @param heat  (optional)
     * @param maxheat  (optional)
     * @param name  (optional)
     * @param lane  (optional)
     * @param meter  (optional)
     * @param time  (optional)
     * @param finished  (optional)
     * @param content  (optional)
     * @param srw0  (optional)
     * @param yob0  (optional)
     * @param club0  (optional)
     * @param srw1  (optional)
     * @param yob1  (optional)
     * @param club1  (optional)
     * @param srw2  (optional)
     * @param yob2  (optional)
     * @param club2  (optional)
     * @param srw3  (optional)
     * @param yob3  (optional)
     * @param club3  (optional)
     * @param srw4  (optional)
     * @param yob4  (optional)
     * @param club4  (optional)
     * @param srw5  (optional)
     * @param yob5  (optional)
     * @param club5  (optional)
     * @param srw6  (optional)
     * @param yob6  (optional)
     * @param club6  (optional)
     * @param srw7  (optional)
     * @param yob7  (optional)
     * @param club7  (optional)
     * @param srw8  (optional)
     * @param yob8  (optional)
     * @param club8  (optional)
     * @param srw9  (optional)
     * @param yob9  (optional)
     * @param club9  (optional)
     * @param srw10  (optional)
     * @param yob10  (optional)
     * @param club10  (optional)
     * @return [kotlin.String]
     */
    @PUT("easywk/any/livework.php")
    suspend fun easyWkLivetimingGetPut(@Query("pwd") pwd: kotlin.String? = null, @Query("action") action: kotlin.String? = null, @Query("keepsum") keepsum: kotlin.String? = null, @Query("firstlane") firstlane: kotlin.Int? = null, @Query("lanecount") lanecount: kotlin.Int? = null, @Query("vername") vername: kotlin.String? = null, @Query("event") event: kotlin.Int? = null, @Query("heat") heat: kotlin.Int? = null, @Query("maxheat") maxheat: kotlin.Int? = null, @Query("name") name: kotlin.String? = null, @Query("lane") lane: kotlin.Int? = null, @Query("meter") meter: kotlin.String? = null, @Query("time") time: kotlin.Int? = null, @Query("finished") finished: kotlin.String? = null, @Query("content") content: kotlin.String? = null, @Query("srw0") srw0: kotlin.String? = null, @Query("yob0") yob0: kotlin.String? = null, @Query("club0") club0: kotlin.String? = null, @Query("srw1") srw1: kotlin.String? = null, @Query("yob1") yob1: kotlin.String? = null, @Query("club1") club1: kotlin.String? = null, @Query("srw2") srw2: kotlin.String? = null, @Query("yob2") yob2: kotlin.String? = null, @Query("club2") club2: kotlin.String? = null, @Query("srw3") srw3: kotlin.String? = null, @Query("yob3") yob3: kotlin.String? = null, @Query("club3") club3: kotlin.String? = null, @Query("srw4") srw4: kotlin.String? = null, @Query("yob4") yob4: kotlin.String? = null, @Query("club4") club4: kotlin.String? = null, @Query("srw5") srw5: kotlin.String? = null, @Query("yob5") yob5: kotlin.String? = null, @Query("club5") club5: kotlin.String? = null, @Query("srw6") srw6: kotlin.String? = null, @Query("yob6") yob6: kotlin.String? = null, @Query("club6") club6: kotlin.String? = null, @Query("srw7") srw7: kotlin.String? = null, @Query("yob7") yob7: kotlin.String? = null, @Query("club7") club7: kotlin.String? = null, @Query("srw8") srw8: kotlin.String? = null, @Query("yob8") yob8: kotlin.String? = null, @Query("club8") club8: kotlin.String? = null, @Query("srw9") srw9: kotlin.String? = null, @Query("yob9") yob9: kotlin.String? = null, @Query("club9") club9: kotlin.String? = null, @Query("srw10") srw10: kotlin.String? = null, @Query("yob10") yob10: kotlin.String? = null, @Query("club10") club10: kotlin.String? = null): Response<kotlin.String>

    /**
     * GET easywk/v2/livework.php
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.String]
     */
    @GET("easywk/v2/livework.php")
    suspend fun easyWkLivetimingV2(): Response<kotlin.String>

    /**
     * POST easywk/v2/livework.php
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param easyWkAction 
     * @return [kotlin.String]
     */
    @POST("easywk/v2/livework.php")
    suspend fun easyWkLivetimingV2Post(@Body easyWkAction: kotlin.collections.List<EasyWkAction>): Response<kotlin.String>

    /**
     * GET easywk/v3
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @return [kotlin.String]
     */
    @GET("easywk/v3")
    suspend fun easyWkLivetimingV3(): Response<kotlin.String>

    /**
     * POST easywk/v3
     * 
     * Excluded from the auth middleware entirely.
     * Responses:
     *  - 200: OK
     *  - 500: Internal Server Error
     *
     * @param easyWkActionV3Request 
     * @return [kotlin.String]
     */
    @POST("easywk/v3")
    suspend fun easyWkLivetimingV3Post(@Body easyWkActionV3Request: EasyWkActionV3Request): Response<kotlin.String>

}
