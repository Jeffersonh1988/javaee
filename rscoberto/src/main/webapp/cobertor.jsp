<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.JavaBeans" %>
    <%@ page import="java.util.ArrayList" %>
    <%
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)request.getAttribute("produto");
   
    %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Cadastro de Cobertor</title>
<link rel="icon" type="image/x-icon" href="img/icone.ico">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Cadastro de Produto</h1>
	<a href="novo.html"class="botao1"> Novo Produto</a>
	<a href="report"class="botao2"> Relatorio</a>
	<table id=tabela>
	<thead>
	<tr>
		<th>Id</th>
		<th>Nome</th>
		<th>Marca</th>
		<th>Tamanho</th>
		<th>Preco</th>
		<th>Opções </th>
		<th> </th>
	</tr>
	</thead>
		<tbody>
		<%for (int i = 0; i<lista.size(); i++){ %>
			<tr>
				<td> <%=lista.get(i).getId() %></td>
				<td> <%=lista.get(i).getNome() %></td>
				<td> <%=lista.get(i).getMarca() %></td>
				<td> <%=lista.get(i).getTamanho() %></td>
				<td> <%=lista.get(i).getPreco() %></td>
				<td><a href="select?id=<%=lista.get(i).getId()%>" class="botao1"> Editar</a> 
				<td><a href="javascript:confirmar(<%=lista.get(i).getId()%>)" class="botao2"> Excluir</a> 
			</td>
			</tr>
		<%} %>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"> </script>
</body>
</html>