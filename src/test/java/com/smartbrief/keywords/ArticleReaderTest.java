package com.smartbrief.keywords;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Multimap;

public class ArticleReaderTest {
	private static final String FOLDER = "testArticlesToRead";
	private static final ArticleReader articleReader = new ArticleReader(FOLDER);
	private static final UUID TEST_NODE = UUID.fromString("9c15c54b-c556-4a90-8c35-742563264288");
	private static final String TEST_HEADLINE = "test headline";
	private static final String TEST_FULLBODY = "test full body";
	private static final String TEST_LEADTEXT = "test lead text";

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testLoadNodeIds() throws Exception {
		Collection<NodeId> value = articleReader.loadNodeIds();
		Collection<NodeId> expected = new ArrayList<>();
		NodeId nodeId = new NodeId(TEST_NODE);
		expected.add(nodeId);
		assertEquals(value.size(), 1);
		assertEquals(value.toString(), expected.toString());
	}

	@Test
	public void testLoadArticlesByNode() throws Exception {
		Multimap<NodeId, ScoredArticle> value = articleReader.loadArticlesByNode(TEST_NODE);
		ScoredArticle scoredArticle = new ScoredArticle(TEST_HEADLINE, TEST_FULLBODY, TEST_LEADTEXT);
		NodeId nodeId = new NodeId(TEST_NODE);
		assertEquals(value.values().iterator().next(), scoredArticle);
		assertEquals(value.keys().iterator().next(), nodeId);
		assertEquals(value.size(), 1);
	}
}