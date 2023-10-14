package com.yuxuan66.wife.support;

import com.yuxuan66.wife.support.config.BotConfig;
import lombok.RequiredArgsConstructor;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * QQ机器人核心
 *
 * @author Sir丶雨轩
 * @since 2023/10/14
 */
@Component
@RequiredArgsConstructor
public class BotCore {

    private Bot bot;

    private final BotConfig botConfig;

    /**
     * 程序启动初始化QQ机器人
     */
    @PostConstruct
    public void init() throws Exception {
        bot = BotFactory.INSTANCE.newBot(botConfig.getQq(), BotAuthorization.byQRCode(), configuration -> {
            configuration.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_WATCH);
            configuration.fileBasedDeviceInfo();
        });
        bot.login();
        if(!bot.isOnline()){
            throw new Exception("QQ机器人登录失败");
        }
    }

    /**
     * 推送给指定QQ消息
     * @param qq QQ号
     * @param message 消息
     */
    public void push(long qq,String message){
        Objects.requireNonNull(bot.getFriend(qq)).sendMessage(message);
    }

    /**
     * 推送给配置文件中的QQ消息
     * @param message 消息
     */
    public void push(String message){
        Objects.requireNonNull(bot.getFriend(botConfig.getQq())).sendMessage(message);
    }


}
