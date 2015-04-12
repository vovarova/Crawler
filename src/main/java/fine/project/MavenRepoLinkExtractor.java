package fine.project;

import java.io.IOException;
import java.net.URL;
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
			ContentType contentType = ContentType.get(entity);
			System.out.println("url " + url + " #" + contentType);
			if (contentType!=null && ContentType.TEXT_HTML.getMimeType().equals(contentType.getMimeType())) {
				String content = EntityUtils.toString(entity);
				Elements linkElements = Jsoup.parse(content).select("a[href]");
				for (Element element : linkElements) {
					String href = element.attr("href");
					links.add(new URL(new URL(url), href).toExternalForm());
				}

			} else {
				try{					
					EntityUtils.toByteArray(entity);
				}catch(IOException e){
					throw new IOException("Error while processing "+url , e);
				}
			}
			EntityUtils.consume(entity);
		}
		return links;

	}

	public HttpClient getHttpclient() {
		return httpclient;
	}

	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
}
