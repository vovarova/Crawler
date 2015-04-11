package fine.project;

import org.apache.http.impl.client.HttpClients;

public class App {
	public static void main(String[] args) {
		String url = "http://www.imdb.com/list/ls051756557/"; 
		if(args.length>0){			
			url = args[0];
		}
		TaskExecutor taskExecutor = new TaskExecutor(100);
		MavenRepoLinkExtractor mavenRepoLinkExtractor = new MavenRepoLinkExtractor(HttpClients.createSystem());
		LinkCrawler linkCrawler = new LinkCrawler();
		linkCrawler.setLinkExtractor(mavenRepoLinkExtractor);
		linkCrawler.setUrl(url);
		linkCrawler.setTaskExecutor(taskExecutor);
		taskExecutor.executeTask(linkCrawler);
	}
}
