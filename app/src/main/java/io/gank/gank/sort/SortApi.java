package io.gank.gank.sort;

import io.gank.gank.sort.data.SortData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yinzhegang on 2019/3/15.
 */

public interface SortApi {
    @GET("api/data/{dataType}/{size}/{page}")
    Call<SortData> getResult(@Path("dataType") String dataType, @Path("size") int size, @Path("page") int page);
}
