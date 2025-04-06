package chatapp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;

public class chatserver {

    private static final int PORT = 9011;
    private static HashSet<String> users = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public static void main(String[] args) throws Exception {
        
    	System.out.println("Chat server started on port " + PORT);
        ServerSocket listener = new ServerSocket(PORT);
        
        try {
            while (true) {
                Socket socket = listener.accept();
                Thread handlerthread = new Thread(new Handler(socket));
                handlerthread.start();
            }
        }finally {
            listener.close();
        }
    }

    private static class Handler implements Runnable {
        private String user;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
               
                // Request a name from this client
                while (true) {
                    out.println("SUBMITNAME");
                    user = in.readLine();
                    
                    if (user == null) {
                        return;
                    }
                 
                    // Check if name is already in use
                    if (!users.contains(users)) {
                            users.add(user );
                            break;
                    } 
                }
                    
                out.println("NAMEACCEPTED");
                writers.add(out);

                // Accept and broadcast messages from this client
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer: writers) {
                    	writer.println("MESSAGE " + user + ":" + input);
                    }
                    
                }
                
            }catch(IOException e) {
            	System.out.println(e);
            
            }finally {
            	if(users != null) {
            		users.remove(user);
            	}
            	if(out !=null) {
            		writers.remove(out);
            	}
            	try {
            		socket.close();
            	}catch (IOException e) {
            		
            	}
            	
            }
        }
        
    }
    
}
    
            
            	

        