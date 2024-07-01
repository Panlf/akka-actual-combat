package com.plf.akka.mailbox.control;

import akka.dispatch.ControlMessage;

/**
 * @author Breeze
 * @date 2024/7/1
 */
public class ControlMsg implements ControlMessage {

    private final String status;

    public ControlMsg(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
