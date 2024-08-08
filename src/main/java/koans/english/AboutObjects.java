package koans.english;

import frc.*;

public class AboutObjects {
    /**
     * # The first object
     * 
     * Create a class 'geom.Point' with 2 final private decimal fields 'x' and 'y'. The constructor should take 'x' and 'y' as parameters.
     * 
     * ---------   TIPS --------------
     * 
     * Up to now, we only attached methods to the classes themselves. This is useful and allow to organize methods in your program.
     * But classes can be much more powerful than simple 'method folders'.
     * 
     * A class can serve as a template for code elements called 'objects'. Objects are grouping together values and methods which can act on those values.
     * 
     * For example, let's say we want to represent, in the code, persons who can introduce themselves. A 'person' object could therefore have: a name, an age, and an 'introduce' method.
     * 
     * The name and age are kind of variables attached to the 'person' object. Their values can be different from one 'person' object to the other.
     * A 'variable' attached to an object is called a 'field'.
     * 
     * The 'introduce' method, similarly, is attached to the object. Calling it on 2 different 'person' objects can produce a different result.
     * 
     * You can create an object out of a given class by calling a very special method called a constructor. The constructor... constructs an object out of the given class.
     * 
     * The result of a constructor is a value, which type is the class itself.
     * You call a constructor by using the 'new' keyword, and the name of the class.
     * Let's see how that goes with a Person class:
     * 
     *     // The constructor of Person takes a name and an age
     *     Person julien = new Person("Julien", 44);
     *     Person stephane = new Person("Stephane", 26);
     * 
     * Now we have 2 'Person' objects, so we can call their methods. We said in our example 'Person' objects had an 'introduce' method. Let's call it:
     * 
     *     julien.introduce();
     * 
     * Result in the console:
     * 
     * Hello, my name is Julien and I am 44
     * 
     * What happens if we call the same method on the 'stephane' object?
     * 
     *     stephane.introduce();
     * 
     * Result in the console:
     * 
     * Hello, my name is Stephane and I am 26
     * 
     * Same method, but a different result! This is because a method attached to an object can access the object's fields, which can be different from one object to the other.
     * 
     * What happens if we call the method on the class itself?
     * 
     *     Person.introduce(); // Error, this is not valid Java code
     * 
     * This is producing an error, because the method is attached to the objects of the Person class, not to the class itself.
     * By default, a method is attached to the objects of the class. If you want to attach a method on the class itself, you have to say it is 'static'.
     * 'static' means 'attached to the class'.
     * 
     * So how do we declare class fields? Like so:
     * 
     *     public class Person {
     *         // Fields go at the beginning of the class.
     *         private final String name;
     *         private final int age;
     *         
     *         public introduce() {
     *             // In an object's method, we can use object field' values, like if they were simple variables
     *             System.out.println("Hello, my name is " + name + " and I am " + age);
     *         }
     *     }
     * 
     * Like variables, fields have a type and a name. Unlike variables, we must specify if they are visible or not by code outside the class.
     * Although Java allows to have fields visible outside the class, it is a bad code design which often create unintended bugs.
     * So they always should be 'private', meaning we can only use them inside the class.
     * They also should be 'final', meaning that their values is assigned in the constructor once and for all, and they will never change during an object's lifetime.
     * 
     * But what about declaring the constructor itself? Constructors are very special methods.
     * They don't specify a return type, because the return type of a constructor is always the class.
     * And their name is simply the name of the class. Ex:
     * 
     *     public class Person {
     *         // Fields go at the beginning of the class.
     *         private final String name;
     *         private final int age;
     *         
     *         // The constructor of the class Person
     *         // Return type of the method: none.
     *         // Name of the method: same as the name of the class.
     *         public Person(String name, int age) {
     *             // We set the value of the object's fields in the constructor.
     *             this.name = name;
     *             this.age = age; 
     *         }
     * 
     *         public introduce() {
     *             // In an object's method, we can use object fields
     *             System.out.println("Hello, my name is " + name + " and I am " + age);
     *         }
     *     }
     * 
     * We use the 'this' keyword to differentiate the constructor parameters 'name' and 'age' from the object's fields, which have the same names.
     * 'this' is a kind of special variable pointing to the current object. So 'this.[name of a field]' refers to the object's field.
     * By default, in a method, a name without 'this' refer to a parameter or a variable of the method, not the class field.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * we can create a new geom.Point object
     * 
     */


    /**
     * # An object method
     * 
     * Create a method 'toString' in 'geom.Point' which returns a text representation of a Point object as follow:
     * 
     * Point([x value], [y value])
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * new Point(2.5, 4.3).toString() should return the String "Point(2.5, 4.3)"
     * 
     */


    /**
     * # An other object method
     * 
     * Create a method 'translate' in 'geom.Point' which takes 2 translation coordinates 'tx' and 'ty', and returns a new point object, which is the initial point translated by tx and ty.
     * 
     * ---------   TIPS --------------
     * 
     * When we translate by tx and ty a point A of coordinates x and y, the resulting point has the coordinates x + tx and y + ty.
     * 
     * You will need to create a new object of type point to return it, so you might need a refresher on how to create objects in the first exercise of this series.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * new Point(2.5, 4.3).translate(2, -1) should return a new point with coordinates 4.5 and 3.3
     * 
     */


    /**
     * # An object... using other kind of objects
     * 
     * Create a class 'geom.Triangle' with 3 final private Point fields 'a', 'b', and 'c'. The constructor should take 'a', 'b', and 'c' as parameters.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * we can create a new geom.Triangle object
     * 
     */


    /**
     * # Using other object's method
     * 
     * Using 'Point.toString', write a method 'toString' in 'geom.Triangle' which returns a text representation of a Triangle object as follow:
     * 
     * Triangle(Point([x value of 'a'], [y value of 'a']), Point([x value of 'b'], [y value of 'b']), Point([x value of 'c'], [y value of 'c']))
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * new Triangle(new Point(1.0, 1.0), new Point(2.0, 2.0), new Point(3.0, 3.0)).toString() should return the String "Triangle(Point(1.0, 1.0), Point(2.0, 2.0), Point(3.0, 3.0))"
     * 
     */


    /**
     * # Using another object's method
     * 
     * Using 'Point.translate', write a method 'translate' in 'geom.Triangle' which takes 2 translation coordinates tx and ty, and returns a new Triangle object, which is the initial triangle translated by the given coordinates.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     Triangle myTriangle = new Triangle(new Point(1.0, 1.0), new Point(2.0, 2.0), new Point(3.0, 3.0)).translate(2.0, -1.0);
     * 
     * Should result in 'myTriangle' being a Triangle object with:
     * 
     *   - myTriangle.a being Point(3.0, 0.0)
     *   - myTriangle.b being Point(4.0, 1.0)
     *   - myTriangle.c being Point(5.0, 2.0)
     * 
     */


    /**
     * # Apply learnings: the Circle class
     * 
     * Create a class 'geom.Circle' with fields 'center' (Point), and 'radius' (double). The constructor should take 'center', and 'radius' as parameters.
     * Using 'geom.Point.toString, write a method 'toString' in 'geom.Circle' which returns a text representation of a Circle object as follow:
     * 
     * Circle(Point([x value of 'center'], [y value of 'center']), [value of 'radius'])
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * new Circle(new Point(2.0, 1.0), 3.6).toString() should return the String "Circle(Point(2.0, 1.0), 3.6)"
     * 
     */


    /**
     * # Apply learnings: circle translation
     * 
     * Using 'Point.translate', write a method 'translate' in 'geom.Circle' which takes 2 translation coordinates tx and ty, and returns a new Circle object, which is the initial circle translated by the given coordinates.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     Circle myCircle = new Circle(new Point(1.0, 1.0), 3.6).translate(2.0, -1.0);
     * 
     * Should result in 'myCircle' being a Circle object with:
     * 
     *   - myCircle.center being Point(3.0, 0.0)
     *   - myCircle.radius being 3.6
     * 
     */


    /**
     * # Objects with mutating fields
     * 
     * Now, we want to program a scouting app for the 2024 game 'Crescendo', were robots have to score foam circles called 'notes' in a 'speaker' and an 'amplifier' (abbreviated 'amp').
     * In auto mode, scoring a note in the speaker is worth 5 points, and scoring a note in the amp is worth 2 points. Let's design classes helping us compute the scores of robots during the autonomous period.
     * Create a class 'frc.RobotAutoScore' with non final fields 'notesInSpeaker' (int), 'notesInAmp' (int), initialized to 0. The constructor should take no parameters.
     * Write a method 'toString' in 'frc.RobotAutoScore' which returns a text representation of a RobotAutoScore object as follow:
     * 
     * RobotScore: notes in speaker = [notesInSpeaker value]; notes in amp = [notesInAmp value]
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * new RobotAutoScore().toString() should return the String "RobotScore: notes in speaker = 0; notes in amp = 0"
     * 
     */


    /**
     * # Mutate object's fields
     * 
     * Write a method 'noteScoredInSpeaker' in 'frc.RobotAutoScore' which takes no parameter, and increases the value of the 'notesInSpeaker' field by 1.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     RobotAutoScore robotScore = new RobotAutoScore();
     *     robotScore.noteScoredInSpeaker();
     *     System.out.println(robotScore);
     * 
     * Should display:
     * 
     *   RobotScore: notes in speaker = 1; notes in amp = 0
     * 
     */


    /**
     * # More object's fields mutations
     * 
     * Write a method 'noteScoredInAmp' in 'frc.RobotAutoScore' which takes no parameter, and increase the value of the 'notesInAmp' field by 1.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     RobotAutoScore robotScore = new RobotAutoScore();
     *     robotScore.noteScoredInAmp();
     *     System.out.println(robotScore);
     * 
     * Should display:
     * 
     *   RobotScore: notes in speaker = 0; notes in amp = 1
     * 
     */


    /**
     * # Computing the total score for a robot
     * 
     * Write a method 'totalScore' in 'frc.RobotAutoScore' which takes no parameter, and return the total score (2 points for amp notes, 5 points for speaker notes).
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * The following code:
     * 
     *     RobotAutoScore robotScore = new RobotAutoScore();
     *     robotScore.noteScoredInSpeaker();
     *     robotScore.noteScoredInAmp();
     *     System.out.println(robotScore.totalScore());
     * 
     * Should display:
     * 
     *   7
     * 
     */

     
    /**
     * # Computing the total score for an entire alliance
     * 
     * Create a class 'frc.AllianceAutoScore' with final fields 'robotAScore' (RobotAutoScore), 'robotBScore' (RobotAutoScore) and 'robotCScore' (RobotAutoScore). The constructor should take a value for these as parameters.
     * Write a method 'totalScore' in 'frc.AllianceAutoScore' which compute the total score for the alliance.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     *     RobotAutoScore robotAScore = new RobotAutoScore();
     *     robotAScore.noteScoredInSpeaker();
     *     robotAScore.noteScoredInAmp();
     *     RobotAutoScore robotBScore = new RobotAutoScore();
     *     RobotAutoScore robotCScore = new RobotAutoScore();
     *     robotAScore.noteScoredInSpeaker();
     *     AllianceAutoScore allianceScore = new AllianceAutoScore(robotAScore, robotBScore, robotCScore);
     *     System.out.println(allianceScore.totalScore());
     * 
     * Should display:
     * 
     *   12
     * 
     */

     
    
}
