package ConexionRed;

import javafx.collections.ObservableList;
import models.Ticket;
import models_tablas.TicketT;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ConexionServidor {

    private final ObservableList<TicketT> listaTickets;

    ServerSocket serverSocket;

    public ConexionServidor(ObservableList<TicketT> listaTickets) {
        this.listaTickets = listaTickets;
         Thread miHilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(1399);
                    serverSocket.setReuseAddress(true);
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    while(true){
                        //esperara una conexión

                        Socket socket = serverSocket.accept();
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        String data = dataInputStream.readUTF();
                        SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
                        String s = remoteSocketAddress.toString();
                        String ipCelular = s.substring(1,s.indexOf(':'));
                        socket.close();
                        if( !enviarListaTickets(ipCelular,data.split(";")[0])){
                            continue;
                        }
                        System.out.println(data);
                      //  serverSocket.close();

                        //para terminar este hilo
                      //  break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        miHilo.setDaemon(true);
        miHilo.start();

    }

    private boolean enviarListaTickets(String ipCelular,String numCliente) {


        ArrayList<Ticket> listaTicketCliente = new ArrayList<>();
        for(TicketT ticketT:listaTickets){
            if(ticketT.getIdCliente()==Integer.parseInt(numCliente))
                listaTicketCliente.add(ticketT.getTicket());
        }

        ConexionCliente conexionCliente = new ConexionCliente(ipCelular,listaTicketCliente);
        return conexionCliente.enviar();

    }


}
