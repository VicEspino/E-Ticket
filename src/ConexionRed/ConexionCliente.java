package ConexionRed;

import models.Ticket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConexionCliente   {


    private final ArrayList<Ticket> listaTicketCliente;
    private final String ipCelular;

    public ConexionCliente(String ipCelular, ArrayList<Ticket> listaTicketCliente) {

        this.ipCelular = ipCelular;
        this.listaTicketCliente = listaTicketCliente;


    }

    boolean enviar(){
        try {
            Socket socket = new Socket(ipCelular,1399);
            // DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //dataOutputStream.writeUTF("Mensaje enviado por socket xd");
            //dataOutputStream.close();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(listaTicketCliente);
            objectOutputStream.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
