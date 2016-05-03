package br.dagostini.exemplos;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LerIp {

	public LerIp(){}
	
	public String retornarIp() throws RuntimeException{
		InetAddress IP;
		try {
			IP = InetAddress.getLocalHost();
			String IPString = IP.getHostAddress();
			
			if(IPString.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
				return IPString;
			}else{
				throw new RuntimeException("A máquina não obteve IP");
			}
		} catch (UnknownHostException e) {
			throw new RuntimeException("Erro ao retornar IP");
		}
	}

	public static void main(String[] args) {
		System.out.println(new LerIp().retornarIp());
	}
}
