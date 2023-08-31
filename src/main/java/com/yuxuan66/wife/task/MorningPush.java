package com.yuxuan66.wife.task;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.wife.support.config.BotConfig;
import com.yuxuan66.wife.utils.Util;
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
 * 早上推送消息给老婆信息
 * @author Sir丶雨轩
 * @since 2023/7/4
 */
@Component
public class MorningPush {

    /**
     * QQ机器人
     */
    private final Bot bot;

    /**
     * QQ机器人配置
     */
    private final BotConfig botConfig;

    /**
     * 程序启动初始化QQ机器人
     */
    public MorningPush(BotConfig botConfig) {
        this.botConfig = botConfig;
        bot = BotFactory.INSTANCE.newBot(botConfig.getQq(), BotAuthorization.byQRCode(), configuration -> {
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

        // 今天
        Date toDay = new Date();
        // 认识的日子
        Date acquaintance = DateUtil.parseDate(botConfig.getAcquaintance());
        // 相恋的日子
        Date fallingInLove = DateUtil.parseDate(botConfig.getFallingInLove());
        // 相恋多少天计算
        String loveInterval = DateUtil.formatBetween(fallingInLove, toDay, BetweenFormatter.Level.DAY);

        // 是否是认识的那一天
        boolean isCommemorate = DateUtil.dayOfMonth(toDay) == DateUtil.dayOfMonth(acquaintance);
        // 表白的那一天
        boolean isLove = DateUtil.dayOfMonth(toDay) == DateUtil.dayOfMonth(fallingInLove);
        // 已经认识几个月了/相恋几个月0
        long acquaintanceMonth = DateUtil.betweenMonth(acquaintance, toDay, true);

        // 拼接发送信息
        StringBuilder result = new StringBuilder();
        result.append("老婆！起床啦☀\n");
        result.append("今天是").append(DateUtil.today()).append(" ").append(week).append("\n");
        result.append("天气：").append(weather).append("\n");
        result.append("风向：").append(windDirection).append("/").append(windPower).append("\n");
        result.append("今日气温：").append(min).append("~").append(max).append("\n");
        result.append("日出时间：").append(sunrise).append("~").append(sunset).append("\n");
        result.append("彩虹屁：").append(rainbowFart).append("\n");
        result.append("今天是我们相恋的第").append(loveInterval).append("\n");
        result.append("我们已经订婚").append(DateUtil.formatBetween(DateUtil.parseDate(botConfig.getEngagement()), new Date(), BetweenFormatter.Level.DAY)).append("\n");
        result.append("距离小可爱生日还有").append(DateUtil.betweenDay(new Date(), DateUtil.parseDate(botConfig.getBirthday()), true)).append("天\n");
        if (isLove) {
            result.append("今天是我们相恋").append(acquaintanceMonth).append("个月纪念日哦！\n");
        }
        if (isCommemorate) {
            result.append("我们已经认识").append(acquaintanceMonth).append("个月啦！\n");
        }

        // 开始计算领证的日子
        int numberOfDaysForCertificateAcquisition = Convert.toInt(DateUtil.formatBetween(DateUtil.parseDate(botConfig.getObtainingACertificate()), new Date(), BetweenFormatter.Level.DAY));
        if(numberOfDaysForCertificateAcquisition == 0){
            result.append("今天是我们领证的日子哦！\n");
        }else if(numberOfDaysForCertificateAcquisition > 0){
            result.append("距离我们领证还有").append(numberOfDaysForCertificateAcquisition).append("天哦！\n");
        }else {
            result.append("我们已经领证").append(Math.abs(numberOfDaysForCertificateAcquisition)).append("天啦！\n");
        }

        result.append("名言：").append(oneDay);


        Objects.requireNonNull(bot.getFriend(botConfig.getWifeQQ())).sendMessage(result.toString());
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

        Objects.requireNonNull(bot.getFriend(botConfig.getWifeQQ())).sendMessage(result);
    }

}
