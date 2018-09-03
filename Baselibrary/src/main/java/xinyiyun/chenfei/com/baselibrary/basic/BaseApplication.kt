package xinyiyun.chenfei.com.baselibrary.basic

import android.app.Application
import android.content.Context
import android.os.Environment
import android.support.multidex.BuildConfig
import android.support.multidex.MultiDex
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.elvishew.xlog.printer.AndroidPrinter
import com.elvishew.xlog.printer.file.FilePrinter
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator
import xinyiyun.chenfei.com.baselibrary.model.LoginInfo
import xinyiyun.chenfei.com.baselibrary.utils.Preference
import java.io.File

/**
 * Created by chenfei on 2018/5/10.
 */
open class BaseApplication : Application() {

    companion object {
        lateinit var application: BaseApplication
        var loginInfo: LoginInfo by Preference(LoginInfo())
        val tag = "yncw"
        var token =""
    }


    override fun onCreate() {
        super.onCreate()
        application = this
        var config: LogConfiguration = LogConfiguration.Builder().logLevel(if (BuildConfig.DEBUG) LogLevel.ALL else {
            LogLevel.NONE
        }).tag(tag).build()
        var androidPrinter = AndroidPrinter()
        var filePrinter = FilePrinter.Builder(Environment.getExternalStorageDirectory().absolutePath + File.separator + tag)
                .fileNameGenerator(DateFileNameGenerator())
                .backupStrategy(NeverBackupStrategy()).build()
        XLog.init(config, androidPrinter, filePrinter)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}