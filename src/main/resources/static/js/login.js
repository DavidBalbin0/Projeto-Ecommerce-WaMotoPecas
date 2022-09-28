function logarUsuario(){
    let url = "http://localhost:8080/auth"
    let email = document.getElementById("email").value
    let senha = document.getElementById("senha").value
    let body = {
        "email": email,
        "senha": senha
    }
    postLogin(url,body)
}