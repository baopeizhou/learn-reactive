package org.bob.learn;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Observable<Object> observable = Observable.create(observer->{
            observer.onNext("处理的数据是："+Math.random()*100);
            observer.onComplete();
        }).cache();
        observable.subscribe(consumer->{
           System.out.println("我处理的元素是："+consumer);
        });
        observable.subscribe(consumer->{
            System.out.println("我处理的元素是："+consumer);
        });
        log.info("learn-undertow");
        SpringApplication.run(Application.class, args);
        log.info("learn-undertow");
    }
}