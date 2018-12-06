package com.airboard.airbd.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @Description:
 * @Auther: Wansghuo
 * @Date: 2018/11/27 16:22
 */
@Data
@Document(indexName = "company", type = "user", shards = 1, replicas = 0, refreshInterval = "-1")
public class User {

    @Id
    private Long id;
    @Field
    private String userName;
    @Field
    private String passWord;
    @Field
    private String remark;

}
