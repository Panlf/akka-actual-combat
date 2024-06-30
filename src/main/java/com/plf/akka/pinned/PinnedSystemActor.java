package com.plf.akka.pinned;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author Lancer
 * @date 2024-06-09
 */
public class PinnedSystemActor {
    public static void main(String[] args) {
        ActorSystem.create("config-sys", ConfigFactory.load("appsys"));
        ActorSystem actorSystem = ActorSystem.create("actor-pinned-sys");

        for(int i=0;i<20;i++){
            ActorRef actorRef =actorSystem.actorOf(Props.create(PinnedActor.class)
                    .withDispatcher("my-pinned-dispatcher"),"pinned-"+i);
            actorRef.tell("Hello Pinned",ActorRef.noSender());
        }

    }
}
