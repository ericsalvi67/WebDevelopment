/* =========================================================
   PROJETOS & REQUISITOS - SCRIPTS E VALIDAÇÕES (verifyForm)
   Baseado no padrão limpo de Scripts.js de Maintanances
   ========================================================= */

// Validação do Formulário de Projeto (antes de enviar ao banco)
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

// Validação do Formulário de Requisitos (antes de enviar ao banco)
function verifyRequirementForm() {
  var typeSelect = document.getElementById("type");
  var codeNumberInput = document.getElementById("code-number");
  var versionInput = document.getElementById("version");
  var reqInput = document.getElementById("requirement");
  var descInput = document.getElementById("description");
  var priInput = document.getElementById("priority");
  var comInput = document.getElementById("complexity");

  var type = typeSelect ? typeSelect.value : "";
  var codeNumber = codeNumberInput ? codeNumberInput.value.trim() : "";
  var version = versionInput ? versionInput.value.trim() : "";
  var requirement = reqInput ? reqInput.value.trim() : "";
  var description = descInput ? descInput.value.trim() : "";
  var priority = priInput ? parseInt(priInput.value, 10) : 1;
  var complexity = comInput ? parseInt(comInput.value, 10) : 1;

  // 1. Validação do Tipo
  if (type !== "RF" && type !== "RNF") {
    alert("Por favor, selecione um tipo de requisito válido (RF ou RNF).");
    if (typeSelect) typeSelect.focus();
    return false;
  }

  // 2. Validação do Código Numérico
  if (codeNumber.length === 0) {
    alert("Por favor, informe o número do código do requisito.");
    if (codeNumberInput) codeNumberInput.focus();
    return false;
  }

  var num = parseInt(codeNumber, 10);
  if (isNaN(num) || num <= 0) {
    alert("O código do requisito deve ser um número inteiro positivo.");
    if (codeNumberInput) codeNumberInput.focus();
    return false;
  }

  if (codeNumber.length > 4) {
    alert("Número do código muito longo (máximo de 4 dígitos permitidos).");
    if (codeNumberInput) codeNumberInput.focus();
    return false;
  }

  // 3. Validação da Versão
  var ver = parseInt(version, 10);
  if (isNaN(ver) || ver <= 0) {
    alert("A versão do requisito deve ser um número maior ou igual a 1.");
    if (versionInput) versionInput.focus();
    return false;
  }

  // 4. Validação do Título / Requisito
  if (requirement.length === 0) {
    alert("Por favor, insira o título do requisito.");
    if (reqInput) reqInput.focus();
    return false;
  }

  if (requirement.length < 3) {
    alert("O título do requisito deve ter no mínimo 3 caracteres.");
    if (reqInput) reqInput.focus();
    return false;
  }

  if (requirement.length > 100) {
    alert("Título do requisito maior que o permitido no banco (máximo de 100 caracteres).");
    if (reqInput) reqInput.focus();
    return false;
  }

  // 5. Validação da Descrição (opcional, mas limitada a 255 no banco)
  if (description.length > 255) {
    alert("A descrição do requisito é maior que o permitido (máximo de 255 caracteres).");
    if (descInput) descInput.focus();
    return false;
  }

  // 6. Validação de Prioridade e Complexidade (1 a 5)
  if (isNaN(priority) || priority < 1 || priority > 5) {
    alert("A prioridade deve ser um valor entre 1 e 5.");
    return false;
  }

  if (isNaN(complexity) || complexity < 1 || complexity > 5) {
    alert("A complexidade deve ser um valor entre 1 e 5.");
    return false;
  }

  // Garante que o input oculto 'code' receba o código formatado padrão (ex: RF001 ou RNF001)
  var codeHidden = document.getElementById("code");
  if (codeHidden) {
    codeHidden.value = type + String(num).padStart(3, "0");
  }

  console.log("Formulário de requisito validado com sucesso!");
  return true;
}

// Função genérica verifyForm / validateForm compatível com chamadas diretas
function verifyForm(target) {
  if (target === "project" || document.getElementById("name")) {
    return verifyProjectForm();
  }
  return verifyRequirementForm();
}

function validateForm() {
  return verifyForm();
}

// =========================================================
// FUNÇÕES AUXILIARES DE NAVEGAÇÃO, ABAS E EXCLUSÃO
// =========================================================

// Alterna entre a Aba 1 (Projeto) e a Aba 2 (Requisitos)
function switchTab(tabId) {
  var contents = document.querySelectorAll(".tab-content");
  contents.forEach(function (c) {
    c.style.display = "none";
  });

  var btns = document.querySelectorAll(".tab-btn");
  btns.forEach(function (b) {
    b.classList.remove("active");
  });

  var targetTab = document.getElementById(tabId);
  if (targetTab) {
    targetTab.style.display = "block";
  }

  var activeBtn = document.getElementById(
    tabId === "tab-projeto" ? "btn-tab-projeto" : "btn-tab-requisitos"
  );
  if (activeBtn) {
    activeBtn.classList.add("active");
  }
}

// Atualiza o prefixo do código ao alterar o tipo (RF ou RNF)
function updateCodePrefix() {
  var typeSelect = document.getElementById("type");
  var prefixSpan = document.getElementById("code-prefix");
  if (typeSelect && prefixSpan) {
    prefixSpan.textContent = typeSelect.value;
  }
  updateCodePreview();
}

// Atualiza a prévia do código formatado com 3 dígitos (ex: RF001 ou RNF001)
function updateCodePreview() {
  var typeSelect = document.getElementById("type");
  var numInput = document.getElementById("code-number");
  var preview = document.getElementById("code-preview");
  var codeHidden = document.getElementById("code");

  if (!typeSelect || !numInput) return;

  var type = typeSelect.value;
  var num = numInput.value ? numInput.value.padStart(3, "0") : "001";
  var formatted = type + num;

  if (preview) preview.textContent = formatted;
  if (codeHidden) codeHidden.value = formatted;
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

// Exclui requisito individual via DELETE
function deleteRequirement(contextPath, id, projectId) {
  if (!confirm("Deseja realmente excluir este requisito?")) {
    return;
  }

  fetch(contextPath + "/RequirementsServlet?id=" + id, {
    method: "DELETE",
  })
    .then(function (response) {
      if (response.ok) {
        window.location.href =
          contextPath + "/ProjectsServlet?a=getById&id=" + projectId + "&tab=req";
      } else {
        alert("Erro ao excluir requisito.");
      }
    })
    .catch(function (error) {
      console.error("Erro no DELETE:", error);
      alert("Erro ao excluir requisito.");
    });
}

// Filtra requisitos por prioridade e complexidade (RF9)
function filterRequirements() {
  var pri = document.getElementById("filter-priority").value;
  var com = document.getElementById("filter-complexity").value;
  var rows = document.querySelectorAll("#requirements-table-body tr");

  rows.forEach(function (row) {
    var rowPri = row.getAttribute("data-priority");
    var rowCom = row.getAttribute("data-complexity");

    var matchPri = !pri || rowPri === pri;
    var matchCom = !com || rowCom === com;

    if (matchPri && matchCom) {
      row.style.display = "";
    } else {
      row.style.display = "none";
    }
  });
}
