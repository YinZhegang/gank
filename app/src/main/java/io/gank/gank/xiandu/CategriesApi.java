package io.gank.gank.xiandu;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yinzhegang on 2019/3/19.
 */

public interface CategriesApi {
    @GET("api/xiandu/category/{dataType}")
    Call<CategoriesData>getResults(@Path("dataType") String dataType);
}
