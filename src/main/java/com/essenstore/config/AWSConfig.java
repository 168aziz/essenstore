package com.essenstore.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

//    @Value("${aws.access.key.id}")
//    private String accessKey;
//
//    @Value("${aws.secret.key.id}")
//    private String secretKey;
//
//    @Value("${aws.region}")
//    private String region;
//
//    @Bean
//    public AWSCredentials awsCredentials() {
//        return new BasicAWSCredentials(accessKey, secretKey);
//    }
//
//    @Bean
//    public AWSCredentialsProvider awsCredentialsProvider(AWSCredentials awsCredentials) {
//        return new AWSStaticCredentialsProvider(awsCredentials);
//    }
//
//    @Bean
//    public AmazonS3 amazonS3(AWSCredentials awsCredentials) {
//        return AmazonS3ClientBuilder
//                .standard()
//                .withRegion(region)
//                .withCredentials(awsCredentialsProvider(awsCredentials))
//                .build();
//    }
//
//    @Bean
//    public TransferManager transferManager(AmazonS3 amazonS3) {
//        return TransferManagerBuilder.standard()
//                .withS3Client(amazonS3)
//                .withMultipartUploadThreshold((long) (5 * 1024 * 1025))
//                .build();
//    }

}
