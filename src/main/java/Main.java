public class Main {
    public static void main(String[] args) throws Exception {
        int serverPort = 8089;
        NetworkServer networkServer = new NetworkServer(serverPort);
        networkServer.serverStart();
    }
}
