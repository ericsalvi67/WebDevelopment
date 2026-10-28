/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function deletePerson(contextPath, id) {

    if (!confirm("Deseja realmente excluir este carro?")) {
        return;
    }

    fetch(contextPath + "/CarsServlet?id=" + id, {
        method: "DELETE"
    })
    .then(response => {
        if (response.ok) {
            window.location.href =
                contextPath + "/CarsServlet?a=getAll";
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
    var numberPlate = document.getElementById("number_plate").value.trim();
    var color = document.getElementById("color").value.trim();

    if (name.length === 0) {
        alert("Por favor, insira o nome do veículo.");
        return false;
    }

    if (name.length > 150) {
        alert("Nome do veículo maior que o permitido (150 caracteres).");
        return false;
    }

    if (numberPlate.length === 0) {
        alert("Por favor, insira a placa do veículo.");
        return false;
    }

    if (numberPlate.length > 10) {
        alert("Placa maior que o permitido (10 caracteres).");
        return false;
    }

    if (color.length === 0) {
        alert("Por favor, insira a cor do veículo.");
        return false;
    }

    if (color.length > 50) {
        alert("Cor maior que o permitido (50 caracteres).");
        return false;
    }

    console.log("Formulário validado com sucesso!");
    return true;
}
