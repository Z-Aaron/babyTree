package io.github.kolacbb.babytree.net.service;

import io.github.kolacbb.babytree.model.Account;
import io.github.kolacbb.babytree.model.ResponsBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * api docs: http://docs.bmob.cn/data/Restful/b_developdoc/doc/index.html#对象
 * Created by kolab on 2016/11/13.
 */
public interface AccountService {

    @GET("/1/classes/account")
    Call<Account> query(@Query(value = "where", encoded=true) String emailJson);

    /**
     * 新建账户
     * @param accountJson
     * @return
     */
    @POST("/1/classes/account")
    Call<ResponsBody> signup(@Body String accountJson);


}
