package com.mks.zakati.networkapi;


import com.google.gson.GsonBuilder;
import com.mks.zakati.utils.Lg;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by rahil on 9/9/15.
 */
public class ApiClient {

    private static ApiClient client;
    //private static ApiClient instance;
    private Apis apis;

    public ApiClient() {
        //code for retrofit 2.0


        //setting up client
        OkHttpClient client=getUnsafeOkHttpClient();


        //rest adapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        /*.serializeNulls()*/
                        .create()))
                .build();

        apis = retrofit.create(Apis.class);

    }
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            Interceptor HEADER_INTERCEPTOR = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {


                    //chain.request().header("token") == null
                    Request.Builder builder = chain.request().newBuilder()
                            .addHeader("language", "english");
                        builder.addHeader("token", "soqek_user");



                    return chain.proceed(builder.build());
                }
            };


            //for logging
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

            loggingInterceptor.setLevel(Lg.ISDEBUG ? HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();


            builder.addInterceptor(loggingInterceptor)
                    .addNetworkInterceptor(HEADER_INTERCEPTOR)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS);

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ApiClient getClient() {
        if (client == null)
            client = new ApiClient();
        return client;
    }

    public Apis getApis() {
        return apis;
    }
}
