package com.ohdoking.tddpractice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest
@Import(ReservationHttpConfiguration.class)
@RunWith(SpringRunner.class)
public class ReservationHttpTest {


    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void get(){

        Mockito.when(this.reservationRepository.findAll())
                .thenReturn(Flux.just(new Reservation(1, "Jane")));

        this.webTestClient
                .get()
                .uri("http://localhost:8080/reservations")
                .exchange()
                .expectBody()
                .jsonPath("@.[0].name").isEqualTo("Jane");
    }
}
