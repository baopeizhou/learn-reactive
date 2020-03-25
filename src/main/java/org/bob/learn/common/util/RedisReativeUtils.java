package org.bob.learn.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveRedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.stream.Collectors;


public class RedisReativeUtils {

    private static ReactiveRedisConnection reactiveRedisConnection;


    /**
     * 私有构造器
     * 禁止通过反射构造类实例
     * @throws IllegalAccessException 非法访问异常
     */
    private RedisReativeUtils() throws IllegalAccessException {
        throw new IllegalAccessException("禁止访问RedisReativeUtils私有构造方法");
    }

    /**
     * Redis底层对象
     */
    @Component
    final static class Redis {
        Redis(@Autowired LettuceConnectionFactory lettuceConnectionFactory){
            reactiveRedisConnection = lettuceConnectionFactory.getReactiveClusterConnection();
        }
    }


    public static Mono<Boolean> exists(String key){
        return reactiveRedisConnection.keyCommands().exists(ByteBuffer.wrap(key.getBytes()));
    }

    public static Mono<Long> del(String key){
        return reactiveRedisConnection.keyCommands().del(ByteBuffer.wrap(key.getBytes()));
    }

    public static Mono<Boolean> hset(String key, String hashKey,String hashValue) {
        return reactiveRedisConnection.hashCommands().hSet(ByteBuffer.wrap(key.getBytes()),ByteBuffer.wrap(hashKey.getBytes()),ByteBuffer.wrap(hashValue.getBytes()))
    }

    public static Flux<Map.Entry<ByteBuffer, ByteBuffer>> entries(String key) {
        return reactiveRedisConnection.hashCommands().hGetAll(ByteBuffer.wrap(key.getBytes()));
    }

}
