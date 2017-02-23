package br.com.oauth2.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfiguration {

	@Bean
	public OkHttpClient okHttpClient() {

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		return httpClient.build();
	}

	@Bean
	public Retrofit retrofit(OkHttpClient client) {

		return new Retrofit.Builder()
				.baseUrl("http://localhost:8081/api/v1/users/")
				.client(client)
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
	}

	@Bean
	public UserService userService(Retrofit retrofit) {
		return retrofit.create(UserService.class);
	}
}