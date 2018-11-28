package carbit3333333.gmail.com.moneytracker;

import android.app.Application;
import android.arch.core.BuildConfig;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String TAG = "App";
    public static final String PREFS_NAME = "shared_prefs";
    public static final String KEY_TOKEN = "auth_token";


    private Api api;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new authInterSector())
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(carbit3333333.gmail.com.moneytracker.BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        api = retrofit.create(Api.class);
    }

    public Api getApi() {
        return api;
    }
    public void saveAuthToken(String token){
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(KEY_TOKEN, token)
                .apply();
    }
    public String getAuthToken(){
        return getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getString(KEY_TOKEN,null);
    }
    public boolean isAutorized(){
        return !TextUtils.isEmpty(getAuthToken());
    }
    private class authInterSector implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
          Request request = chain.request();
          HttpUrl url = request.url();
          HttpUrl.Builder urlBuilder = url.newBuilder();
          HttpUrl newUrl = urlBuilder.addQueryParameter("auth-token", getAuthToken()).build();

          Request.Builder reBuilder = request.newBuilder();
          Request newRequest1 = reBuilder.url(newUrl).build();

          return chain.proceed(newRequest1);
        }
    }
}
