package ProjectThree350;

import java.util.Scanner;

/**
  * Factory class to start five threads.  *
**/

public class Factory {
    public static void main(String args[]) {
        
        Scanner input = new Scanner(System.in);     
        System.out.println("Input the total memory size (in bytes): ");
            int mem = input.nextInt(); // the total memory we have available
        System.out.println("Input the memory size of each frame (in bytes): ");
            int frameSize = input.nextInt(); // variable stores the size of each frame
            input.close();
        
        if (frameSize > mem) {
            frameSize = mem;
        }
        
    PhysicalMemory physicalMemory = new PhysicalMemory(mem, frameSize);
    Thread[] threadArray = new Thread[5];
    
    for(int i=0; i<5 ;i++)
        threadArray[i] = new Thread(new MemoryThread(physicalMemory, i)); //i is threadID
    
      for(int i=0; i<5; i++)
        threadArray[i].start();
    }
}