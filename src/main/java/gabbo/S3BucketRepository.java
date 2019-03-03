package gabbo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class S3BucketRepository implements BoardRepository {
    private final List<LeaderBoard> boards;
    private String repoName;

    public S3BucketRepository(List<LeaderBoard> boards, String repoName) {
        this.boards = boards;
        this.repoName = repoName;
    }

    @Override
    public List<LeaderBoard> getAll() {

        String s3Bucket = System.getenv("TEST_S3_BUCKET_NAME");
        AmazonS3 s3client = accessBucket();
        S3Object s3Object = s3client.getObject(s3Bucket,this.repoName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        return boards;
    }

    @Override
    public List<LeaderBoard> save(LeaderBoard newBoard) {

        boards.add(newBoard);
        File newFile = new File(String.valueOf(boards));

        String s3Bucket = System.getenv("TEST_S3_BUCKET_NAME");
        AmazonS3 s3client = accessBucket();
        s3client.putObject(s3Bucket,this.repoName,newFile,);
        return boards;
    }

    @Override
    public Optional<LeaderBoard> find(String name) {
        return Optional.empty();
    }

    public static AmazonS3 accessBucket() {

        String accessKey = System.getenv("AWS_ACCESS_KEY_ID");
        String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_WEST_2)
                .build();
        return s3client;

    }
}
