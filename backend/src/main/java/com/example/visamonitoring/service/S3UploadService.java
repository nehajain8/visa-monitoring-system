package com.example.visamonitoring.service;

import com.example.visamonitoring.entity.Overstayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Service
public class S3UploadService {

    private final S3Client s3Client;
    private final ObjectMapper objectMapper;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3UploadService() {
        this.s3Client = S3Client.builder()
                .region(Region.EU_WEST_2) // adjust to your region
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public void uploadOverstayers(List<Overstayer> overstayers) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            objectMapper.writeValue(outputStream, overstayers);
            String fileName = "overstayers-" + LocalDate.now() + ".json";

            PutObjectRequest putRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType("application/json")
                    .build();

            s3Client.putObject(putRequest, RequestBody.fromString(outputStream.toString(StandardCharsets.UTF_8)));

            System.out.println("Uploaded overstayers to S3: " + fileName);

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload overstayers to S3", e);
        }
    }
}
