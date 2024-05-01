
public interface IConta {
	
	int sacar(double valor, boolean porTransferencia);
	
	void depositar(double valor, boolean porTransferencia);
	
	void transferir(double valor, IConta contaDestino);
	
	void imprimirExtrato();
}
