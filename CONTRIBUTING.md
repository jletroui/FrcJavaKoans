# Table of content

- [Design decisions](#design-decisions)
    - [Rationale for a FRC-specific Java basics curiculum](#rationale-for-a-frc-specific-java-basics-curiculum)
    - [What the FRC Java Koans is _not_](#what-the-frc-java-koans-is-not)
    - [Technical aspects](#technical-aspects)
- [Contributing](#contributing)

# Design decisions

## Rationale for a FRC-specific Java basics curiculum

Although there are some resources to teach Java to kids, we did not find any really suited to our needs as FRC mentors.

### Terseness

Most resources are using a fair amount of text and go into lots of details. Kids could have short to medium attention span, especially after a whole day at school. So even if kids joining an FRC team can be quite engaged, they won't enjoy an additional lecture or a large reading. Therefore, this curiculum tries to have as few text to read as possible between exercises. To achieve that, it strives to decompose all concepts in as small bites as possible in order to be able to introduce the least amount of material between 2 sessions of hands on practice. This allows students to spend more time practicing than reading.

### Only what's necessary, not more

The primary goal of FRC Java Koans are not to teach all of Java capabilities and available sugar syntax. But rather, teach them the minimum required before mentors can start teaching how to program a robot with WPILib. Lots of resources are, for example, explaining how to express conditions (the if / else statement, but also the ternary operator and the switch / case statement). We chose a single one. The reason is that kids are coming for programming robots. We observed they are way more motivated to learn about a new syntax or concept once they have a robot moving, and that syntax or concept unlocks a new cool trick of the robot. So we are teaching the necessary remainder of Java during the robot programming lessons.

In particular: kids don't need to know about functionnal interfaces, arrays, or for loops to learn to program a basic `TimedRobot`. Therefore, we try to bring them as fast as possible to the point they can program a robot simply. Once they have a robot moving, teaching them functionnal interfaces (for example when teaching them commands) or arrays (when teaching them swerve drive programming) can usually be done while they are programming the robot. They appear to not require specific exercises to master those.

You might ask why not start right away with programming the robot then? The students are way too lost in the midst of pages of arcane syntax, and we observed anecdotally it is slower to teach that way. In particular, it is requiring a lot more repetition, because they jump too fast from one concept they don't master to another, and don't register them the first time they hear about it.

That being said, the bonus koans are providing additional material. We found good success using them only after students have started programming actual robots, so as to keep the kids excited.

### Enough exercises

Most of the resources have too few exercices. Or exercises not really relevant to the kind of thinking we need for programming robots with WPILib. On the other hand, (most) kids like to learn by practicing. So this curiculum is focused on having a good amount exercices. Of course "a good amount" is not an exact science, so we expect to tweak this as our experience teaching various profiles of rookies is growing.

### VSCode

Most of the non-FRC resources are focusing on other tools than used by the FRC. This is introducing some friction to the learning. Either mentors have to transcribe material for VSCode, or the students have to learn unnecessary skills, like other IDEs.

### Interactive

Non interactive lessons are a problem for 2 reasons:

- First version of this curriculum was just textual. But this required a lot of mentor time for each students, because all of the feedback whether an exercise was complete was done by the mentors. Inevitably, during feedback sessions, some other students were stuck or ready for feedback and were waiting for free mentors to come to help them.
- Most students tends to stay more focused when they are practicing than when they are listening or reading. So more time goes into learning when having an interactive curiculum.

### Progress line

Random exercices are fine. But let us tell you that, for some competitive kids, a progress line counting the number of remaining exercices is a great motivator (thanks Andy for the great suggestion!) ;)

### Localizable

Despite the obvious need to read and write some level of english in order te program in Java, learning is slowed down if all the material is given in english for non native english speakers.

### On attention, time, and engagement

We expressed above a concern for saving student's attention / motivation / time. We are not implying students could not read a book, or study a blog. It's just that, pragmatically, we would rather spend their attention and engagement on programming a competition robot than teaching them programming basics. And thus, saving attention, time, and learning energy during the "programming basics" phase of rookies training is like investing for the more crucial parts of the season. We also believe reducing the learning curve could have a positive impact on student retention and engagement in our team.

## What the FRC Java Koans is _not_

- A complete and detailed Java course
- A robot programming course
- Something "hands off" to let the students go through without assistance. Despite being possible for a motivated kid to follow the curiculum by themselves, this is meant to be used by mentors who can provide guidance to students.

## Technical aspects

### Design goals

- Strive to work on a bare WPILib installation: on VSCode with no need for a plugin.
- Simple start: no dependency other than the Java standard library, so as to avoid a build step with a dependency management tool. This has consequences: the project has to includes a mini test framework for example.
- Java 17, because as of 2024, this is the version used by default in WPILib's VSCode.

### Compromises and limitations

- The koans are, for now, not looking at the code within methods. Therefore, some instructions compliance cannot be assessed. For example: 'using method xyz()'.
- In exercises relying on the standard input, it would have been nice to let students use `System.console().readLine()`. However, since `System.console()` is caching the standard input stream, we did not find a way to switch back and forth a silenced stream and the real one. Hence the static `Helpers.readLine()` method.
- Similarly, because we need to control the seed, we could not let the students use `Math.random()`. Here again, we provide students a `Helpers.random()` method.
- Because we decided to have actual random seeds, the 'Not 7!' koans exercices are not catching all the non-compliance all the time. Which means a student could believe they completed a koan, when they did not. They would receive feedback about this koan later, when they would have started another koan.

# Contributing

Pull requests for translations, curiculum tweaks or new capabilities are welcome.

When submitting bugs, please submit a zip file of the koans in a state exhibiting the issue.

## I want to contribute. What can I do?

Here are suggestions, in ascending order of involvement:

* Submit [issues](https://github.com/jletroui/FrcJavaKoans/issues/new) for text issues: typos, awkward, ambiguous, etc...
* Submit pull requests for text issues: typos, awkward, ambiguous, etc...
* Report [bugs](https://github.com/jletroui/FrcJavaKoans/issues/new). Please submit a zip file of the koans in a state exhibiting the issue.
* Submit [issues](https://github.com/jletroui/FrcJavaKoans/issues/new) or pull requests for better koans replacing existing ones. In particular, one improvements we are trying to converge to are koans exhibiting the same pedagogic targets and quality, but FRC and robot themed.
* Submit [issues](https://github.com/jletroui/FrcJavaKoans/issues/new) or pull requests for new koans in existing series plugging a hole in the learning journey.
* Better engine code comments or test coverage.
* Submit a new bonus koan series. Current potentially beneficial areas not covered: sugar syntax (var, ternary operator, etc...), inheritance (and its fallbacks), generics, etc...
* A new language localization. This involves:
  1. Adding the new [Locale](https://github.com/jletroui/FrcJavaKoans/blob/master/src/main/java/engine/Locale.java) and [Localizable helper](https://github.com/jletroui/FrcJavaKoans/blob/master/src/main/java/engine/Localizable.java#L20).
  2. Translating all [sensei and assertion messages](https://github.com/jletroui/FrcJavaKoans/blob/master/src/main/java/engine/Texts.java).
  3. Translating all [koans title and texts](https://github.com/jletroui/FrcJavaKoans/blob/master/src/main/java/sensei/Texts.java).
  4. Adding a [`*PathToEnlightment.java`](https://github.com/jletroui/FrcJavaKoans/tree/master/src/main/java).
  5. Copying the [main series of koans](https://github.com/jletroui/FrcJavaKoans/tree/master/src/main/java/koans/english) in a package for the new language. Translate all the comments in there.
  6. Repeat 5. for the [bonus koans](https://github.com/jletroui/FrcJavaKoans/tree/master/src/main/java/bonuses/english).
  7. Add koans solutions for the new language in [the testing companion project](https://github.com/jletroui/FrcJavaKoansTests), to make sure koans work in the new language as well.

## Testing

Automated testing of this project was challenging for a few reasons, main ones being:

1. By design, we don't have a build tool (Gradle or Maven for example), nor access to any libraries. So no JUnit on hand to test the koans engine.
2. We would not want to include solutions to the koans within the project, because the students might stumble on them, which would affect their learning.

Here are the compromises we came up with to solve these challenges:

For 1., we have created a mini test framework in `engine.test.runner` in order to run unit and integration tests of the koans engine. Tests are located in `engine.test`. To run those tests, simply run the `engine.test.runner.TestRunner.main` method.

For 2., we have created a [brother project](https://github.com/jletroui/FrcJavaKoansTests) testing the koans themselves.
