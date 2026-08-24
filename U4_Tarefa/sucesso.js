document.title = "Sucesso";

const h1 = document.createElement("h1");
h1.textContent = "Obrigado pelo comentário!";
h1.style.textAlign = "center";

document.body.appendChild(h1);

const voltar = document.createElement("input");
voltar.type = "button";
voltar.value = "Voltar";
voltar.onclick = function() {
    window.location.href = 'index.html';
};

document.body.appendChild(voltar);

