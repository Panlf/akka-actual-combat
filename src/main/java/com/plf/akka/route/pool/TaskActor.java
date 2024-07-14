package com.plf.akka.route.pool;

import akka.actor.UntypedAbstractActor;

public class TaskActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(getSelf()+"->"+message+"-->"+getContext().parent());
    }
}
