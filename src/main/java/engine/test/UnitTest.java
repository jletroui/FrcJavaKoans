package engine.test;

import engine.Koan;

public record UnitTest(Koan koan, UnitTestExpectation... expectations) {}
