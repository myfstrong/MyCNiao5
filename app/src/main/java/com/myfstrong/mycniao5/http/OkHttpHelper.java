package com.myfstrong.mycniao5.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class OkHttpHelper {
    private OkHttpClient mOkHttpClient;

    private OkHttpClient.Builder builder;

    private Gson mGson;

    private Handler mHandler;

    private OkHttpHelper() {
        mOkHttpClient = new OkHttpClient();
        builder = mOkHttpClient.newBuilder();

        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10,TimeUnit.SECONDS);
        builder.writeTimeout(30,TimeUnit.SECONDS);

        mHandler = new Handler(Looper.getMainLooper());

    }

    public static OkHttpHelper getInstance() {
        return new OkHttpHelper();
    }

    public void get(String url,BaseCallBack callBack) {
        Request request = buildRequset(url,null,HttpMethodType.GET);
        doRequest(request,callBack);
    }

    public void post(String url, Map<String,String> parmas,BaseCallBack callBack) {
        Request request = buildRequset(url,parmas,HttpMethodType.POST);
        doRequest(request,callBack);
    }

    public void doRequest(final Request request, final BaseCallBack callBack) {

        callBack.onRequestBefore(request);

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(request,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String resultStr = response.body().string();

                    if (callBack.mType == String.class) {
                        callBack.onSuccess(response,resultStr);

                        callBackSuccess(callBack,call,resultStr);
                    } else {
                        try {
                            Object object = mGson.fromJson(resultStr,callBack.mType);
                            callBack.onSuccess(response,object);
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                            callBack.onError(response,response.code(),e);
                        }
                    }
                } else {
                    callBack.onError(response,response.code(),null);
                }
            }
        });
    }

    private Request buildRequset(String url,Map<String,String> parmas,HttpMethodType methodType) {
        Request.Builder builder = new Request.Builder()
                .url(url);

        if (methodType == HttpMethodType.GET) {
            builder.get();
        } else if (methodType == HttpMethodType.POST) {

            RequestBody body = buildFormData(parmas);
            builder.post(body);
        }

        return builder.build();
    }

    private RequestBody buildFormData(Map<String,String> parmas) {
        FormBody.Builder builder = new FormBody.Builder();
        if (parmas != null) {
            for (Map.Entry<String,String> entry : parmas.entrySet()) {
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        return builder.build();
    }

    private void callBackSuccess(final BaseCallBack callBack, final Response response, final Object object) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess(response,object);
            }
        });
    }

    private void callbackError(final BaseCallBack callback , final Call call, final Exception e ){

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(call,,e);
            }
        });
    }

    enum HttpMethodType {
        GET,
        POST
    }

}
