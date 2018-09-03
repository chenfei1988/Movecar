package xinyiyun.chenfei.com.baselibrary.net

import android.widget.Toast
import com.elvishew.xlog.XLog
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import android.R.attr.port
import android.R.attr.host
import android.R.attr.scheme
import okhttp3.HttpUrl
import java.io.IOException


/**
 * Created by chenfei on 2018/5/10.
 */

object ApiService {


    //val BASE_URL = "http://222.180.198.106:8899/vmss/sscwapp/"
    val BASE_URL = "http://183.224.48.146:9001/"
   // val BASE_URL = "http://192.168.15.27:9791/"
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
                        it.newBuilder().removeHeader("Pragma").header("Cache-Control", it.request().cacheControl().toString())
                               .build()
                    }
                }
                //20M缓存
                .cache(Cache(BaseApplication.application.cacheDir, 20 * 1024 * 1024))
                .addInterceptor(MoreBaseUrlInterceptor()) //多URL连接
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .addInterceptor {
                    var response = it.proceed(it.request().newBuilder().addHeader("Authorization",BaseApplication.token).build())
                   // var response = it.proceed(it.request())
                    if (response.isSuccessful) {
                        val result = response.body()?.source()?.apply { request(Long.MAX_VALUE) }?.buffer()?.clone()?.readString(Charset.forName("UTF-8"))?.let {
                            if (!it.isNullOrEmpty()) {
                                if (it.startsWith("(") && it.endsWith(")")) {
                                    it.substring(1, it.length - 1)
                                }
                               else if (it=="请重新登录"){
                                    "{\"Tip\":  \"token过期，请重新登录 \"," +
                                            "  \"Status\": false}"
                                }
                                else {
                                    it
                                }
                            } else {
                                "{\"Tip\": \"没有该权限，请联系管理员绑定权限\","+"\"Status\": false }"
                            }
                        }
                        XLog.e(result)
                        response.newBuilder().body(ResponseBody.create(response.body()?.contentType(), result)).build()
                    } else {
                        response
                    }
                }


        okHttpClient = mBuilder.build()

        retrofit = Retrofit.Builder()
                //处理多BaseUrl,添加应用拦截器
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    /*
      具体服务实例化
   */
    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }

    class MoreBaseUrlInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            //获取原始的originalRequest
            val originalRequest = chain.request()
            //获取老的url
            val oldUrl = originalRequest.url()
            //获取originalRequest的创建者builder
            val builder = originalRequest.newBuilder()
            //获取头信息的集合如：manage,mdffx
            val urlnameList = originalRequest.headers("urlname")
            if (urlnameList != null && urlnameList!!.size > 0) {
                //删除原有配置中的值,就是namesAndValues集合里的值
                builder.removeHeader("urlname")
                //获取头信息中配置的value,如：manage或者mdffx
                val urlname = urlnameList!!.get(0)
                var baseURL: HttpUrl? = null
                //根据头信息中配置的value,来匹配新的base_url地址
                if ("manage" == urlname) {
                    baseURL = HttpUrl.parse(BASE_URL)
                } else if ("mdffx" == urlname) {
                  //  baseURL = HttpUrl.parse(BASE_URL2)
                }
                //重建新的HttpUrl，需要重新设置的url部分
                val newHttpUrl = oldUrl.newBuilder()
                        .scheme(baseURL!!.scheme())//http协议如：http或者https
                        .host(baseURL.host())//主机地址
                        .port(baseURL.port())//端口
                        .build()
                //获取处理后的新newRequest
                val newRequest = builder.url(newHttpUrl).build()
                return chain.proceed(newRequest)
            } else {
                return chain.proceed(originalRequest)
            }

        }
    }

}