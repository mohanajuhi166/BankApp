package rules;

import main.Account;
import main.Transaction;

import java.util.concurrent.atomic.AtomicReference;

public class AvailableLimitRule implements Rules {
    @Override
    public boolean applyRule(AtomicReference<Account> accountAtomicReference, String ruleString,
                             Transaction transaction) {
        Account singularAccount = accountAtomicReference.get();

        int newLimit =
                getNewAvailableLimit
                        (singularAccount.getAvailableLimit().get(),
                                transaction);
        if (newLimit > 0) {

            //atomic
            singularAccount.getAvailableLimit().getAndSet(newLimit);
            addTransactionToAccount(accountAtomicReference, transaction);
            return true;
        } else {
            return false;
        }
    }

    public void addTransactionToAccount(final AtomicReference<Account> accountAtomicReference,
                                        final Transaction transaction) {
        accountAtomicReference.get().addTransaction(transaction);
    }

    public int getNewAvailableLimit(final int balance,
                                    final Transaction transaction) {
        int newLimit = balance - transaction.getAmount().get();
        return newLimit;
    }

    @Override
    public String getRuleString() {
        return Constants.INSUFF_LIMIT;
    }
}
