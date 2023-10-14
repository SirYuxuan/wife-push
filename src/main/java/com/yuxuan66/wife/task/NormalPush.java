package com.yuxuan66.wife.task;

import com.yuxuan66.wife.cron.CronConst;
import com.yuxuan66.wife.service.NormalService;
import com.yuxuan66.wife.support.BotCore;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 系统内置常规推送任务
 *
 * @author Sir丶雨轩
 * @since 2023/10/14
 */
@Component
@RequiredArgsConstructor
public class NormalPush {

    private final BotCore botCore;

    private final NormalService normalService;


    /**
     * 每天早上7.15推送消息
     */
    @PostConstruct
    @Scheduled(cron = CronConst.MORNING)
    public void morning() {
        botCore.push(normalService.morning());
    }

    /**
     * 每天晚上9.15推送消息
     */
    @PostConstruct
    @Scheduled(cron = CronConst.NIGHT)
    public void night() {
        botCore.push(normalService.night());
    }

}
