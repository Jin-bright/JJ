
const item = document.querySelectorAll(".menu__item");
const icon = document.querySelectorAll(".menu__icon");
const text = document.querySelectorAll(".menu__text");
const active = document.querySelector("#active");
const active2 = document.querySelector("#active-2");
const active3 = document.querySelector("#active-3");
let colors = ["#c6a700","#c25e00", "#b91400","#5c007a"];

/* */
let getItem = (event) => {
    getIcon();
    let indexItem = event.currentTarget.id;
    indexItem = indexItem.split("i-").join("");

    active.style.left = `\${(indexItem * 100) + 10}px`;
    active.style.background = colors[indexItem];

    active2.style.left = `\${(indexItem * 100) + 10}px`;
    active2.style.background = colors[indexItem];
    active2.classList.add("is-item-animated");

    active3.style.left = `\${(indexItem * 100) + 70}px`;
    active3.style.background = colors[indexItem];
    active3.classList.add("is-item-animated");

    event.currentTarget.children[0].classList.add("is-icon-visible");
    event.currentTarget.children[1].classList.add("is-text-visible");

    setTimeout(() => {
        active.classList.remove("is-item-animated");
    }, 300);

}

let getIcon = (event) =>{
    for (var i = 0; i < icon.length; i++) {
        icon[i].classList.remove("is-icon-visible");
    }
    for (var i = 0; i < text.length; i++) {
        text[i].classList.remove("is-text-visible");
    }
}

let mainFunc = (event) =>{
    for (var i = 0; i < item.length; i++) {
        item[i].addEventListener("click", getItem);
    }
}
/* */
window.addEventListener("load", mainFunc);