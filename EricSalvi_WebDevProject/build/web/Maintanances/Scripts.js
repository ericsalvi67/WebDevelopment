/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function deletePerson(contextPath, id) {

    if (!confirm("Deseja realmente excluir esta Manutenção?")) {
        return;
    }

    fetch(contextPath + "/MaintananceServlet?id=" + id, {
        method: "DELETE"
    })
    .then(response => {
        if (response.ok) {
            window.location.href =
                contextPath + "/MaintananceServlet?a=getAll";
        } else {
            alert("Erro ao excluir Manutenção.");
        }
    })
    .catch(error => {
        console.error("Erro no DELETE:", error);
        alert("Erro ao excluir Manutenção.");
    });
}

function validateForm() {
    var name = document.getElementById("name").value.trim();
    var carPlate = document.getElementById("car_plate").value.trim();

    if (name.length === 0) {
        alert("Por favor, insira o nome do veículo.");
        return false;
    }

    if (name.length > 150) {
        alert("Nome do veículo maior que o permitido (150 caracteres).");
        return false;
    }

    if (carPlate.length === 0) {
        alert("Por favor, insira a placa do veículo.");
        return false;
    }

    if (carPlate.length > 10) {
        alert("Placa maior que o permitido (10 caracteres).");
        return false;
    }

    console.log("Formulário validado com sucesso!");
    return true;
}
