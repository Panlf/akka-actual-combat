package com.plf.akka.route;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lancer
 * @date 2024-07-03
 */
public class RouterTaskActor extends UntypedAbstractActor {

    private Router router;

    @Override
    public void preStart() throws Exception {
        List<Routee> listRoutee = new ArrayList<>();
        for(int i=0;i<2;i++){
            ActorRef actorRef = getContext().actorOf(Props.create(RouteeActor.class)
                    ,"routeeActor"+i);
            listRoutee.add(new ActorRefRoutee(actorRef));
        }
        //轮询
        router = new Router(new RoundRobinRoutingLogic(),listRoutee);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        router.route(message,getSender());
    }
}
