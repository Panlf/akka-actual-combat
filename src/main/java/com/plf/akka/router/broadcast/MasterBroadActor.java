package com.plf.akka.router.broadcast;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.routing.*;
import com.plf.akka.router.pool.TaskActor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 消息中转处理器
 * @author Lancer
 * @date 2024-12-14
 */
public class MasterBroadActor extends UntypedAbstractActor {

    private Router router;

    @Override
    public void preStart() throws Exception {
        ActorRef actorRef1 = getContext().actorOf(Props.create(BroadWorker1.class), "bw1");
        ActorRef actorRef2 = getContext().actorOf(Props.create(BroadWorker2.class), "bw2");
        // 配合配置
        /*
            akka.actor.deployment {
                /masterBroadActor/broadRouter {
                    router = broadcast-group
                    routees.paths = ["/user/masterBroadActor/bw1","/user/masterBroadActor/bw2"]
                }
            }
        */
        List<Routee> listRoutee = new ArrayList<>();
        listRoutee.add(new ActorRefRoutee(actorRef1));
        listRoutee.add(new ActorRefRoutee(actorRef2));
        // router = getContext().actorOf(FromConfig.getInstance().props(),"broadRouter");
        router =  new Router(new BroadcastRoutingLogic(),listRoutee);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        router.route(message,getSender());
    }
}
