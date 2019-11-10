package interfaces;

import javafx.collections.ObservableList;
import models.ResumenArticulo;

public interface ITransferirObjeto {

    void tranferirObjeto(int IDCompra,int IDCliente,float totalCompra, ObservableList<ResumenArticulo> listProductosComprados);

}
