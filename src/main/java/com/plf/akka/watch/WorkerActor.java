package com.plf.akka.watch;

import akka.actor.UntypedAbstractActor;

/**
 * @author Lancer
 * @date 2024-06-01
 */
public class WorkerActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("收到了消息："+message);
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("Worker postStop");
    }
}
