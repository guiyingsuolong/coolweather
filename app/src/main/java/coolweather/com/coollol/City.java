package coolweather.com.coollol;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018-01-17.
 */

public class City extends DataSupport {
    private int id;
    private String cityName;//记录市的名字
    private int cityCode;//记录市的代号
    private int proinceId;//记录当前市所属省的id值

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProinceId() {
        return proinceId;
    }

    public void setProinceId(int proinceId) {
        this.proinceId = proinceId;
    }
}
