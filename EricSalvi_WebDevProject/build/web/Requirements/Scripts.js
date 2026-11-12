/* =========================================================
   REQUISITOS - SCRIPTS E VALIDAÇÕES (verifyRequirementForm)
   ========================================================= */

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

  if (type !== "RF" && type !== "RNF") {
    alert("Por favor, selecione um tipo de requisito válido (RF ou RNF).");
    if (typeSelect) typeSelect.focus();
    return false;
  }

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

  var ver = parseInt(version, 10);
  if (isNaN(ver) || ver <= 0) {
    alert("A versão do requisito deve ser um número maior ou igual a 1.");
    if (versionInput) versionInput.focus();
    return false;
  }

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

  if (description.length > 255) {
    alert("A descrição do requisito é maior que o permitido (máximo de 255 caracteres).");
    if (descInput) descInput.focus();
    return false;
  }

  if (isNaN(priority) || priority < 1 || priority > 5) {
    alert("A prioridade deve ser um valor entre 1 e 5.");
    return false;
  }

  if (isNaN(complexity) || complexity < 1 || complexity > 5) {
    alert("A complexidade deve ser um valor entre 1 e 5.");
    return false;
  }

  var codeHidden = document.getElementById("code");
  if (codeHidden) {
    codeHidden.value = type + String(num).padStart(3, "0");
  }

  console.log("Formulário de requisito validado com sucesso!");
  return true;
}

function validateForm() {
  return verifyRequirementForm();
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

// Carrega os dados do requisito selecionado nos campos acima para edição
function editRequirement(button) {
  var row = button.closest("tr");
  if (!row) return;

  var id = row.getAttribute("data-id");
  var code = row.getAttribute("data-code") || "";
  var type = row.getAttribute("data-type") || "RF";
  var version = row.getAttribute("data-version") || "1";
  var requirement = row.getAttribute("data-requirement") || "";
  var description = row.getAttribute("data-description") || "";
  var priority = row.getAttribute("data-priority") || "1";
  var complexity = row.getAttribute("data-complexity") || "1";

  var numericPart = code.replace(/\D+/g, "");
  var codeNum = numericPart ? parseInt(numericPart, 10) : 1;

  document.getElementById("req-id").value = id;
  document.getElementById("type").value = type;
  document.getElementById("code-number").value = codeNum;
  updateCodePrefix();

  document.getElementById("version").value = version;
  document.getElementById("requirement").value = requirement;
  document.getElementById("description").value = description;

  document.getElementById("priority").value = priority;
  document.getElementById("priority-val").value = priority;

  document.getElementById("complexity").value = complexity;
  document.getElementById("complexity-val").value = complexity;

  var legend = document.getElementById("req-form-legend");
  if (legend) {
    legend.textContent = "Editar Requisito (" + code + " - v" + version + ")";
  }

  var submitBtn = document.getElementById("btn-submit-req");
  if (submitBtn) {
    submitBtn.value = "Salvar Alterações";
  }

  var cancelBtn = document.getElementById("btn-cancel-req");
  if (cancelBtn) {
    cancelBtn.style.display = "inline-block";
  }

  var fieldset = document.getElementById("req-fieldset");
  if (fieldset) {
    fieldset.scrollIntoView({ behavior: "smooth" });
  }
  document.getElementById("requirement").focus();
}

// Restaura o formulário de requisitos para o modo de adição
function resetRequirementForm() {
  var form = document.getElementById("req-form");
  if (form) {
    form.reset();
  }

  document.getElementById("req-id").value = "0";
  document.getElementById("type").value = "RF";
  document.getElementById("code-number").value = "";
  updateCodePrefix();

  document.getElementById("version").value = "1";
  document.getElementById("priority").value = "1";
  document.getElementById("priority-val").value = "1";
  document.getElementById("complexity").value = "1";
  document.getElementById("complexity-val").value = "1";

  var legend = document.getElementById("req-form-legend");
  if (legend) {
    legend.textContent = "Adicionar Novo Requisito / Nova Versão";
  }

  var submitBtn = document.getElementById("btn-submit-req");
  if (submitBtn) {
    submitBtn.value = "Adicionar Requisito";
  }

  var cancelBtn = document.getElementById("btn-cancel-req");
  if (cancelBtn) {
    cancelBtn.style.display = "none";
  }
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
          contextPath + "/RequirementsServlet?project_id=" + projectId;
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
    if (!row.hasAttribute("data-priority")) return;

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
