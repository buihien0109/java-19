const heading = document.getElementById("heading");
console.log(heading);

const heading1 = document.querySelector("#heading");
console.log(heading1);

// ? truy cập p1, p2, p3
const p1 = document.querySelector("p");
const p2 = document.querySelector(".para");
// .para + .para || p:nth-of-type(3)
const p3 = document.querySelector(".para + .para");
console.log(p3);

// div + ul > li:nth-of-type(3) || div + ul .li + li
const li3 = document.querySelector("body > ul .li + li");
console.log(li3);

const paraList = document.querySelectorAll("p");
console.log(paraList);

for (let i = 0; i < paraList.length; i++) {
    paraList[i].style.color = "red";
    paraList[i].style.backgroundColor = "black";
}

Array.from(paraList).map(e => console.log(e))

// Get/set content
const ul1 = document.querySelector(".box ul");
console.log(ul1.innerHTML);
console.log(ul1.innerText);
console.log(ul1.textContent);

heading.innerHTML = "Xin chào";
heading.innerHTML = "<span>Hello</span>"

heading.textContent = "<span>Hello</span>"

const socialMedia = [
    {
        label: "Google",
        link: "https://google.com"
    },
    {
        label: "Facebook",
        link: "https://facebook.com"
    },
    {
        label: "Instagram",
        link: "https://www.instagram.com/"
    }
];

const socialMediaEl = document.querySelector(".social-media")
const renderSocialMedia = list => {
    socialMediaEl.innerHTML = "";
    let html = "";
    list.forEach(e => {
        html += `
            <li>
                <input type="checkbox" ${e.status ? "checked" : ""}>
                <span class="${e.status ? "active" : ""}">${e.title}</span>
                <button>Edit</button>
                <button>Delete</button>
            </li>
        `
    })
    socialMediaEl.innerHTML = html;
}
renderSocialMedia(socialMedia);

// Thêm phần tử
const btn = document.createElement("button");
btn.innerText = "Click me";
console.log(btn);

document.body.prepend(btn);

const btnCopy = btn.cloneNode(true)
document.body.appendChild(btnCopy);

const btnCopy_1 = btn.cloneNode(true)
const scriptEl = document.querySelector("script");
document.body.insertBefore(btnCopy_1, scriptEl);

// Insert part 2
p3.insertAdjacentHTML("afterend", `<input type="text" placeholder="Enter name"/>`)
p1.insertAdjacentElement("beforebegin", btn.cloneNode(true))

// Xóa
document.body.removeChild(p1);
p2.parentElement.removeChild(p2);

// Thay thế
// let newElement = document.createElement('h1');
// newElement.innerText = "Xin chào các bạn";
// document.body.replaceChild(newElement, p3);
