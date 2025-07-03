<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COBERTOR SORISSO</title>
<link rel="icon" type="image/x-icon" href="img/icone.ico">
<link rel="stylesheet" href="style.css">
<body>
	<h1>Editar Produto</h1>
	<form name="frmcadastro" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly value="<%out.print(request.getAttribute("id")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="caixa1"value="<%out.print(request.getAttribute("nome")); %>" ></td>
			</tr>
			<tr>
				<td><input type="text" name="marca" class="caixa2"value="<%out.print(request.getAttribute("marca")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="tamanho" class="caixa2"value="<%out.print(request.getAttribute("tamanho")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="preco" class="caixa1"value="<%out.print(request.getAttribute("preco")); %>"></td>
			</tr>
			<tr>
				<td><input type="button" value="Salvar" class="botao1"
					onclick="validar()"></td>
			</tr>
		</table>
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>