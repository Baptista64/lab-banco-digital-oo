import java.util.ArrayList;
import java.util.List;

public class Banco {

	private String nome;
	private ArrayList<Conta> contas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		this.contas = new ArrayList<>();
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void addConta(Conta conta) {
		contas.add(conta);
	}

	public void listarContas() {

		for (Conta c : contas) {

			System.out.println("Contas criadas: ");
			System.out.println("===============================");
			c.imprimirExtrato();
			System.out.println("===============================");
		}

	}

	public Conta getConta(int numero) {

		for (int i = 0; i < contas.size(); i++) {

			Conta conta = contas.get(i);

			if (conta.getNumero() == numero) {

				return conta;
			}

		}
		System.out.println("Conta não encontrada");
		return null;
	}

}
