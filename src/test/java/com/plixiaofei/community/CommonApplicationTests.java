package com.plixiaofei.community;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.analysis.AnalyzerBuilders;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.plixiaofei.community.domain.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class CommonApplicationTests {
    @Autowired
    ElasticsearchClient esClient;

    @Test
    void contextLoads() {
    }

    @Test
    void testIndex() {
        try {
            esClient.indices().create(c -> c.index("question").settings(s -> s.analysis(i -> i.analyzer("title", AnalyzerBuilders.custom().tokenizer("ik_smart").build()._toAnalyzer()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testPut() {
        Question question = new Question();
        question.setId(1L);
        question.setTitle("我是个坏坏的人");
        question.setShortDescription("这题怎么做");
        question.setFullDescription("这题好难啊，该怎么做");
        try {
            esClient.index(i -> i
                    .index("question")
                    .id(question.getId() + "")
                    .document(question));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearch() {
        try {
            SearchResponse<Question> search = esClient.search(s -> s.index("question")
                            .query(q -> q.match(f -> f.field("title")
                                    .query("坏")
                            )).highlight(h -> h.fields("title", f ->
                                    f.preTags("<font color='red'>")
                                            .postTags("</font>")))
                    , Question.class);
            TotalHits total = search.hits().total();

            List<Hit<Question>> hits = search.hits().hits();
            hits.forEach(h -> h.source().setTitle(h.highlight().get("title").toString()));
            hits.forEach(e -> System.out.println(e.source().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testDel() {
        DeleteRequest.Builder deleteRequest = new DeleteRequest.Builder();
        deleteRequest.index("question").id(1 + "");
        try {
            esClient.delete(deleteRequest.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
