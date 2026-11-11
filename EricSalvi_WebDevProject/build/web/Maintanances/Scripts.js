// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
//  */

// function deletePerson(contextPath, id) {
//   if (!confirm("Deseja realmente excluir esta Manutenção?")) {
//     return;
//   }

//   fetch(contextPath + "/MaintananceServlet?id=" + id, {
//     method: "DELETE",
//   })
//     .then((response) => {
//       if (response.ok) {
//         window.location.href = contextPath + "/MaintananceServlet?a=getAll";
//       } else {
//         alert("Erro ao excluir Manutenção.");
//       }
//     })
//     .catch((error) => {
//       console.error("Erro no DELETE:", error);
//       alert("Erro ao excluir Manutenção.");
//     });
// }

// function validateForm() {
//   var name = document.getElementById("name").value.trim();
//   var carPlate = document.getElementById("car_plate").value.trim();
//   var doneBy = document.getElementById("done_by").value.trim();
//   var doneDate = document.getElementById("done_at").value;
//   var observations = document.getElementById("observations").value.trim();

//   if (name.length === 0) {
//     alert("Por favor, insira o nome do veículo.");
//     return false;
//   }

//   if (name.length > 150) {
//     alert("Nome do veículo maior que o permitido (150 caracteres).");
//     return false;
//   }

//   if (carPlate.length === 0) {
//     alert("Por favor, insira a placa do veículo.");
//     return false;
//   }

//   if (carPlate.length > 10) {
//     alert("Placa maior que o permitido (10 caracteres).");
//     return false;
//   }

//   if (doneBy.length > 100) {
//     alert("Realizado por maior que o permitido (100 caracteres).");
//     return false;
//   }

//   if (doneDate && new Date(doneDate + "T00:00:00") > new Date()) {
//     alert("A data de realização não pode ser maior que a data atual.");
//     return false;
//   }

//   if (observations.length > 500) {
//     alert("Observação maior que o permitido (500 caracteres).");
//     return false;
//   }

//   console.log("Formulário validado com sucesso!");
//   return true;
// }

// function addMaintenanceOption() {
//   var select = document.getElementById("option_select");
//   var option = select.options[select.selectedIndex];
//   var value = document.getElementById("option_value").value;

//   if (!option.value || value === "") {
//     alert("Selecione uma opção e informe o valor unitário.");
//     return;
//   }

//   var table = document.getElementById("selected_options");
//   var row = table.insertRow();
//   row.dataset.optionId = option.value;
//   row.innerHTML =
//     "<td>" +
//     option.dataset.name +
//     "</td>" +
//     "<td>" +
//     option.dataset.description +
//     "</td>" +
//     "<td>" +
//     value +
//     "</td>" +
//     '<td><button type="button" onclick="removeMaintenanceOption(this)">Remover</button>' +
//     '<input type="hidden" name="option_ids" value="' +
//     option.value +
//     '">' +
//     '<input type="hidden" name="unit_values" value="' +
//     value +
//     '"></td>';

//   option.disabled = true;
//   select.selectedIndex = 0;
//   document.getElementById("option_value").value = "";
// }

// function removeMaintenanceOption(button) {
//   var row = button.parentElement.parentElement;
//   var option = document.querySelector(
//     '#option_select option[value="' + row.dataset.optionId + '"]',
//   );
//   if (option) {
//     option.disabled = false;
//   }
//   row.remove();
// }
