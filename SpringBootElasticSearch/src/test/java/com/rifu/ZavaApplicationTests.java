package com.rifu;

import com.rifu.bean.Msg;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZavaApplicationTests {

    @Autowired
    JestClient jestClient;


    /**
     * 测试保存数据到elasticsearch
     */
    @Test
    public void testSaveData() {
        Msg msg = new Msg();
        msg.setId(1);
        msg.setSender("rifu");
        msg.setReceiver("lili");
        msg.setContent(" i love you");

        Index index = new Index.Builder(msg).index("zava").type("msg").build();

        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试查询数据
     */
    @Test
    public void testSelectData() {
        String json = "{\n" +
                "    \"query\": {\n" +
                "        \"match\": {\n" +
                "            \"content\": \"love\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = new Search.Builder(json).addIndex("zava").addType("msg").build();
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ==============使用SpringData-ElasticSearch==============
     */



}
