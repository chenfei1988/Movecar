package xinyiyun.chenfei.com.movecar.net

import io.reactivex.Observable
import retrofit2.http.*
import xinyiyun.chenfei.com.baselibrary.model.Count
import xinyiyun.chenfei.com.baselibrary.model.LoginInfo
import xinyiyun.chenfei.com.baselibrary.model.LoginRequestEnvelope
import xinyiyun.chenfei.com.movecar.category.ClientInfo
import xinyiyun.chenfei.com.movecar.category.TeamInfo
import xinyiyun.chenfei.com.movecar.category.VehicleInfo
import xinyiyun.chenfei.com.movecar.requestmodel.*

/**
 * Created by YangQiang on 2017/11/4.
 */
interface ApiInterface {

    companion object {
        const val CACHE_CONTROL = "Cache-Control: max-age=1"
        const val CONTENT_TYPE = "Content-Type: text/xml; charset=utf-8"
        const val SOAPACTION = "SOAPAction: http://kingdonsoft.com/"
        const val PAGE_SIZE = 40
    }

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/validateUser",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun validateUser(@Body requestEnvelope: LoginRequestEnvelope):  Observable<List<LoginInfo>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetClientInfoCount",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetClientInfoCount(@Body requestEnvelope: ClientInfoCountRequestEnvelope):  Observable<List<Count>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetClientInfo",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetClientInfo(@Body requestEnvelope: ClientInfoRequestEnvelope):  Observable<List<ClientInfo>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTeamNoInfo",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetTeamNoInfo(@Body requestEnvelope: TeamNoInfoRequestEnvelope):  Observable<List<TeamInfo>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetvehicleInfoCount",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetVehicleInfoCount(@Body requestEnvelope: VehicleInfoCountRequestEnvelope):  Observable<List<Count>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetvehicleInfo",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetVehicleInfo(@Body requestEnvelope: VehicleInfoRequestEnvelope):  Observable<List<VehicleInfo>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTpVeDate")
    @POST("WebService.asmx")
    fun GetTpVeDate(@Body requestEnvelope: VehicleDateRequestEnvelope):  Observable<List<VehicleDate>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTonJiInfoCount",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetTonJiInfoCount(@Body requestEnvelope: GetTonJiInfoCountRequestEnvelope):  Observable<List<Count>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTonJiInfo",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetTonJiInfo(@Body requestEnvelope: GetTonJiInfoRequestEnvelope):  Observable<List<GetTonJiInfo>>


    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTonJiTeamNoInfoCount",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetTonJiTeamNoInfoCount(@Body requestEnvelope: GetTonJiTeamNoInfoCountRequestEnvelope):  Observable<List<Count>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTonJiTeamNoInfo",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetTonJiTeamNoInfo(@Body requestEnvelope: GetTonJiTeamNoInfoRequestEnvelope):  Observable<List<TonJiTeamNoInfo>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTonJiAlarmInfoCount",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetTonJiAlarmInfoCount (@Body requestEnvelope: GetTonJiAlarmInfoCountRequestEnvelope):  Observable<List<Count>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTonJiAlarmInfo",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetTonJiAlarmInfo(@Body requestEnvelope: GetTonJiAlarmInfoRequestEnvelope):  Observable<List<TonJiAlarmInfo>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTonJiAlarmTypeInfoCount",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetTonJiAlarmTypeInfoCount(@Body requestEnvelope: GetTonJiAlarmTypeInfoCountRequestEnvelope):  Observable<List<Count>>

    @Headers("Content-Type: text/xml; charset=utf-8", "SOAPAction: http://kingdonsoft.com/GetTonJiAlarmTypeInfo",CACHE_CONTROL)
    @POST("WebService.asmx")
    fun GetTonJiAlarmTypeInfo(@Body requestEnvelope: GetTonJiAlarmTypeInfoRequestEnvelope):  Observable<List<TonJiAlarmTypeInfo>>

}

