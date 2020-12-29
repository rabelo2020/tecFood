package com.rabelo.tecfood.api.controller.teste;

import java.util.Random;

public class TesteMain {

	public static void main(String[] args) {

		float[][] alunos = new float[4][4];
		float aluno0, aluno1, aluno2, aluno3, verificador;
		do {
			for (int i = 0; i < alunos.length; i++) {
				for (int j = 0; j < alunos.length; j++) {
					alunos[i][j] = new Random().nextInt(10);
				}
			}
			aluno0 = (alunos[0][0] + alunos[0][1] + alunos[0][2] + alunos[0][3]) / 4;
			aluno1 = (alunos[1][0] + alunos[1][1] + alunos[1][2] + alunos[1][3]) / 4;
			aluno2 = (alunos[2][0] + alunos[2][1] + alunos[2][2] + alunos[2][3]) / 4;
			aluno3 = (alunos[3][0] + alunos[3][1] + alunos[3][2] + alunos[3][3]) / 4;

			if (aluno0 >= 7) {
				System.out.println("Parabéns Aluno 0, você foi aprovado com a média: " + aluno0);
			} else {
				System.out.println("Infelizmente o Aluno 0, você foi reprovado com a média: " + aluno0);
			}

			if (aluno1 >= 7) {
				System.out.println("Parabéns Aluno 1, você foi aprovado com a média: " + aluno1);
			} else {
				System.out.println("Infelizmente o Aluno 1, você foi reprovado com a média: " + aluno1);
			}

			if (aluno2 >= 7) {
				System.out.println("Parabéns Aluno 2, você foi aprovado com a média: " + aluno2);
			} else {
				System.out.println("Infelizmente o Aluno 2, você foi reprovado com a média: " + aluno2);
			}

			if (aluno3 >= 7) {
				System.out.println("Parabéns Aluno 3, você foi aprovado com a média: " + aluno3);
			} else {
				System.out.println("Infelizmente o Aluno 3, você foi reprovado com a média: " + aluno3);
			}

			if (aluno0 >= 7 && aluno1 >= 7 && aluno2 >= 7 && aluno3 >= 7) {
				System.out.println("Parabéns, todos estão aprovados!");
			}

			if (aluno0 >= 7 && aluno1 >= 7 && aluno2 >= 7 && aluno3 >= 7) {
				verificador = 1;
			} else {
				verificador = 0;
				System.out.println("\t//////////////////\t");
			}

		} while (verificador == 0);
	}
}
