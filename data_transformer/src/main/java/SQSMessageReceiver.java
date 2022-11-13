import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.json.simple.JSONObject;

import java.util.List;

public class SQSMessageReceiver {
    private final String url;
    private final AmazonSQS client;

    public SQSMessageReceiver(String url, String region, String accessKey, String secretKey) {
        this.url = url;
        client = AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(url, region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

    }

    public List<Message> receiveMessages(int max) {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(url)
                .withMaxNumberOfMessages(max)
                .withWaitTimeSeconds(2);
        List<Message> messages = client.receiveMessage(receiveMessageRequest).getMessages();
        if (messages.isEmpty()){
            return null;
        }else {
            return messages;
        }
    }
}