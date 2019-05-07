package io.gank.gank.meizi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yinzhegang on 2019/3/19.
 */

public interface MeiziApi {
    @GET("api/data/福利/{size}/{page}")
    Call<MeiziData> getResult(@Path("size") int size,@Path("page") int page);
}
