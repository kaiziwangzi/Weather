package com.brook.weather.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.brook.weather.converters.GsonConverterFactory;
import com.brook.weather.rxadapter.RxJavaCallAdapterFactory;
import com.brook.weather.utils.L;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class WeatherRequest {

	public static WeatherCallApi build() {
		return build(WeatherCallApi.class, GsonConverterFactory.create(),
				RxJavaCallAdapterFactory.create());
	}

	public static <T> T build(Class<T> api, Converter.Factory converter,
			CallAdapter.Factory callAdapter) {
		String baseUrl = "";
		Retrofit.Builder retrofit = new Retrofit.Builder();
		retrofit.baseUrl(baseUrl);
		if (null != converter) {
			retrofit.addConverterFactory(converter);
		}
		if (null != callAdapter) {
			retrofit.addCallAdapterFactory(callAdapter);
		}
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.addInterceptor(new Interceptor() {

			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request().newBuilder()
						.addHeader("x-from", "brook")
						.addHeader("User-Agent", "brook").build();
				return chain.proceed(request);
			}
		});

		if (L.DEBUG) {
			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
			interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
			builder.addInterceptor(interceptor);
		}

		builder.connectTimeout(5, TimeUnit.SECONDS);
		builder.retryOnConnectionFailure(true);
		retrofit.client(builder.build());
		return retrofit.build().create(api);
	}
}
