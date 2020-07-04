package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ImageWriter
 * @author Ruth Miller
 * ruthmiller2000@gmail.com
 */
class ImageWriterTest {
    ImageWriter imWriter = new ImageWriter("grid_test", 1600, 1000, 800, 500);

    @Test
    void writeToImage() {
        int Nx = imWriter.getNx();
        int Ny = imWriter.getNy();
        for(int j = 0; j < Nx; j++){
            for(int i = 0; i < Ny; i++){
                if (i % 30 == 0 || j % 30 == 0) {
                    imWriter.writePixel(j, i, new Color(45,24,212).getColor());
                } else {
                    imWriter.writePixel(j, i, new Color(214,36,65).getColor());
                }
            }
        }
        imWriter.writeToImage();
    }
}