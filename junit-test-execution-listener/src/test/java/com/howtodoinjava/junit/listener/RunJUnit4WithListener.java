package com.howtodoinjava.junit.listener;

import com.howtodoinjava.junit.booking.BookingJUnit4Test;
import org.junit.runner.JUnitCore;

public class RunJUnit4WithListener {

    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new LoggingRunListener());
        core.run(BookingJUnit4Test.class);
    }
}
