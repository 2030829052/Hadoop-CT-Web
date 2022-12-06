package org.fengyue.hadoopctweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class HadoopCtWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopCtWebApplication.class, args);
    }

    @Configuration
    public static class IndexConfig {
        @EventListener({ApplicationReadyEvent.class})
        public void applicationReadyEvent() {
            System.out.println("应用已经准备就绪 ... 启动浏览器");
            String url = "http://localhost:9090";
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
