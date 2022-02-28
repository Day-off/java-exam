package ee.taltech.iti0202.mysticorbs.exceptions;

import ee.taltech.iti0202.mysticorbs.oven.Oven;

import java.awt.desktop.UserSessionEvent;

public class CannotFixException extends Exception{

    private Oven brokenOven;
    private Reason error;

    public enum Reason {
        IS_NOT_BROKEN,
        FIXED_MAXIMUM_TIMES,
        NOT_ENOUGH_RESOURCES
    }

    public CannotFixException(Oven oven, Reason reason){
        brokenOven = oven;
        error = reason;
    }

    public Oven getOven() {
        return brokenOven;
    }

    public Reason getReason() {
        return error;
    }
}
