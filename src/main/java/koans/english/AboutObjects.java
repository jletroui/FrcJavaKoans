package koans.english;

public class AboutObjects {
    /**
     * Create a class 'geom.Point' with 2 final private decimal members 'x' and 'y'. The constructor should take 'x' and 'y' as parameters.
     * 
     * ---------   TIPS --------------
     * 
     * Up to now, we only attached methods to the classes themselves. But classes can be much more powerful than that.
     * 
     * A class can serve as a template for things called objects. Objects are grouping methods and data.
     * 
     * For example, we could say we need to represent a Person in the code, such that each person could have a name and an age, and an 'introduce' method.
     * 
     * The name and age are kind of variables attached to the object, and can be different from one person to the other. A 'variable' attached to an object is called a 'field'.
     * 
     * The 'introduce' method, similarly, is attached to the object. Calling it on 2 different persons can produce a different result if it is using fields.
     * 
     * You can create an object of a given class by calling a very special method called a constructor. The constructor... constructs an object of the given class.
     * 
     * The result of a constructor is a value, which type is the class itself.
     * You call a constructor by using the 'new' keyword, and the name of the class.
     * Let's see how that goes with a Person class:
     * 
     *     // The constructor of a person takes their name and their age
     *     Person julien = new Person("Julien", 44);
     *     Person stephane = new Person("Stephane", 26);
     * 
     * Now that we have 2 person objects, we can call their methods. We said in our example persons had an 'introduce' method. Let's call it:
     * 
     *     julien.introduce();
     * 
     * Result in the console:
     * 
     * Hello, my name is Julien and I am 44
     * 
     * What happens if we call the same method on stephane?
     * 
     *     stephane.introduce();
     * 
     * Result in the console:
     * 
     * Hello, my name is Stephane and I am 26
     * 
     * A different result! This is because a method attached to an object can access the object's fields, which can be different from one object to the other.
     * 
     * What happens if we call the method on the class itself?
     * 
     *     Person.introduce(); // Error, this is not valid Java
     * 
     * This is producing an error, because the method is attached to the objects of the Person class, not the class itself.
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
     *             // In an object's method, we can use object fields
     *             System.out.println("Hello, my name is " + name + " and I am " + age);
     *         }
     *     }
     * 
     * Like variables, fields have a type and a name. Unlike variables, we must specify if they are visible or not by code outside the class.
     * Although Java allows to have fields visible outside the class, it is a bad code design. So they always should be 'private'.
     * They also should be 'final', meaning that once their values is assigned in the constructor, they will never change.
     * 
     * But what about the constructor? Constructors are special methods.
     * They don't specify a return type, because the return type of a constructor is always the Class.
     * And their name is simply the name of the class. Ex:
     * 
     *     public class Person {
     *         // Fields go at the beginning of the class.
     *         private final String name;
     *         private final int age;
     *         
     *         // No return type. Name = name of the class.
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
     * We use the 'this' keyword to differentiate the constructor parameters 'name' and 'age' from the object's fields.
     * 'this' is a kind of special variable pointing to the current object. So 'this.[name of a field]' refers to the object's field.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * we can create a new geom.Point object
     * 
     */


    /**
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
     * Create a method 'translate' in 'geom.Point' which takes 2 translation coordinates in x and y, and returns a new point object, which is the initial point translated by the given coordinates.
     * 
     * ---------   TIPS --------------
     * 
     * When we translate by xT and yT a point A of coordinates xA and xB, the resulting point has the coordinates xA+xT and yA+yT.
     * 
     * You will need to create a new object of type point to return it, so you might need a refresher on how to create objects in the first exercise of this series.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * new Point(2.5, 4.3).translate(2, -1) should return a point with coordinates 4.5 and 3.3
     * 
     */


    /**
     * Create a class 'geom.Triangle' with 3 final private Point members 'a', 'b', and 'c'. The constructor should take 'a', 'b', and 'c' as parameters.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * we can create a new geom.Triangle object
     * 
     */


    /**
     * Using 'Point.toString', write a method 'toString' in 'geom.Triangle' which returns a text representation of a Triangle object as follow:
     * 
     * Triangle(Point([xA value], [yA value]), Point([xB value], [yB value]), Point([xC value], [yC value]))
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * new Triangle(new Point(1.0, 1.0), new Point(2.0, 2.0), new Point(3.0, 3.0)).toString() should return the String "Triangle(Point(1.0, 1.0), Point(2.0, 2.0), Point(3.0, 3.0))"
     * 
     */


    /**
     * Using 'Point.translate', write a method 'translate' in 'geom.Triangle' which takes 2 translation coordinates in x and y, and returns a new triangle object, which is the initial triangle translated by the given coordinates.
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * new Triangle(new Point(1.0, 1.0), new Point(2.0, 2.0), new Point(3.0, 3.0)).translate(2.0, -1.0) should return a triangle with:
     * 
     *   - Point a = Point(3.0, 0.0)
     *   - Point b = Point(4.0, 1.0)
     *   - Point c = Point(5.0, 2.0)
     * 
     */
}
