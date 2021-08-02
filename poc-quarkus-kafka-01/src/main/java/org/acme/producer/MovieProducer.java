package org.acme.producer;

import io.smallrye.reactive.messaging.kafka.Record;
import org.acme.domain.MovieDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped
public class MovieProducer {

    @Inject
    @Channel("movies-out")
    Emitter<Record<Integer, String>> emitter;

    public void sendMovieToKafka(MovieDTO movie) {
        emitter.send(Record.of(movie.year, movie.title));
    }

}
