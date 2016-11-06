package io.github.kolacbb.babytree.net.service;

import io.github.kolacbb.babytree.model.Article;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kolab on 2016/11/6.
 */

public interface BusinessService {

    /**
     * 获取主页数据 (include后跟表名，该表所有数据会返回，在此处不应该动态变化)
     */
    @GET("article?include=head_image")
    Call<Article> getHomePageFeed();
}
