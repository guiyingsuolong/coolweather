package coolweather.com.coollol.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018-01-18.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
