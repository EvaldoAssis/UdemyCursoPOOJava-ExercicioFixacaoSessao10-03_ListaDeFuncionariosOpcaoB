package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidade.Funcionario;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		// Declarando e instanciando uma lista
		List<Funcionario> list = new ArrayList<>();

		System.out.print("Quantos funcionários vão ser registrados ?");
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {

			System.out.println();
			System.out.println("Funcionário: #" + (i + 1) + ": ");

			System.out.print("Id: ");
			Integer idFuncionario = sc.nextInt();
			
			while (hasId(list, idFuncionario)) {
				System.out.print("ID existente. Tente novamente: ");
				idFuncionario = sc.nextInt();
			}

			System.out.print("Nome: ");
			sc.nextLine();
			String nomeFuncionario = sc.nextLine();

			System.out.print("Salário: ");
			Double salarioFuncionario = sc.nextDouble();
			
			Funcionario func = new Funcionario(idFuncionario, nomeFuncionario, salarioFuncionario);
			
			list.add(func);

		}

		System.out.println();
		System.out.print("Entre com o ID do funcionário que vai ter o salário alterado: ");
		int idAumentaSalario = sc.nextInt();
		
		
		/* Explicação dos passos seguintes:
		 * list.stream = transformando a lista num stream
		 * stream chama a função filter, que filtra a lista a partir do elementos que atenderem a um predicado
		 * 
		 * Neste caso o predicado é: 
		 * Eu quero somente os elementos x tal que o x.getIdFuncionario seja igual ao idAumentaSalario e pega o primeiro (findFirst)
		 * 
		 * Caso não exista o primeiro retorna nulo orElse(null)
		 * */		
		
		Funcionario func = list.stream().filter(x -> x.getIdFuncionario() == idAumentaSalario).findFirst().orElse(null);
		
		if (func == null) {
			System.out.println("Este Id não existe");
		} else {
			System.out.print("Entre com a porcentagem: ");
			double porcentagem = sc.nextDouble();
			func.aumentaSalario(porcentagem);
		}

		System.out.println();
		System.out.println("Lista de funcionários");
		
		for (Funcionario f : list) {
			System.out.println(f);
		}

	}
	
	public static boolean hasId(List<Funcionario> list, int id) {
		Funcionario func = list.stream().filter(x -> x.getIdFuncionario() == id).findFirst().orElse(null);
		return func != null;
	}

}
