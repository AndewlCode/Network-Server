import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
                        BufferedReader in = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));
                ) {
                    System.out.printf("New connection accepted %d %n", clienSocket.getPort());
                    final String name = in.readLine();
                    out.printf("Hello, %s! Your port is %d %n", name, clienSocket.getPort());
                }
            }
        }
    }
}
