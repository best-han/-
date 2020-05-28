package com.windaka.suizhi.common.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Map;

public class ElasticSearchUtil {
    private volatile static RestHighLevelClient client;
    private static String host="172.16.1.162";
    private static int port=9200;
    private ElasticSearchUtil(){

    }
    //双检锁（DCL）实现单例模式
    private static RestHighLevelClient getClient(){
        if(client==null){
            synchronized (ElasticSearchUtil.class){
                if(client==null){
                    client=new RestHighLevelClient(
                            RestClient.builder(new HttpHost(host, port, "http")));
                }
            }
        }
        return client;
    }
    //插入数据
    public static void insert(String index,String type,Map<String, Object> params){
        getClient();
        IndexRequest indexRequest = new IndexRequest(index, type)
                .source(params);
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                System.out.println(index+"."+type+":插入成功");
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println(index+"."+type+":异常"+e.toString());
            }
        };
        //异步插入
        client.indexAsync(indexRequest, listener);
    }
    //查询数据
    public static SearchResponse select(String index, String type, SearchSourceBuilder searchSourceBuilder){
        getClient();
        //建立请求
        SearchRequest searchRequest=new SearchRequest(index);//库名
        searchRequest.types(type);//表名
        //建立查询
        searchRequest.source(searchSourceBuilder);
        //同步查询
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest);
//            System.out.println(searchResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
    //初始化
    public static void getInitClient(){
        getClient();
    }
}
