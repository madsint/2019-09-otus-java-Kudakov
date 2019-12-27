package ru.otus.homework.dispensers;

import ru.otus.homework.CashOutException;
import ru.otus.homework.bills.Bill;
import ru.otus.homework.cassettes.Cassette;
import ru.otus.homework.cassettes.EmptyCassetteException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DispenserImpl implements Dispenser {
    private final Map<Bill, Cassette> cassettes;
    private final Map<Bill, Cassette> startState;

    public DispenserImpl(Map<Bill, Cassette> cassettes) {
        this.cassettes = cassettes;
        startState = new HashMap<>();
        updateStartState();
    }

    @Override
    public Map<Bill, Cassette> getStartState() {
        return startState;
    }

    @Override
    public void updateStartState() {
        cassettes.forEach(
                (bill, cassette) -> startState.put(
                        bill,
                        new Cassette(cassette.getAmount())
                )
        );
    }

    @Override
    public int putIntoBuckets(Map<Bill, Integer> bills) {
        int sum = 0;
        for (Map.Entry<Bill, Integer> entry : bills.entrySet()) {
            Bill bill = entry.getKey();
            Integer amount = entry.getValue();
            for (int i = 0; i < amount; i++) {
                cassettes.get(bill).addBill();
            }
            sum = sum + bill.getNominal() * amount;
        }
        return sum;
    }

    @Override
    public Map<Bill, Integer> getBills(int sum) throws CashOutException, EmptyCassetteException {
        Map<Bill, Integer> billsCashOut = new HashMap<>();
        TreeSet<Bill> nominates = new TreeSet<>(cassettes.keySet());
        while (nominates.size() != 0 && sum != 0) {
            Bill billMaxNominal = nominates.pollLast();
            int billsNeed = sum / billMaxNominal.getNominal();
            if (billsNeed == 0) {
                continue;
            }
            int billAmount = cassettes.get(billMaxNominal).getAmount();
            if (billsNeed - billAmount >= 0) {
                sum = sum - billAmount * billMaxNominal.getNominal();
                billsCashOut.put(
                        billMaxNominal,
                        cassettes.get(billMaxNominal).getAmount()
                );
                continue;
            }
            sum = sum - billsNeed * billMaxNominal.getNominal();
            billsCashOut.put(
                    billMaxNominal,
                    billsNeed
            );
        }

        if (sum != 0) {
            throw new CashOutException();
        }

        for (Map.Entry<Bill, Integer> entry : billsCashOut.entrySet()) {
            Bill bill = entry.getKey();
            Integer amount = entry.getValue();
            for (int i = 0; i < amount; i++) {
                cassettes.get(bill).getBill();
            }
        }

        return billsCashOut;
    }

    @Override
    public int getBalance() {
        int sum = 0;
        for (Map.Entry<Bill, Cassette> entry : cassettes.entrySet()) {
            sum = sum + entry.getKey().getNominal() * entry.getValue().getAmount();
        }
        return sum;
    }

    @Override
    public Map<Bill, Cassette> getCassettes() {
        return cassettes;
    }
}
