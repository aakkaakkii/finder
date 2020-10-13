package com.finder.filestorage.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    @Value("${amazon.bucket.accessKey}")
    private String amazonBucketAccessKey;
    @Value("${amazon.bucket.secretKey}")
    private String amazonBucketSecretKey;
    @Value("${amazon.bucket.region}")
    private String amazonBucketRegion;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                amazonBucketAccessKey,
                amazonBucketSecretKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withRegion(amazonBucketRegion)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
