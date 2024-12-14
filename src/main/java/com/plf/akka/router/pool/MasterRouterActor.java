package com.plf.akka.router.pool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.routing.RoundRobinPool;

/**
 * 消息中转处理器
 * @author Lancer
 * @date 2024-12-14
 */
public class MasterRouterActor extends UntypedAbstractActor {

    ActorRef router = null;

    @Override
    public void preStart() throws Exception {
        router=getContext().actorOf(new RoundRobinPool(3).props(Props.create(TaskActor.class)),"taskActor");
        System.out.println("router:"+router);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        router.tell(message,getSender());
    }
}
