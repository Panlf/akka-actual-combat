package com.plf.akka.circuit;

import akka.actor.UntypedAbstractActor;
import akka.pattern.CircuitBreaker;
import scala.concurrent.duration.Duration;
import scala.runtime.BoxedUnit;

import java.util.Date;

/**
 * @author Lancer
 * @date 2024-06-08
 */
public class WorkerActor extends UntypedAbstractActor {

    private CircuitBreaker circuitBreaker;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        this.circuitBreaker = new CircuitBreaker(
                getContext().system().scheduler(),
                5,
                Duration.create(2, "second"),
                Duration.create(1, "minute"),
                getContext().dispatcher()).onOpen(
                () -> {
                    System.out.println(new Date() + " CircuitBreaker 开启");
                    return BoxedUnit.UNIT;
                }
        ).onHalfOpen(() -> {
            System.out.println(new Date() + " CircuitBreaker 半开启");
            return BoxedUnit.UNIT;
        }).onClose(() -> {
            System.out.println(new Date() + " CircuitBreaker 关闭");
            return BoxedUnit.UNIT;
        });
    }


    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            String msg = (String) message;
            if(msg.startsWith("sync")){
                getSender().tell(circuitBreaker.callWithSyncCircuitBreaker(()->{
                    System.out.println("收到信息===>"+msg);
                    Thread.sleep(3000);
                    return msg;
                }),getSelf());
            }
        }
    }
}
