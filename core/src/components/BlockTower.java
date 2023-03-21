package components;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class BlockTower {

    private List<Integer> blockList = new ArrayList<Integer>();
    private int height;
    private Dictionary numberToColor = new Hashtable();

    // Constructor with input height, normally 50
    public BlockTower(int height){
        this.height = height;
        Random random = new Random();

        int previousNumber = 100; // not 0, 1, 2 or 3
        for(int i = 0; i < height; i++){
            int zeroToThree = random.nextInt(4);
            while (zeroToThree == previousNumber) {
                zeroToThree = random.nextInt(4);
            }
            previousNumber = zeroToThree;
            blockList.add(zeroToThree);
        }

        // Shows which number is which color in the dictionary
        numberToColor.put(0, "red");
        numberToColor.put(1, "orange");
        numberToColor.put(2, "green");
        numberToColor.put(3, "yellow");
    }

    // get copy of current list
    public List<Integer> getCopyOfCurrentList() {
        List<Integer> newList = new ArrayList<Integer>();
        for(Integer i : blockList) {
            newList.add(i);
        }
        return newList;
    }

    // get color of number
    public String getColorOfNumber(int numberBlock) {
        return (String) numberToColor.get(blockList.get(0));
    }

    // Getters and setters for the various variables
    public int getHeight() {
        return height;
    }

    // get current height of list
    public int getCurrentHeight() {
        return blockList.size();
    }

    // remove and return next block
    public int popNextBlock() {
        int nextNumber = blockList.get(0);
        blockList.remove(0);
        return nextNumber;
    }

    // get number of next block
    public int getNextBlock() {
        return blockList.get(0);
    }

    // get the color of the next block
    public String getColorOfNextBlock() {
        return (String) numberToColor.get(blockList.get(0));
    }

    // check if the next block is the same number as the argument
    public boolean checkNextBlock(int number) {
        return blockList.get(0) == number;
    }

    // check if the next block has the same color as the argument
    public boolean checkNextBlock(String color) {
        return numberToColor.get(blockList.get(0)) == color;
    }

    // Main
    public static void main(String[] args) {
        BlockTower bt = new BlockTower(50);

        for (int i = 0; i < 50; i++) {
            System.out.println(bt.getCurrentHeight());
            System.out.println(bt.getColorOfNumber(bt.getNextBlock()));
            System.out.println(bt.popNextBlock());
        }
    }
}
