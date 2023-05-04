package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void initialImage() {

        ImageWriter imageWriter=new ImageWriter("intial image-stage5",800,500);
        int counterRow=1,counterColumn=1;
        for (int row=0;row<500;row++){
            for (int column=0;column<800;column++){

                /*
                if(counterRow%48==1||counterRow%48==2||counterColumn%48==1||counterColumn%48==2)
                    //write red
                    imageWriter.writePixel(row,column, new Color(3,3,3));
                else
                    //write yellow
                counterColumn+=1;

                 */

                if((row%50 == 2) || ((row+1)%50 ==2))
                    imageWriter.writePixel(row,column, new Color(3,3,3));

                else
                    if ((column%50 == 2) || ((column+1)%50 ==2))
                        imageWriter.writePixel(row,column, new Color(3,3,3));
                    else
                        imageWriter.writePixel(row,column, new Color(7,0,4));


            }
            counterRow+=1;
        }
    }

    @Test
    void writePixel() {
    }
}