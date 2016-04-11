package com.example.scorpio.BindMyService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    
    //2.实现服务成功绑定的代码，返回一个中间人
    @Override
    public IBinder onBind(Intent arg0) {
        System.out.println("服务被成功绑定了");
        return new MiddlePerson();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        System.out.println("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();
    }
    
    /*这是服务里面的一个方法*/

    public void methodInService() {
        Toast.makeText(this, "哈哈，服务给你办好了暂住证。。。", Toast.LENGTH_SHORT).show();
    }

    //1.第一步服务要暴露方法 ，必须要有一个中间人
    private class MiddlePerson extends Binder implements IMiddlePerson {

        public void callMethodInService(int money) {
            if (money >= 50) {
                methodInService();
            } else {
                Toast.makeText(getApplicationContext(),"多准备点钱。。。",Toast.LENGTH_SHORT).show();
            }
        }


        /*陪领导打麻将*/
        public void playMajiang() {
            System.out.println("陪领导打麻将。。。");
        }
    }
}
