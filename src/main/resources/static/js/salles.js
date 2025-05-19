function openModal() {
  const modal = document.getElementById("addSalleModal");
  if (modal) modal.style.display = "block";
}

function closeModal() {
  const modal = document.getElementById("addSalleModal");
  if (modal) modal.style.display = "none";
}

document.addEventListener("DOMContentLoaded", function () {
  const addBtn = document.getElementById("openModalBtn-salle");
  const addSalleForm = document.getElementById("addSalleForm");

  if (addBtn) {
    addBtn.addEventListener("click", openModal);
  }

  addSalleForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const formData = new FormData(addSalleForm);
    const data = {
      numeroSalle: formData.get("numeroSalle"),
      capacity: parseInt(formData.get("capacity")),
      type: formData.get("type"),
    };

    fetch("/salles/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (response.ok) return response.text();
        throw new Error("Erreur lors de l'ajout de la salle");
      })
      .then(() => {
        const tbody = document.querySelector("#sallesTable tbody");
        const newRow = document.createElement("tr");
        newRow.innerHTML = `<td>${data.numeroSalle}</td><td>${data.capacity}</td><td>${data.type}</td>`;
        tbody.appendChild(newRow);
        closeModal();
        addSalleForm.reset();
      })
      .catch((error) => {
        console.error(error);
        alert(error.message);
      });

  });

  window.onclick = function (event) {
    const modal = document.getElementById("addSalleModal");
    if (event.target === modal) {
      closeModal();
    }
  };
});
