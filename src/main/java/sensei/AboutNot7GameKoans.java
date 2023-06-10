package sensei;

import static engine.Assertions.assertIf;
import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNextStdOutLineIsEmpty;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertReturnValueWithRandomEquals;
import static engine.Factories.localClass;
import static sensei.Texts.DO_YOU_WANT_TO_CONTINUE;
import static sensei.Texts.DO_YOU_WANT_TO_THROW_AGAIN;
import static sensei.Texts.N;
import static sensei.Texts.OH_NO_NOT7_YOU_LOOSE;
import static sensei.Texts.PLAYER_1_ITS_YOUR_TURN;
import static sensei.Texts.PLAYER_1_WINS;
import static sensei.Texts.PLAYER_2_ITS_YOUR_TURN;
import static sensei.Texts.PLAYER_2_WINS;
import static sensei.Texts.TIE;
import static sensei.Texts.WELL_DONE_YOUR_SCORE_IS;
import static sensei.Texts.Y;
import static sensei.Texts.YOUR_RESULT_SO_FAR;
import static sensei.Texts.YOU_THREW;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import engine.FormatParam;
import engine.Koan;
import engine.KoanResult;
import engine.Local;
import engine.Printer;
import engine.ResToIntFunction;
import engine.ResultAssertion;

public class AboutNot7GameKoans {
    private static final Local<Class<?>> CLASS =
        localClass(koans.english.AboutNot7Game.class)
        .fr(koans.french.AboutNot7Game.class); // TODO

    private static final GameRoundv5Assertions GAME_ROUND_ASSERTIONS = new GameRoundv5Assertions(false);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, "die6")
            .whenCalling()
            .then(
                assertReturnValueWithRandomEquals(rand -> die(rand))
            ),
        new Koan(CLASS, "askQuestion", String.class)
            .useConsole()
            .whenCallingWith(DO_YOU_WANT_TO_CONTINUE)
            .withStdInInputs(List.of(Y))
            .then(
                assertNextStdOutLineEquals(DO_YOU_WANT_TO_CONTINUE),
                assertReturnValueEquals(true),
                assertNoMoreLineInStdOut()
            )
            .whenCallingWith(DO_YOU_WANT_TO_CONTINUE)
            .withStdInInputs(List.of(N))
            .then(
                assertNextStdOutLineEquals(DO_YOU_WANT_TO_CONTINUE),
                assertReturnValueEquals(false),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "throwDice")
            .useConsole()
            .whenCalling()
            .then(
                assertReturnValueWithRandomEquals(2, expectedDieSumResult(0, 1)),
                assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(0), expectedDieResult(1)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "gameRoundv1")
            .useConsoleAndShowStdinInputs()
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
            .useConsoleAndShowStdinInputs()
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
            .useConsoleAndShowStdinInputs()
            .whenCalling()
            .withStdInInputs(List.of(N))
            .then(
                AboutNot7GameKoans::gameRoundv3Assertions
            )
            .whenCalling()
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                AboutNot7GameKoans::gameRoundv3Assertions
            ),
        new Koan(CLASS, "gameRoundv4")
            .useConsoleAndShowStdinInputs()
            .whenCalling()
            .withStdInInputs(List.of(N))
            .then(
                AboutNot7GameKoans::gameRoundv4Assertions
            )
            .whenCalling()
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                AboutNot7GameKoans::gameRoundv4Assertions
            ),
        new Koan(CLASS, "gameRoundv5")
            .useConsoleAndShowStdinInputs()
            .whenCalling()
            .withStdInInputs(List.of(N))
            .then(
                new GameRoundv5Assertions(true),
                assertNoMoreLineInStdOut()
            )
            .whenCalling()
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                new GameRoundv5Assertions(true),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "not7Gamev1")
            .useConsoleAndShowStdinInputs()
            .whenCalling()
            .withStdInInputs(List.of(N, N))
            .then(
                assertNextStdOutLineEquals(PLAYER_1_ITS_YOUR_TURN),
                assertNextStdOutLineIsEmpty(),
                GAME_ROUND_ASSERTIONS,
                assertNextStdOutLineEquals(PLAYER_2_ITS_YOUR_TURN),
                assertNextStdOutLineIsEmpty(),
                GAME_ROUND_ASSERTIONS,
                assertNoMoreLineInStdOut()
            )
            .whenCalling()
            .withStdInInputs(List.of(Y, Y, N, Y, Y, N))
            .then(
                assertNextStdOutLineEquals(PLAYER_1_ITS_YOUR_TURN),
                assertNextStdOutLineIsEmpty(),
                GAME_ROUND_ASSERTIONS,
                assertNextStdOutLineEquals(PLAYER_2_ITS_YOUR_TURN),
                assertNextStdOutLineIsEmpty(),
                GAME_ROUND_ASSERTIONS,
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, "not7Gamev2")
            .useConsoleAndShowStdinInputs()
            .whenCalling()
            .withStdInInputs(List.of(N, N))
            .then(
                assertNextStdOutLineEquals(PLAYER_1_ITS_YOUR_TURN),
                assertNextStdOutLineIsEmpty(),
                GAME_ROUND_ASSERTIONS,
                assertNextStdOutLineEquals(PLAYER_2_ITS_YOUR_TURN),
                assertNextStdOutLineIsEmpty(),
                GAME_ROUND_ASSERTIONS,
                AboutNot7GameKoans::assertWinnerLine,
                assertNoMoreLineInStdOut()
            )
            .whenCalling()
            .withStdInInputs(List.of(Y, Y, N, Y, Y, N))
            .then(
                assertNextStdOutLineEquals(PLAYER_1_ITS_YOUR_TURN),
                assertNextStdOutLineIsEmpty(),
                GAME_ROUND_ASSERTIONS,
                assertNextStdOutLineEquals(PLAYER_2_ITS_YOUR_TURN),
                assertNextStdOutLineIsEmpty(),
                GAME_ROUND_ASSERTIONS,
                AboutNot7GameKoans::assertWinnerLine,
                assertNoMoreLineInStdOut()
            )
    );

    private static boolean gameRoundv1Assertions(Printer p, KoanResult res) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        int loopCount = 0;
        boolean wantsToContinue = true;

        while (wantsToContinue) {
            final int dieOffset = 2 * loopCount;

            final var asserted = res.executeAssertions(p, 
                assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(res, dieOffset), expectedDieResult(res, dieOffset + 1)),
                assertNextStdOutLineEquals(DO_YOU_WANT_TO_THROW_AGAIN)
            );
            if (!asserted) {
                return false;
            }

            Optional<String> answer = res.nextStdInLine();
            wantsToContinue = answer.isPresent() && answer.get().trim().equals(Y.get(res.locale));
            loopCount++;
        }
        
        return res.executeAssertions(p, 
            assertNoMoreLineInStdOut()
        );
    }

    private static boolean gameRoundv2Assertions(Printer p, KoanResult res) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        int loopCount = 0;
        boolean wantsToContinue = true;

        while (wantsToContinue) {
            final int dieOffset = 2 * loopCount;

            final var asserted = res.executeAssertions(p, 
                assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(res, dieOffset), expectedDieResult(res, dieOffset + 1)),
                assertNextStdOutLineEquals(YOUR_RESULT_SO_FAR, expectedDieSum(res, 0, loopCount + 1)),
                assertNextStdOutLineEquals(DO_YOU_WANT_TO_THROW_AGAIN)
            );
            if (!asserted) {
                return false;
            }

            Optional<String> answer = res.nextStdInLine();
            wantsToContinue = answer.isPresent() && answer.get().trim().equals(Y.get(res.locale));
            loopCount++;
        }

        return res.executeAssertions(p, 
            assertNoMoreLineInStdOut()
        );
    }

    private static boolean gameRoundv3Assertions(Printer p, KoanResult res) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        int loopCount = 0;
        boolean wantsToContinue = true;

        while (wantsToContinue) {
            final int dieOffset = 2 * loopCount;

            final var asserted = res.executeAssertions(p, 
                assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(res, dieOffset), expectedDieResult(res, dieOffset + 1)),
                assertNextStdOutLineEquals(YOUR_RESULT_SO_FAR, expectedDieSum(res, 0, loopCount + 1)),
                assertNextStdOutLineEquals(DO_YOU_WANT_TO_THROW_AGAIN)
            );
            if (!asserted) {
                return false;
            }

            Optional<String> answer = res.nextStdInLine();
            wantsToContinue = answer.isPresent() && answer.get().trim().equals(Y.get(res.locale));
            loopCount++;
        }

        return res.executeAssertions(p, 
            assertNoMoreLineInStdOut(),
            assertReturnValueWithRandomEquals(2 * loopCount, expectedDieSumResult(0, loopCount))
        );
    }

    private static boolean gameRoundv4Assertions(Printer p, KoanResult res) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        int loopCount = 0;
        boolean wantsToContinue = true;
        var asserted = true;

        while (wantsToContinue) {
            final int dieOffset = 2 * loopCount;

            if (diceThrowSum(res, dieOffset) == 7) {
                return res.executeAssertions(p,
                    assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(res, dieOffset), expectedDieResult(res, dieOffset + 1)),
                    assertNextStdOutLineEquals(OH_NO_NOT7_YOU_LOOSE),
                    assertNextStdOutLineIsEmpty(),
                    assertNoMoreLineInStdOut(),
                    assertReturnValueEquals(0)
                );
            } else {
                asserted = res.executeAssertions(p, 
                    assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(res, dieOffset), expectedDieResult(res, dieOffset + 1)),
                    assertNextStdOutLineEquals(YOUR_RESULT_SO_FAR, expectedDieSum(res, 0, loopCount + 1)),
                    assertNextStdOutLineEquals(DO_YOU_WANT_TO_THROW_AGAIN)
                );
            }
            if (!asserted) {
                return false;
            }

            Optional<String> answer = res.nextStdInLine();
            wantsToContinue = answer.isPresent() && answer.get().trim().equals(Y.get(res.locale));
            loopCount++;
        }

        return res.executeAssertions(p, 
            assertNoMoreLineInStdOut(),
            assertReturnValueWithRandomEquals(2 * loopCount, expectedDieSumResult(0, loopCount))
        );
    }

    /**
     * These more complex assertions allow to reuse them for a game with multiple rounds.
     */
    private static class GameRoundv5Assertions implements ResultAssertion {
        private final boolean shouldAssertReturnValue;
        private int loopCount = 0;
        private KoanResult lastResultSeen = null;
        private List<Integer> scores = new ArrayList<>();

        public GameRoundv5Assertions(boolean shouldAssertReturnValue) {
            this.shouldAssertReturnValue = shouldAssertReturnValue;
        }

        @Override
        public boolean validate(Printer p, KoanResult res) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
            var wantsToContinue = true;
            var asserted = true;
            var loopOffset = res == lastResultSeen ? loopCount : 0;
            if (res != lastResultSeen) {
                scores.clear();
            }
            loopCount = loopOffset;
            lastResultSeen = res;

            while (wantsToContinue) {
                final int dieOffset = 2 * loopCount;

                if (diceThrowSum(res, dieOffset) == 7) {
                    loopCount++;
                    scores.add(0);
                    return  res.executeAssertions(p,
                        assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(res, dieOffset), expectedDieResult(res, dieOffset + 1)),
                        assertNextStdOutLineEquals(OH_NO_NOT7_YOU_LOOSE),
                        assertNextStdOutLineIsEmpty(),
                        assertIf(shouldAssertReturnValue, assertReturnValueEquals(0))
                    );
                } else {
                    asserted = res.executeAssertions(p, 
                        assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(res, dieOffset), expectedDieResult(res, dieOffset + 1)),
                        assertNextStdOutLineEquals(YOUR_RESULT_SO_FAR, expectedDieSum(res, loopOffset, loopCount + 1 - loopOffset)),
                        assertNextStdOutLineEquals(DO_YOU_WANT_TO_THROW_AGAIN)
                    );
                }
                if (!asserted) {
                    return false;
                }

                Optional<String> answer = res.nextStdInLine();
                wantsToContinue = answer.isPresent() && answer.get().trim().equals(Y.get(res.locale));
                loopCount++;
            }

            int score = expectedDieSum(res, loopOffset, loopCount - loopOffset);
            scores.add(score);
            return res.executeAssertions(p,
                assertNextStdOutLineIsEmpty(),
                assertNextStdOutLineEquals(WELL_DONE_YOUR_SCORE_IS, score),
                assertNextStdOutLineIsEmpty(),
                assertIf(shouldAssertReturnValue, assertReturnValueWithRandomEquals(2 * loopCount, expectedDieSumResult(loopOffset, loopCount - loopOffset)))
            );
        }
        
        public int player1Score() {
            return scores.size() > 0 ? scores.get(0) : 0;
        }
        
        public int player2Score() {
            return scores.size() > 1 ? scores.get(1) : 0;
        }
    }

    private static boolean assertWinnerLine(Printer p, KoanResult res) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        var expected = TIE;
        if (GAME_ROUND_ASSERTIONS.player1Score() > GAME_ROUND_ASSERTIONS.player2Score()) {
            expected = PLAYER_1_WINS;
        } else if (GAME_ROUND_ASSERTIONS.player2Score() > GAME_ROUND_ASSERTIONS.player1Score()) {
            expected = PLAYER_2_WINS;
        }
        return res.executeAssertions(p, assertNextStdOutLineEquals(expected));
    }

    private static int die(double rand) {
        return (int)Math.ceil(6 * rand);
    }

    private static int expectedDieResult(KoanResult res, int dieIndex) {
        var rn = res.randomNumber(dieIndex);
        var dieResult = die(rn);
        return dieResult;
    }

    private static FormatParam expectedDieResult(int dieIndex) {
        return res -> String.valueOf(expectedDieResult(res, dieIndex));
    }

    private static int diceThrowSum(KoanResult res, int dieIndex) {
        return die(res.randomNumber(dieIndex)) + die(res.randomNumber(dieIndex + 1));
    }

    private static ResToIntFunction expectedDieSumResult(int throwOffset, int throwCount) {
        return res -> Arrays
            .stream(res.randomNumbers(throwOffset * 2, throwCount*2))
            .mapToInt(rn -> die(rn))
            .sum();
    }

    private static int expectedDieSum(KoanResult res, int throwOffset, int throwCount) {
        return expectedDieSumResult(throwOffset, throwCount).apply(res);
    }
}
