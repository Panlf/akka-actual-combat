package com.plf.akka.router.pool;

import akka.actor.UntypedAbstractActor;

/**
 * 自动成为master的下级
 * @author Lancer
 * @date 2024-12-14
 */
public class TaskActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(getSelf()+"->"+message+"-->"+getContext().parent());
    }
}
