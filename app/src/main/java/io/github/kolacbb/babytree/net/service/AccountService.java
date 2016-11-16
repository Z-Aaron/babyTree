package io.github.kolacbb.babytree.net.service;

import io.github.kolacbb.babytree.model.Account;
import io.github.kolacbb.babytree.model.ResponsBody;
import io.github.kolacbb.babytree.model.Result;
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

    /**
     * 指定的checkJson语句查询，用于登录：Login，
     * @param checkJson 根据BombRequestJsonBuilder 生成的json，后使用URLEncoder编码。编码格式gb2312,使用工具类StringUtils
     * @return
     */
    @GET("/1/classes/account")
    Call<Result<Account>> query(@Query(value = "where", encoded=true) String checkJson);

    /**
     * 新建账户
     * @param account
     * @return
     */
    @POST("/1/classes/account")
    Call<ResponsBody> signup(@Body Account account);


}
