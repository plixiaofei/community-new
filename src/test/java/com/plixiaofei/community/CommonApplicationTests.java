package com.plixiaofei.community;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.analysis.AnalyzerBuilders;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.plixiaofei.community.component.Producer;
import com.plixiaofei.community.domain.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SimpleMailMessage mailRabbitmqAt;
    @Test
    void sendMessage() {
        // to 多用户
        mailRabbitmqAt.setSubject("测试 Subject");
        mailRabbitmqAt.setText("测试文本");
        javaMailSender.send(mailRabbitmqAt);
    }


    @Autowired
    private Producer producer;
    @Test
    void testMQ() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("index", i);
//            mailRabbitmqAt.setSubject("测试主题" + i);
//            mailRabbitmqAt.setText("测试内容" + i);
//            javaMailSender.send(mailRabbitmqAt);
            producer.sendAt(map);
        }
    }
}
