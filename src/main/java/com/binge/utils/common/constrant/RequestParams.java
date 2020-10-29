package com.binge.utils.common.constrant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 请求参数
 * @Authror fanbin CAI
 * @Date 2020/10/29 10:44
 */
@Data
@Component
public class RequestParams {

    //彩票接口key
    @Value("${Brings.query.key}")
    private String key;

}
