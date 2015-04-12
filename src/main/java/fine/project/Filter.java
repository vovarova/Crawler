package fine.project;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Filter {
	private String baseHost;
	private Map<String,LinksContainer> linksMap=new ConcurrentHashMap<String, LinksContainer>();
	LinksContainer startLinksContainer;
	public Filter(String baseHost) {
		super();
		this.baseHost = baseHost;
	}

	public boolean needToExecute(String baseUrl, String link) {
		if(linksMap.get(link)==null && passFilter(link)){
			LinksContainer baseLinksContainer = linksMap.get(baseUrl);
			if(baseLinksContainer==null){
				baseLinksContainer = new LinksContainer(baseUrl);
				startLinksContainer = baseLinksContainer;
				linksMap.put(baseUrl, baseLinksContainer);
			}
			LinksContainer linkContainer = baseLinksContainer.addLink(link);
			linksMap.put(link, linkContainer);
			return true;
		}
		return false;

	}
	
	private boolean passFilter(String link){
		try {
			URL urlObj = new URL(link);
			String host = urlObj.getHost();
			return host.contains(baseHost);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	public String getBaseHost() {
		return baseHost;
	}

	public void setBaseHost(String baseHost) {
		this.baseHost = baseHost;
	}

	public Map<String, LinksContainer> getLinksMap() {
		return linksMap;
	}

	public void setLinksMap(Map<String, LinksContainer> linksMap) {
		this.linksMap = linksMap;
	}
}
