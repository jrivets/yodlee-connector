package org.jrivets.connector.yodlee.transaction;

/**
 * @author kbabushkin
 * @since 11/24/14
 */

public class TransactionSearchRequest {

    private String cobSessionToken;
    private String userSessionToken;

    private TransactionContainerType containerType;
    private TransactionSplitType transactionSplitType;

    private int higherFetchLimit;
    private int lowerFetchLimit;

    private int rangeStartNumber;
    private int rangeEndNumber;

    private int searchClientId;
    private String searchClientName;

    private boolean ignoreUserInput;

    public TransactionSearchRequest(String cobSessionToken, String userSessionToken) {
        this.cobSessionToken = cobSessionToken;
        this.userSessionToken = userSessionToken;
    }

    public TransactionSearchRequest withContainerType(TransactionContainerType containerType) {
        this.containerType = containerType;
        return this;
    }

    public TransactionSearchRequest withTransactionSplitType(TransactionSplitType transactionSplitType) {
        this.transactionSplitType = transactionSplitType;
        return this;
    }

    public TransactionSearchRequest withHigherFetchLimit(int higherFetchLimit) {
        this.higherFetchLimit = higherFetchLimit;
        return this;
    }

    public TransactionSearchRequest withLowerFetchLimit(int lowerFetchLimit) {
        this.lowerFetchLimit = lowerFetchLimit;
        return this;
    }

    public TransactionSearchRequest withRangeStartNumber(int rangeStartNumber) {
        this.rangeStartNumber = rangeStartNumber;
        return this;
    }

    public TransactionSearchRequest withRangeEndNumber(int rangeEndNumber) {
        this.rangeEndNumber = rangeEndNumber;
        return this;
    }

    public TransactionSearchRequest withSearchClientId(int searchClientId) {
        this.searchClientId = searchClientId;
        return this;
    }

    public TransactionSearchRequest withSearchClientName(String searchClientName) {
        this.searchClientName = searchClientName;
        return this;
    }

    public TransactionSearchRequest withIgnoreUserInput(boolean ignoreUserInput) {
        this.ignoreUserInput = ignoreUserInput;
        return this;
    }

    public String getCobSessionToken() {
        return cobSessionToken;
    }

    public String getUserSessionToken() {
        return userSessionToken;
    }

    public TransactionContainerType getContainerType() {
        return containerType;
    }

    public TransactionSplitType getTransactionSplitType() {
        return transactionSplitType;
    }

    public int getHigherFetchLimit() {
        return higherFetchLimit;
    }

    public int getLowerFetchLimit() {
        return lowerFetchLimit;
    }

    public int getRangeStartNumber() {
        return rangeStartNumber;
    }

    public int getRangeEndNumber() {
        return rangeEndNumber;
    }

    public int getSearchClientId() {
        return searchClientId;
    }

    public String getSearchClientName() {
        return searchClientName;
    }

    public boolean isIgnoreUserInput() {
        return ignoreUserInput;
    }
}
