let tbody = document.getElementById("corpoTabela")
let tabela = document.getElementById("minhaTabela")

function criaLinha(produto){
    linha = document.createElement("tr")
    tdId = document.createElement("td")
    tdNome = document.createElement("td")
    tdPreco = document.createElement("td")
    tdDescricao = document.createElement("td")
    tdId.innerHTML = produto.id
    tdNome.innerHTML = produto.nome
    tdPreco.innerHTML = produto.preco
    tdDescricao.innerHTML = produto.descricao
    linha.appendChild(tdId)
    linha.appendChild(tdNome)
    linha.appendChild(tdPreco)
    linha.appendChild((tdDescricao))
    return linha
}

function main(){
    let data = get("http://localhost:8080/produtos")
    let produtos =  JSON.parse(data)
    console.log(produtos)
    produtos.forEach(element => {
        let linha = criaLinha(element);
        tbody.appendChild(linha)
        adcBotaoLinha()
    })
}

function adcBotaoLinha(){
    for(var i = 0; i < tabela.rows.length; i++){
        tabela.rows[i].addEventListener("click", function (){
            index = this.rowIndex;
            document.getElementById("txtId").innerText = tabela.rows[index].cells[0].innerText
            document.getElementById("txtNome").value = tabela.rows[index].cells[1].innerText
            document.getElementById("txtPreco").value = tabela.rows[index].cells[2].innerText
            document.getElementById("txtDescricao").value = tabela.rows[index].cells[3].innerText
            document.getElementById("btnCadastrar").disabled = true
            let idProduto = tabela.rows[index].cells[0].innerText
            let data = get("http://localhost:8080/produtos/" + idProduto)
            let produto = JSON.parse(data)
            document.getElementById("txtUrlImagem").value = produto.imagemUrl
        })
    }
}

function cancelarFormulario(){
    document.getElementById("btnCadastrar").disabled = false
    document.getElementById("txtId").innerHTML = ''
    document.getElementById("txtNome").value = ''
    document.getElementById("txtPreco").value = ''
    document.getElementById("txtDescricao").value = ''
    document.getElementById("txtUrlImagem").value = ''
}

function alterarProduto(){
    alert("alterado")
    let idProduto = document.getElementById("txtId").innerText
    let nomeProduto = document.getElementById("txtNome").value
    let preco = document.getElementById("txtPreco").value
    console.log(idProduto)
    let url = "http://localhost:8080/produtos/" + idProduto
    console.log(url)
    let body = {
        "nomeProduto": nomeProduto,
        "preco": preco
    }
    put(url, body)
    console.log("ok")
    location.reload()
}

function cadastrarProduto(){
    let url = "http://localhost:8080/cadastrar"
    alert("menssagem")
    let nomeProduto = document.getElementById("txtNome").value
    let preco = document.getElementById("txtPreco").value
    let descricao = document.getElementById("txtDescricao").value
    let imagemUrl = document.getElementById("txtUrlImagem").value
    let body = {
        "nomeProduto": nomeProduto,
        "preco": preco,
        "descricao": descricao,
        "imagemUrl": imagemUrl
    }
    post(url, body)
    location.reload()
}

function excluirProduto(){
    let idProduto = document.getElementById("txtId").innerText
    let url = "http://localhost:8080/produtos/" + idProduto
    deletar(url)
    this.location.reload()

}

main()