function verifyLoginForm() {
  var emailInput = document.getElementById("email");
  var passwordInput = document.getElementById("password");

  var email = emailInput ? emailInput.value.trim() : "";
  var password = passwordInput ? passwordInput.value : "";

  if (email.length === 0) {
    alert("Por favor, insira o email.");
    if (emailInput) emailInput.focus();
    return false;
  }

  if (password.length === 0) {
    alert("Por favor, insira a senha.");
    if (passwordInput) passwordInput.focus();
    return false;
  }

  return true;
}

function verifyUserForm() {
  var emailInput = document.getElementById("email");
  var passwordInput = document.getElementById("password");
  var confirmInput = document.getElementById("confirm_password");
  var roleInput = document.getElementById("role");

  var email = emailInput ? emailInput.value.trim() : "";
  var password = passwordInput ? passwordInput.value : "";
  var confirmPassword = confirmInput ? confirmInput.value : "";
  var role = roleInput ? roleInput.value : "";

  if (email.length === 0) {
    alert("Por favor, insira o email do usuário.");
    if (emailInput) emailInput.focus();
    return false;
  }

  if (email.length < 3) {
    alert("O email deve ter no mínimo 3 caracteres.");
    if (emailInput) emailInput.focus();
    return false;
  }

  if (email.length > 60) {
    alert("Email maior que o permitido no banco (máximo de 60 caracteres).");
    if (emailInput) emailInput.focus();
    return false;
  }

  if (password.length === 0) {
    alert("Por favor, insira a senha.");
    if (passwordInput) passwordInput.focus();
    return false;
  }

  if (password.length < 3) {
    alert("A senha deve ter no mínimo 3 caracteres.");
    if (passwordInput) passwordInput.focus();
    return false;
  }

  if (password.length > 60) {
    alert("A senha deve ter no máximo 60 caracteres.");
    if (passwordInput) passwordInput.focus();
    return false;
  }

  if (confirmPassword.length === 0) {
    alert("Por favor, confirme a senha.");
    if (confirmInput) confirmInput.focus();
    return false;
  }

  if (password !== confirmPassword) {
    alert("A confirmação de senha não coincide com a senha informada.");
    if (confirmInput) confirmInput.focus();
    return false;
  }

  if (role !== "1" && role !== "2") {
    alert("Por favor, selecione um perfil válido (Admin ou Usuário).");
    if (roleInput) roleInput.focus();
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
