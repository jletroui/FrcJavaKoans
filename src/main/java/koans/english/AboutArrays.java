package koans.english;

public class AboutArrays {
    /**
     * # Indexing an array
     * 
     * Define the method 'valueAtIndex' which as a parameter for an interger array and an index, and returns the value at that index 
     * 
     * ---------   TIPS --------------
     * 
     * Arrays are used to store multiple values in a single variable, instead of declaring separate variables for each value.
     * 
     * In Java arrays can be formed from any data type, but all values in the array must be the same type. They are defined
     * by listing the type followed by '[]' and then a name for the variable.
     * 
     *     type brackets name
     *       v    v      v
     *      String[] cars;
     * 
     * Values can be add into the array using a comma seperated list and are fixed in size when they are defined.
     * 
     *      String[] cars = {"BMW", "Ford", "Tesla"};
     * 
     * Array can be created using any type and are defined the same way.
     * 
     *      int[] nums = {10, 20, 30, 40, 50};
     * 
     * But whats the point of storing the values in these lists. Glad you asked! You can access these values by referencing the
     * name of the array and the index at which the value is stored. However there is one trick to watch out for. Most programming langauges
     * start the index count from 0. So that means the first value of the array is actaully at index 0 and not index 1;
     * 
     *      nums[0] -> 10;
     *      cars[2] -> "Tesla"
     * 
     * -------------------------------
     * 
     * Expected result:
     * 
     * valueAtIndex({20, 15, 10, 5, 0}, 1) should return 15;
     * 
     */

    public static int valueAtIndex(int[] array, int index){
        return array[index];
    }
    

    /**
     * # Searching an Array
     * 
     * Define the method 'indexOfValue' which as a parameter for an interger array and an value, and returns the index of that value. 
     *  - If the value cannot be found in the array return -1. 
     *  - The method should return the first instance of the number if it happens to appear more than once in the array.
     * 
     * ---------  TIPS --------------
     * 
     * Arrays have helpful properties that you can access to get information about their size. This is very helpful for looping through array if
     * their size is unknown. You can use the 'length' property to get the size of an array. Remeber though that the index counting may start
     * from 0 but that doesn't mean you starting count from 1 for the size.
     * 
     *      int[] nums = {5, 10, 15, 20, 25};
     *      int length = nums.length;           <---- length will equal 5
     * 
     * ------------------------------
     * 
     * THE ARRAYS ARE NOT SORTED
     * 
     * Expected result:
     * indexOfValue({20, 10, 15, 5, 25}, 3) should return 5;
     * 
     */
    public static int indexOfValue(int[] array, int value){
        int count = 0;
        
        while(count < array.length-1 && value != array[count]){

            count++;

        }
        if(value == array[count]){

            return count;

        } else {

            return -1;

        }
    }

    /**
     * # Sorting an Array
     * 
     * Define the method 'sortArray' which as a parameter fo an interger array, and returns the array sorted in rising order
     * 
     * ---------  TIPS -------------- 
     * 
     * Sometimes it is helpful to sort an array for easier access or searching. In order to do that we will need to be able to edit already existing
     * array values. Lucky for us there is away to do that.
     * 
     *      int[] nums = {10, 11, 12, 13, 15};
     *      nums[4] = 14;                       <---- nums now equals {10, 11, 12, 13, 14}
     * 
     * -------------------------------
     * 
     * Expected result:
     * sortArray({20, 10, 15, 5, 25}) should return {5, 10, 15, 20, 25};
     *  
     */

     public static int[] sortArray(int[] array){
        int[] temp = new int[array.length];
        int min = 0;
        for(int i = 0; i < temp.length; i++){
            min = array[i];
            for(int j = i; j < temp.length; j++){

                if(array[j] < min){

                    int tempInt = min;
                    min = array[j];
                    array[j] = tempInt;

                }

            }

            temp[i] = min;

        }

        return temp;
     }

}
