import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Crawler{
	private static final int MAX_NO_OF_THREADS=50;
	public WorkerThread mThreads[];
	private IndexedFilesHolder mFiles;
	private Lock mLock;
	
	public Crawler(){
		mFiles = new IndexedFilesHolder();
		mThreads = new WorkerThread[MAX_NO_OF_THREADS];
		mLock = new ReentrantLock();
	}
	
	public void CrawlFiles(File dir) {
			int threadNumber=0;
			Vector<String> tempVector;
			try {
				File[] files = dir.listFiles();
				for (File file : files) {
					if(!file.isHidden()){
						if (file.isDirectory()) {
							//System.out.println("directory:" + file.getCanonicalPath());
							if(threadNumber<MAX_NO_OF_THREADS){
								mThreads[threadNumber]=new WorkerThread(file,this);
								mThreads[threadNumber].start();
								threadNumber++;
							}
							else{
								CrawlFiles(file);
							}
						} else {
							mLock.lock();
							//System.out.println("     file:" + file.getCanonicalPath());
							if(mFiles.mIndexedFiles.containsKey(file.getName())){
								mFiles.mIndexedFiles.get(file.getName()).add(file.getCanonicalPath());
							}else{
								tempVector = new Vector<String>();
								tempVector.add(file.getCanonicalPath());
								mFiles.mIndexedFiles.put(file.getName(), tempVector);
							}
							mLock.unlock();
						}
					}
					else
					{
						System.out.println(file+". This file is hidden.");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	public String SearchFile(String fileName){
		String temp="";
		if(mFiles.mIndexedFiles.containsKey(fileName)){
			System.out.println(mFiles.mIndexedFiles.get(fileName));
			temp = mFiles.mIndexedFiles.get(fileName).toString();
		}
		else{
			System.out.println("File not found.");
			temp="File not found.";
		}
		return temp;
	}
}