package fine.project;

import java.util.List;

public class LinkCrawler implements Task {
	private TaskExecutor taskExecutor;
	private String url;
	private LinkExtractor linkExtractor;
	private Filter filter;
	

	protected LinkCrawler getNewCrawler(String url) {
		LinkCrawler linkCrawler = new LinkCrawler();
		linkCrawler.setLinkExtractor(linkExtractor);
		linkCrawler.setFilter(filter);
		linkCrawler.setUrl(url);
		linkCrawler.setTaskExecutor(taskExecutor);
		return linkCrawler;
	}

	public void run() {
		//System.out.println("Processing link "+url);
		try {
			List<String> links = linkExtractor.getLinks(url);
			for (String link : links) {
				if(filter!=null && filter.needToExecute(url,link)){
					LinkCrawler newCrawler = getNewCrawler(link);
					taskExecutor.executeTask(newCrawler);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LinkExtractor getLinkExtractor() {
		return linkExtractor;
	}

	public void setLinkExtractor(LinkExtractor linkExtractor) {
		this.linkExtractor = linkExtractor;
	}

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
}
