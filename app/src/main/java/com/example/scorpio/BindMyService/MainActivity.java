package com.example.scorpio.BindMyService;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyConn conn;
    private IMiddlePerson mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //绑定服务
    public void bind(View view) {
        //3.activity采用绑定的方式去开启服务
        Intent intent = new Intent(this, MyService.class);
        conn = new MyConn();
        bindService(intent, conn, BIND_AUTO_CREATE);
        
    }

    //解除绑定服务
    public void unbind(View view) {
        unbindService(conn);
    }

    @Override
    protected void onDestroy() {
        System.out.println("哎，activity挂了");
        super.onDestroy();
    }

    //调用服务里面的方法
    public void call(View view) {
        //5.通过中间人调用服务里面的方法
        mp.callMethodInService(55);
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("在activity里面成功获得了中间人");
            mp=(IMiddlePerson)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
