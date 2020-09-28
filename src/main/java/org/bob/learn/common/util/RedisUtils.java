package org.bob.learn.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Redis工具类
 */
@Slf4j
public final class RedisUtils {

    private static ReactiveStringRedisTemplate redisTemplate;

    /**
     * 私有构造器
     * 禁止通过反射构造类实例
     * @throws IllegalAccessException 非法访问异常
     */
    private RedisUtils() throws IllegalAccessException {
        throw new IllegalAccessException("禁止访问RedisUtils私有构造方法");
    }

    /**
     * Redis底层对象
     */
    @Component
    final static class Redis {
        Redis(@Autowired ReactiveStringRedisTemplate redisTemplate){
            RedisUtils.redisTemplate = redisTemplate;
            RedisUtils.redisTemplate = redisTemplate;
        }
    }

    /**
     * 获取Redis模板
     * @return Redis模板
     */
    public static ReactiveStringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /************************************ key 相关的工具方法 ********************************************/
    public static Mono<Boolean> exists(String key){
        return redisTemplate.hasKey(key);
    }

    public static Mono<Duration> ttl(String key){
        return redisTemplate.getExpire(key);
    }

    public static Flux<String> keys(String pattern){
        return redisTemplate.keys(pattern);
    }

    public static Mono<Boolean> expire(String key, Duration duration){
       return redisTemplate.expire(key,duration);
    }

    public static Mono<Boolean> expireAt(String key, Instant expireAt){
        return redisTemplate.expireAt(key,expireAt);
    }

    public static Mono<DataType> type(String key){
        return redisTemplate.type(key);
    }

    public static Mono<Long> del(String key){
        return redisTemplate.delete(key);
    }

    public static Mono<Long> unlink(String key){
        return redisTemplate.unlink(key);
    }


   /****************************** value操作工具方法 ******************************************************/
   public static Mono<Boolean> set(String key, String value){
       return redisTemplate.opsForValue().set(key, value);
   }

   public static Mono<Boolean> set(String key, String value, long timeout){
        return set(key,value, Duration.ofSeconds(timeout));
    }

   public static Mono<Boolean> set(String key, String value, Duration duration){
        return redisTemplate.opsForValue().set(key, value,duration);
    }


    public static Mono<Boolean> setex(String key, String value){
        return redisTemplate.opsForValue().setIfPresent(key, value);
    }

    public static Mono<Boolean> setex(String key, String value, long timeout){
        return setex(key, value,Duration.ofSeconds(timeout));
    }

    public static Mono<Boolean> setex(String key, String value, Duration duration){
        return redisTemplate.opsForValue().setIfPresent(key, value,duration);
    }

    public static Mono<Boolean> setnx(String key, String value){
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public static Mono<Boolean> setnx(String key, String value, long timeout){
        return setnx(key, value,Duration.ofSeconds(timeout));
    }

    public static Mono<Boolean> setnx(String key, String value, Duration duration){
        return redisTemplate.opsForValue().setIfAbsent(key, value,duration);
    }

    public static Mono<String> get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /***************************** set 操作工具方法 ****************************************/


    public static Mono<Long> sadd(String key, String...value){
      return redisTemplate.opsForSet().add(key,value);
    }


    public static Mono<Long> srem(String key, Object... value){
        return redisTemplate.opsForSet().remove(key,value);
    }

    public static Mono<Boolean> sismember(String key, Object value){
        return redisTemplate.opsForSet().isMember(key,value);
    }


    public static Flux<String> smembers(String key){
        return redisTemplate.opsForSet().members(key);
    }


    public static Mono<Long> scard(String key){
        return redisTemplate.opsForSet().size(key);
    }

    public static Flux<String> sunion(String key, String otherKey){
        return redisTemplate.opsForSet().union(key, otherKey);
    }

    public static Flux<String> sunion(String key, Collection<String> otherKeys){
        return redisTemplate.opsForSet().union(key,otherKeys);
    }

    public static Mono<Long> sunionstore(String key, String otherKey, String destKey){
        return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    public static Mono<Long> sunionstore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForSet().unionAndStore(key,otherKeys,destKey);
    }

    public static Flux<String> sdiff(String key, String otherKey){
        return redisTemplate.opsForSet().difference(key, otherKey);
    }


    public static Flux<String> sdiff(String key, Collection<String> otherKeys){
        return redisTemplate.opsForSet().difference(key,otherKeys);
    }

    public static Flux<String> sinter(String key, String otherKey){
        return redisTemplate.opsForSet().intersect(key,otherKey);
    }

    public static Flux<String> sinter(String key, Collection<String> otherKeys){
        return redisTemplate.opsForSet().intersect(key,otherKeys);
    }

    public static Mono<Long> sinterstore(String key, String otherKey,String destKey){
        return redisTemplate.opsForSet().intersectAndStore(key,otherKey,destKey);
    }

    public static Mono<Long> sinterstore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForSet().intersectAndStore(key,otherKeys,destKey);
    }

    /***********************************哈希表操作*************************************/

    public static Mono<Boolean> hexists(String key,String hashKey)  {
        return redisTemplate.opsForHash().hasKey(key,hashKey);
    }

    public static Mono<Boolean> hset(String key, String hashKey,String hashValue) {
        return redisTemplate.opsForHash().put(key,hashKey,hashValue);
    }

    public static Mono<Boolean> hsetnx(String key, String hashKey,String hashValue) {
        return redisTemplate.opsForHash().putIfAbsent(key,hashKey,hashValue);
    }

    public static Mono<Boolean> hmset(String key, Map<String,String> hashMap) {
        return redisTemplate.opsForHash().putAll(key,hashMap);
    }

    public static Mono<String> hget(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key,hashKey).map(Object::toString);
    }

    public static Mono<List<String>> hmget(String key, Collection<Object> hashKeys) {
       return redisTemplate.opsForHash().multiGet(key, hashKeys).map(objects -> objects.stream().map(Object::toString).collect(Collectors.toList()));
    }

    public static Mono<Map<String,String>> entries(String key) {
        return redisTemplate.opsForHash().entries(key).collect(Collectors.toMap(Object::toString,Object::toString));
    }
}
