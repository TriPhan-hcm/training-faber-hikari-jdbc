
package com.faber.airmgr.config;

import java.util.HashMap;
import java.util.Map;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.redisson.spring.cache.CacheConfig;

@Configuration
public class RedisConfig {
    String str = "redis://127.0.0.1:6379";
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(str);
        return Redisson.create(config);
    }
    @Bean
    public RedissonSpringCacheManager cacheManager(RedissonClient redissonClient){
        Map<String, CacheConfig> config = new HashMap<>();
        config.put("test", new CacheConfig(24*60*1000,12*60*1000));
        return new RedissonSpringCacheManager(redissonClient,config);
    }
}
