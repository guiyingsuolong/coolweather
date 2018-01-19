package coolweather.com.coollol.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018-01-18.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
