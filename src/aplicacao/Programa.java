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

		System.out.print("Quantos funcion�rios v�o ser registrados ?");
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {

			System.out.println();
			System.out.println("Funcion�rio: #" + (i + 1) + ": ");

			System.out.print("Id: ");
			Integer idFuncionario = sc.nextInt();
			
			while (hasId(list, idFuncionario)) {
				System.out.print("ID existente. Tente novamente: ");
				idFuncionario = sc.nextInt();
			}

			System.out.print("Nome: ");
			sc.nextLine();
			String nomeFuncionario = sc.nextLine();

			System.out.print("Sal�rio: ");
			Double salarioFuncionario = sc.nextDouble();
			
			Funcionario func = new Funcionario(idFuncionario, nomeFuncionario, salarioFuncionario);
			
			list.add(func);

		}

		System.out.println();
		System.out.print("Entre com o ID do funcion�rio que vai ter o sal�rio alterado: ");
		int idAumentaSalario = sc.nextInt();
		
		
		/* Explica��o dos passos seguintes:
		 * list.stream = transformando a lista num stream
		 * stream chama a fun��o filter, que filtra a lista a partir do elementos que atenderem a um predicado
		 * 
		 * Neste caso o predicado �: 
		 * Eu quero somente os elementos x tal que o x.getIdFuncionario seja igual ao idAumentaSalario e pega o primeiro (findFirst)
		 * 
		 * Caso n�o exista o primeiro retorna nulo orElse(null)
		 * */		
		
		Funcionario func = list.stream().filter(x -> x.getIdFuncionario() == idAumentaSalario).findFirst().orElse(null);
		
		if (func == null) {
			System.out.println("Este Id n�o existe");
		} else {
			System.out.print("Entre com a porcentagem: ");
			double porcentagem = sc.nextDouble();
			func.aumentaSalario(porcentagem);
		}

		System.out.println();
		System.out.println("Lista de funcion�rios");
		
		for (Funcionario f : list) {
			System.out.println(f);
		}

	}
	
	public static boolean hasId(List<Funcionario> list, int id) {
		Funcionario func = list.stream().filter(x -> x.getIdFuncionario() == id).findFirst().orElse(null);
		return func != null;
	}

}
