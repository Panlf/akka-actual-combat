package com.plf.akka.tool.schedule;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.time.Duration;

/**
 * @author Lancer
 * @date 2024-12-14
 */
public class SchedulerActor extends UntypedAbstractActor {



    private final LoggingAdapter logger = Logging.getLogger(getContext().system(),this);

    @Override
    public void onReceive(Object message) throws Throwable {
        logger.info("receive message : {}",message);
    }

    public static void main(String[] args) throws InterruptedException {

        ActorSystem actorSystem = ActorSystem.create("actor-schedule-sys");

        ActorRef actorRef =actorSystem.actorOf(Props.create(SchedulerActor.class),"actor-schedule");
        //actorRef.tell("Hello",ActorRef.noSender());

        //延迟1s之后，每隔3s发送一条消息
        Cancellable cancellable = actorSystem.scheduler().scheduleAtFixedRate(Duration.ofSeconds(1),
                Duration.ofSeconds(3),
                actorRef,
                "你好啊",
                actorSystem.dispatcher(),
                ActorRef.noSender());

        Thread.sleep(10 * 1000);
        //取消定时任务
        cancellable.cancel();
    }
}
