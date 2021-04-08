package com.springbatch.faturacartaocredito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorConfig {

    private static final int MAX_NUMBER_OF_THREADS = 4;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(MAX_NUMBER_OF_THREADS);
        executor.setQueueCapacity(MAX_NUMBER_OF_THREADS);
        executor.setMaxPoolSize(MAX_NUMBER_OF_THREADS);
        return executor;
    }
}