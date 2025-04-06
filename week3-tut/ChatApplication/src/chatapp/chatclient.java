package chatapp;

import java.io.*;
import javax.swing.*;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class chatclient {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chat Client");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);
    
    public chatclient() {
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        textField.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
        
    }
        
           
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
        		frame, 
        		"Enter IP Address of the Server: ",
        		"Welcome to the Chat Room",
        		JOptionPane.QUESTION_MESSAGE
        );
    	
	}
	
	private String getName() {
		
		return JOptionPane.showInputDialog(
				frame,
				"Choose a screen name: ",
				"Screen name selection",
				JOptionPane.PLAIN_MESSAGE
		);
	}
		

    private void run() throws IOException {
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9011);
        
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        while (true) {
        	
        	String line = in.readLine();
        	
        	if(line.startsWith("SUBMITNAME")) {
        		out.println(getName());
        	}else if (line.startsWith("NAMEACCEPTED")) {
        		textField.setEditable(true);
        	}else if(line.startsWith("MESSAGE")) {
        		messageArea.append(line.substring(8) + "\n");
        	}
        }
        		
     }
    

    public static void main(String[] args) throws Exception {
        chatclient client = new chatclient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}

