package com.azhar.wrokforce.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

@Configuration
public class TomcatConfig implements WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> {

    private final Executor ioBoundTaskExe;

    public TomcatConfig(@Qualifier("ioExecutor") Executor ioBoundTaskExe) {
        this.ioBoundTaskExe = ioBoundTaskExe;
    }

    @Override
    public void customize(ConfigurableTomcatWebServerFactory factory) {
        factory.addProtocolHandlerCustomizers(protocolHandler ->
                protocolHandler.setExecutor(
                        this.ioBoundTaskExe
                )

        );
    }

//
//    @Override
//    public void customize(ConfigurableTomcatWebServerFactory factory) {
//        factory.addProtocolHandlerCustomizers(protocolHandler ->
//                protocolHandler.setExecutor(
//                        Executors.newVirtualThreadPerTaskExecutor()
//                )
//        );
//    }
}
