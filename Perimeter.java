/*
LANG: JAVA
TASK: Part 1: Count the perimeter
*/

import java.io.*;

public class Perimeter {
    public static void main (String[] args) throws IOException {
        // BufferedReader is a Java class to read the text from an input file
        // faster than RandomAccessFile
        BufferedReader f = new BufferedReader(new FileReader("src/perimeter.in"));

        // Printwriter prints text to an output file
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/stdout")));

        // parsing N amount of coordinates
        int nValue = Integer.parseInt(f.readLine());

        // creating empty 2d array with N rows and 2 columns holding x and y value of coordinates
        double[][] coordinates = new double[nValue][2];

        // iteration to read in coordinates from input file
        for(int point=0; point<nValue; point++){

            // reading in line and splitting into x and y values of coordinates
            String coordinate= f.readLine();
            String[] token= coordinate.split(";");
            coordinates[point][0]=Double.parseDouble(token[0]); //x value
            coordinates[point][1]=Double.parseDouble(token[1]); //y value
        }

        double sum=0; //variable to store sum of perimeters

        //The following algorithm uses 3 for loops to check if 3 coordinates form a right triangle with sides parallel to
        //the x-axis and another side parallel to the y axis. This is done by finding a qualified right angle vertex by
        // checking for its x-value to match another coordinate's x-value and for its y-value to match
        // another coordinate's y-value. Matching x-coordinates form the vertical leg of the triangle parallel to the y-axis while matching
        //y-coordinates form the horizontal leg of the triangle parallel to the x-axis. The distance between the two points
        //whose x or y coordinates do not match is the hypotenuse. Adding the three sides gives us our perimeter.


        //outer for loop serves as first marker
        for(int point1=0; point1<nValue; point1++){

            //1st inner loop starts at the next available coordinate in the 2d coordinates array, serves as second marker
            for(int point2=point1+1; point2<nValue; point2++){

                    //3rd inner loop starts at the next available coordinate in the 2d coordinates array, serves as third marker
                    for(int point3=point2+1; point3<nValue; point3++) {

                        //storing coordinates parsed into the 2d array as x and y values

                        double x1= coordinates[point1][0]; //(x1, y1)
                        double y1= coordinates[point1][1];
                        double x2= coordinates[point2][0]; //(x2, y2)
                        double y2= coordinates[point2][1];
                        double x3= coordinates[point3][0]; //(x3, y3)
                        double y3= coordinates[point3][1];

                        //checking if any coordinate (from the 3 coordinates above) has the same x-value and same y-value
                        //as the other two coordinates. This coordinate is the right angle vertex.
                        if(((x1==x2 || x1==x3) && (y1==y2 || y1==y3)) ||
                                ((x2==x3 || x2==x1) && (y2==y1 || y2==y3)) ||
                                ((x3==x1 || x3==x2) && (y3==y1 || y3==y2))
                        ){
                            //calculating perimeter of triangle using static helper method and adding it to total sum of perimeters
                            sum+= computePerimeter(x1,y1,x2,y2,x3,y3);
                        }

                    }
            }
        }

        //rounding sum of perimeters to nearest integer and printing it to stdout
        out.println((int) Math.round(sum)); // output result
        out.close();

    }

    public static double computePerimeter(double x1, double y1, double x2, double y2, double x3, double y3){
        //calculating individual distances between points (legs and hypotenuse)
        double dist1= Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
        double dist2= Math.sqrt(Math.pow(x2-x3,2)+Math.pow(y2-y3,2));
        double dist3= Math.sqrt(Math.pow(x3-x1,2)+Math.pow(y3-y1,2));

        //adding side lengths into perimeter and returning it
        return dist1+dist2+dist3;
    }


}
