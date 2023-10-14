package com.yuxuan66.wife.service;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.yuxuan66.wife.support.config.BotConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 计算各种日期
 *
 * @author Sir丶雨轩
 * @since 2023/10/14
 */
@Component
@RequiredArgsConstructor
public class DayCalcService {

    private final BotConfig botConfig;

    /**
     * 计算相恋天数
     *
     * @return 相恋天数
     */
    public String loveInterval() {
        return DateUtil.formatBetween(DateUtil.parse(botConfig.getLoveDay()), DateUtil.date(), BetweenFormatter.Level.DAY);
    }

    /**
     * 计算距离生日还有多久
     *
     * @return 距离生日还有多久
     */
    public long daysUntilBirthday() {
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 解析生日字符串
        LocalDate birthday = LocalDate.parse(botConfig.getBirthday(), formatter);

        // 如果今年生日还未过，则直接计算与今年的生日天数差距
        if (today.isBefore(birthday.withYear(today.getYear()))) {
            return today.until(birthday.withYear(today.getYear()), ChronoUnit.DAYS);
        }
        // 如果今年生日已经过了，则计算到明年的生日天数差距
        else {
            return today.until(birthday.withYear(today.getYear() + 1), ChronoUnit.DAYS);
        }
    }

    /**
     * 纪念日计算
     *
     * @param anniversaryString 纪念日字符串
     * @return 纪念日计算结果
     */
    public String calculateAnniversary(String type, String anniversaryString) {
        // 获取时间
        String date = JSONObject.parseObject(JSONObject.toJSONString(botConfig)).getString(anniversaryString);
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 解析纪念日字符串
        LocalDate anniversary = LocalDate.parse(date, formatter);

        if (anniversary.isBefore(today)) {
            long daysPassed = anniversary.until(today, ChronoUnit.DAYS);
            return "我们已经" + type + daysPassed + " 天";
        } else if (anniversary.isAfter(today)) {
            long daysAhead = today.until(anniversary, ChronoUnit.DAYS);
            return "距离" + type + "还有 " + daysAhead + " 天";
        } else {
            return "今天是" + type + "日！";
        }
    }


    /**
     * 计算纪念日年数
     *
     * @param anniversaryString 纪念日字符串
     * @return 纪念日年数
     */
    public Integer calculateAnniversaryYear(String anniversaryString) {
        // 获取时间
        String date = JSONObject.parseObject(JSONObject.toJSONString(botConfig)).getString(anniversaryString);
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 解析纪念日字符串
        LocalDate anniversary = LocalDate.parse(date, formatter);

        // 判断是否是纪念日
        if (today.getMonthValue() == anniversary.getMonthValue() && today.getDayOfMonth() == anniversary.getDayOfMonth()) {
            int years = today.getYear() - anniversary.getYear();
            return years > 0 ? years : 1;
        } else {
            return null;
        }
    }
}
