package io.github.kolacbb.babytree.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 该类为单例类，该类业务端统一调用使用该类进行网络请求，
 * 获取Service实例便于统一管理网络请求方式。
 * Created by kolab on 2016/11/5.
 */

public class RetrofitManager {

    // bmob key
    private static final String BMOB_APPLICATION_ID = "eced2ae394c89029ebaeafc2fb435bba";
    private static final String BMOB_API_KEY = "86628cf8eff3c0f2c8f384a6dc9aef63";
    private static final String BMOB_SECRET_KEY = "d1682be71c333722";
    private static final String BMOB_MASTER_KEY = "225d9995cf61f71883f01c968c29fec3";

    // bmob api
    private static final String API = "https://api.bmob.cn/1/";

    private static OkHttpClient sOkHttpClient;
    private static Interceptor sInterceptor;
    private static Retrofit sRetrofit;

    /**
     * 想要获取Retrofit实例，只能通过该方法获取Retrofit实例。
     * 该方法中，统一为每一个retrofit请求添加上token，即bomb请求key
     * 设置application id， 添加retrofit api key，设置请求体格式
     * @return
     */
    public static Retrofit getInstance() {

        if (sInterceptor == null) {
            sInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    final RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=gb2312"), "");

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("X-Bmob-Application-Id", BMOB_APPLICATION_ID)
                            .addHeader("X-Bmob-REST-API-Key", BMOB_API_KEY)
                            .addHeader("Content-Type", "application/json");
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };
        }

        if (sOkHttpClient == null) {
            sOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(sInterceptor)
                    .build();
        }

        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(sOkHttpClient)
                    .build();
        }
        return sRetrofit;
    }

    /**
     * 业务方法通过该静态方法，传入与服务端协议好的好的service类，进行业务请求
     * 统一网络请求的方式。
     * @param service 与服务端歇息好的service类
     * @param <T> 服务端返回的java bean类，retrofit 将服务端传回的数据直接解析为bean类
     *           供业务端使用
     * @return retrofit解析好了的bean类
     */
    public static  <T> T create(final Class<T> service) {
        return getInstance().create(service);
    }
}
