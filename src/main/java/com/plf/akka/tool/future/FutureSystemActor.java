package com.plf.akka.tool.future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.OnComplete;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.plf.akka.tool.schedule.SchedulerActor;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author Lancer
 * @date 2024-12-14
 */
public class FutureSystemActor {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("actor-future-sys");

        ActorRef actorRef =actorSystem.actorOf(Props.create(FutureActor.class),"actor-future");
        //3秒超时
        Timeout timeout = new Timeout(FiniteDuration.apply(3, TimeUnit.SECONDS));
        Future<Object> future = Patterns.ask(actorRef, "hello future", timeout);

        future.onComplete(new OnComplete<Object>() {
            @Override
            public void onComplete(Throwable failure, Object success) {
                if (failure != null) {
                    System.out.println("Error: " + failure.getMessage());
                } else {
                    String response = (String) success;
                    System.out.println(response);
                }
            }
        }, actorSystem.dispatcher());
 /*
        try {
            Object result = Await.result(future, timeout.duration());
            System.out.println(result);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
*/
    }
}
