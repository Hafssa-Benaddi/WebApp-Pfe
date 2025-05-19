// JavaScript pour la gestion des professeurs

document.addEventListener("DOMContentLoaded", function () {
  const fileInput = document.getElementById("fileInput");
  const fileName = document.getElementById("fileName");
  const searchInput = document.getElementById("searchInput");
  const departmentFilter = document.getElementById("departmentFilter");
  const table = document.getElementById("professorTable");
  const rows = table.querySelectorAll("tbody tr");

  // Gestion affichage du fichier
  fileInput.addEventListener("change", function () {
    if (fileInput.files.length > 0) {
      fileName.textContent = "Fichier sélectionné : " + fileInput.files[0].name;
    } else {
      fileName.textContent = "";
    }
  });

  // Générer dynamiquement les départements
  const departmentsSet = new Set();

  rows.forEach(row => {
    const department = row.cells[3].textContent.trim();
    if (department !== "") {
      departmentsSet.add(department);
    }
  });

  // Ajouter les options au select
  departmentsSet.forEach(department => {
    const option = document.createElement("option");
    option.value = department;
    option.textContent = department;
    departmentFilter.appendChild(option);
  });

  // Filtrer le tableau
  function filterTable() {
    const searchText = searchInput.value.toLowerCase();
    const selectedDepartment = departmentFilter.value.toLowerCase();

    rows.forEach(row => {
      const name = row.cells[1].textContent.toLowerCase();
      const department = row.cells[3].textContent.toLowerCase();
      const matchName = name.includes(searchText);
      const matchDepartment = selectedDepartment === "" || department.includes(selectedDepartment);

      if (matchName && matchDepartment) {
        row.style.display = "";
      } else {
        row.style.display = "none";
      }
    });
  }

  searchInput.addEventListener("input", filterTable);
  departmentFilter.addEventListener("change", filterTable);
});

// Gérer l'ouverture de la modal
function openModal() {
  document.getElementById("addProfessorModal").style.display = "block";
}

// Gérer la fermeture de la modal
function closeModal() {
  document.getElementById("addProfessorModal").style.display = "none";
}

// Fermer la modal si l'utilisateur clique en dehors
window.onclick = function (event) {
  const modal = document.getElementById("addProfessorModal");
  if (event.target == modal) {
    modal.style.display = "none";
  }
};
