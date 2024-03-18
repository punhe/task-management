const fullscreenButton = document.getElementById('sherah-header__full');
const htmlElement = document.documentElement;

fullscreenButton.addEventListener('click', () => {
    if (document.fullscreenElement) {
        document.exitFullscreen();
    } else {
        htmlElement.requestFullscreen();
    }
});










