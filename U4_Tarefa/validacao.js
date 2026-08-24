function validateForm() {
    var avaliacao = document.getElementById("avaliacao").value;
    var nota = document.getElementById("nota").value;
    var comentario = document.getElementById("comentario").value;

    if (avaliacao === "") {
        alert("Por favor, selecione uma avaliação.");
        printf(avaliacao);
        return false;
    }

    if (nota === "") {
        alert("Por favor, selecione uma nota.");
        printf(nota);
        return false;
    }

    if (comentario.trim() === "") {
        alert("Por favor, insira um comentário.");
        printf(comentario);
        return false;
    }


    if (comentario.length < 50 || comentario.length > 100) {
        alert("O comentário deve ter entre 50 e 100 caracteres.");
        printf(comentario.length);
        return false;
    }

    return true;
}