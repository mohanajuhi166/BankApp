package main;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

//Class that represents the account.

@Getter
@Setter
@NoArgsConstructor
public class Account {

    @SerializedName(value = "activeCard", alternate = {"active-card"})
    private AtomicBoolean activeCard;
    @SerializedName(value = "availableLimit", alternate = {"available-limit"})
    private AtomicInteger availableLimit;
    //The transient is included in order to avoid this attribute to persist in the JSON parse from GSON.
    private transient CopyOnWriteArrayList<Transaction> transactions = new CopyOnWriteArrayList<>();

    public Account(AtomicBoolean activeCard, AtomicInteger availableLimit) {
        this.activeCard = activeCard;
        this.availableLimit = availableLimit;
        transactions = new CopyOnWriteArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

}
