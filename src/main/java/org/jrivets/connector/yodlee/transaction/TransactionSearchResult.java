package org.jrivets.connector.yodlee.transaction;

/**
 * @author kbabushkin
 * @since 11/24/14
 */

public class TransactionSearchResult {

    class SearchIdentifier {
        private String identifier;
    }

    private int numberOfHits;
    private SearchIdentifier searchIdentifier;

    TransactionSearchResult() {
    }

    public int getNumberOfHits() {
        return numberOfHits;
    }

    public String getSearchIdentifier() {
        return searchIdentifier.identifier;
    }
}
