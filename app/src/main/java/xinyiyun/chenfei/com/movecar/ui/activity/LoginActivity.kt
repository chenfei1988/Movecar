package xinyiyun.chenfei.com.movecar.ui.activity

import ittiger.indexlist.helper.PinYinHelper
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import xinyiyun.chenfei.com.baselibrary.annotation.ContentView
import xinyiyun.chenfei.com.baselibrary.annotation.StateView
import xinyiyun.chenfei.com.baselibrary.annotation.ToolbarView
import xinyiyun.chenfei.com.baselibrary.basic.BaseActivity
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication
import xinyiyun.chenfei.com.baselibrary.common.isEmpty
import xinyiyun.chenfei.com.baselibrary.utils.CheckPermissionsUtil
import xinyiyun.chenfei.com.baselibrary.utils.PreferencesHelper
import xinyiyun.chenfei.com.baselibrary.utils.SPreferencesUtils
import xinyiyun.chenfei.com.movecar.R
import xinyiyun.chenfei.com.movecar.base.MyApplication
import xinyiyun.chenfei.com.movecar.common.api
import xinyiyun.chenfei.com.movecar.gen.VehicleInfoDao
import xinyiyun.chenfei.com.movecar.presenter.MovecarPresenter
import xinyiyun.chenfei.com.movecar.utils.DialogHelper


@ContentView(R.layout.activity_login)

class LoginActivity : BaseActivity() {
    override fun onViewCreated() {
        val checkPermissionsUtil = CheckPermissionsUtil(this)
        checkPermissionsUtil.requestAllPermission(this)
        if(PreferencesHelper.getBooleanData("remenber")){
            login_edt_user_name.setText(PreferencesHelper.getStringData("loginname"))
            login_edt_user_pass.setText(PreferencesHelper.getStringData("password"))
            chb_remenber.isChecked =true
        }
        login_btn_login.onClick {
            listOf(login_edt_user_name, login_edt_user_pass).forEach {
                if (it.isEmpty()) {
                    toast(it.hint)
                    return@onClick
                }
            }
            DialogHelper.showLoadingDialog(this@LoginActivity)
            api<MovecarPresenter> {
                login(login_edt_user_name.text.toString().trim(), login_edt_user_pass.text.toString().trim(), {
                    this.apply {
                        if (userid > 0) {
                            BaseApplication.loginInfo = this
                            PreferencesHelper.setInfo("remenber",chb_remenber.isChecked)
                            PreferencesHelper.setInfo("loginname",login_edt_user_name.text.toString().trim())
                            PreferencesHelper.setInfo("password",login_edt_user_pass.text.toString().trim())
                            GetClientCount()
                        } else {
                            DialogHelper.dismissLoadingDialog()
                            toast("登陆失败，请检查账号密码")
                        }
                    }
                })
            }
        }
    }

    fun GetClientCount() {
        api<MovecarPresenter> {
            clientinfocount(BaseApplication.loginInfo.userid.toString(), {
                this.apply {
                    val count = this.count
                    if (count > 0) {
                        GetClientInfo(count)
                    }
                }
            })
        }
    }

    fun GetClientInfo(count: Int) {
        api<MovecarPresenter> {
            clientinfo(BaseApplication.loginInfo.userid.toString(), count, {
                this.apply {
                    var clientdao = MyApplication.mDaoSession.clientInfoDao
                    clientdao.deleteAll()
                    clientdao.insertInTx(this)
                    var teamdao = MyApplication.mDaoSession.teamInfoDao
                    teamdao.deleteAll()
                    for (i in this.indices) {
                        if (i == this.size - 1) {
                            GetTeamInfo(this.get(i).companyid, true)
                        } else {
                            GetTeamInfo(this.get(i).companyid, false)
                        }
                    }
                }
            })
        }
    }

    fun GetTeamInfo(companyid: Int, isover: Boolean) {
        api<MovecarPresenter> {
            teaminfo(companyid, {
                this.apply {
                    var teamdao = MyApplication.mDaoSession.teamInfoDao
                    teamdao.insertInTx(this)
                    if (isover) {
                        GetVehicleInfoCount()
                    }
                }
            })
        }
    }

    fun GetVehicleInfoCount() {
        api<MovecarPresenter> {
            vehicleinfocount(BaseApplication.loginInfo.userid.toString(), {
                this.apply {
                    val count = this.count
                    if (count > 0) {
                        GetVehicleInfo(count)
                        SPreferencesUtils.putInt(this@LoginActivity,"vehicletotal",count)
                    }
                }
            })
        }
    }

    fun GetVehicleInfo(count: Int) {
        api<MovecarPresenter> {
            vehicleinfo(BaseApplication.loginInfo.userid.toString(), count, {
                this.apply {
                    DialogHelper.dismissLoadingDialog()
                    var vehicledao = MyApplication.mDaoSession.vehicleInfoDao
                    vehicledao.deleteAll()
                    vehicledao.insertInTx(this)
                    startActivity<MainActivity>()
                    finish()
                }
            })
        }
    }
}