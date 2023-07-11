package com.yuxuan66.wife.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 获取推送需要的数据
 *
 * @author Sir丶雨轩
 * @since 2023/7/4
 */
public class Util {
    /**
     * 天行的密钥
     * <a href="https://www.tianapi.com/">天行数据</a>
     */
    private static final String KEY = "85976a35a1e96afe45c1fadff69a159a";

    /**
     * 获取每日一言的数据
     *
     * @return 每日一言
     */
    public static String oneDay() {
        return HttpUtil.get("http://guozhivip.com/yy/api/api.php").replace("document.write(\"", "").replace("\");", "");
    }

    /**
     * 获取彩虹屁
     *
     * @return 彩虹屁
     */
    public static JSONObject getRainbowFart() {
        return JSONObject.parseObject(HttpUtil.get("https://apis.tianapi.com/caihongpi/index?key=" + KEY));
    }

    /**
     * 查询郑州的天气预报
     * @return 天气预报
     */
    public static JSONObject getWeather() {
        return JSONObject.parseObject(HttpUtil.get("https://apis.tianapi.com/tianqi/index?key=" + KEY + "&city=101180101&type=1"));
    }
}
