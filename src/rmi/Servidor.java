package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Servidor extends UnicastRemoteObject implements Servico{

	protected Servidor() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws RemoteException {
		try {
			Servidor servidor = new Servidor();
			String localizacao = "//localhost/servico";
			Naming.rebind(localizacao, servidor);
		} catch (MalformedURLException e) {
			System.out.println("Erro de URL mal formada: "+e.getMessage());
		} catch (RemoteException e) {
			System.out.println("Erro: "+e.getMessage());
		}
			
		

	}

	@Override
	public String getDataHora() throws RemoteException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy MM:mm");
		return sdf.format(Calendar.getInstance().getTime());
	}

	@Override
	public String inverteString(String string) throws RemoteException {
		// TODO Auto-generated method stub
		String retorno = "";
		StringBuffer strb = new StringBuffer(string);
		retorno = strb.reverse().toString();
		return retorno;
	}

}
