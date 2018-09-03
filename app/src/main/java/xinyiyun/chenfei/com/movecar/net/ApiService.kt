package xinyiyun.chenfei.com.movecar.net

import com.elvishew.xlog.XLog
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * Created by YangQiang on 2017/11/4.
 */
//val BASE_URL = "http://211.139.26.196:8018/APP/"
val BASE_URL = "http://219.153.12.102:8895/"

//val BASE_URL = "http://192.168.3.60:8080/vmss/sscwapp/"
//http://211.139.26.196:8228/vmss/sscwapp/pub/login4A?uid=17a2f78fb2c74a70b609f638cd2dbc01
object ApiService {
    var apiInterface: ApiInterface
    var okHttpClient: OkHttpClient
    var retrofit: Retrofit

    init {
        val mBuilder = OkHttpClient.Builder()
         mBuilder.retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(50, 60, TimeUnit.SECONDS))
                .addNetworkInterceptor {
                    it.proceed(it.request()).let {
                        it.newBuilder().removeHeader("Pragma").header("Cache-Control", it.request().cacheControl().toString()).build()
                    }
                }
                //20M缓存
                .cache(Cache(BaseApplication.application.cacheDir, 20 * 1024 * 1024))
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .addInterceptor {
                    var response = it.proceed(it.request())
                    if (response.isSuccessful) {
                        val result = response.body()?.source()?.apply { request(Long.MAX_VALUE) }?.buffer()?.clone()?.readString(Charset.forName("UTF-8"))?.let {
                            if (!it.isNullOrEmpty()) {
                                val startpoision = it.indexOf("Result>")
                                if (startpoision > 0) {
                                    val end1 = it.substring(startpoision + 7, it.lastIndex + 1)
                                    val endpoision = end1.indexOf("Result>")
                                    if (endpoision > 0) {
                                        val end2 = end1.substring(0, endpoision)
                                        val lastposition = end2.lastIndexOf("</")
                                        if (lastposition > 0) {
                                            end2.substring(0, lastposition)
                                        } else {
                                            it
                                        }

                                    } else {
                                        it
                                    }
                                } else {
                                    it
                                }
                            } else {
                                it
                            }
                        }
                        XLog.e(result)
                       response.newBuilder().body(ResponseBody.create(response.body()?.contentType(), if (result.toString().length<8){"[{\"count\":"+result+"}]"} else{result})).build()
                    } else {
                        response
                    }
                }


        okHttpClient = mBuilder.build()

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apiInterface = retrofit.create(ApiInterface::class.java!!)
    }

    fun getApi(): ApiInterface {
        return apiInterface
    }
}