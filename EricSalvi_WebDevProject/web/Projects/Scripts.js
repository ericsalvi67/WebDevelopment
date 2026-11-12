/* =========================================================
   PROJETOS - SCRIPTS E VALIDAÇÕES (verifyProjectForm)
   ========================================================= */

function verifyProjectForm() {
  var nameInput = document.getElementById("name");
  var name = nameInput ? nameInput.value.trim() : "";

  if (name.length === 0) {
    alert("Por favor, insira o nome do projeto.");
    if (nameInput) nameInput.focus();
    return false;
  }

  if (name.length < 3) {
    alert("O nome do projeto deve ter no mínimo 3 caracteres.");
    if (nameInput) nameInput.focus();
    return false;
  }

  if (name.length > 60) {
    alert("Nome do projeto maior que o permitido no banco (máximo de 60 caracteres).");
    if (nameInput) nameInput.focus();
    return false;
  }

  console.log("Formulário de projeto validado com sucesso!");
  return true;
}

function validateForm() {
  return verifyProjectForm();
}

// Exclui projeto via DELETE
function deleteProject(contextPath, id) {
  if (!confirm("Deseja realmente excluir este projeto e todos os seus requisitos?")) {
    return;
  }

  fetch(contextPath + "/ProjectsServlet?id=" + id, {
    method: "DELETE",
  })
    .then(function (response) {
      if (response.ok) {
        window.location.href = contextPath + "/ProjectsServlet?a=getAll";
      } else {
        alert("Erro ao excluir projeto.");
      }
    })
    .catch(function (error) {
      console.error("Erro no DELETE:", error);
      alert("Erro ao excluir projeto.");
    });
}
