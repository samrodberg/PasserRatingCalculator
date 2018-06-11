package com.rodbergdev.passerratingcalculator;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

public class RatingCalculationTests {

    private String ratingFormat = "###.#";
    private DecimalFormat df = new DecimalFormat(ratingFormat);


    @Test
    public void testBasicRatingCalculation() {
        // Brady 2017 regular season stats
        PasserRating pr = new PasserRating(581,385,4577, 32, 8);
        String actual = df.format(pr.calculatePasserRating());
        String expected = "102.8";

        assertEquals("Basic rating calculation failed", expected, actual);

    }

    @Test
    public void testMoreCompletionsThanAttempts() {
        PasserRating pr = new PasserRating(1,55,1, 0, 0);
        String actual = df.format(pr.calculatePasserRating());
        String expected = "158.3";

        assertEquals("Greater number of completions than attempts failed", expected, actual);

    }

    @Test
    public void testMinimumRating() {
        PasserRating pr = new PasserRating(5,0,0, 0, 5);
        String actual = df.format(pr.calculatePasserRating());
        String expected = "0";

        assertEquals("Minimum rating is not 0", expected, actual);
    }

    @Test
    public void testMaximumRating() {
        PasserRating pr = new PasserRating(5,5,0, 5, 0);
        String actual = df.format(pr.calculatePasserRating());
        String expected = "158.3";

        assertEquals("Maximum rating is not 158.3", expected, actual);
    }
}