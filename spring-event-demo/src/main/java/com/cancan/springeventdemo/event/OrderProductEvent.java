package com.cancan.springeventdemo.event;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author Strive
 * @date 2022/4/22 18:00
 * @description
 */
@Data
@ToString
public class OrderProductEvent extends ApplicationEvent {

    /**
     * 该类型事件携带的信息
     */
    private String orderId;

    public OrderProductEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }
}