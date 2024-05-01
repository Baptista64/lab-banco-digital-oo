
public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static final int SAQUE_FEITO = 1;
	private static final int SAQUE_N_FEITO_SEM_SALDO = 2;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public int sacar(double valor, boolean porTransferencia) {
		if (saldo < valor || saldo == 0) {
			System.out.println("Saldo insuficiente! Saldo disponivel: " + this.saldo);
			return SAQUE_N_FEITO_SEM_SALDO;
		} else {
			saldo -= valor;
			if (!porTransferencia) {

				System.out.println("Saque realizado com sucesso! Saldo disponivel: " + this.saldo);

			}
			return SAQUE_FEITO;
		}
	}

	@Override
	public void depositar(double valor, boolean porTransferencia) {
		saldo += valor;
		if (!porTransferencia) {
			System.out.println("deposito realizado com sucesso! Saldo disponivel: " + this.saldo);

		}
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if (this.sacar(valor, true) == SAQUE_FEITO) {
			contaDestino.depositar(valor, true);
			System.out.println("transferência realizada com sucesso! Saldo disponivel: " + this.saldo);
		}
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
