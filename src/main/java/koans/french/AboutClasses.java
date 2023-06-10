package koans.french;

public class AboutClasses {
    /**
     * Create a class 'utils.MathUtils'. In this class, create a method 'cube' which takes an integer and returns it's cube.
     * 
     * ---------   TIPS --------------
     * 
     * In Java, all your methods must go into classes. Classes are organizing methods together.
     * 
     * Classes are themselves organized into folders called 'packages'.
     * 
     * Packages start at the Java project root folder, generally 'src/main/java'.
     * For example, the package 'koans' is located in 'src/main/java/koans'.
     * 
     * Packages can be nested in each other. For example: package 'english' is located within package 'koans'.
     * Classes in the package 'koans.english' go to 'src/main/java/koans/english'.
     * 
     * Notice: in Java, when we need to locate something within something else, we use the dot notation.
     * For example, the class 'AboutClasses' within the 'english' package within the 'koans' package is noted:
     * 
     *     koans.english.AboutClasses
     * 
     * To create a class, create a file named '[class name].java' in its package folder. You may have to create the folder first.
     * 
     * For example, a 'frc.Robot' class would go in a 'Robot.java' file within the 'src/main/java/frc' folder.
     * 
     * In your file, declare your class this way:
     * 
     *     // The very first line must declare the Class' package
     *     package frc;
     * 
     *     public class Robot {
     *         // Methods of class Robot will go here
     *     }
     * 
     * Reminder: The cube of a number is that number multiplied 3 times by itself.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * utils.MathUtils.cube(2) should return 8
     * 
     */


    /**
     * Using utils.MathUtils.cube, create a method 'displayCube' in AboutClasses which displays the cube of a number in the console.
     * 
     * ---------   TIPS --------------
     * 
     * To call a method in a class, you must first "import" that class with its package name.
     * Imports go after the package declaration, but before the class. Ex:
     * 
     *     // The package of the class you are currently creating
     *     package mypackage;
     *
     *     // Import all the classes you will be using in this file here
     *     import frc.Robot;
     *     import utils.MathUtils;
     * 
     * You can then call methods on imported classes:
     * 
     *     public class MyClass {
     *         public static void illuminateRobot() {
     *             Robot.tunOnDELs(); // Use an imported class by calling one of its methods
     *         }
     *     }
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * displayCube(3) should display '27' in the console.
     */


    /**
     * Create a class 'utils.math.OtherMathUtils'. In this class, create a method 'max' which takes 2 integers and returns the greatest of them.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * utils.math.OtherMathUtils.max(2, 3) should return 3
     * 
     */


    /**
     * Using utils.math.OtherMathUtils.max, create a method 'displayMax' in AboutClasses which display the greater of 2 integers.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * displayMax(5, -1) should display '5' in the console.
     * 
     */


    /**
     * Using the min method created in a previous Koan, create a method 'displayMin' in AboutClasses which display the lesser of 2 integers.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * displayMin(5, -1) should display '-1' in the console.
     * 
     */


}
