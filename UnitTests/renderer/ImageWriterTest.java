package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * test for image writer
 *
 * @author Noa Harel and Talel Ginsberg
 */
class ImageWriterTest {
    /*constants for resolution*/
    int WIDTH=800;
    int LENGTH=500;

    /**
     * Test method for {@link ImageWriter#writePixel(int, int, Color)}.
     */
    @Test
    void initialImage() {
        /*constructing an image based on information in directions*/
        ImageWriter imageWriter=new ImageWriter("initial image-stage5",WIDTH,LENGTH);

        /*creating wanted colors for the net and background*/
        Color background = new Color(java.awt.Color.cyan);
        Color net = new Color(java.awt.Color.blue);

        /*nested loop that goes through every pixel in grid and colors it*/
        for (int row=0;row<WIDTH;row++){
            for (int column=0;column<LENGTH;column++){

                /*for lines on net that are horizontal, for lines that are all net*/
                if ((row % 50 == 1) || ((row + 1) % 50 == 1))
                    imageWriter.writePixel(row, column, net);

                else
                    /*for vertical lines, since we are going through the pixels horizontally we will only reach 2 net
                     dots, every 50 pixels, as opposed to the whole line being net
                     */
                    if ((column % 50 == 1) || ((column + 1) % 50 == 1))
                        imageWriter.writePixel(row, column, net);

                    /*for background colored pixels*/
                    else
                        imageWriter.writePixel(row, column, background);
            }
        }
        /*create a png image*/
        imageWriter.writeToImage();
    }


}