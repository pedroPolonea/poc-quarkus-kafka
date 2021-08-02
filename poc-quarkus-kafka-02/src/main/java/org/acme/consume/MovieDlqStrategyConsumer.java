package org.acme.consume;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieDlqStrategyConsumer {
    private final Logger log = LoggerFactory.getLogger(MovieIgnoreStrategyConsumer.class);

    @Incoming("movies-dlq-in")
    //@Retry(delay = 5000, maxRetries = 5)
    //@NonBlocking
    public void receive(Record<Integer, String> record) {
        log.info("M=receive, I=ESTRATEGIA DLQ - Got a movie: {} = {} ", record.key(), record.value());

        if (record.key() == 0) {
            log.error("M=receive, E=Vixiii!");
            throw new RuntimeException("Vixi");
        }
    }
}
