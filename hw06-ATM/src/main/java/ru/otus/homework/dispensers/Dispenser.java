package ru.otus.homework.dispensers;

import ru.otus.homework.CashOutException;
import ru.otus.homework.bills.Bill;
import ru.otus.homework.cassettes.Cassette;
import ru.otus.homework.cassettes.EmptyCassetteException;

import java.util.Map;

public interface Dispenser {
    Map<Bill, Cassette> getStartState();

    void updateStartState();

    int putIntoBuckets(Map<Bill, Integer> bills);

    Map<Bill, Integer> getBills(int sum) throws CashOutException, EmptyCassetteException;

    int getBalance();

    Map<Bill, Cassette> getCassettes();
}
