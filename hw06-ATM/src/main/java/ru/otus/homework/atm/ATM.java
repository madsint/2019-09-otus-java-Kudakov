package ru.otus.homework.atm;

import ru.otus.homework.CashOutException;
import ru.otus.homework.bills.Bill;
import ru.otus.homework.cassettes.Cassette;
import ru.otus.homework.cassettes.EmptyCassetteException;
import ru.otus.homework.dispensers.Dispenser;

import java.util.Map;

public interface ATM {

    int cashIn(Map<Bill, Integer> bills);

    Map<Bill, Integer> cashOut(int money) throws CashOutException, EmptyCassetteException;

    int getBalance();

    Dispenser getDispenser();

    void setDispenser(Dispenser dispenser);

    Map<Bill, Cassette> getStartState();

    void updateStartState();
}
