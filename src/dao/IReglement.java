package dao;

import entities.Reglement;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IReglement extends Remote {
    public int addReglement(Reglement rg) throws RemoteException;
    public int deleteReglement(int id)throws RemoteException;
    public int updateReglement(Reglement rg)throws RemoteException;
    public List<Reglement> getlistReglement()throws RemoteException;
}
