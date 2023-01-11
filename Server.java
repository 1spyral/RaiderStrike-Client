import java.io.*;
import java.net.*;
public class Server extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    private Messenger messenger;

    Server(Messenger messenger) throws Exception {
        this.messenger = messenger;

        this.socket = new Socket(Const.HOST, Const.PORT);
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.output = new PrintWriter(this.socket.getOutputStream());
    }
    public void run() {
        while (true) {
            System.out.println("hello");
            String message = "";
            try {
                message = this.input.readLine();
                System.out.println("not hello");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (message.length() > 0) {
                System.out.println("hihihi");
                this.messenger.add(message);
            }
        }
    }
    public void print(String x) {
        this.output.println(x);
        this.output.flush();
    }
}
