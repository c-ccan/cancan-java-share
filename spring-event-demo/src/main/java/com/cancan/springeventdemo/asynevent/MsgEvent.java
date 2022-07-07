package com.cancan.springeventdemo.asynevent;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Strive
 * @date 2022/4/24 10:18
 * @description
 */
@Data
@AllArgsConstructor
public class MsgEvent {

    /**
     * 该类型事件携带的信息
     */
    public String orderId;
}