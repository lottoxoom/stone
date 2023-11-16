package com.island.stone.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 线程池配置
 * @author: LOTTO
 * @create: 2022-01-14 14:24
 * @Async失效场景：
 * 一、异步方法使用static修饰
 * 二、异步类没有使用@Component注解（或其他注解）导致spring无法扫描到异步类
 * 三、异步方法不能与异步方法在同一个类中
 * 四、类中需要使用@Autowired或@Resource等注解自动注入，不能自己手动new对象
 * 五、如果使用SpringBoot框架必须在启动类中增加@EnableAsync注解
 * 六、在Async 方法上标注@Transactional是没用的。 在Async 方法调用的方法上标注@Transactional 有效。
 * 七、调用被@Async标记的方法的调用者不能和被调用的方法在同一类中不然不会起作用！！！！！！！
 * 八、使用@Async时要求是不能有返回值的不然会报错的 因为异步要求是不关心结果的
 *
 * 线程池的配置-直接实现AsyncConfigurer接口，重写getAsyncExecutor方法即可
 **/
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    /**
     * 核心线程数（默认线程数）
     */
    private static final int corePoolSize = 10;

    /**
     * 最大线程数
     */
    private static final int maxPoolSize = 100;

    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private static final int keepAliveTime = 10;

    /**
     * 缓冲队列数
     */
    private static final int queueCapacity = 200;

    /**
     * 线程池名前缀
     */
    private static final String threadNamePrefix = "Async-Service-";

    @Bean
    public ThreadPoolTaskExecutor taskExcutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);

        //线程池拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //初始化
        executor.initialize();
        return executor;
    }


}
