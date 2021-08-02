package org.acme.resource;

import org.acme.domain.MovieDTO;
import org.acme.producer.MovieProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MovieResource {
    private final Logger log = LoggerFactory.getLogger(MovieResource.class);

    @Inject
    MovieProducer producer;

    @POST
    public Response send(MovieDTO movie) {
        log.info("M=send, movie={}", movie);

        producer.sendMovieToKafka(movie);
        // Return an 202 - Accepted response.
        return Response.accepted().build();
    }
}
