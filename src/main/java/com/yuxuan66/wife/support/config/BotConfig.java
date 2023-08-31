package com.yuxuan66.wife.support.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 机器人配置类
 * @author Sir丶雨轩
 * @since 2023/8/31
 */
@Data
@Configuration
public class BotConfig {

    /**
     * 机器人QQ号
     */
    @Value("${bot.qq}")
    private Long qq;
    /**
     * 老婆的QQ
     */
    @Value("${bot.wifeQQ}")
    private Long wifeQQ;

    /**
     * 恋爱纪念日
     */
    @Value("${push.fallingInLove}")
    private String fallingInLove;

    /**
     * 相识的日子
     */
    @Value("${push.acquaintance}")
    private String acquaintance;

    /**
     * 订婚的日子
     */
    @Value("${push.engagement:''}")
    private String engagement;

    /**
     * 领证的日子
     */
    @Value("${push.obtainingACertificate:''}")
    private String obtainingACertificate;

    /**
     * 生日
     */
    @Value("${push.birthday:''}")
    private String birthday;


}
