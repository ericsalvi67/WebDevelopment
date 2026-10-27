/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function deleteUmbrella(contextPath, id) {

    if (!confirm("Deseja realmente excluir esta OS?")) {
        return;
    }

    fetch(contextPath + "/UmbrellasServlet?id=" + id, {
        method: "DELETE"
    })
    .then(response => {
        if (response.ok) {
            window.location.href =
                contextPath + "/UmbrellasServlet?a=getAll";
        } else {
            alert("Erro ao excluir OS.");
        }
    })
    .catch(error => {
        console.error("Erro no DELETE:", error);
        alert("Erro ao excluir OS.");
    });
}

function validateForm() {
  var description = document.getElementById("description").value;
  var price = document.getElementById("price").value;
  var categories_id = document.getElementById("categories_id").value;

  if (description.length > 150) {
    alert("Descrição maior que o permitido (150 caracteres).");
    console.log(description);
    return false;
  }
  
  if (description.length <= 0) {
    alert("Por favor, insira uma descrição.");
    console.log(description);
    return false;
  }
  
  if (price <= 0) {
    alert("Por favor, insira um valor.");
    console.log(price);
    return false;
  }
  
  if (categories_id = null) {
    alert("Defina uma categoria");
    console.log(categories_id);
    return false;
  }
  
  console.log("Formulário validado com sucesso!");
  return true;
}
