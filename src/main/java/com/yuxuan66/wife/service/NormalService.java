package com.yuxuan66.wife.service;

import cn.hutool.core.date.DateUtil;
import com.yuxuan66.wife.entity.WeatherInfo;
import com.yuxuan66.wife.utils.TianxingApi;
import com.yuxuan66.wife.utils.YuxuanApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 常规推送
 *
 * @author Sir丶雨轩
 * @since 2023/10/14
 */
@Service
@RequiredArgsConstructor
public class NormalService {

    private final TianxingApi tianxingApi;
    private final YuxuanApi yuxuanApi;
    private final DayCalcService dayCalcService;

    /**
     * 早上推送
     *
     * @return 推送的消息
     */
    public String morning() {

        // 获取今日天气信息
        WeatherInfo weatherInfo = tianxingApi.getWeather();
        // 发送消息拼装
        StringBuilder result = new StringBuilder();
        result.append("早上好，老婆，今天也要开心哦！\n");
        result.append("今天是").append(DateUtil.today()).append(" ").append(weatherInfo.getWeek()).append("\n");
        result.append("今天的天气是").append(weatherInfo.getWeather()).append("\n");
        result.append("今日气温：").append(weatherInfo.getLowest()).append("~").append(weatherInfo.getHighest()).append("\n");
        // 判断是否有异常天气报警
        if (!weatherInfo.getAlarmlist().isEmpty()) {
            result.append("⚠️⚠️⚠️今日天气预警：").append(weatherInfo.getAlarmlist().get(0).getContent()).append("\n");
        }
        result.append("日出时间：").append(weatherInfo.getSunrise()).append("~").append(weatherInfo.getSunset()).append("\n");
        result.append("彩虹屁：").append(tianxingApi.getRainbowFart()).append("\n");
        result.append("今天是我们相恋的第").append(dayCalcService.loveInterval()).append("\n");
        result.append("距离老婆的生日还有").append(dayCalcService.daysUntilBirthday()).append("天\n");

        result.append(dayCalcService.calculateAnniversary("领证", "obtainingACertificate")).append("\n");
        result.append(dayCalcService.calculateAnniversary("结婚", "weddingAnniversary")).append("\n");

        // 判断是否是恋爱，领证，结婚的纪念日
        Integer loveDay = dayCalcService.calculateAnniversaryYear("loveDay");
        Integer obtainingACertificate = dayCalcService.calculateAnniversaryYear("obtainingACertificate");
        Integer weddingAnniversary = dayCalcService.calculateAnniversaryYear("weddingAnniversary");
        if (loveDay != null) {
            result.append("老婆，今天是我们恋爱").append(loveDay).append("周年纪念日哦，快来亲亲老公吧！\n");
        }
        if (obtainingACertificate != null) {
            result.append("老婆，今天是我们领证").append(obtainingACertificate).append("周年纪念日哦，快来亲亲老公吧！\n");
        }
        if (weddingAnniversary != null) {
            result.append("老婆，今天是我们结婚").append(weddingAnniversary).append("周年纪念日哦，快来亲亲老公吧！\n");
        }
        result.append("今日运势：").append(tianxingApi.getStar()).append("\n");
        result.append("每日一言：").append(yuxuanApi.oneDay());
        return result.toString();
    }

    /**
     * 晚上推送
     *
     * @return 推送的消息
     */
    public String night() {
        // 获取明日天气信息
        WeatherInfo weatherInfo = tianxingApi.get7Weather().get(1);
        Date toDay = new Date();

        return "老婆！晚上好🌙\n" +
                "明天是" + DateUtil.formatDate(DateUtil.offsetDay(toDay, 1)) + " " + weatherInfo.getWeek() + "\n" +
                "天气：" + weatherInfo.getWeather() + "\n" +
                "风向：" + weatherInfo.getWind() + "/" + weatherInfo.getWindsc() + "\n" +
                "明日气温：" + weatherInfo.getLowest() + "~" + weatherInfo.getHighest() + "\n" +
                "日出时间：" + weatherInfo.getSunrise() + "~" + weatherInfo.getSunset() + "\n" +
                "友情提示：" + weatherInfo.getTips();
    }
}
