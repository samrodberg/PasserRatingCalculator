package com.rodbergdev.passerratingcalculator;

/**
 * Class designed for functions needed to calculate a Quaterbacks passer rating statistic.
 *
 * @author Samuel Rodberg
 * @version 1.0
 * @since 06-04-18
 */
public class PasserRating {

    private int attempts;
    private int completions;
    private int yards;
    private int touchdowns;
    private int interceptions;

    /**
     * Constructor for inputting statistics necessary for a passer rating to be calculated
     * @param att number of passes attempted
     * @param comp number of passes completed
     * @param yds number of yards thrown for
     * @param td number of touchdown passes thrown
     * @param ints number of interceptions thrown
     */
    public PasserRating(int att, int comp, int yds, int td, int ints) {
        this.attempts = att;
        this.completions = comp;
        this.yards = yds;
        this.touchdowns = td;
        this.interceptions = ints;
    }

    /**
     * Calculates Quarterbacks passer ratting based on NFL formula
     * @return quarterback rating
     */
    public double calculatePasserRating() {

        double attemptsDouble, completionsDouble, yardsDouble, touchdownsDouble, interceptionsDouble;

        attemptsDouble = (double) attempts;
        completionsDouble = (double) completions;
        yardsDouble = (double) yards;
        touchdownsDouble = (double) touchdowns;
        interceptionsDouble = (double) interceptions;

        double a, b, c, d, rating;

        a = ((completionsDouble / attemptsDouble) - 0.3) * 5;
        b = ((yardsDouble / attemptsDouble) - 3) * 0.25;
        c = (touchdownsDouble / attemptsDouble) * 20;
        d = 2.375 - ((interceptionsDouble / attemptsDouble) * 25);

        rating = ((a + b + c + d) / 6) * 100;

        if (rating > 158.3) {
            return 158.3;
        } else if (rating < 0.0) {
            return 0.0;
        } else {
            return rating;
        }
    }
}
