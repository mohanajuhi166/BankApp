package test;

import main.Account;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.Logic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class AccountTest {

    //This test adding an account with an id that another initialized account have.
    @Test
    void accountAlreadyInitialized() {
        Logic logic = new Logic(true);
        AtomicReference<Account> accountAtomicReference = new AtomicReference<>();
        Account account = new Account(new AtomicBoolean(true),
                new AtomicInteger(100));
        logic.createNewAccount(accountAtomicReference, account);

        Account alreadyInitialized = logic.getAccount(accountAtomicReference);
        Assert.assertNotNull(alreadyInitialized);
    }

    //This test checking if the card is active
    @Test
    void cartNotActive() {
        Logic logic = new Logic(true);
        Account account = new Account(new AtomicBoolean(false),
                new AtomicInteger(100));
        boolean isActive = logic.checkIfCardActive(account);
        Assert.assertEquals(false, isActive);
    }

}
