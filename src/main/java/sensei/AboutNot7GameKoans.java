package sensei;

import engine.FormatParam;
import engine.Koan;
import engine.KoanResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import koans.english.AboutNot7Game;

import static engine.Assertions.*;
import engine.Local;
import engine.Locale;
import engine.Printer;
import engine.ResToIntFunction;

import static engine.Localizable.*;
import static sensei.Texts.*;

public class AboutNot7GameKoans {
    private static final Local<Class<?>> CLASS =
        localClass(AboutNot7Game.class)
        .fr(AboutNot7Game.class); // TODO

        public static final List<Koan> koans = List.of(
        // new Koan(CLASS, "die6")
        //     .whenCalling()
        //     .then(
        //         assertResultWithRandomEquals(rand -> die(rand))
        //     ),
        // new Koan(CLASS, "die6Sum", int.class)
        //     .whenCallingWith(2)
        //     .then(
        //         assertResultWithRandomEquals(2, rand -> dieSum(rand))
        //     )
        //     .whenCallingWith(3)
        //     .then(
        //         assertResultWithRandomEquals(3, rand -> dieSum(rand))
        //     ),
        new Koan(CLASS, "askQuestion", String.class)
            .useConsole()
            .whenCallingWith(DO_YOU_WANT_TO_CONTINUE)
            .withStdInInputs(List.of(Y))
            .then(
                assertOutEquals(0, DO_YOU_WANT_TO_CONTINUE),
                assertResultEquals(true),
                assertNoLineInStdOutAfter(1)
            )
            .whenCallingWith(DO_YOU_WANT_TO_CONTINUE)
            .withStdInInputs(List.of(N))
            .then(
                assertOutEquals(0, DO_YOU_WANT_TO_CONTINUE),
                assertResultEquals(false),
                assertNoLineInStdOutAfter(1)
            ),
        new Koan(CLASS, "throwDice")
            .useConsole()
            .whenCalling()
            .then(
                assertResultWithRandomEquals(2, expectedDieSumResult(1)),
                assertOutEquals(0, YOU_THREW, expectedDieResult(0), expectedDieResult(1)),
                assertNoLineInStdOutAfter(1)
            ),
        new Koan(CLASS, "gameRoundv1")
            .useConsole()
            .whenCalling()
            .withStdInInputs(List.of(N))
            .then(
                AboutNot7GameKoans::gameRoundv1Assertions
            )
            .whenCalling()
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                AboutNot7GameKoans::gameRoundv1Assertions
            ),
        new Koan(CLASS, "gameRoundv2")
            .useConsole()
            .whenCalling()
            .withStdInInputs(List.of(N))
            .then(
                AboutNot7GameKoans::gameRoundv2Assertions
            )
            .whenCalling()
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                AboutNot7GameKoans::gameRoundv2Assertions
            ),
        new Koan(CLASS, "gameRoundv3")
            .useConsole()
            .whenCalling()
            .withStdInInputs(List.of(N))
            .then(
                AboutNot7GameKoans::gameRoundv3Assertions
            )
            .whenCalling()
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                AboutNot7GameKoans::gameRoundv3Assertions
            )
    );

    private static boolean gameRoundv1Assertions(Locale l, Printer p, KoanResult res) {
        int loopCount = 0;
        boolean wantsToContinue = true;
        var asserted = true;
        while (wantsToContinue) {
            int dieOffset = 2 * loopCount;
            asserted = res.executeAssertions(l, p, 
                assertOutEquals(loopCount * 2, YOU_THREW, expectedDieResult(dieOffset), expectedDieResult(dieOffset + 1)),
                assertOutEquals(loopCount * 2 + 1, DO_YOU_WANT_TO_THROW_AGAIN)
            );
            if (!asserted) {
                return false;
            }
            Optional<String> answer = res.inputLine(loopCount);
            wantsToContinue = answer.isPresent() && answer.get().trim().equals(Y.get(l));
            loopCount++;
        }
        asserted = res.executeAssertions(l, p, 
            assertNoLineInStdOutAfter(2 * loopCount)
        );
        if (!asserted) {
            return false;
        }
        return true;
    }

    private static boolean gameRoundv2Assertions(Locale l, Printer p, KoanResult res) {
        int loopCount = 0;
        boolean wantsToContinue = true;
        var asserted = true;

        while (wantsToContinue) {
            int dieOffset = 2 * loopCount;

            asserted = res.executeAssertions(l, p, 
                assertOutEquals(loopCount * 3, YOU_THREW, expectedDieResult(dieOffset), expectedDieResult(dieOffset + 1)),
                assertOutEquals(loopCount * 3 + 1, YOUR_RESULT_SO_FAR, expectedDieSum(loopCount + 1)),
                assertOutEquals(loopCount * 3 + 2, DO_YOU_WANT_TO_THROW_AGAIN)
            );
            if (!asserted) {
                return false;
            }

            Optional<String> answer = res.inputLine(loopCount);
            wantsToContinue = answer.isPresent() && answer.get().trim().equals(Y.get(l));
            loopCount++;
        }

        asserted = res.executeAssertions(l, p, 
            assertNoLineInStdOutAfter(3 * loopCount)
        );
        if (!asserted) {
            return false;
        }

        return true;
    }

    private static boolean gameRoundv3Assertions(Locale l, Printer p, KoanResult res) {
        int loopCount = 0;
        boolean wantsToContinue = true;
        var asserted = true;

        while (wantsToContinue) {
            int dieOffset = 2 * loopCount;

            asserted = res.executeAssertions(l, p, 
                assertOutEquals(loopCount * 3, YOU_THREW, expectedDieResult(dieOffset), expectedDieResult(dieOffset + 1)),
                assertOutEquals(loopCount * 3 + 1, YOUR_RESULT_SO_FAR, expectedDieSum(loopCount + 1)),
                assertOutEquals(loopCount * 3 + 2, DO_YOU_WANT_TO_THROW_AGAIN)
            );
            if (!asserted) {
                return false;
            }

            Optional<String> answer = res.inputLine(loopCount);
            wantsToContinue = answer.isPresent() && answer.get().trim().equals(Y.get(l));
            loopCount++;
        }

        asserted = res.executeAssertions(l, p, 
            assertNoLineInStdOutAfter(3 * loopCount),
            assertResultWithRandomEquals(2 * loopCount, expectedDieSumResult(loopCount))
        );
        if (!asserted) {
            return false;
        }

        return true;
    }

    private static int die(double rand) {
        return (int)Math.ceil(6 * rand);
    }

    private static ResToIntFunction expectedDieSumResult(int throwCount) {
        return res ->Arrays.stream(res.randomNumbers(throwCount*2)).mapToInt(rn -> die(rn)).sum();
    }

    private static FormatParam expectedDieSum(int throwCount) {
        return res -> String.valueOf(expectedDieSumResult(throwCount).apply(res));
    }

    private static FormatParam expectedDieResult(int dieIndex) {
        return res -> String.valueOf(die(res.randomNumber(dieIndex)));
    }
}
