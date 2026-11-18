function verifyLoginForm() {
  var email= document.getElementById("email").value.trim();
  var password = document.getElementById("password").value.trim();


  if (email.length === 0 || email === "") {
    alert("Por favor, insira o email.");
    return false;
  }

  if (password.length === 0 || password === "") {
    alert("Por favor, insira a senha.");
    return false;
  }
  
  return true;
}

function verifyUserForm() {
  var email = document.getElementById("email").value.trim();
  var password = document.getElementById("password").value.trim();
  var confirmPassword = document.getElementById("confirm_password").value.trim();
  var role = document.getElementById("role").value.trim();


  if (email.length === 0 || email.length < 5 || email.length > 60) {
    alert("Por favor, insira o email do usuário.");
    return false;
  }
  
  if (!(email.includes("@") && (email.includes(".com") || email.includes(".net")))){
    alert("O email deve ter no mínimo 3 caracteres.");
    return false;
  }   

  if (password.length === 0 || password.length < 3 || password.length > 30) {
    alert("Por favor, insira a senha.");
    return false;
  }

  if (confirmPassword.length === 0) {
    alert("Por favor, confirme a senha.");
    return false;
  }

  if (password !== confirmPassword) {
    alert("A confirmação de senha não coincide com a senha informada.");
    return false;
  }

  if (role !== "1" && role !== "2") {
    alert("Por favor, selecione um perfil válido (Admin ou Usuário).");
    return false;
  }

  console.log("Formulário de usuário validado com sucesso!");
  return true;
}

function verifyUpdatedUserForm() {

  var password = document.getElementById("password").value.trim();
  var confirmPassword = document.getElementById("confirm_password").value.trim();
  var role = document.getElementById("role").value.trim();

  if (!password.length === 0){
      if (password.length < 3 || password.length > 30) {
        alert("Por favor, insira a senha.");
        return false;
      }

      if (confirmPassword.length === 0) {
        alert("Por favor, confirme a senha.");
        return false;
      }

      if (password !== confirmPassword) {
        alert("A confirmação de senha não coincide com a senha informada.");
        return false;
      }
  }
  

  if (role !== "1" && role !== "2") {
    alert("Por favor, selecione um perfil válido (Admin ou Usuário).");
    return false;
  }

  console.log("Formulário de usuário validado com sucesso!");
  return true;
}


// Exclui usuário via DELETE
function deleteUser(contextPath, id) {
  if (!confirm("Deseja realmente excluir este usuário?")) {
    return;
  }

  fetch(contextPath + "/UsersServlet?id=" + id, {
    method: "DELETE",
  })
    .then(function (response) {
      if (response.ok) {
        window.location.href = contextPath + "/UsersServlet?a=getAll";
      } else {
        alert("Erro ao excluir usuário.");
      }
    })
    .catch(function (error) {
      console.error("Erro no DELETE:", error);
      alert("Erro ao excluir usuário.");
    });
}
