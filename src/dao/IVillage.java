package dao;




import entities.Village;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVillage extends Remote {
    public int addVillage(Village village)throws RemoteException;
    public int deleteVillage(int id)throws RemoteException;
    public int updateVillage(Village village)throws RemoteException;
    public List<Village> getlistVillage()throws RemoteException;
}
