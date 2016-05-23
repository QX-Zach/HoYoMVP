package com.example.ozner.hoyomvp.Bean;

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
    @POST("Command/SendPhoneCode")
    public Call<NetJsonResponse> sendPhoneCode(@Field("mobile") String mobile, @Field("order") String order, @Field("scope") String scope);

    /*
    *APP登录
    * @phone 手机号
    * @pssword 密码
     */
    @FormUrlEncoded
    @POST("FamilyAccount/AppLogin")
    public Call<NetJsonResponse> login(@Field("phone") String phone, @Field("password") String password);

    /*
    *获取所有我的绑定银行卡列表
    * @param usertoken
     */
    @FormUrlEncoded
    @POST("Command/GetOwenBindBlankCard")
    public Call<NetJsonResponse> getOwenBindBlankCard(@Field("usertoken") String usertoken);

    /*
    *绑定极光ID
    * @usertoken
    * @notifyid 极光通知ID
     */
    @FormUrlEncoded
    @POST("Command/BingJgNotifyId")
    public Call<NetJsonResponse> bindJgNotifyId(@Field("usertoken") String usertoken, @Field("notifyid") String notifyid);
}
