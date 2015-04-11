package fine.project;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MavenRepoLinkExtractor implements LinkExtractor {
	private HttpClient httpclient;
	
	public MavenRepoLinkExtractor(HttpClient httpclient) {
		this.httpclient = httpclient;
	}

	public List<String> getLinks(String url) throws Exception {
		List<String> links = new ArrayList<String>();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpclient.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			ContentType contentType = ContentType.getOrDefault(entity);
			/*if (ContentType.TEXT_HTML == contentType) {*/
				String content = EntityUtils.toString(entity);
				Elements linkElements = Jsoup.parse(content).select("a[href]");
				for (Element element : linkElements) {
					String href = element.attr("abs:href");
					links.add(href);
				}

			}/* else {
				EntityUtils.toByteArray(entity);
			}*/
			EntityUtils.consume(entity);
//		}
		return links;

	}

	public HttpClient getHttpclient() {
		return httpclient;
	}

	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
}
