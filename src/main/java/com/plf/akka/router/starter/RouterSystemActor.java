package com.plf.akka.router.starter;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.plf.akka.starter.ActorDemo;
import com.typesafe.config.ConfigFactory;

/**
 * @author Lancer
 * @date 2024-12-14
 */
public class RouterSystemActor {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("router-sys", ConfigFactory.load("appsys"));
        ActorRef actorRef =actorSystem.actorOf(Props.create(RouterTaskActor.class),"router-task-actor");
        actorRef.tell("HelloA",ActorRef.noSender());
        actorRef.tell("HelloB",ActorRef.noSender());
        actorRef.tell("HelloC",ActorRef.noSender());
    }
}
