package com.plf.akka.route.pool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.routing.RoundRobinPool;

/**
 * @author Lancer
 * @date 2024-07-14
 */
public class MasterRouterActor extends UntypedAbstractActor {

    ActorRef router = null;

    @Override
    public void preStart() throws Exception {
        //消息中转处理器
        router = getContext().actorOf(new RoundRobinPool(3)
                //TaskActor 自动变成router的子级
                .props(Props.create(TaskActor.class)),"taskActor");
        System.out.println("router:"+router);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        router.tell(message,getSender());
    }
}
