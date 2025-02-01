package main;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.Book;
import entities.Graph;


public class Main {
    // Declare a variável de instância para armazenar o grafo
    private static Graph<Book> g;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean rodando = true;//Controle do loop principal

        // Inicialize o grafo
        g = new Graph<>();

        // Adicione os livros ao grafo
        addBooksToGraph();

        // Instância de livros de teste para remoção
        Book livroRemover1 = new Book("Livro a ser removido", "Autor A", 2025);
        Book livroRemover2 = new Book("Outro livro a ser removido", "Autor B", 2030);
        Book livroRemover3 = new Book("Mais um livro para remoção", "Autor C", 2035);
        g.addEdge(livroRemover1, livroRemover2, Arrays.asList(livroRemover1, livroRemover2));
        
        
        //Instância dos livros para procurar o caminho mais curto entre eles 
        Book livroCaminho1 = new Book("Primeiro livro do caminho", "Autor A", 2000);
        Book livroCaminho2 = new Book("Segundo livro do caminho", "Autor B", 2001);
        Book livroCaminho3 = new Book("Terceiro livro do caminho","Autor C",2003);
        g.addEdge(livroCaminho1, livroCaminho3, Arrays.asList(livroCaminho1,livroCaminho2));
        
        
        // Loop do menu
        while (rodando) {
        	System.out.println("▛----------------------------------▜");
            System.out.println("|(1) Ver livros ");
            System.out.println("|(2) Procurar livro");
            System.out.println("|(3) Procurar recomendações de livro");
            System.out.println("|(4) Excluir livro");
            System.out.println("|(5) Ver fila de livros");
            System.out.println("|(6) Ver caminho mais curto entre livros");
            System.out.println("|(7) Sair");
            System.out.println("▙---------------------------------▟");
            System.out.print("Escolha uma opção: ");

            try {
                int escolha = sc.nextInt();

                switch (escolha) {
                    case 1: {
                        System.out.println(" === LISTAGEM DE LIVROS === ");
                        System.out.println(g);
                        break;
                    }

                    case 2: {
                        System.out.println(" === PROCURAR LIVRO ===");
                        System.out.print("Digite o nome do livro para a busca:");
                        sc.nextLine(); // Consumir a nova linha pendente
                        String pergunta = sc.nextLine();
                        System.out.println("Busca com livros com o nome " + pergunta + ":");
                        g.searchBook(pergunta);
                        break;
                    }

                    case 3: { //Rec dos livros
                        System.out.println(" === RECOMENDAÇÕES DE LIVRO ===");
                        System.out.print("Digite o nome da recomendação");
                        String P = sc.next();
                        g.searchRecommendationOfBook(P);
                        break;
                    }

                    case 4: {
                        System.out.println(" === EXCLUIR LIVROS ===");
                        String remover = sc.nextLine();
                        g.removeBook(livroRemover2); // Remove o livro associado ao livroRemover2
                        break;
                    }

                    case 5: {  
                    	System.out.println("MENU DAS FILAS ***");  //Menu  das filas 
                    	System.out.println("┌----------------------┐");
                    	System.out.println("|Primeiro elemento: (P)|");
                    	System.out.println("|Último elemento:   (U)|");
                    	System.out.println("|Fila completa:     (C)|");
                    	System.out.println("└----------------------┘");
                    	System.out.print("Aperte ENTER  para confimar : ");
                    	char escolhaFila = sc.next().charAt(0);
                    	
                    	if (escolhaFila == 'P' || escolhaFila == 'p') {
                    		g.firstBookQueue();
                    	}
                    	else if (escolhaFila == 'U' || escolhaFila == 'u') {
                    		g.printLastElement();
                    	}
                    	
                    	else if (escolhaFila == 'C' || escolhaFila == 'c') {
                    		g.printQueue();
                    	}
                    	
                    	break;
                    
                    }
                    case 6: { // Opção para encontrar o menor caminho entre os nós.
                        System.out.println("Digite o nome do livro de origem:");
                        String sourceBookName = sc.next();
                        System.out.println("Digite o nome do livro de destino:");
                        String destinationBookName = sc.next();
                        List<Book> shortestPath = g.findShortestPathBetweenBooks(sourceBookName, destinationBookName);
                        if (!shortestPath.isEmpty()) {
                            System.out.println("Menor caminho entre os livros:");
                            for (Book book : shortestPath) {
                                System.out.println(book);
                            }
                        }
                        break;
                    }
                    
                    case 7 : {  //Encerra o prog
                    	System.out.println("Encerrando programa ...");
                    	rodando = false;
                    	break;
                    }
                    default: {
                        System.out.println("Opção inválida! Por favor, escolha uma opção válida de 1 a 5.");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, insira um número inteiro correspondente à sua escolha.");
                sc.nextLine(); // Limpar o buffer do scanner
            }
        }
    }

    public static void addBooksToGraph() {
        // Criando os livros com nomes mais criativos
        Book livro1 = new Book("A Origem", "Christopher Nolan", 2010);
        Book livro2 = new Book("A Teia do Aranha", "Stan Lee", 2002);
        Book livro3 = new Book("O Segredo da Ilha", "Roberto Carlos", 2020);
        Book livro4 = new Book("Noites de Insônia", "Alice Walker", 2015);
        Book livro5 = new Book("A Trilha do Deserto", "Caroline Lawrence", 2008);
        Book livro6 = new Book("A Floresta Encantada", "Richard Powers", 2022);
        Book livro7 = new Book("O Mistério do Farol", "Agatha Christie", 1930);
        Book livro8 = new Book("A Canção da Noite", "Khaled Hosseini", 2018);
        Book livro9 = new Book("As Crônicas de Fogo", "George R.R. Martin", 2011);
        Book livro10 = new Book("O Segredo da Alma", "Jostein Gaarder", 1991);

        // Adicionando os livros ao grafo
        g.addEdge(livro1, livro2, Arrays.asList(livro3, livro4));
        g.addEdge(livro2, livro3, Arrays.asList(livro5, livro6));
        g.addEdge(livro3, livro4, Arrays.asList(livro7, livro8));
        g.addEdge(livro4, livro5, Arrays.asList(livro9, livro10));
        // Adicionando os livros em fila 
        g.addQueu(livro8);
        g.addQueu(livro10);
        g.addBook(livro2);
    }
    
}

