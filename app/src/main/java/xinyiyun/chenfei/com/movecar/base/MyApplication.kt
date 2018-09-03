package xinyiyun.chenfei.com.movecar.base

import android.database.sqlite.SQLiteDatabase
import com.baidu.mapapi.SDKInitializer
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication
import xinyiyun.chenfei.com.movecar.gen.DaoMaster
import xinyiyun.chenfei.com.movecar.gen.DaoSession

/**
 * Created by YangQiang on 2017/8/31.
 */
class MyApplication : BaseApplication() {
    companion object {
        lateinit var application: MyApplication
        lateinit  var mHelper: DaoMaster.DevOpenHelper
        lateinit  var db: SQLiteDatabase
        lateinit  var mDaoMaster: DaoMaster
        lateinit var mDaoSession: DaoSession
    }
    override fun onCreate() {
        super.onCreate()
        application = this
        SDKInitializer.initialize(this)
        setDatabase()
    }

    /**
     * 设置greenDao
     */
    private fun setDatabase() {
        mHelper = DaoMaster.DevOpenHelper(this, "tobacco.db", null)
        db = mHelper.writableDatabase
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = DaoMaster(db)
        mDaoSession = mDaoMaster.newSession()
    }

    fun getDaoSession(): DaoSession {
        return mDaoSession
    }

    fun getDb(): SQLiteDatabase {
        return db
    }

}