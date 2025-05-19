function openModal() {
  document.getElementById("addFiliereModal").style.display = "block";
}

function hideAddModal() {
  document.getElementById("addFiliereModal").style.display = "none";
}

/* filtrer les filieres */
function filterFilieres() {
    const niveau = document.getElementById("niveauFilter").value;
    const rows = document.querySelectorAll("tbody tr");

    rows.forEach(row => {
        const filiereNiveau = row.getAttribute("data-niveau");
        if (niveau === "all" || filiereNiveau === niveau) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    });
}
