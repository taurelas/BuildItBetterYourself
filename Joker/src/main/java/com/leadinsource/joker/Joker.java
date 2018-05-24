package com.leadinsource.joker;

import java.util.Random;

public class Joker {
    private static final String[] JOKES = {"\"Anthony, do you think I'm a bad mother?\"\n\"My name is Paul.\"",
            "I managed to lose my rifle when I was in the army. I had to pay $855 to cover the loss.\n" +
                    "I'm starting to understand why a Navy captain always goes down with his ship.",
            "Father: \"Son, you were adopted.\"\n" +
                    "Son: \"What?! I knew it! I want to meet my biological parents!\"\n"+
                    "Father: \"We are your biological parents. Now pack up, the new ones will pick you up in 20 minutes.\""
    };

    public static String getJoke() {
        Random random = new Random();
        int index = random.nextInt(JOKES.length);

        return JOKES[index];
    }
}
