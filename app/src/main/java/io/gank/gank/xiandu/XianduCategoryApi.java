package io.gank.gank.xiandu;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yinzhegang on 2019/3/19.
 */

public interface XianduCategoryApi {
    @GET("api/xiandu/categories")
    Call<XianduCategoryData> getResult();
}
