/*
LANG: JAVA
TASK: Part 2: Marlin’s image
*/

import java.io.*;
import java.util.*;

public class Image {
    public static void main (String[] args) throws IOException {

        // BufferedReader is a Java class to read the text from an input file
        // faster than RandomAccessFile
        BufferedReader f = new BufferedReader(new FileReader("src/Image.in"));

        // Printwriter prints text to an output file
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/stdout")));

        // parsing N (image side length)
        int nValue = Integer.parseInt(f.readLine());

        //creating arraylist for pixel values
        ArrayList<Integer> pixels = new ArrayList<>();

        // iteration to read in values from input file
        for (int i = 0; i < nValue; i++) {

            // reading in line and splitting into x and y values of coordinates
            String value = f.readLine();
            String[] token = value.split(";");

            //adding input to arraylist of pixel values
            for(int j=0; j< nValue; j++){
                pixels.add(Integer.parseInt(token[j]));
            }
        }

        //using helper method to find difference between the highest and lowest color channel value
        double difference= findMaxMinDifference(pixels);


        //if the difference is greater than or equal to 10, modify color channel
        if(difference>=10){

            //calculating value to add to every single pixel value that is less than 100
            double addValue= 0.1*difference;

            //for each pixel value in arraylist
            for(int i=0; i<nValue*nValue; i++){

                //if the pixel value is less than 100
                if(pixels.get(i)<100){

                    //add 0.1*the difference to the pixel value
                    int roundedValue= (int) Math.round(pixels.get(i)+addValue); //round it to nearest integer
                    pixels.set(i, roundedValue); //modify existing pixel value
                }
            }
        }

        //for loop to print modified values in same format NxN
        for(int i=0; i<pixels.size(); i++){

            //printing value to output file
            out.print(pixels.get(i));

            //if the next value will be in a new row, create new line
            if((i+1)%nValue==0){
                out.println();
            } else { //otherwise put a semicolon between values
                out.print(";");
            }

        }

        //close output file
        out.close();

    }

    //finding the highest and lowest value of the color channel
    // and returning the difference between them
    public static double findMaxMinDifference(ArrayList<Integer> arr)
    {
        //setting max and min to first pixel value in arraylist
        double max=arr.get(0);
        double min= arr.get(0);


        for(int i=1; i<arr.size(); i++){

            //if the current pixel value is greater than the current maximum,
            //replace the maximum pixel value with the current pixel value
            if(arr.get(i)>max){
                max= arr.get(i);
            }

            //if the current pixel value is less than the current minimum,
            //replace the minimum pixel value with the current pixel value
            if(arr.get(i)<min){
                min=arr.get(i);
            }
        }
        //returning the difference between the maximum and minimum pixel values
        return max-min;
    }


}
