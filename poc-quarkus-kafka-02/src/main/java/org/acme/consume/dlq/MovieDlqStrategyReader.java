package org.acme.consume.dlq;

import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import org.acme.consume.MovieCommonStrategyConsumer;
import org.acme.domain.MovieDTO;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MovieDlqStrategyReader {

    private final Logger log = LoggerFactory.getLogger(MovieCommonStrategyConsumer.class);

    @Incoming("movies-dlq-reader")
    public CompletionStage<Void> dead(Message<MovieDTO> rejected) {
        log.info("M=dead, I=Lendo mensage em DLQ do consumidor movies-dlq-in");
        IncomingKafkaRecordMetadata<String, String> metadata = rejected.getMetadata(IncomingKafkaRecordMetadata.class)
                .orElseThrow(() -> new IllegalArgumentException("Expected a message coming from Kafka"));
        String reason = new String(metadata.getHeaders().lastHeader("dead-letter-reason").value());
        log.info("M=dead, movieDTO={}, Mensagem de erro={}.", rejected.getPayload().toString(), reason);

        return rejected.ack();
    }
}
