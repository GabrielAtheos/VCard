package coolcatmeow.org.welcomeanimation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable
{
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    private Thread runningThread;
    private boolean running;

    private static final int PORT = 9090;
    private static final String IPaddress = "159.203.234.217";

    private String name = null;
    private String info = null;
    public String gMessage = "";

    public ServerConnection(String foo)
    {
        name = foo;
        try
        {
            socket = new Socket(IPaddress, PORT);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            running = true;
            runningThread = new Thread(this);
            runningThread.start();
        }catch(Exception e)
        {
            e.printStackTrace();
            disconnect();
        }
    }

    public void disconnect()
    {
        running = false;
        if(runningThread != null)
            runningThread.interrupt();
        runningThread = null;

        try
        {
            reader.close();
        }catch(Exception e){}
        reader = null;

        try
        {
            writer.close();
        }catch(Exception e){}
        writer = null;

        try
        {
            socket.close();
        }catch(Exception e){}
        socket = null;
    }

    public void sendMessage(String message)
    {
        if(running)
        {
            writer.println(message);
            writer.flush();
        }
    }

    public void run()
    {
        String message = "";
        try
        {
            sendMessage(name);
            if((message = reader.readLine()) != null && running)
            {
                System.out.println("Message Recieved: " + message);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Caught in the run() method of ServerConnection");
            disconnect();
        }
        gMessage = message;
    }
}