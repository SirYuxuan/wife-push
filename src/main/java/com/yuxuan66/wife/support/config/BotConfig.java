package com.yuxuan66.wife.support.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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
    @Value("${push.loveDay}")
    private String loveDay;

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
     * 结婚纪念日
     */
    @Value("${push.weddingAnniversary:''}")
    private String weddingAnniversary;

    /**
     * 星座
     */
    @Value("${push.star:''}")
    private String star;

    /**
     * 生日
     */
    @Value("${push.birthday:''}")
    private String birthday;

    /**
     * 城市代码
     */
    @Value("${tianxing.cityCode:''}")
    private String cityCode;

    /**
     * 天行的密钥
     */
    @Value("${tianxing.apiKey:''}")
    private String apiKey;

}
