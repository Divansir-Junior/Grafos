package entities;

import java.util.Objects;

public class Book {
	private String nome;
	private String autor;
	private Integer anoPublicacao;

	public Book() {
		
	}

	public Book(String nome, String autor, Integer anoPublicacao) {
		super();
		this.nome = nome;
		this.autor = autor;
		this.anoPublicacao = anoPublicacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	 
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Book livro = (Book) o;
	        return anoPublicacao == livro.anoPublicacao &&
	                Objects.equals(nome, livro.nome) &&
	                Objects.equals(autor, livro.autor);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(nome, anoPublicacao, autor);
	    }

	    @Override
	    public String toString() {
	        return "NOME = " + nome + " | " +
	               "Ano publicação = " + anoPublicacao + " | " +
	               "Autor do livro = " + autor;
	    }
}