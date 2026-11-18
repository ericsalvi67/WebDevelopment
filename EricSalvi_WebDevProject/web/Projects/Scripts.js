function verifyProjectForm() {
  var name = document.getElementById("name").value.trim();

  if (name.length === 0) {
    alert("Por favor, insira o nome do projeto.");
    return false;
  }

  if (name.length < 3) {
    alert("O nome do projeto deve ter no mínimo 3 caracteres.");
    return false;
  }

  if (name.length > 60) {
    alert("Nome do projeto maior que o permitido no banco (máximo de 60 caracteres).");
    return false;
  }

  console.log("Formulário de projeto validado com sucesso!");
  return true;
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
