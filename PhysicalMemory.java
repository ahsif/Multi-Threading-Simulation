package ProjectThree350;
import java.util.concurrent.Semaphore;
public class PhysicalMemory {
    
    
    private Boolean[] availableFrames;
    private static Semaphore lock = new Semaphore(1); 
    public PageTableList PTL = new PageTableList(); // We are creating a new page table list for use in the entire program.
    
    
    PhysicalMemory(int mem, int frameSize) {
        availableFrames = new Boolean[(mem / frameSize)]; 
        for(int i = 0; i < availableFrames.length; i++){
          availableFrames[i] = false; //[F,F,F,F,F .... F]
        }
    }
    
    public Boolean[] getFrames(){
        return availableFrames;
    }

  
  /**********************************************************/
    
    public boolean requestMemory(int id, int requestLength) {
        /* If the memory has enough free frames to satisfy this request, allocate the frames to the thread and build the page table.
        @param id the threadID of the requesting thread
        @param requestLength the number of pages the thread requests
        @return If the request can be satisfied (which means the freeframes in memory is greater or equal to the requestLength), 
        return true else return false*/ 

        // try catch block to aquire the lock, otherwise there will be an exception thrown.
        try{ lock.acquire();}
        catch(InterruptedException ie) {System.out.println("cant aquire the lock");}
        int count = 0;
        
        for (Boolean bool1 : this.getFrames()) { //finds how many empty frames we have
            if (bool1 == false) {
                count++;
            }
        }
        
        if(requestLength > count){ 
          lock.release();
          return false;
          } //when we exit the method we need to release the lock 
           
        
        else{
          PageTable currPT = new PageTable(id, requestLength);//create the page table here for this thread
          PTL.addFirst(currPT); //add the new page table to the PageTable Linked List.
            for(int i = 0; i < requestLength; i++){ // loops until we have allocated all of the memory
                for(int j = 0; j < availableFrames.length; j++){ // allocate the memory to the next empty index in the boolean array
                    if(availableFrames[j] == false){ //if this frame isn't occupied, we occupy it.
                        currPT.appendEntry(j);//append to the list within the newly created page table the current frame number that we are occupying.
                        //perform the action needs to preform the action of thread id
                        availableFrames[j] = true;
                        count++;
                        break;
                    }}}
            lock.release();
            return true;}} //when we exit the method we need to release the lock 
        
    public int accessMemory (int id, int pageNumber) {
        /* Convert logical page number to physical frame number. 
        @param id the threadID of the requesting thread
        @param pageNumber the page number the thread wants to access
        @return the frameNumber (physical address) of the pageNumber(logical address)*/
                if (PTL == null)
        	return -1;

        return PTL.lookup(id).lookupPageTable(pageNumber);
    }
    
    public void releaseMemory(int id) {
        /* Set the frames allocated to the calling thread to free 
        @param id the threadID of the calling thread */ 
        //this is like the reverse of the else statement for request memory - AHSIF.
        try{ lock.acquire();} // do we need to lock here?
        catch(InterruptedException ie) {System.out.println("cant aquire the lock");}
            for(int i = 0; i < PTL.lookup(id).getLength(); i++){
                availableFrames[PTL.lookup(id).lookupPageTable(i)] = false;
            }
        lock.release();
    }
}