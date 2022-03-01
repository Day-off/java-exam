package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;

public interface Fixable {

    /***
     * throw error
     */
    void fix() throws CannotFixException;

    /***
     * count time
     */
    int getTimesFixed();
}
