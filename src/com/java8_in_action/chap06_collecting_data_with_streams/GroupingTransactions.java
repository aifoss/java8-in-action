package com.java8_in_action.chap06_collecting_data_with_streams;

import org.java8.java8_in_action.common.Transaction2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class GroupingTransactions {

    public static void groupImperatively() {
        Map<Transaction2.Currency, List<Transaction2>> transactionsByCurrencies = new HashMap<>();

        for (Transaction2 transaction : Transaction2.transactions) {
            Transaction2.Currency currency = transaction.getCurrency();
            List<Transaction2> transactionsForCurrency = transactionsByCurrencies.get(currency);

            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }

            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
        System.out.println();
    }

    public static void groupFunctionally() {
        Map<Transaction2.Currency, List<Transaction2>> transactionsByCurrencies =
                Transaction2.transactions.stream()
                        .collect(Collectors.groupingBy(Transaction2::getCurrency));

        System.out.println(transactionsByCurrencies);
        System.out.println();
    }


    public static void main(String... args) {
        groupImperatively();
        groupFunctionally();
    }

}
