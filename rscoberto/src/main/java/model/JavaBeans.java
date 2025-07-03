package model;

public class JavaBeans {
	private String id;
	private String nome;
	private String marca;
	private String tamanho;
	private String preco;
	
	
	public JavaBeans() {
		super();
		
	}
	
	public JavaBeans(String id, String nome, String marca, String tamanho, String preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.tamanho = tamanho;
		this.preco = preco;
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
}
