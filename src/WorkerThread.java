import java.io.File;


public class WorkerThread extends Thread {
	public File mFile;
	public Crawler mCrawler;
	
	public WorkerThread(){
		
	}
	
	public WorkerThread(File file,Crawler crawler){
		mCrawler= crawler;
		mFile = new File(file.toString());
		setDaemon(false);
		//System.out.println(mFile.getAbsolutePath());
	}
	
	
	public void run(){
		//mFile = new File();
		mCrawler.CrawlFiles(mFile);
	}

}
