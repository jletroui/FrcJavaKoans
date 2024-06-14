package sensei;

import static engine.Assertions.assertIf;
import static engine.Assertions.assertNextStdOutLineEquals;
import static engine.Assertions.assertNextStdOutLineIsEmpty;
import static engine.Assertions.assertNoMoreLineInStdOut;
import static engine.Assertions.assertReturnValueEquals;
import static engine.Assertions.assertReturnValueWithRandomEquals;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Localizable.localClass;
import static engine.script.Expression.callKoanMethod;
import static sensei.Texts.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import engine.FormatParam;
import engine.Koan;
import engine.KoanResult;
import engine.Localizable;
import engine.Printer;
import engine.ResToIntFunction;
import engine.ResultAssertion;

public class AboutNot7GameKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(koans.english.AboutNot7Game.class);
        
    private static final GameRoundv5Assertions GAME_ROUND_ASSERTIONS = new GameRoundv5Assertions(false);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, ROLLING_A_6_SIDED_DIE)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("die6")
            )
            .when(callKoanMethod("die6"))
            .then(
                assertReturnValueWithRandomEquals(rand -> die(rand))
            )
            .when(callKoanMethod("die6"))
            .then(
                assertReturnValueWithRandomEquals(rand -> die(rand))
            ),
        new Koan(CLASS, ASKING_A_QUESTION_TO_THE_PLAYER)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("askQuestion", String.class)
            )
            .when(callKoanMethod("askQuestion", DO_YOU_WANT_TO_CONTINUE))
            .withStdInInputs(List.of(Y))
            .then(
                assertNextStdOutLineEquals(DO_YOU_WANT_TO_CONTINUE),
                assertReturnValueEquals(true),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("askQuestion", DO_YOU_WANT_TO_CONTINUE))
            .withStdInInputs(List.of(N))
            .then(
                assertNextStdOutLineEquals(DO_YOU_WANT_TO_CONTINUE),
                assertReturnValueEquals(false),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, ROLLING_2_DICE)
            .useConsole()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("throwDice")
            )
            .when(callKoanMethod("throwDice"))
            .then(
                assertReturnValueWithRandomEquals(2, expectedDieSumResult(0, 1)),
                assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(0), expectedDieResult(1)),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("throwDice"))
            .then(
                assertReturnValueWithRandomEquals(2, expectedDieSumResult(0, 1)),
                assertNextStdOutLineEquals(YOU_THREW, expectedDieResult(0), expectedDieResult(1)),
                assertNoMoreLineInStdOut()
            ),
        new Koan(CLASS, PROGRAMMING_A_ROUND_OF_THE_GAME_1ST_STEP)
            .useConsoleAndShowStdinInputs()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("gameRoundv1")
            )
            .when(callKoanMethod("gameRoundv1"))
            .withStdInInputs(List.of(N))
            .then(
                AboutNot7GameKoans::gameRoundv1Assertions
            )
            .when(callKoanMethod("gameRoundv1"))
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                AboutNot7GameKoans::gameRoundv1Assertions
            ),
        new Koan(CLASS, PROGRAMMING_A_ROUND_OF_THE_GAME_2ND_STEP)
            .useConsoleAndShowStdinInputs()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("gameRoundv2")
            )
            .when(callKoanMethod("gameRoundv2"))
            .withStdInInputs(List.of(N))
            .then(
                AboutNot7GameKoans::gameRoundv2Assertions
            )
            .when(callKoanMethod("gameRoundv2"))
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                AboutNot7GameKoans::gameRoundv2Assertions
            ),
        new Koan(CLASS, PROGRAMMING_A_ROUND_OF_THE_GAME_3RD_STEP)
            .useConsoleAndShowStdinInputs()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("gameRoundv3")
            )
            .when(callKoanMethod("gameRoundv3"))
            .withStdInInputs(List.of(N))
            .then(
                AboutNot7GameKoans::gameRoundv3Assertions
            )
            .when(callKoanMethod("gameRoundv3"))
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                AboutNot7GameKoans::gameRoundv3Assertions
            ),
        new Koan(CLASS, PROGRAMMING_A_ROUND_OF_THE_GAME_4TH_STEP)
            .useConsoleAndShowStdinInputs()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("gameRoundv4")
            )
            .when(callKoanMethod("gameRoundv4"))
            .withSeed(1010) // 7 directly
            .then(
                AboutNot7GameKoans::gameRoundv4Assertions
            )
            .when(callKoanMethod("gameRoundv4"))
            .withSeed(1000) // non 7 result and n
            .withStdInInputs(List.of(N))
            .then(
                AboutNot7GameKoans::gameRoundv4Assertions
            )
            .when(callKoanMethod("gameRoundv4"))
            .withSeed(1001) // o, o, and 7
            .withStdInInputs(List.of(Y, Y))
            .then(
                AboutNot7GameKoans::gameRoundv4Assertions
            )
            .when(callKoanMethod("gameRoundv4"))
            .withSeed(1003) // o, o, n
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                AboutNot7GameKoans::gameRoundv4Assertions
            ),
        new Koan(CLASS, PROGRAMMING_A_ROUND_OF_THE_GAME_FINAL_STEP)
            .useConsoleAndShowStdinInputs()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("gameRoundv5")
            )
            .when(callKoanMethod("gameRoundv5"))
            .withSeed(1010) // 7 directly
            .withStdInInputs(List.of(N))
            .then(
                new GameRoundv5Assertions(true),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("gameRoundv5"))
            .withSeed(1000) // non 7 result and n
            .withStdInInputs(List.of(N))
            .then(
                new GameRoundv5Assertions(true),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("gameRoundv5"))
            .withSeed(1003) // o, o, n
            .withStdInInputs(List.of(Y, Y, N))
            .then(
                new GameRoundv5Assertions(true),
                assertNoMoreLineInStdOut()
            )
            .when(callKoanMethod("gameRoundv5"))
            .withSeed(1001) // o, o, et 7
            .withStdInInputs(List.of(Y, Y))
            .then(
                new GameRoundv5Assertions(true),
                assertNoMoreLineInStdOut()
            ),
        // Since the display of who is playing is not random dependant, not using seeds here.
        new Koan(CLASS, PROGRAMMING_THE_GAME_1ST_STEP)
            .useConsoleAndShowStdinInputs()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("not7Gamev1")
            )
            .when(callKoanMethod("not7Gamev1"))
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
            .when(callKoanMethod("not7Gamev1"))
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
        // Since the display of who has won is random dependant, use seeds.
        new Koan(CLASS, PROGRAMMING_THE_GAME_FINAL_STEP)
            .useConsoleAndShowStdinInputs()
            .beforeFirstTest(
                assertKoanMethodIsInvokable("not7Gamev2")
            )
            .when(callKoanMethod("not7Gamev2"))
            .withSeed(2002) // Player 2 wins
            .withStdInInputs(List.of(N))
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
            .when(callKoanMethod("not7Gamev2"))
            .withSeed(2000) // Player 1 wins
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
            .when(callKoanMethod("not7Gamev2"))
            .withSeed(2005) // Tie
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
    );

    private static boolean gameRoundv1Assertions(Printer p, KoanResult res) {
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

    private static boolean gameRoundv2Assertions(Printer p, KoanResult res) {
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

    private static boolean gameRoundv3Assertions(Printer p, KoanResult res) {
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

    private static boolean gameRoundv4Assertions(Printer p, KoanResult res) {
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
        public boolean validate(Printer p, KoanResult res) {
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

    private static boolean assertWinnerLine(Printer p, KoanResult res) {
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
