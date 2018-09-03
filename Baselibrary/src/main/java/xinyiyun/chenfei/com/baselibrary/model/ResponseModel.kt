package xinyiyun.chenfei.com.baselibrary.model

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.*
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
        @SerializedName("vehicletotal") var vehicletotal: Int=0, //1
        @SerializedName("Company") val company: String=""
): Serializable

open class LoadMoreData(var hasMore: Boolean = true)
open class Response(@SerializedName("status") val status: String = "", //ok
                    @SerializedName("msg") val msg: String = "" //
) : Serializable

data class Count(
        @SerializedName("count") val count: Int//1472
): Serializable