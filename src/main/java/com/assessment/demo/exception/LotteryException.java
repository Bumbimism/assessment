package com.assessment.demo.exception;

public class LotteryException extends BaseException {
    public LotteryException(String message) {
        super(message);
    }

    public static LotteryException notFoundTicket() {
        return new LotteryException("Ticket Not Available.");
    }
}
