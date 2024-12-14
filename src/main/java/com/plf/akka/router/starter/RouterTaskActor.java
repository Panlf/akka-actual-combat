package com.plf.akka.router.starter;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RandomRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由器
 * @author Lancer
 * @date 2024-12-14
 */
public class RouterTaskActor extends UntypedAbstractActor {
    private Router router;

    @Override
    public void preStart() throws Exception {
        List<Routee> listRoutee = new ArrayList<>();
        for(int i=0;i<2;i++){
            ActorRef ref = getContext().actorOf(Props.create(RouteeActor.class), "routeeActor" + i);
            listRoutee.add(new ActorRefRoutee(ref));
        }
        //RandomRoutingLogic: 继承自RoutingLogic，并重写了select方法。在select方法中，它通过原子类AtomicLong来做递增，
        //然后对routees的长度取余数，以此得到当前routee的下标，而在调用Router的route方法时，会通过如下send方法给目标发送消息
       router =  new Router(new RandomRoutingLogic(),listRoutee);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        router.route(message,getSender());
    }
}
