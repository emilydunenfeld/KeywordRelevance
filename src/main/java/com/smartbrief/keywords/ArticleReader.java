package com.smartbrief.keywords;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@Component
public class ArticleReader {
	private final File folder;
	//private final Multimap<NodeId, ScoredArticle> map = HashMultimap.create();

	public ArticleReader(@Value("#{systemProperties['folder'] ?: 'articlesToRead'}") String folder) {
		this.folder = new File(folder);
	}

	// Returns list of node ids
	public Collection<NodeId> loadNodeIds() throws Exception {
		Collection<NodeId> nodeIds = new ArrayList<>();
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.getName().endsWith(".json")) {
				NodeId nodeId = new NodeId(UUID.fromString(file.getName().substring(5, 41).toLowerCase()));
				nodeIds.add(nodeId);
			}
		}
		return nodeIds;
	}

	// Returns multimap of inputed node id with corresponding scored articles
	public Multimap<NodeId, ScoredArticle> loadArticlesByNode(UUID id) throws Exception {
		Multimap<NodeId, ScoredArticle> map = HashMultimap.create();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
		StringBuilder fileNameOfId = new StringBuilder("node-" + id.toString().toUpperCase() + ".json");
		NodeId nodeId = new NodeId(id);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.getName().equals(fileNameOfId.toString())) {
				InputStream stream2 = new FileInputStream(file);
				List<ScoredArticle> scoredArticles = Arrays.asList(mapper.readValue(stream2, ScoredArticle[].class));
				stream2.close();
				for (ScoredArticle scoredArticle : scoredArticles) {
					map.put(nodeId, scoredArticle);
				}
			}
		}
		return map;
	}
}