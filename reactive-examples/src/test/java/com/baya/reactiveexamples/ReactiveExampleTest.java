package com.baya.reactiveexamples;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class ReactiveExampleTest {

    Person fiona = new Person("Fiona", "Glenanne");
    Person michael = new Person("Michael", "Weston");
    Person sam = new Person("Sam", "Axe");
    Person jesse = new Person("Jesse", "Porter");

    @Test
    void monoTest() {
        Mono<Person> personMono = Mono.just(fiona);

        Person person = personMono.block();

        assert person != null;
        log.info(person.sayMyName());
    }

    @Test
    void monoTransform() {
        Mono<Person> personMono = Mono.just(fiona);

        PersonCommand command = personMono.map(PersonCommand::new).block();

        assert command != null;
        log.info(command.sayMyName());
    }

    @Test
    void monoFilterThatReturnsNullPointerException() {
        Mono<Person> personMono = Mono.just(sam);

        Person samAxe = personMono.filter(person1 -> person1.getFirstName().equalsIgnoreCase("foo")).block();

        assertThrows(NullPointerException.class, () -> {
            log.info(samAxe.sayMyName());
        });
    }

    @Test
    void testFlux() {
        Flux<Person> personFlux = Flux.just(michael, fiona, sam, jesse);

        personFlux.subscribe(person -> log.info(person.sayMyName()));
    }

    @Test
    public void fluxTestFilter() {

        Flux<Person> personFlux = Flux.just(michael, fiona, sam, jesse);

        personFlux.filter(person -> person.getFirstName().equals(fiona.getFirstName()))
                .subscribe(person -> log.info(person.sayMyName()));

    }

    @Test
    public void fluxTestDelayNoOutput() throws Exception {

        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .subscribe(person -> log.info(person.sayMyName()));

    }

    @Test
    public void fluxTestDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayMyName()));

        countDownLatch.await();
    }

    @Test
    public void fluxTestFilterDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .filter(person -> person.getFirstName().contains("i"))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayMyName()));

        countDownLatch.await();
    }
}