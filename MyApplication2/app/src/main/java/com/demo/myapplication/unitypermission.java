package com.demo.myapplication;

import android.app.Activity;
import android.util.Log;


import java.util.List;

public class unitypermission {
    private String TAG = "PERMISSION";
    private Activity _unityActivity;

    Activity getActivity() {
        if (null == _unityActivity) {
            try {
                Class<?> classtype = Class.forName("com.unity3d.player.UnityPlayer");
                Activity activity = (Activity) classtype.getDeclaredField("currentActivity").get(classtype);
                _unityActivity = activity;
            } catch (ClassNotFoundException e) {

            } catch (IllegalAccessException e) {

            } catch (NoSuchFieldException e) {

            }
        }
        return _unityActivity;
    }

    public void RequestPermission() {
        com.hjq.permissions.XXPermissions.with(getActivity())
                // 申请单个权限
                .permission(com.hjq.permissions.Permission.MANAGE_EXTERNAL_STORAGE)
                // 设置权限请求拦截器（局部设置）
                //.interceptor(new PermissionInterceptor())
                // 设置不触发错误检测机制（局部设置）
                //.unchecked()
                .request(new com.hjq.permissions.OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean allGranted) {
                        if (!allGranted) {
                            return;
                        }
                        Log.i(TAG, "onGranted: 申请权限成功");
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean doNotAskAgain) {
                        if (doNotAskAgain) {
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            com.hjq.permissions.XXPermissions.startPermissionActivity(getActivity(), permissions);
                        } else {
                            // 处理权限被拒绝的情况
                        }
                    }
                });
    }
}
