package components;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class BlockTower {

    private List<Integer> blockList = new ArrayList<Integer>();
    private int height;

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
    }

    // get copy of current list
    public List<Integer> getCopyOfCurrentList() {
        List<Integer> newList = new ArrayList<Integer>();
        for(Integer i : blockList) {
            newList.add(i);
        }
        return newList;
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

    // check if the next block is the same number as the argument
    public boolean checkNextBlock(int number) {
        return blockList.get(0) == number;
    }

}
