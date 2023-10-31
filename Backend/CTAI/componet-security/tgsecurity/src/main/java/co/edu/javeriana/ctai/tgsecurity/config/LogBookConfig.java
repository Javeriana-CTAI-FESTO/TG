package co.edu.javeriana.ctai.tgsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.DefaultSink;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.StreamHttpLogWriter;
import org.zalando.logbook.json.JsonHttpLogFormatter;

import static org.zalando.logbook.json.JsonPathBodyFilters.jsonPath;

@Configuration
public class LogBookConfig {

    @Bean
    public Logbook logbook() {
        return Logbook.builder()

                .bodyFilter(jsonPath("$.token").replace("xxxx"))
//
                .sink(new DefaultSink(
                        new PrincipalHttpLogFormatter(new JsonHttpLogFormatter()),
                        new StreamHttpLogWriter()
                ))
                .build();
    }
}
