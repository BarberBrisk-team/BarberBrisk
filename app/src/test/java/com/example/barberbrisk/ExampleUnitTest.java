package com.example.barberbrisk;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import static org.junit.Assert.*;

import com.google.api.SystemParameterRule;

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


    @Rule
//    public final SystemParameterRule systemOutRule = new SystemOutRule().enableLog();

    private void print(String output) {
        System.out.println(output);
    }

    @Test
    public void givenSystemOutRule_whenInvokePrintln_thenLogSuccess() {
        DataBase.NewBarber("Dana", "Shapira","0503617555", new File(""));

        print("Hello World!");
//        assertEquals("Hello World!", systemOutRule.getLog().trim());
    }

}