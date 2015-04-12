package fine.project;

import java.net.URL;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

public class App {
	static long stdate =System.currentTimeMillis();
	public static void main(String[] args) throws Exception {
		String url = "https://www.google.com/"; 
		if(args.length>0){			
			url = args[0];
		}
		
		
		TaskExecutor taskExecutor = new TaskExecutor(100);
		CloseableHttpClient createSystem = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0").setRedirectStrategy(new LaxRedirectStrategy()).setMaxConnTotal(200).build();
		MavenRepoLinkExtractor mavenRepoLinkExtractor = new MavenRepoLinkExtractor(createSystem);
		LinkCrawler linkCrawler = new LinkCrawler();
		Filter filter = new Filter(new URL(url).getHost());
		linkCrawler.setFilter(filter);
		linkCrawler.setLinkExtractor(mavenRepoLinkExtractor);
		linkCrawler.setUrl(url);
		linkCrawler.setTaskExecutor(taskExecutor);
		taskExecutor.filter = filter;
		taskExecutor.startMonitoring();
		taskExecutor.executeTask(linkCrawler);
	}
}
