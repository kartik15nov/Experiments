package service;

import model.Person;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface CardService {
    void saveCard(Person cardHolder);

    Optional<Person> getCardHolder(String id);

    boolean areBloodRelated(Person c1, Person c2) throws ExecutionException, InterruptedException;
}
