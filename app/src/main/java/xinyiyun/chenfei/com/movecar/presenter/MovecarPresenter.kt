package xinyiyun.chenfei.com.movecar.presenter

import com.trello.rxlifecycle2.LifecycleProvider
import xinyiyun.chenfei.com.baselibrary.common.apiObservable
import xinyiyun.chenfei.com.baselibrary.model.*
import xinyiyun.chenfei.com.baselibrary.model.LoginInfo
import xinyiyun.chenfei.com.movecar.base.BasePresenter
import xinyiyun.chenfei.com.movecar.category.ClientInfo
import xinyiyun.chenfei.com.movecar.category.TeamInfo
import xinyiyun.chenfei.com.movecar.category.VehicleInfo
import xinyiyun.chenfei.com.movecar.requestmodel.*

/**
 * Created by chenfei on 2018/1/12.
 */
class MovecarPresenter(lifecycleProvider: LifecycleProvider<*>) : BasePresenter(lifecycleProvider) {

    /**
     * 登陆
     */
    fun login(username:String,password:String,block: LoginInfo.() -> Unit) {
        val requestModel = LoginRequestModel(username, password)
        val requestBody = LoginRequestBody(requestModel)
        val requestEnvelope = LoginRequestEnvelope(requestBody)
        apiService {
            validateUser(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.first().apply(block)
            }
        }
    }

    /**
     * 获取客户个数
     */
    fun clientinfocount(UserID:String ,block: Count.() -> Unit) {
        val requestModel = ClientInfoCountRequestModel(UserID)
        val requestBody = ClientInfoCountRequestBody(requestModel)
        val requestEnvelope = ClientInfoCountRequestEnvelope(requestBody)
        apiService {
            GetClientInfoCount(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.first().apply(block)
            }
        }
    }

    /**
     * 获取客户信息
     */
    fun clientinfo(userid:String ,pagesize:Int ,block: List<ClientInfo>.() -> Unit) {
        val requestModel = ClientInfoRequestModel(userid,1,pagesize)
        val requestBody = ClientInfoRequestBody(requestModel)
        val requestEnvelope = ClientInfoRequestEnvelope(requestBody)
        apiService {
            GetClientInfo(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.apply(block)
            }
        }
    }

    /**
     * 获取车队信息
     */
    fun teaminfo(companyid:Int,block: List<TeamInfo>.() -> Unit) {
        val requestModel = TeamNoInfoRequestModel(companyid)
        val requestBody = TeamNoInfoRequestBody(requestModel)
        val requestEnvelope = TeamNoInfoRequestEnvelope(requestBody)
        apiService {
            GetTeamNoInfo(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.apply(block)
            }
        }
    }


    /**
     * 获取车辆个数
     */
    fun vehicleinfocount(UserID:String ,block: Count.() -> Unit) {
        val requestModel = VehicleInfoCountRequestModel(UserID)
        val requestBody = VehicleInfoCountRequestBody(requestModel)
        val requestEnvelope = VehicleInfoCountRequestEnvelope(requestBody)
        apiService {
            GetVehicleInfoCount(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.first().apply(block)
            }
        }
    }

    /**
     * 获取车辆列表信息
     */
    fun vehicleinfo(userid:String ,pagesize:Int ,block: List<VehicleInfo>.() -> Unit) {
        val requestModel = VehicleInfoRequestModel(userid,1,pagesize)
        val requestBody = VehicleInfoRequestBody(requestModel)
        val requestEnvelope = VehicleInfoRequestEnvelope(requestBody)
        apiService {
            GetVehicleInfo(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.apply(block)
            }
        }
    }
    /**
     * 获取车辆详细信息
     */
    fun GetTpVeDate(userid:String,cph:String,pagesize:Int ,block: VehicleDate.() -> Unit) {
        val requestModel = VehicleDateRequestModel(userid,1,pagesize,cph)
        val requestBody = VehicleDateRequestBody(requestModel)
        val requestEnvelope = VehicleDateRequestEnvelope(requestBody)
        apiService {
            GetTpVeDate(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.first().apply(block)
            }
        }
    }

    /**
     * 获取车辆统计个数
     */
    fun GetTonJiInfoCount(UserID:String ,StarTime:String,EndTime:String,block: Count.() -> Unit) {
        val requestModel = GetTonJiInfoCountRequestModel(UserID,StarTime,EndTime)
        val requestBody = GetTonJiInfoCountRequestBody(requestModel)
        val requestEnvelope = GetTonJiInfoCountRequestEnvelope(requestBody)
        apiService {
            GetTonJiInfoCount(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.first().apply(block)
            }
        }
    }

    /**
     * 获取车辆统计信息
     */
    fun GetTonJiInfo(userid:String ,pagesize:Int ,StarTime:String,EndTime:String,block: List<GetTonJiInfo>.() -> Unit) {
        val requestModel = GetTonJiInfoRequestModel(userid,1,pagesize,StarTime,EndTime)
        val requestBody =  GetTonJiInfoRequestBody(requestModel)
        val requestEnvelope =  GetTonJiInfoRequestEnvelope(requestBody)
        apiService {
            GetTonJiInfo(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.apply(block)
            }
        }
    }

    /**
     * 获取车队统计个数
     */
    fun tjteaminfocount(UserID:String ,block: Count.() -> Unit) {
        val requestModel = GetTonJiTeamNoInfoCountRequestModel(UserID)
        val requestBody = GetTonJiTeamNoInfoCountRequestBody(requestModel)
        val requestEnvelope = GetTonJiTeamNoInfoCountRequestEnvelope(requestBody)
        apiService {
            GetTonJiTeamNoInfoCount(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.first().apply(block)
            }
        }
    }

    /**
     * 获取车队统计信息
     */
    fun tjteaminfo(userid:String ,pagesize:Int ,block: List<TonJiTeamNoInfo>.() -> Unit) {
        val requestModel = GetTonJiTeamNoInfoRequestModel(userid,1,pagesize)
        val requestBody = GetTonJiTeamNoInfoRequestBody(requestModel)
        val requestEnvelope =GetTonJiTeamNoInfoRequestEnvelope(requestBody)
        apiService {
            GetTonJiTeamNoInfo(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.apply(block)
            }
        }
    }

    /**
     * 获取报警统计个数
     */
    fun GetTonJiAlarmInfoCount(UserID:String ,StarTime:String,EndTime:String,block: Count.() -> Unit) {
        val requestModel = GetTonJiAlarmInfoCountRequestModel(UserID,StarTime,EndTime)
        val requestBody = GetTonJiAlarmInfoCountRequestBody(requestModel)
        val requestEnvelope = GetTonJiAlarmInfoCountRequestEnvelope(requestBody)
        apiService {
            GetTonJiAlarmInfoCount(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.first().apply(block)
            }
        }
    }

    /**
     * 获取报警统计信息
     */
    fun GetTonJiAlarmInfo(userid:String ,pagesize:Int ,StarTime:String,EndTime:String,block: List<TonJiAlarmInfo>.() -> Unit) {
        val requestModel = GetTonJiAlarmInfoRequestModel(userid,1,pagesize,StarTime,EndTime)
        val requestBody =  GetTonJiAlarmInfoRequestBody(requestModel)
        val requestEnvelope =  GetTonJiAlarmInfoRequestEnvelope(requestBody)
        apiService {
            GetTonJiAlarmInfo(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.apply(block)
            }
        }
    }

    /**
     * 获取报警类型个数
     */
    fun GetTonJiAlarmTypeInfoCount(UserID:String ,StarTime:String,EndTime:String,block: Count.() -> Unit) {
        val requestModel = GetTonJiAlarmTypeInfoCountRequestModel(UserID,StarTime,EndTime)
        val requestBody =GetTonJiAlarmTypeInfoCountRequestBody(requestModel)
        val requestEnvelope = GetTonJiAlarmTypeInfoCountRequestEnvelope(requestBody)
        apiService {
            GetTonJiAlarmTypeInfoCount(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.first().apply(block)
            }
        }
    }

    /**
     * 获取报警类型信息
     */
    fun GetTonJiAlarmTypeInfo(userid:String ,pagesize:Int ,StarTime:String,EndTime:String,block: List<TonJiAlarmTypeInfo>.() -> Unit) {
        val requestModel = GetTonJiAlarmTypeInfoRequestModel(userid,1,pagesize,StarTime,EndTime)
        val requestBody =  GetTonJiAlarmTypeInfoRequestBody(requestModel)
        val requestEnvelope =  GetTonJiAlarmTypeInfoRequestEnvelope(requestBody)
        apiService {
            GetTonJiAlarmTypeInfo(requestEnvelope).compose(apiTransformer()).apiObservable {
                it.apply(block)
            }
        }
    }

}

