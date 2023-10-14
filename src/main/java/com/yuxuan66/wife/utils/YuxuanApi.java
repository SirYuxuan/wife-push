package com.yuxuan66.wife.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * 调用雨轩PublicApi
 * <a href="https://api.yuxuan66.com/">Public API</a>
 * @author Sir丶雨轩
 * @since 2023/10/14
 */
@Component
public class YuxuanApi {


    /**
     * 获取每日一言的数据
     * @return 每日一言
     */
    public String oneDay(){
        return  JSONObject.parseObject(HttpUtil.get("https://api.yuxuan66.com/text/oneDay")).getString("data");
    }

}
