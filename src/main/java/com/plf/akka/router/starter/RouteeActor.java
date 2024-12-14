package com.plf.akka.router.starter;

import akka.actor.UntypedAbstractActor;

/**
 * 路由目标
 * @author Lancer
 * @date 2024-12-14
 */
public class RouteeActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(getSelf()+"-->"+message);
    }
}
