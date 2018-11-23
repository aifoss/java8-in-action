package com.java8_in_action.chap05_working_with_streams;

import com.java8_in_action.common.Trader;
import com.java8_in_action.common.Transaction1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction1> transactions = Arrays.asList(
                new Transaction1(brian, 2011, 300),
                new Transaction1(raoul, 2012, 1000),
                new Transaction1(raoul, 2011, 400),
                new Transaction1(mario, 2012, 710),
                new Transaction1(mario, 2012, 700),
                new Transaction1(alan, 2012, 950)
        );

        // query 1: find all transactions from year 2011 and sort them by value
        List<Transaction1> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction1::getValue))
                .collect(Collectors.toList());

        System.out.println(tr2011);

        // query 2: what are the unique cities where the traders work?
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(cities);

        // query 3: find all traders from Cambridge and sort them by name
        List<Trader> traders = transactions.stream()
                .map(Transaction1::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println(traders);

        // query 4: return a string of all trader names sorted alphabetically
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        System.out.println(traderStr);

        // query 5: are there any trader based in Milan?
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        System.out.println(milanBased);

        // query 6: update all transactions so that traders from Milan are set to Cambridge
        transactions.stream()
                .map(Transaction1::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .forEach(trader -> trader.setCity("Cambridge"));

        System.out.println(transactions);

        // query 7: what's the highest value in all transactions?
        int highestValue = transactions.stream()
                .map(Transaction1::getValue)
                .reduce(0, Integer::max);

        System.out.println(highestValue);
    }

}
