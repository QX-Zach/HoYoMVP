package com.example.ozner.hoyomvp.HttpService;

import com.example.ozner.hoyomvp.Bean.AuthorityDetail;
import com.example.ozner.hoyomvp.Bean.IndexInfo;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by ozner_67 on 2016/5/23.
 */
public interface HttpApiService {
    /*
    *发送验证码
    *@param mobile 手机号
    * @param order 为什么获取验证码 eg:注册要传值:register 绑定银行卡:BindCard 重置密码:ResetPassword
    * @param scope 要注册的权限,仅注册传值：user 微信注册 engineer app注册
     */
    @FormUrlEncoded
    @POST("Command/SendPhoneCode")
    Call<NetJsonResponse<String>> sendPhoneCode(@Field("mobile") String mobile, @Field("order") String order, @Field("scope") String scope);

    /*
    *校验手机号和验证码
    * @phone 手机号
    * @code 验证码
     */
    @FormUrlEncoded
    @POST("FamilyAccount/AppChenkPhone")
    Call<NetJsonResponse<String>> checkVerifyCode(@Field("phone") String phone, @Field("code") String code);

    /*
    * 用户注册
    * @token 校验手机号时返回的有效token
    * @realname 实名
    * @cardid 身份证号
    * @password 登录密码
    * @scope 要注册的权限：user 微信注册 engineer app注册
     */
    @FormUrlEncoded
    @POST("FamilyAccount/AppRegister")
    Call<NetJsonResponse<String>> registSubmit(@Field("token") String token, @Field("realname") String name, @Field("cardid") String cardid, @Field("password") String password, @Field("scope") String scope);

    /*
    *APP登录
    * @phone 手机号
    * @pssword 密码
     */
    @FormUrlEncoded
    @POST("FamilyAccount/AppLogin")
    Call<NetJsonResponse<String>> login(@Field("phone") String phone, @Field("password") String password);

    /*
    *获取当前权限的信息或者审核进度，获取团队成员信息
    * @usertoken
     */
    @FormUrlEncoded
    @POST("AppInterface/GetNowAuthorityDetail")
    Call<NetJsonResponse<AuthorityDetail>> getNowAuthorityDetail(@Field("usertoken") String usertoken);

    /*
    *刷新首页信息
     */
    @FormUrlEncoded
    @POST("AppInterface/RefreshIndex")
    Call<NetJsonResponse<IndexInfo>> refreshIndexInfo(@Field("usertoken") String usertoken);

    /*
    *获取所有我的绑定银行卡列表
    * @param usertoken
     */
    @FormUrlEncoded
    @POST("Command/GetOwenBindBlankCard")
    Call<NetJsonResponse> getOwenBindBlankCard(@Field("usertoken") String usertoken);

    /*
    *绑定极光ID
    * @usertoken
    * @notifyid 极光通知ID
     */
    @FormUrlEncoded
    @POST("Command/BingJgNotifyId")
    Call<NetJsonResponse> bindJgNotifyId(@Field("usertoken") String usertoken, @Field("notifyid") String notifyid);
}
