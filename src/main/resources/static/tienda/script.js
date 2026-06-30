const open_menu = document.querySelector('#open-menu');
const close_menu = document.querySelector('#close-menu');
const nav_bottom = document.querySelector('.nav-bottom');

open_menu.addEventListener('click', function () {
  nav_bottom.classList.add('active');
});

close_menu.addEventListener('click', function () {
  nav_bottom.classList.remove('active');
});
