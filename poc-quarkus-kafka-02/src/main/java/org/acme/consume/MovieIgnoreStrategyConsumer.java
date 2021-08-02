package org.acme.consume;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieIgnoreStrategyConsumer {
    private final Logger log = LoggerFactory.getLogger(MovieIgnoreStrategyConsumer.class);

    @Incoming("movies-ignore-in")
    //@Retry(delay = 5000, maxRetries = 5)
   // @NonBlocking
    public void receive(Record<Integer, String> record) {
        log.info("M=receive, I=ESTRATEGIA IGNORE - Got a movie: {} = {} ", record.key(), record.value());

        if (record.key() == 0) {
            log.error("M=receive, E=Vixiii!");
            throw new RuntimeException("Vixi");
        }
    }
}
