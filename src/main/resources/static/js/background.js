const slides = document.querySelectorAll('.slide');
let current = 0;

function showNextSlide() {
    slides[current].style.opacity = 0;
    current = (current + 1) % slides.length;
    slides[current].style.opacity = 1;
}

slides[current].style.opacity = 1; 
setInterval(showNextSlide, 5000); // change every 5 seconds
