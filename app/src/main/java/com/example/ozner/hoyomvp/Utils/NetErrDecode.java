package com.example.ozner.hoyomvp.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinde on 2016/1/13.
 */
public class NetErrDecode {
    private static Map<Integer, String> ErrMap = new HashMap<>();

    static {
        ErrMap.put(-1, "网络请求失败，请检查网络是否连接");
        ErrMap.put(-10001, "登录参数/模式错误");
        ErrMap.put(-10002, "微信AppId为空");
        ErrMap.put(-10003, "刷新微信信息错误");
        ErrMap.put(-10004, "服务器异常");
        ErrMap.put(-10005, "获取微信用户信息错误");
        ErrMap.put(-10006, "用户信息保存数据库失败");
        ErrMap.put(-10007, "微信用户未注册绑定手机");
        ErrMap.put(-10008, "用户已经不存在");
        ErrMap.put(-10009, "访问接口参数错误");
        ErrMap.put(-10010, "验证码发送频繁，请稍后再试!");
        ErrMap.put(-10011, "手机号已经被注册");
        ErrMap.put(-10012, "验证码无效");
        ErrMap.put(-10013, "验证码已经使用过了");
        ErrMap.put(-10014, "验证码已经过期");
        ErrMap.put(-10015, "权限错误，无法访问");
        ErrMap.put(-10016, "实名信息提取失败");
        ErrMap.put(-10017, "身份证已经被使用过了");
        ErrMap.put(-10018, "手机号或者密码错误");
        ErrMap.put(-10019, "统一订单号生产失败,请稍后再试");
        ErrMap.put(-10020, "订单生成失败");
        ErrMap.put(-10021, "非法注册");
        ErrMap.put(-10022, "订单不存在或者被抢");
        ErrMap.put(-10023, "订单已经被抢了");
        ErrMap.put(-10024, "订单超过了可抢时间");
        ErrMap.put(-10025, "上传图片失败");
        ErrMap.put(-10026, "非法操作");
        ErrMap.put(-10027, "订单已经提交完成");
        ErrMap.put(-10028, "已经评价过了");
        ErrMap.put(-10029, "非法支付");
        ErrMap.put(-10030, "定位失败");
        ErrMap.put(-10031, "已经是当前角色，不用升级");
        ErrMap.put(-10032, "有角色权限在审核中，请等上一次审核结果出来后，再提交");
        ErrMap.put(-10033, "组(合伙人)不存在");
        ErrMap.put(-10034, "当前组(合伙人)不能加入");
        ErrMap.put(-10035, "已经是当前组（合伙人）的成员");
        ErrMap.put(-10036, "已经加入到了其他组，退出后再加入");
        ErrMap.put(-10037, "你不是当前组的成员");
        ErrMap.put(-10038, "银行卡已经被自己绑定过了");
        ErrMap.put(-10039, "该工程师没有加入任何团队");
        ErrMap.put(-10040, "未经过实名认证");
        ErrMap.put(-10041, "订单已经结算");
        ErrMap.put(-10042, "被封号了");
        ErrMap.put(-10043, "订单已经被抢");
        ErrMap.put(-10044, "所属网点不在此服务区域");
        ErrMap.put(-10045, "账户余额不足");
        ErrMap.put(-10046, "您未绑定银行卡");
        ErrMap.put(-10047, "对组的操作权限不够");

        ErrMap.put(70000, "审核中");
        ErrMap.put(70002, "审核失败");
        ErrMap.put(70003, "被封号了");

    }

    public static String getErrMsg(int errCode) {
        if (ErrMap.containsKey(errCode)) {
            return ErrMap.get(errCode);
        } else {
            return "";
        }
    }

    public static void ShowErrMsgDialog(Context context, int errCode, String defMsg) {
        try {
            if (errCode != 0) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                if (ErrMap.containsKey(errCode)) {
                    alertDialog.setMessage(ErrMap.get(errCode));
                } else {
                    if (defMsg != null && defMsg != "")
                        alertDialog.setMessage(defMsg);
                    else
                        alertDialog.setMessage("未知错误");
                }

                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
