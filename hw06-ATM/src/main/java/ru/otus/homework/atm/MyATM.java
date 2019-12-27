package ru.otus.homework.atm;

import ru.otus.homework.CashOutException;
import ru.otus.homework.bills.Bill;
import ru.otus.homework.cassettes.Cassette;
import ru.otus.homework.cassettes.EmptyCassetteException;
import ru.otus.homework.dispensers.Dispenser;

import java.util.Map;

public class MyATM implements ATM {

    private Dispenser dispenser;

    public MyATM(Dispenser dispenser) {
        this.dispenser = dispenser;
    }

    @Override
    public int cashIn(Map<Bill, Integer> bills) {
        return dispenser.putIntoBuckets(bills);
    }

    @Override
    public Map<Bill, Integer> cashOut(int sum) throws CashOutException, EmptyCassetteException {
        return dispenser.getBills(sum);
    }

    @Override
    public int getBalance() {
        return dispenser.getBalance();
    }

    @Override
    public Dispenser getDispenser() {
        return dispenser;
    }

    @Override
    public void setDispenser(Dispenser dispenser) {
        this.dispenser = dispenser;
    }

    @Override
    public Map<Bill, Cassette> getStartState() {
        return dispenser.getStartState();
    }

    @Override
    public void updateStartState() {
        dispenser.updateStartState();
    }
}
