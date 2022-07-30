package entity;

import java.time.LocalDate;

public class Operazione {
	
	private String iban;
	private double importo;
	private String data=LocalDate.now().toString();
	private boolean deposito=false;
	private boolean prelievo=false;
	
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isDeposito() {
		return deposito;
	}
	public void setDeposito(boolean deposito) {
		this.deposito = deposito;
	}
	public boolean isPrelievo() {
		return prelievo;
	}
	public void setPrelievo(boolean prelievo) {
		this.prelievo = prelievo;
	}
	
	

	
}
