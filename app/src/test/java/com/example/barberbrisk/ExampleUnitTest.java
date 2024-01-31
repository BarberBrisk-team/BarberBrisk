package com.example.barberbrisk;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.builders.JUnit4Builder;

import static org.junit.Assert.*;

import com.google.firebase.FirebaseApp;

import java.io.File;

import com.example.barberbrisk.objects.DataBase;

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
        DataBase.NewBarber("Dana", "Shapira","050617555", new File(""));
        assertTrue(true);
    }

}