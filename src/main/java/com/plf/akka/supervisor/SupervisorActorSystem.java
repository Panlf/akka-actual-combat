package com.plf.akka.supervisor;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Lancer
 * @date 2024-06-07
 */
public class SupervisorActorSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("supervisor-actor-sys");

        actorSystem.actorOf(Props.create(SupervisorActor.class),"supervisor-actor");
    }
}
