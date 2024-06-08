package com.plf.akka.supervisor;

import akka.actor.*;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Lancer
 * @date 2024-06-07
 */
public class SupervisorActor extends UntypedAbstractActor {

    //定义监督策略 在1分钟内重启超过3次则停止该Actor
    private final SupervisorStrategy supervisorStrategy = new OneForOneStrategy(3, Duration.create(1, "minute"), param -> {
        if(param instanceof IOException){
            System.out.println("===== IOException =====");
            // 恢复运行
            return SupervisorStrategy.resume();
        }else if(param instanceof IndexOutOfBoundsException){
            System.out.println("===== IndexOutOfBoundsException =====");
            // 重启
            return SupervisorStrategy.restart();
        }else if(param instanceof SQLException){
            System.out.println("===== SQLException =====");
            // 停止
            return SupervisorStrategy.stop();
        } else {
            System.out.println("===== escalate =====");
            // 升级失败
            return SupervisorStrategy.escalate();
        }
    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return supervisorStrategy;
    }


    @Override
    public void preStart() throws Exception {
        //创建子级Actor
        ActorRef workerActor = getContext().actorOf(Props.create(WorkerActor.class),"workerActor");

        //查找Actor
        //ActorSelection w = getContext().actorSelection("workerActor");
        //System.out.println(w.anchorPath());
        // System.out.println("2==>"+workerActor.path());
        //ActorSelection w = getContext().actorSelection("workerActor");
        //w.tell("getValue",getSelf());


        //监控child
        getContext().watch(workerActor);
        System.out.println("已经成功监控");

        //workerActor.tell(new IOException(),getSelf());
        //workerActor.tell(new SQLException("SQL异常"),getSelf());
        workerActor.tell(new IndexOutOfBoundsException(),getSelf());
        workerActor.tell("getValue",getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof Terminated){
            Terminated terminated = (Terminated) message;
            System.out.println(terminated.getActor()+"已经终止");
        } else {
            System.out.println("SupervisorActor stateCount = "+message);
        }
    }
}
