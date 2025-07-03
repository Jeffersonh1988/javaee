package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/gravadados", "/select", "/update", "/delete","/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans produto = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			cadastro(request, response);
		} else if (action.equals("/gravadados")) {
			novoProduto(request, response);
		} else if (action.equals("/select")) {
			listarProduto(request, response);
		} else if (action.equals("/update")) {
			editarProduto(request, response);
		} else if (action.equals("/delete")) {
			removerProduto(request, response);
		}else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		
		}
	}

	// Listar Produto
	protected void cadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um obj que recebe JavaBeans
		ArrayList<JavaBeans> lista = dao.listarProdutos();
		// Encaminhar a lista ao documento coberto.jsp
		request.setAttribute("produto", lista);
		RequestDispatcher rd = request.getRequestDispatcher("cobertor.jsp");
		rd.forward(request, response);

	}

	// novo produto
	protected void novoProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setar as varias Javabeans
		produto.setNome(request.getParameter("nome"));
		produto.setMarca(request.getParameter("marca"));
		produto.setTamanho(request.getParameter("tamanho"));
		produto.setPreco(request.getParameter("preco"));
		// invocar o metato InserirProduto passando o objeto contato
		dao.inserirProduto(produto);
		// Redirecionar para o documento cobertor.jsp
		response.sendRedirect("main");
	}

	// Editar Produto
	protected void listarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do ID do produto que será editado
		String id = request.getParameter("id"); // CORRIGIDO AQUI
		System.out.println("ID recebido: " + id);

		// setar o ID no JavaBeans
		produto.setId(id);

		// executar o método selecionarProduto (DAO)
		dao.selecionarProduto(produto);

		// setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("id", produto.getId());
		request.setAttribute("nome", produto.getNome());
		request.setAttribute("marca", produto.getMarca());
		request.setAttribute("tamanho", produto.getTamanho());
		request.setAttribute("preco", produto.getPreco());

		// encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	protected void editarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as varias JavaBeans
		produto.setId(request.getParameter("id"));
		produto.setNome(request.getParameter("nome"));
		produto.setMarca(request.getParameter("marca"));
		produto.setTamanho(request.getParameter("tamanho"));
		produto.setPreco(request.getParameter("preco"));
		// Execultar o metodo alterarProduto
		dao.alterarProduto(produto);
		// Redirecionar para o documento coberto.jsp (atualizando as autualização)
		response.sendRedirect("main");
	}

	// Remover Produto
	protected void removerProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do contato a ser excluido passando pelo validador.js
		String id = request.getParameter("id");
		// setar a variavel id no JAvaBeans
		produto.setId(id);
		// ececultar o metodo deletarProduto (DAO) passando o objeto contato
		dao.deletarProduto(produto);
		// Redirecionar para o documento coberto.jsp (atualizando as autualização)
		response.sendRedirect("main");

	}
	//Gerar Relatorio em PDF
	protected void gerarRelatorio (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Document documento = new Document();
	try {
		// Tipo de  contaudo
	response.setContentType("apllication/pdf");
	// nome do documento
	response.addHeader ("Content-Disposition", "inline; filename=" + "produto.pdf");
	// Criar documento
	PdfWriter.getInstance(documento, response.getOutputStream());
	//abrir documento -> gerar conteudo
	documento.open();
	documento.add(new Paragraph("Lista de Contato:"));
	documento.add(new Paragraph(" "));
	// Criar uma tabela
	PdfPTable tabela = new PdfPTable(4);
	//Cabeçalho
	PdfPCell col1 = new PdfPCell (new Paragraph("Nome"));
	PdfPCell col2 = new PdfPCell (new Paragraph("Marca"));
	PdfPCell col3 = new PdfPCell (new Paragraph("Tamanho"));
	PdfPCell col4 = new PdfPCell (new Paragraph("Preco"));
	tabela.addCell(col1);
	tabela.addCell(col2);
	tabela.addCell(col3);
	tabela.addCell(col4);
	// Popular a tabela com os contatos
	ArrayList<JavaBeans> lista = dao.listarProdutos();
	for (int i = 0; i < lista.size(); i++) {
		tabela.addCell(lista.get(i).getNome());
		tabela.addCell(lista.get(i).getMarca());
		tabela.addCell(lista.get(i).getTamanho());
		tabela.addCell(lista.get(i).getPreco());
	}
	documento.add(tabela);
	documento.close();
	
	} catch (Exception e) {
		System.out.println(e);
		documento.close();
		
	}
	}
	
	
}
	
//teste de excluir System.out.println(id);

/*
 * //Teste de recebimento System.out.println(request.getParameter("id"));
 * System.out.println(request.getParameter("nome"));
 * System.out.println(request.getParameter("marca"));
 * System.out.println(request.getParameter("tamanho"));
 * System.out.println(request.getParameter("preco"));
 */

/*
 * // teste de recebimento System.out.println(produto.getId());
 * System.out.println(produto.getNome());
 * System.out.println(produto.getMarca());
 * System.out.println(produto.getTamanho());
 * System.out.println(produto.getPreco());
 */

/*
 * teste de conexao dao.testeConexao();
 */

/*
 * //TESTE DE RECEBIMENTO System.out.println(request.getParameter("nome"));
 * System.out.println(request.getParameter("marca"));
 * System.out.println(request.getParameter("tamanho"));
 * System.out.println(request.getParameter("preco"));
 */

/*
 * teste de recebimento da lista for (int i = 0; i < lista.size(); i++) {
 * System.out.println(lista.get(i).getId());
 * System.out.println(lista.get(i).getNome());
 * System.out.println(lista.get(i).getMarca());
 * System.out.println(lista.get(i).getTamanho());
 * System.out.println(lista.get(i).getPreco()); }
 */