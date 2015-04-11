package fine.project;

import java.util.List;

public interface LinkExtractor {
	List<String> getLinks(String url) throws Exception;
}