import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class DataWriter {

    public static void main(String[] args) {
        SQSMessageReceiver messageReceiver = new SQSMessageReceiver(
                "http://localhost:4566/000000000000/login-queue",
                "US_WEST_1", "test", "test");
        Repository repository = new Repository("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");
        repository.clearTable("user_logins");

        while (true) {

            List<Message> messages = messageReceiver.receiveMessages(10);
            if (messages != null) {
                for (Message message : messages) {
                    UserLogin userLogin = new UserLogin(message);
                    repository.writeIntoTable(userLogin, "user_logins");
                }
            }else{
                break;
            }
        }
        System.out.println("Data transferred successfully.");
    }
}
