package org.jrivets.connector.yodlee.transaction;

/**
 * @author kbabushkin
 * @since 11/24/14
 */

public enum TransactionContainerType {
    ALL("all"), BANK("bank"), CREDIT_CARD("credit"), INVESTMENT("stocks"),
    INSURANCE("insurance"), LOAN("loans"), REWARD_PROGRAM("miles"), MORTGAGE("mortgage");

    private final String yName;

    TransactionContainerType(String yName) {
        this.yName = yName;
    }

    public String getYName() {
        return yName;
    }
}
