package com.yuxuan66.wife.task;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.wife.utils.Util;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Objects;

/**
 * @author Sir丶雨轩
 * @since 2023/7/4
 */
@Component
public class MorningPush {
    private final Bot bot;

    /**
     * 程序启动初始化QQ机器人
     */
    public MorningPush() {
        bot = BotFactory.INSTANCE.newBot(1718018032L, BotAuthorization.byQRCode(), configuration -> {
            configuration.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH);
            configuration.fileBasedDeviceInfo();
        });
        bot.login();
    }


    /**
     * 每天早上推送消息给老婆微信
     */
    @Scheduled(cron = "0 15 7 * * ?")
    @PostConstruct
    public void push() {
        // 3.1 每日一言
        String oneDay = Util.oneDay();
        // 3.2 彩虹屁
        String rainbowFart = Util.getRainbowFart().getJSONObject("result").getString("content");
        // 3.3 天气
        JSONObject weatherData = Util.getWeather().getJSONObject("result");
        // 周几
        String week = weatherData.getString("week");
        // 天气
        String weather = weatherData.getString("weather");
        // 最低温度
        String min = weatherData.getString("lowest");
        // 最高温度
        String max = weatherData.getString("highest");
        // 风向
        String windDirection = weatherData.getString("wind");
        // 风力
        String windPower = weatherData.getString("windsc");
        // 日出时间
        String sunrise = weatherData.getString("sunrise");
        // 日落时间
        String sunset = weatherData.getString("sunset");

        Date toDay = new Date();
        // 3.4 生日计算
        // 3.5 相恋日子计算
        String loveInterval = DateUtil.formatBetween(DateUtil.parseDate("2023-05-11"), toDay, BetweenFormatter.Level.DAY);
        // 3.6 每月认识纪念日 每月5号为认识纪念日 每月11号为相恋纪念日
        boolean isCommemorate = DateUtil.dayOfMonth(toDay) == 5;
        // 表白的那一天
        boolean isLove = DateUtil.dayOfMonth(toDay) == 11;
        // 已经认识几个月了/相恋几个月0
        long acquaintanceMonth = DateUtil.betweenMonth(DateUtil.parseDate("2023-05-5"), toDay, true);
        StringBuilder result = new StringBuilder();
        result.append("老婆！起床啦☀\n");
        result.append("今天是").append(DateUtil.today()).append(" ").append(week).append("\n");
        result.append("天气：").append(weather).append("\n");
        result.append("风向：").append(windDirection).append("/").append(windPower).append("\n");
        result.append("今日气温：").append(min).append("~").append(max).append("\n");
        result.append("日出时间：").append(sunrise).append("~").append(sunset).append("\n");
        result.append("彩虹屁：").append(rainbowFart).append("\n");
        result.append("今天是我们相恋的第").append(loveInterval).append("\n");
        result.append("我们已经订婚").append(DateUtil.formatBetween(DateUtil.parseDate("2023-08-02"), new Date(), BetweenFormatter.Level.DAY)).append("\n");
        result.append("距离小可爱生日还有").append(DateUtil.betweenDay(new Date(), DateUtil.parseDate("2024-02-22"), true)).append("天\n");
        if (isLove) {
            result.append("今天是我们相恋").append(acquaintanceMonth).append("个月纪念日哦！\n");
        }
        if (isCommemorate) {
            result.append("我们已经认识").append(acquaintanceMonth).append("个月啦！\n");
        }
        result.append("名言：").append(oneDay);


        Objects.requireNonNull(bot.getFriend(1348517163L)).sendMessage(result.toString());
    }


    @Scheduled(cron = "0 15 21 * * ?")
    @PostConstruct
    public void eveningPush() {

        // 3.3 天气
        JSONObject weatherData = Util.get7Weather().getJSONObject("result").getJSONArray("list").getJSONObject(1);
        // 周几
        String week = weatherData.getString("week");
        // 天气
        String weather = weatherData.getString("weather");
        // 最低温度
        String min = weatherData.getString("lowest");
        // 最高温度
        String max = weatherData.getString("highest");
        // 风向
        String windDirection = weatherData.getString("wind");
        // 风力
        String windPower = weatherData.getString("windsc");
        // 日出时间
        String sunrise = weatherData.getString("sunrise");
        // 日落时间
        String sunset = weatherData.getString("sunset");
        String tips = weatherData.getString("tips");

        Date toDay = new Date();

        String result = "老婆！晚上好🌙\n" +
                "明天是" + DateUtil.formatDate(DateUtil.offsetDay(toDay, 1)) + " " + week + "\n" +
                "天气：" + weather + "\n" +
                "风向：" + windDirection + "/" + windPower + "\n" +
                "明日气温：" + min + "~" + max + "\n" +
                "日出时间：" + sunrise + "~" + sunset + "\n" +
                "友情提示：" + tips;

        Objects.requireNonNull(bot.getFriend(1348517163L)).sendMessage(result);
    }

}
