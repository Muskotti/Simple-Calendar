package fi.tuni.simple_calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HerokuService {
    @GET("hello")
    Call<ResponseBody> hello();
}