package com.workforce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * private final Executor ioBoundTaskExe;
 *     private final Executor cpuBoundTaskExe;
 *
 *     public OrderServiceApp(@Qualifier("ioExecutor") Executor ioBoundTaskExe,
 *                            @Qualifier("cpuExecutor") Executor cpuBoundTaskExe) {
 *         this.ioBoundTaskExe = ioBoundTaskExe;
 *         this.cpuBoundTaskExe = cpuBoundTaskExe;
 *     }
 */
@Configuration
public class ExecutorConfig {

    @Bean(name = "ioExecutor")
    public Executor ioBoundTaskExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean("cpuExecutor")
    public Executor cpuExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int cpus = Runtime.getRuntime().availableProcessors();
        int corePoolSize = cpus / 2;
        executor.setCorePoolSize(corePoolSize); //Minimum pool size = corePoolSize.
        executor.setMaxPoolSize(cpus - 1);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("CPU-");
        executor.initialize();
        return executor;
    }



//    @Value("${cpu.pool.size:#{T(java.lang.Runtime).getRuntime().availableProcessors()}}")
//    private int poolSize;
//
//    @Value("${cpu.queue.size:0}")
//    private int queueSize;

//    @Bean(name = "cpuExecutor")
//    public ExecutorService cpuBoundTaskExecutor() {
//        BlockingQueue<Runnable> workQueue = (queueSize > 0)
//                ? new ArrayBlockingQueue<>(queueSize)
//                : new SynchronousQueue<>();
//
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                poolSize,                          // corePoolSize
//                poolSize,                          // maximumPoolSize
//                60L, TimeUnit.SECONDS,             // keep-alive for excess threads
//                workQueue,
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy() // rejection policy
//        );
//
//        executor.prestartAllCoreThreads();  // optionally warm up threads
//        return executor;
//    }
}
