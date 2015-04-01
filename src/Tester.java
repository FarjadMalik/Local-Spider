import java.awt.EventQueue;
import java.io.File;


public class Tester {
	private static Crawler crawler;
	
	public static void main(String[] args) {
		crawler = new Crawler();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocalSpiderGUI frame = new LocalSpiderGUI(crawler);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void CrawlStarted(String dir) {
		File currentDir = new File(dir); // current directory
		crawler.CrawlFiles(currentDir);
		for(int i=0;i<100;i++){
			try {
				if(crawler.mThreads[i]!=null){
					crawler.mThreads[i].join();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
