package com.yupi.hewoj.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yupi.hewoj.common.RedisData;
import com.yupi.hewoj.constant.RedisContant;
import com.yupi.hewoj.model.entity.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.yupi.hewoj.constant.RedisContant.CACHE_NULL_TTL;


/**
 * 封装一个工具类，用于插叙redis即处理redis未命中的各种问题，也用于设置值到redis
 */
@Component
@Slf4j
public class CacheClient {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);


    /**
     * 根据指定的key查询缓存，并反序列化为指定类型，利用缓存空值的方式解决缓存穿透问题，使用互斥锁解决缓存击穿问题
     * @param keyPrefix 缓存业务前缀
     * @param id 缓存的id
     * @param type 缓存的类型
     * @param dbFallback 缓存中没有数据，查询数据库的函数
     * @param time 设置重新缓存到redis的时间
     * @param unit 时间单位
     * @return
     * @param <R>
     * @param <T>
     */
    public <R,T> R queryWithPassThrough(String keyPrefix,T id,Class<R> type,Function<T,R> dbFallback,Long time,TimeUnit unit){
        String key=keyPrefix+id;
       String json=stringRedisTemplate.opsForValue().get(key);
       //查找到后反序列华为执行类型，然后返回
       if(StrUtil.isNotBlank(json)){
           R r=JSONUtil.toBean(json,type);
           return r;
       }
       //缓存的是空值""，为防止缓存穿透，直接返回空值，而不去查数据库
       else if(json!=null){
           return null;
       }
       //缓存未命中,查数据库，为保证在高并发下缓存中没有只查一次数据库（你面缓存击穿问题），使用互斥锁
       else{
           String lockKey= RedisContant.REDIS_lOCK_QUESTION_KEY+id;

          boolean islock= getLock(lockKey);
          //已经有线程出发了线程重建，自己休眠一会，再去查缓存
           try {
               if(islock==false){
                   Thread.sleep(200);
                   queryWithPassThrough(keyPrefix,id,type,dbFallback,time,unit);
               }
               else{
                   //查数据库
                   R r=dbFallback.apply(id);
                   if(r==null){
                       //防止缓存穿透设为""
                       stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL,TimeUnit.MINUTES);
                       return null;
                   }
                   else{
                       stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(r),time,unit);
                       return r;
                   }
               }
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           } finally {
               unLock(lockKey);
           }
       }
        return null;
    }


    public <R, ID> R queryWithLogicalExpire(String keyPrefix, ID id, Function<ID, R> dbFallback, Class<R> type,Long time,TimeUnit unit) {
        String key= keyPrefix+id;
        String json=stringRedisTemplate.opsForValue().get(key);
        //未命中直接返回null
        if(json==null) return null;
        //命中
        RedisData redisData=JSONUtil.toBean(json,RedisData.class);
        R r = JSONUtil.toBean((JSONObject) redisData.getData(), type);
        LocalDateTime localDateTime=redisData.getExpireTime();
        //没有过期
        if(localDateTime.isAfter(LocalDateTime.now())){
            return r;
        }
        //设置的逻辑超时时间过期
        String lockKey = RedisContant.REDIS_lOCK_QUESTION_KEY+ id;
        boolean islock=getLock(lockKey);
        //互斥锁获取失败,直接返回旧的数据
        if(islock==false) return r;
        //互斥锁获取成功
        CACHE_REBUILD_EXECUTOR.submit(()->{
            try {
                //缓存重建
                R newr=dbFallback.apply(id);
                this.setWithLogicalExpire(key,newr,time,unit);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                unLock(lockKey);
            }
        });
        return r;
    }

    /**
     * 将论及过期时间一同存入redis
     * @param key
     * @param value
     * @param time
     * @param unit
     */

    public  void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit){
       RedisData redisData=new RedisData();
       redisData.setData(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
       redisData.setData(value);
       stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(redisData));
    }
    public void set(String key, Object value, Long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, timeUnit);
    }


    /**
     * 拿到锁
     * @param key
     * @return
     */
    private boolean getLock(String key){
       Boolean flag= stringRedisTemplate.opsForValue().setIfAbsent(key,"1",10,TimeUnit.SECONDS);
       return BooleanUtil.isTrue(flag);
    }

    /**
     * 释放锁
     * @param key
     * @return
     */
    private void unLock(String key){
        stringRedisTemplate.delete(key);
    }
}
