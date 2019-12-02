package interfaces;

import javafx.collections.ObservableList;
import models.ResumenArticulo;

import java.util.ArrayList;

public interface ITransferirObjeto {

    void tranferirObjeto(int IDCompra,int IDCliente,float totalCompra, ArrayList<ResumenArticulo> listProductosComprados);

}
