package ru.otus.homework;

public class Customer {
    private final long id;
    private String name;
    private long scores;

    public Customer(long id,
                    String name,
                    long scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (o instanceof Customer customer) {
            return id == customer.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
