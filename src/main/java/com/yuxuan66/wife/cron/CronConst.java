package com.yuxuan66.wife.cron;

/**
 * Cron表达式的常量
 * @author Sir丶雨轩
 * @since 2023/10/14
 */
public interface CronConst {

    /**
     * 早上7.15推送
     */
    String MORNING = "0 15 7 * * ?";

    /**
     * 晚上7.15推送
     */
    String NIGHT = "0 15 19 * * ?";
}
