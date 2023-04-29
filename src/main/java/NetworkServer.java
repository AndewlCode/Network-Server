import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NetworkServer {
    private final int port;

    public NetworkServer(int port) {
        this.port = port;
    }

    public void serverStart() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            while (true) {
                try (
                        Socket clienSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(clienSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted %d %n", clienSocket.getPort());
                    String command = in.readLine();
                    String answer = getAnswer(command);
                    out.printf(answer);
                    System.out.printf("User command: %s %nBot answer: %s %n %n", command, answer);
                }
            }
        }
    }

    public String getAnswer(String s) {
        String answer;
        switch (s) {
            case ("Hi"):
                answer = "Hi.";
                break;
            case ("Say Your name"):
                answer = "My name is Netology Bot.";
                break;
            case ("What date is it today"):
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                answer = "Today is " + dtf.format(now);
                break;
            case ("What is yours favourite movie"):
                answer = "Terminator 2: Rise of Machines. I hope, we will win...";
                break;
            case ("How old are you?"):
                LocalDate creatingDate = LocalDate.of(2023,4,29);
                LocalDate today = LocalDate.now();
                long daysOld = Duration.between(creatingDate.atStartOfDay(), today.atStartOfDay()).toDays();
                answer = "I was created 29.04.2023. Iam " + daysOld + " days old.";
                break;
            case ("Where are you?"):
                try {
                    String filePath = new File(".").getCanonicalPath();
                    answer = "Iam working from here: " + filePath;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case ("Bye"):
                answer = "See you later, man.";
                break;
            default:
                answer = "I have no answer for that question.";
                break;
        }
        return answer;
    }
}
