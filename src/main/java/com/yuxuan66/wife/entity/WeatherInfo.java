package com.yuxuan66.wife.entity;

import lombok.Data;

import java.util.List;

/**
 * 天气信息
 *
 * @author Sir丶雨轩
 * @since 2023/10/14
 */
@Data
public class WeatherInfo {

    /**
     * 日期
     */
    private String date;

    /**
     * 星期几
     */
    private String week;

    /**
     * 省份
     */
    private String province;

    /**
     * 区域
     */
    private String area;

    /**
     * 区域ID
     */
    private String areaid;

    /**
     * 天气情况
     */
    private String weather;

    /**
     * 天气图片
     */
    private String weatherimg;

    /**
     * 天气代码
     */
    private String weathercode;

    /**
     * 实时温度
     */
    private String real;

    /**
     * 最低温度
     */
    private String lowest;

    /**
     * 最高温度
     */
    private String highest;

    /**
     * 风向
     */
    private String wind;

    /**
     * 风速
     */
    private String windspeed;

    /**
     * 风力等级
     */
    private String windsc;

    /**
     * 日出时间
     */
    private String sunrise;

    /**
     * 日落时间
     */
    private String sunset;

    /**
     * 月升时间
     */
    private String moonrise;

    /**
     * 月落时间
     */
    private String moondown;

    /**
     * 降水量
     */
    private String pcpn;

    /**
     * 紫外线指数
     */
    private String uv_index;

    /**
     * 空气质量指数
     */
    private String aqi;

    /**
     * 空气质量
     */
    private String quality;

    /**
     * 能见度
     */
    private String vis;

    /**
     * 湿度
     */
    private String humidity;

    /**
     * 预警列表
     */
    private List<Alarm> alarmlist;

    /**
     * 天气相关的提示
     */
    private String tips;

    /**
     * 嵌套类表示警报信息
     */
    @Data
    public static class Alarm {
        /**
         * 警报的省份
         */
        private String province;

        /**
         * 警报的城市
         */
        private String city;

        /**
         * 警报级别
         */
        private String level;

        /**
         * 警报类型
         */
        private String type;

        /**
         * 警报内容
         */
        private String content;

        /**
         * 警报时间
         */
        private String time;

    }
}
