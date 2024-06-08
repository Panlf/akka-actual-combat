package com.plf.akka.circuit;

import akka.actor.*;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

/**
 * 熔断
 * @author Lancer
 * @date 2024-06-08
 */
public class CircuitBreakerActor extends UntypedAbstractActor {
    /*
       circuit breaker有三个状态 Closed 、 Open 、Half-Open

       Closed状态
       1）正常情况下，circuit breaker是closed状态：异常或者调用超过配置的callTimeout，就增加一次失败计数
       2）成功则重置失败计数为0
       3）当失败次数到达maxFailures，会进入Open状态

       Open状态
       1）所有调用伴随着抛出CircuitBreakerOpenException，立即失败
       2）在resetTimeout后，circuit breaker进入Half-Open状态

       Half-Open状态
       1）第一次调用尝试运行进入而不会快速失败
       2）所有其他调用会处于Open状态，进而快速失败
       3）如果第一次调用成功，会重回Closed状态
       4）如果第一个调用失败，会进入Open状态，然后等待下一次resetTimeout

     */

    private ActorRef workerChild;

    private static final SupervisorStrategy supervisorStrategy =
            new OneForOneStrategy(20,
                    Duration.create(1, "minute"), new Function<Throwable, SupervisorStrategy.Directive>() {
                @Override
                public SupervisorStrategy.Directive apply(Throwable param) throws Exception {
                    System.out.println("我进入了容错");
                    return SupervisorStrategy.resume();
                }
            });

    public SupervisorStrategy supervisorStrategy() {
        return supervisorStrategy;
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        workerChild = getContext().actorOf(Props.create(WorkerActor.class),"WorkerActor");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        workerChild.tell(message,getSender());
    }
}
