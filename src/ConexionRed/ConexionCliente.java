package ConexionRed;

import models.Ticket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConexionCliente   {


    public ConexionCliente(String ipCelular, ArrayList<Ticket> listaTicketCliente) {

        try {
            Socket socket = new Socket(ipCelular,1399);
           // DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //dataOutputStream.writeUTF("Mensaje enviado por socket xd");
            //dataOutputStream.close();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(listaTicketCliente);
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
