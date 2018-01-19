package coolweather.com.coollol.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.IntDef;
import android.widget.Toast;

import java.io.IOException;

import coolweather.com.coollol.gson.Weather;
import coolweather.com.coollol.util.HttpUtil;
import coolweather.com.coollol.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdataService extends Service {
    public AutoUpdataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        updataWeather();
        updataBingPic();
        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour=60*60*3;
        Toast.makeText(this,"yunxingle",Toast.LENGTH_SHORT).show();
        long triggerAtTime= SystemClock.elapsedRealtime()+anHour;
        Intent i=new Intent(this,AutoUpdataService.class);
        PendingIntent pi=PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }
//更新天气
    private void updataWeather() {
        SharedPreferences pres= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = pres.getString("wather", null);
        if(weatherString!=null){
            //有缓存时直接解析天气数据
            Weather weather = Utility.hanleWeatherRsponse(weatherString);
            String weatherId=weather.basic.weatherId;
            String weatherUrl="http://guolin.tech/api/weather?cityid="+weatherId+"&key=f5dbcc8853b24bada2807644af86ba6c";
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                        String responseText=response.body().toString();
                        Weather weather=Utility.hanleWeatherRsponse(responseText);
                        if(weather!=null&&"ok".equals(weather.status)){
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdataService.this).edit();
                            editor.putString("weather",responseText);
                            editor.apply();
                        }
                }
            });
        }
    }
    //更新图片
    private void  updataBingPic(){
        String requestBingPic="http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic=response.body().string();
                SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(AutoUpdataService.this).edit();
                editor.putString("big_pic",bingPic);
                editor.apply();
            }
        });
    }
}
