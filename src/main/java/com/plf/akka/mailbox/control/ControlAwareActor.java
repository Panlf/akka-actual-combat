package com.plf.akka.mailbox.control;

import akka.actor.UntypedAbstractActor;

/**
 * @author Breeze
 * @date 2024/7/1
 */
public class ControlAwareActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(message);
    }
}
