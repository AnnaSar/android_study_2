package lynx.ancha.starwars.di.modul;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lynx.ancha.starwars.data.rest.RestApiPeoples;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    public NetModule() {

    }

    @Singleton
    @Provides
    RestApiPeoples getRestApiPeoples(Retrofit retrofit) {
        return retrofit.create(RestApiPeoples.class);
    }

    @Singleton
    @Provides
    Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit
                .Builder()
                .baseUrl("https://swapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(
                message -> Log.d("TAG", message)
        );
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client.addInterceptor(interceptor);
        return client.build();
    }
}
