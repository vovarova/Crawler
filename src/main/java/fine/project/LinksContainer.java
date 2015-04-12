package fine.project;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LinksContainer {
	private String baseUrl;
	private Set<LinksContainer> container =new HashSet<LinksContainer>();
	
	public LinksContainer(String baseUrl) {
		this.baseUrl = baseUrl;
	}


	public LinksContainer addLink(String url){
		LinksContainer linksContainer = new LinksContainer(url);
		container.add(linksContainer);
		return linksContainer;
	}


	public String getBaseUrl() {
		return baseUrl;
	}


	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}


	public Set<LinksContainer> getContainer() {
		return container;
	}


	public void setContainer(Set<LinksContainer> container) {
		this.container = container;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SIMPLE_STYLE);
	}
	
}
