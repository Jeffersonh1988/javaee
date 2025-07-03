/**
 * Validacao de formulario
 */

function validar(){
	let nome = frmcadastro.nome.value
	let preco = frmcadastro.preco.value
	if (nome === ""){
		alert("Preencha o campo nome")
		frmcadastro.nome.focus()
			return false 
	} else if (preco === ""){
		alert("Preencha o campo Preço")
				frmcadastro.preco.focus()
					return false 
	} else {
		document.forms ["frmcadastro"].submit()
	}
}