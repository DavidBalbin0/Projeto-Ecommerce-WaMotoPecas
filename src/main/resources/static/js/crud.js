function get(url){
    let request = new XMLHttpRequest()
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function put(url, body){
    let request = new XMLHttpRequest()
    request.open("PUT", url, true)
    request.setRequestHeader("Content-type", "application/json")
    request.send(JSON.stringify(body))
}

function post(url, body){
    console.log("Body=", body)
    let request = new XMLHttpRequest()
    request.open("POST", url, true)
    request.setRequestHeader("Content-type", "application/json")
    request.send(JSON.stringify(body))
    console.log(JSON.stringify(body))
}

function deletar(url){
    let request = new XMLHttpRequest()
    request.open("DELETE", url, true)
    request.send()
}

function postLogin(url, body){
    console.log("Body=", body)
    let request = new XMLHttpRequest()
    request.open("POST", url, true)
    request.setRequestHeader("Content-type", "application/json")
    let dadosJwt = ""
    request.onload = function () {
        console.log(this.responseText)
        dadosJwt = JSON.parse(this.responseText)
        console.log(dadosJwt)
        let tipo = dadosJwt.tipo
        let token = dadosJwt.token
        let tipoToken = tipo + " " + token
        request.setRequestHeader("Autorization", tipoToken)

    }
    request.send(JSON.stringify(body))
}