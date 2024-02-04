package com.example.barberbrisk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.barberbrisk.DB.DataBase;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


//    public final SystemParameterRule systemOutRule = new SystemOutRule().enableLog();



    @Test
    public void givenSystemOutRule_whenInvokePrintln_thenLogSuccess() {
        DataBase.NewBarber("1", "Dana", "Shapira","050617555", "Barber1@gmail.com");
        assertTrue(true);
    }

}