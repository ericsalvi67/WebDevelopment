function toggleProjectEdit() {
  const name = document.getElementById("project-name");
  const actions = document.getElementById("project-actions");
  name.disabled = false;
  name.focus();
  actions.hidden = false;
}

function updateCodePrefix() {
  const prefix = document.getElementById("type").value;
  document.getElementById("code-prefix").textContent = prefix;
  updateCodePreview();
}

function updateCodePreview() {
  const prefix = document.getElementById("type").value;
  const number = document.getElementById("code-number").value;
  document.getElementById("code-preview").textContent =
    prefix + (number ? number : "");
}

function prepareRequirementCode() {
  const prefix = document.getElementById("type").value;
  const number = document.getElementById("code-number").value;
  document.getElementById("code").value = prefix + number;
}

function addProjectRequirement() {
  const number = document.getElementById("code-number").value;
  const text = document.getElementById("requirement").value.trim();

  if (!number || !text) {
    alert("Informe o código e o requisito.");
    return;
  }
  
  const type = document.getElementById("type").value;
  const values = [
    type + number,
    type,
    text,
    document.getElementById("description").value.trim(),
    document.getElementById("priority").value,
    document.getElementById("complexity").value,
    document.getElementById("version").value,
  ];
  const row = document.getElementById("project-requirements").insertRow();
  row.innerHTML =
    "<td>" +
    values[0] +
    "</td><td>" +
    values[6] +
    "</td><td>" +
    values[2] +
    "</td><td>" +
    values[3] +
    "</td><td>" +
    values[4] +
    "</td><td>" +
    values[5] +
    '</td><td><button type="button" class="botao remove-requirement" ' +
    'onclick="removeProjectRequirement(this)">Remover</button></td>';
  const fields = document.getElementById("requirement-fields");
  [
    "code",
    "type",
    "requirement",
    "description",
    "priority",
    "complexity",
    "version",
  ].forEach(function (name, index) {
    const input = document.createElement("input");
    input.type = "hidden";
    input.name = name;
    input.value = values[index];
    fields.appendChild(input);
  });
  document.getElementById("code-number").value = "";
  document.getElementById("requirement").value = "";
  document.getElementById("description").value = "";
}

function removeProjectRequirement(button) {
  const row = button.parentElement.parentElement;
  const index = Array.from(row.parentElement.children).indexOf(row);
  const fields = document.getElementById("requirement-fields");
  for (let i = 0; i < 7; i++) {
    fields.removeChild(fields.children[index * 7]);
  }
  row.remove();
}

function prepareProjectRequirements() {
  return true;
}
