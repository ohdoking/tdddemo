package com.ohdoking.tddpractice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataR2dbcTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void persist(){
        Flux<Reservation> jane = this.reservationRepository
                .deleteAll()
                .thenMany(this.reservationRepository.save(new Reservation("1", "Jane")))
                .thenMany(this.reservationRepository.findAll());

        StepVerifier
                .create(jane)
                .expectNextMatches(result -> result.getId() != null && result.getName().equalsIgnoreCase("Jane"))
                .verifyComplete();

    }
}
