import java.util.ArrayList;
import java.util.Scanner;

class SistemaBancario {
    private double saldo;
    private ArrayList<Double> depositos;
    private ArrayList<Double> saques;

    public SistemaBancario() {
        this.saldo = 0.0;
        this.depositos = new ArrayList<>();
        this.saques = new ArrayList<>();
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.depositos.add(valor);
            this.saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado com sucesso.%n", valor);
        } else {
            System.out.println("O valor do depósito deve ser positivo.");
        }
    }

    public void sacar(double valor) {
        if (this.saldo >= valor && valor <= 500.00 && this.saques.size() < 3) {
            this.saques.add(valor);
            this.saldo -= valor;
            System.out.printf("Saque de R$ %.2f realizado com sucesso.%n", valor);
        } else if (valor > 500.00) {
            System.out.println("O valor máximo de saque é R$ 500.00.");
        } else if (this.saques.size() >= 3) {
            System.out.println("Você já realizou o limite máximo de saques diários.");
        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
        }
    }

    public void extrato() {
        System.out.println("Extrato:");
        if (this.depositos.isEmpty() && this.saques.isEmpty()) {
            System.out.println("Não foram realizadas movimentações.");
        } else {
            for (double deposito : this.depositos) {
                System.out.printf("Depósito: R$ %.2f%n", deposito);
            }
            for (double saque : this.saques) {
                System.out.printf("Saque: R$ %.2f%n", saque);
            }
        }
        System.out.printf("Saldo atual: R$ %.2f%n", this.saldo);
    }
}

public class ContaBancaria {
    public static void main(String[] args) {
        SistemaBancario banco = new SistemaBancario();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("----- Sistema Bancário da DIO.ME -----");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Extrato");
            System.out.println("4. Sair");
            System.out.print("Selecione uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Informe o valor do depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    banco.depositar(valorDeposito);
                    scanner.nextLine();  // Limpa o buffer
                    break;
                case "2":
                    System.out.print("Informe o valor do saque: ");
                    double valorSaque = scanner.nextDouble();
                    banco.sacar(valorSaque);
                    scanner.nextLine();  // Limpa o buffer
                    break;
                case "3":
                    banco.extrato();
                    break;
                case "4":
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
