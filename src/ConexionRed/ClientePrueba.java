package ConexionRed;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientePrueba  {

    public ClientePrueba() {

        try {
            Socket socket = new Socket("192.168.0.101",1399);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF("Vic, escritorio a android");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
