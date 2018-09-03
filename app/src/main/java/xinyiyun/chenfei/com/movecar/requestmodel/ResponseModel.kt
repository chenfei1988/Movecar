package xinyiyun.chenfei.com.movecar.requestmodel

import com.google.gson.annotations.SerializedName
import xinyiyun.chenfei.com.baselibrary.common.Entity
import java.io.Serializable

/**
 * Created by Chenfei on 2018/1/12.
 */
@Entity
data class LoginInfo(
        @SerializedName("userid") val userid: Int=0, //1472
        @SerializedName("Name") val name: String="", //测试孙
        @SerializedName("Popedom") val popedom: Int=0, //2
        @SerializedName("Sex") val sex: String="",
        @SerializedName("Diploma") val diploma: String="",
        @SerializedName("Politics") val politics: String="",
        @SerializedName("Birthday") val birthday: String="", //null
        @SerializedName("Tel") val tel: String="",
        @SerializedName("EmailAddress") val emailAddress: String="",
        @SerializedName("ZhiWu") val zhiWu: String="",
        @SerializedName("PassWord") val passWord: String="", //123456
        @SerializedName("khqc") val khqc: String="",
        @SerializedName("UserType") val userType: Int=0, //1
        @SerializedName("Company") val company: String=""
): Serializable

open class LoadMoreData(var hasMore: Boolean = true)
open class Response(@SerializedName("status") val status: String = "", //ok
                    @SerializedName("msg") val msg: String = "" //
) : Serializable

/**
 * 车辆位置详细信息
 */
data class VehicleDate(
		@SerializedName("cameraTotal") val cameraTotal: Int, //4
		@SerializedName("simNo") val simNo: String, //14551351726
		@SerializedName("Dname") val dname: String, //null
		@SerializedName("Dphone") val dphone: String, //null
		@SerializedName("companyid") val companyid: String, //null
		@SerializedName("khqc") val khqc: String, //出租车
		@SerializedName("TeamNo") val teamNo: String, //默认
		@SerializedName("OwnNo") val ownNo: String,
		@SerializedName("Cph") val cph: String, //渝B5T660
		@SerializedName("platecolor") val platecolor: String, //蓝色
		@SerializedName("Deviceid") val deviceid: String, //101608405067
		@SerializedName("Time") val time: String, //2017-11-08 18:42:22.000Z
		@SerializedName("Longitude") val longitude: Double, //106.56915
		@SerializedName("Latitude") val latitude: Double, //29.53226
		@SerializedName("Velocity") val velocity: Int, //38
		@SerializedName("Locate") val locate: Boolean, //true
		@SerializedName("Alarm") val alarm: Int, //0
		@SerializedName("signal") val signal: Int, //0
		@SerializedName("satelliteLen") val satelliteLen: Int, //0
		@SerializedName("Angle") val angle: Int, //254
		@SerializedName("VehId") val vehId: Int, //54057
		@SerializedName("userid") val userid: String //111
):Serializable

data class GetTonJiInfo(
		@SerializedName("companyid") val companyid: Int, //24
		@SerializedName("CompanyName") val companyName: String, //长运大足运输分公司
		@SerializedName("VCount") val vCount: Int, //38
		@SerializedName("Alarm") val alarm: Int, //0
		@SerializedName("AlarmResult") val alarmResult: Int, //0
		@SerializedName("SMS") val sMS: Any, //null
		@SerializedName("OnLineV") val onLineV: Int, //36
		@SerializedName("LocateV") val locateV: Int, //36
		@SerializedName("userid") val userid: String, //111
		@SerializedName("StopV") val stopV: Int, //2
		@SerializedName("NoData") val noData: Int //0
):Serializable



data class TonJiTeamNoInfo(
		@SerializedName("userid") val userid: String, //111
		@SerializedName("companyid") val companyid: Int, //24
		@SerializedName("teamno") val teamno: String, //运输高速车队
		@SerializedName("Num") val num: Int //38
)


data class TonJiAlarmInfo(
		@SerializedName("companyid") val companyid: Int, //160
		@SerializedName("CompanyName") val companyName: Any, //null
		@SerializedName("VCount") val vCount: Int, //0
		@SerializedName("Alarm") val alarm: Int, //1
		@SerializedName("AlarmResult") val alarmResult: Int, //1
		@SerializedName("SMS") val sMS: Int, //0
		@SerializedName("OnLineV") val onLineV: Int, //0
		@SerializedName("LocateV") val locateV: Int, //0
		@SerializedName("userid") val userid: String, //111
		@SerializedName("StopV") val stopV: Int, //0
		@SerializedName("NoData") val noData: Int //0
)

data class TonJiAlarmTypeInfo(
		@SerializedName("userid") val userid: String, //111
		@SerializedName("companyid") val companyid: Int, //160
		@SerializedName("note") val note: String, //超速报警
		@SerializedName("Num") val num: Int //1
)