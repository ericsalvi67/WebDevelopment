/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function deletePerson(contextPath, id) {

    if (!confirm("Deseja realmente excluir esta pessoa?")) {
        return;
    }

    fetch(contextPath + "/PeopleServlet?id=" + id, {
        method: "DELETE"
    })
    .then(response => {
        if (response.ok) {
            window.location.href =
                contextPath + "/PeopleServlet?a=getAll";
        } else {
            alert("Erro ao excluir pessoa.");
        }
    })
    .catch(error => {
        console.error("Erro no DELETE:", error);
        alert("Erro ao excluir pessoa.");
    });
}

function validateForm() {
  var name = document.getElementById("name").value;
  var email = document.getElementById("email").value;
  var phone = document.getElementById("phone").value;
  var birth = document.getElementById("date_of_birth").value;

  if (name.length > 150) {
    alert("Nome maior que o permitido (150 caracteres).");
    console.log(name);
    return false;
  }
  
  if (name.length <= 0) {
    alert("Por favor, insira um nome.");
    console.log(name);
    return false;
  }
  
  if (email.length > 50) {
    alert("Email maior que o permitido (50 caracteres).");
    console.log(email);
    return false;
  }
  
  if (email.length <= 0) {
    alert("Por favor, insira um email.");
    console.log(email);
    return false;
  }
  
  if (phone.length > 50) {
    alert("Telefone maior que o permitido (15 caracteres).");
    console.log(phone);
    return false;
  }
  
  if (phone.length <= 0) {
    alert("Por favor, insira um telefone.");
    console.log(phone);
    return false;
  }
  
 if (new Date(birth) >= new Date()) {
  alert("Data de nascimento inválida.");
  console.log(birth);
  return false;
}
  
  console.log("Formulário validado com sucesso!");
  return true;
}
