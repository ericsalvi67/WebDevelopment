/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function deleteCategory(contextPath, id) {

    if (!confirm("Deseja realmente excluir esta Categoria?")) {
        return;
    }

    fetch(contextPath + "/CategoriesServlet?id=" + id, {
        method: "DELETE"
    })
    .then(response => {
        if (response.ok) {
            window.location.href =
                contextPath + "/CategoriesServlet?a=getAll";
        } else {
            alert("Erro ao excluir Categoria.");
        }
    })
    .catch(error => {
        console.error("Erro no DELETE:", error);
        alert("Erro ao excluir Categoria.");
    });
}

function validateForm() {
  var description = document.getElementById("description").value;

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
 
  console.log("Formulário validado com sucesso!");
  return true;
}
