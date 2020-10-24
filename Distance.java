/*
LANG: JAVA
TASK: Part 3: Marlin’s Distance
*/

import java.io.*;
import java.util.*;

public class Distance {


    public static void main (String[] args) throws IOException {

        // BufferedReader is a Java class to read the text from an input file
        // faster than RandomAccessFile
        BufferedReader f = new BufferedReader(new FileReader("src/distance.in"));

        // Printwriter prints text to an output file
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/stdout")));

        // parsing parameters
        double FOV = Math.toRadians((Double.parseDouble(f.readLine())));
        String[] imageDimensions= f.readLine().split("; ");
        double blindingBox= Double.parseDouble(f.readLine());
        double distMoved = Math.abs(Double.parseDouble(f.readLine()));

        double answer =0;

        double halfAngle = FOV/2;
        int width = Integer.parseInt(imageDimensions[0]); //x coordinate

        //calculating the pixel distance from Marlin standing at the center of the pool to the object
        double distFromMarlinToObject = (width/2.0)/Math.sin(halfAngle);

        //calculating true distance to object in meters given blinding box conversion factor
        //linear formula
        double distToObjectInMeters = distMoved*blindingBox/distFromMarlinToObject;


        out.format("%.2f", distToObjectInMeters); // output true distance to nearest hundredth
        out.close();

    }



//        I researched some journal papers regarding object
//        distance tracking, and I got close in one paper
//        that discussed FOV and finding object distance
//        using openCV. I was stuck on understanding the blinding box
//        measure, and I
//        tried various parameters like a scaler based on dist moved,
//        a conversion factor between pixels and meters,
//        and a bounding box.


}
