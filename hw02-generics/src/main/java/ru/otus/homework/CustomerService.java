package ru.otus.homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class CustomerService {

    private final TreeMap<Customer, String> customerStringNavigableMap;

    public CustomerService() {
        customerStringNavigableMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    }

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> customerStringEntry = customerStringNavigableMap.firstEntry();
        return copy(customerStringEntry);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return copy(customerStringNavigableMap.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        customerStringNavigableMap.put(customer, data);
    }

    private Map.Entry<Customer, String> copy(Map.Entry<Customer, String> customerStringEntry) {
        if (customerStringEntry != null) {
            Customer customer = customerStringEntry.getKey();
            Customer newCustomer = new Customer(customer.getId(), customer.getName(), customer.getScores());
            customerStringEntry = Map.entry(newCustomer, customerStringEntry.getValue());
        }
        return customerStringEntry;
    }
}
