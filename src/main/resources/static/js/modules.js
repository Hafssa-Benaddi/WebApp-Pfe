function toggleDetails(button) {
      const details = button.parentElement.nextElementSibling;
      details.classList.toggle("active");
      button.textContent = details.classList.contains("active") ? "▲" : "▼";
    }
 function openAddModuleModal() {
  document.getElementById('addModuleModal').style.display = 'block';
}

function closeAddModuleModal() {
  document.getElementById('addModuleModal').style.display = 'none';
}
window.onclick = function(event) {
  let modal = document.getElementById('addModuleModal');
  if (event.target === modal) {
    modal.style.display = 'none';
  }
}

