package io.github.kolacbb.babytree.net.service;

import java.util.List;

import io.github.kolacbb.babytree.model.Article;
import io.github.kolacbb.babytree.model.Commodity;
import io.github.kolacbb.babytree.model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kolab on 2016/11/6.
 */

public interface BusinessService {

    @GET("/1/classes/article")
    Call<Result<Article>> getArticles();


    @GET("/1/classes/Commodities")
    Call<Result<Commodity>> getCommodities();



}
