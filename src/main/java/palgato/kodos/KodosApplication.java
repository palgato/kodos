package palgato.kodos;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

@SpringBootApplication
public class KodosApplication {

	public static void main(String[] args) {

		String accessKey = "CHANGE_ME";
		String secretKey = "CHANGE_ME";
		String s3Bucket = "kodosboard";

		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(Regions.EU_WEST_2)
				.build();
		S3Object s3object = s3client.getObject(s3Bucket, "unoBoard.csv");
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		String readFile = readFile(inputStream);
		System.out.println("readFile = " + readFile);

		SpringApplication.run(KodosApplication.class, args);

	}

	private static String readFile(S3ObjectInputStream inputStream) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()))) {
			return br.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}