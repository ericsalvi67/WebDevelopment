/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function deletePerson(contextPath, id) {

    if (!confirm("Deseja realmente excluir este carro?")) {
        return;
    }

    fetch(contextPath + "/OptionsServlet?id=" + id, {
        method: "DELETE"
    })
    .then(response => {
        if (response.ok) {
            window.location.href =
                contextPath + "/OptionsServlet?a=getAll";
        } else {
            alert("Erro ao excluir carro.");
        }
    })
    .catch(error => {
        console.error("Erro no DELETE:", error);
        alert("Erro ao excluir carro.");
    });
}

function validateForm() {
    var name = document.getElementById("name").value.trim();
    var description = document.getElementById("description").value.trim();

    if (name.length === 0) {
        alert("Por favor, insira o nome válido.");
        return false;
    }

    if (name.length > 150) {
        alert("Nome da opção maior que o permitido (150 caracteres).");
        return false;
    }

    if (description.length === 0) {
        alert("Por favor, insira uma descrição válida.");
        return false;
    }

    if (description.length > 300) {
        alert("Descrição da opção maior que o permitido (300 caracteres).");
        return false;
    }

    console.log("Formulário validado com sucesso!");
    return true;
}
