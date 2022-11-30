package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 * This is the lab05 of CS61B 2018spring. The author is @GreyPreacher
 */
public class HexWorld {
    public static void addHexagon(int s){
        int space = s - 1;
        int draw = s;
        for (int i = 0; i < s; i++){//2*s rows in total, the upper s rows
            for (int j = 0; j < space; j++) {
                System.out.print(" ");//s - 1 space in the first row
            }
            for (int j = 0; j < draw; j++){
                System.out.print("*");
            }
            System.out.print("\n");
            space--;
            draw += 2;
        }
        draw -= 2;
        space ++;
        for (int i = 0; i < s; i++){//the lower s rows
            for (int j = 0; j < space; j++) {
                System.out.print(" ");//s - 1 space in the first row
            }
            for (int j = 0; j < draw; j++){
                System.out.print("*");
            }
            System.out.print("\n");
            space++;
            draw -= 2;
        }
    }
    public static void main(String[] args){
        addHexagon(10);
    }
}
