import java.util.Scanner;

public class Main {

	private static Banco banco;
	private static Scanner scanner;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);

		banco = new Banco();
		banco.setNome("Banco DIO");

		System.out.println("=====================");
		System.out.println(" Bem-vindo ao " + banco.getNome());
		System.out.println("=====================");

		menu();

	}

	private static void menu() {

		while (true) {

			System.out.println("   ");
			System.out.println("1 - Criar conta");
			System.out.println("2 - Depositar");
			System.out.println("3 - Sacar");
			System.out.println("4 - Transferir");
			System.out.println("5 - imprimirExtrato");
			System.out.println("6 - Ver lista de contas");
			System.out.println("7 - Sair");

			int escolhha = scanner.nextInt();

			switch (escolhha) {
				case 1:

					criarConta();

					break;

				case 2: {

					depositarSacar(false);
				}

					break;

				case 3:

					depositarSacar(true);
					break;

				case 4:

					transferir();

					break;

				case 5:

					imprimirExtrato();
					break;

				case 6:

					verListaDeContas();
					break;
				case 7:
					System.out.println("Fechando o programa...");
					System.exit(0);
				default:
					System.out.println("Opção errada!");
					System.out.println("-----------------");
					break;
			}
		}

	}

	private static void criarConta() {

		System.out.print("Digite o nome do usuário: ");
		String nome = scanner.next();

		Cliente cli = new Cliente();
		cli.setNome(nome);

		System.out.print("Digite o tipo de conta[1 = Conta Corrente || 2 - Conta Poupança]: ");
		int tipo = scanner.nextInt();
		Conta c;

		if (tipo == 1) {
			c = new ContaCorrente(cli);
		} else {
			c = new ContaPoupanca(cli);
		}

		banco.addConta(c);

		System.out.println("Conta criada com sucesso: ");
		c.imprimirExtrato();

	}

	private static void depositarSacar(boolean sacar) {

		if (!existemContas()) {
			return;
		}

		System.out.print("Digite o número da conta do cliente: ");
		int numero = scanner.nextInt();

		System.out.println("Digite o valor: ");
		double valor = scanner.nextDouble();

		Conta conta = banco.getConta(numero);

		if (conta != null) {
			if (sacar) {

				conta.sacar(valor, false);

			} else {

				conta.depositar(valor, false);
			}

		}
	}

	private static void transferir() {

		if (!existemContas()) {
			return;
		}

		Conta contaOrigem = null, contaDestino = null;

		System.out.print("Digite a conta de destino: ");
		int numero = scanner.nextInt();

		contaDestino = banco.getConta(numero);

		if (contaDestino != null) {

			System.out.print("Digite a conta de origem: ");
			int numero2 = scanner.nextInt();

			contaOrigem = banco.getConta(numero2);

			if (contaOrigem != null) {

				System.out.print("Digite o valor: ");
				double valor = scanner.nextDouble();

				contaOrigem.transferir(valor, contaDestino);
			}

		}

	}

	private static void imprimirExtrato() {

		if (!existemContas()) {
			return;
		}

		System.out.print("Digite o número da conta do cliente: ");
		int numero = scanner.nextInt();

		Conta conta = banco.getConta(numero);
		if (conta != null) {
			conta.imprimirExtrato();
		}
	}

	private static void verListaDeContas() {

		if (!existemContas()) {
			return;
		}

		banco.listarContas();
	}

	private static boolean existemContas() {

		boolean existem = banco.getContas().size() > 0;

		if (!existem) {
			System.out.println("Não existe nehuma conta! Por favor crie ao menos uma conta");
			System.out.println("------------------------");
		}
		return existem;

	}

}
