package com.yuxuan66.wife.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.wife.entity.WeatherInfo;
import com.yuxuan66.wife.support.config.BotConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 天性API调用
 * <a href="https://www.tianapi.com/">天行数据</a>
 *
 * @author Sir丶雨轩
 * @since 2023/10/14
 */
@Component
@RequiredArgsConstructor
public class TianxingApi {

    private final BotConfig botConfig;

    /**
     * 获取彩虹屁
     *
     * @return 彩虹屁
     */
    public String getRainbowFart() {
        return JSONObject.parseObject(HttpUtil.get("https://apis.tianapi.com/caihongpi/index?key=" + botConfig.getApiKey()))
                .getJSONObject("result").getString("content");
    }

    /**
     * 获取当日天气
     *
     * @return 天气信息
     */
    public WeatherInfo getWeather() {
        return JSONObject.parseObject(HttpUtil.get("https://apis.tianapi.com/tianqi/index?key=" + botConfig.getApiKey() + "&city=" + botConfig.getCityCode() + "&type=" + 1))
                .getJSONObject("result").toJavaObject(WeatherInfo.class);
    }

    /**
     * 获取7日天气
     *
     * @return 天气信息
     */
    public List<WeatherInfo> get7Weather() {
        return JSONObject.parseObject(HttpUtil.get("https://apis.tianapi.com/tianqi/index?key=" + botConfig.getApiKey() + "&city=" + botConfig.getCityCode() + "&type=" + 7))
                .getJSONObject("result").getJSONArray("list").toJavaList(WeatherInfo.class);
    }

    /**
     * 获取星座的今日运势
     * @return 运势
     */
    public String getStar() {
        for (Object o : JSONObject.parseObject(HttpUtil.get("https://apis.tianapi.com/star/index?key=" + botConfig.getApiKey() + "&astro=" + botConfig.getStar())).getJSONObject("result").getJSONArray("list")) {
            JSONObject obj = (JSONObject) o;
            if ("今日概述".equals(obj.getString("type"))) {
                return obj.getString("content");
            }
        }
        return  null;
    }

}
