package service;

import model.Person;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CardServiceImpl implements CardService {

    private final HashMap<String, Person> pMap;
    ExecutorService executor = Executors.newFixedThreadPool(2);
    private volatile boolean status;

    public CardServiceImpl(HashMap<String, Person> cardHolderHashMap) {
        this.pMap = cardHolderHashMap;
    }


    @Override
    public void saveCard(Person cardHolder) {
        if (cardHolder != null && cardHolder.getId() != null) {
            pMap.put(cardHolder.getId(), cardHolder);
        }
    }

    @Override
    public Optional<Person> getCardHolder(String id) {
        return Optional.ofNullable(pMap.get(id));
    }

    @Override
    public boolean areBloodRelated(Person p1, Person p2) throws ExecutionException, InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);
        this.status = false;

        Map<String, Person> tmpMap = Collections.synchronizedMap(new HashMap<>());

        executor.submit(() -> {
            getParents(p1, tmpMap, p1.getId());
            latch.countDown();
        });

        executor.submit(() -> {
            getParents(p2, tmpMap, p2.getId());
            latch.countDown();
        });

        latch.await();
        executor.shutdownNow();
        return status;
    }

    private void getParents(Person p, Map<String, Person> tmpMap, String id) {

        if (p != null && id != null) {
            if (!tmpMap.containsKey(p.getId())) {
                tmpMap.put(p.getId(), p);

                getParents(pMap.get(p.getFatherId()), tmpMap, p.getFatherId());
                getParents(pMap.get(p.getMotherId()), tmpMap, p.getMotherId());
            } else {
                synchronized (this) {
                    this.status = true;
                }
            }
        } else {
            if (tmpMap.containsKey(id)) {
                synchronized (this) {
                    this.status = true;
                }
            }
            if (id != null)
                tmpMap.put(id, null);
        }
    }
}
