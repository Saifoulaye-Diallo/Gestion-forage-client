package dao;

import entities.Facture;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFacture extends Remote {
    public int addFacture(Facture facture) throws RemoteException;
    public int deleteFacture(int id)throws RemoteException;
    public int updateFacture(Facture facture)throws RemoteException;
    public List<Facture> getlistFacture()throws RemoteException;
}
