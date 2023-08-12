/*
B1 : Tạo danh sách author và các dữ liệu liên quan
B2 : Render ảnh của author ra ngoài giao diện
B3 : Active thông tin của 1 author bất kỳ khi vừa vào trang. Author nào được active sẽ có thêm class "selected"
B4 : Lắng nghe sự kiện khi click vào image, để active author vừa được chọn
*/

const testimonialList = [
    {
        name: "Mae West",
        quote: "You only live once, but if you do it right, once is enough",
        image: "https://images.unsplash.com/photo-1564564321837-a57b7070ac4f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8bWFufGVufDB8fDB8fHww&auto=format&fit=crop&w=800&q=60",
        color: "#331D2C"
    },
    {
        name: "Narcotics Anonymous",
        quote: "Insanity is doing the same thing, over and over again, but expecting different results",
        image: "https://images.unsplash.com/photo-1605462863863-10d9e47e15ee?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzB8fG1hbnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=800&q=60",
        color: "#1D5B79"
    },
    {
        name: "Dr. Seuss",
        quote: "Don't cry because it's over, smile because it happened.",
        image: "https://images.unsplash.com/photo-1567515004624-219c11d31f2e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTl8fG1hbnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=800&q=60",
        color: "#EF6262"
    }
];

const testimonialsContainerEl = document.querySelector(".testimonials-container");
const textEl = document.querySelector(".text");
const nameEl = document.querySelector(".name");
const authorsContainerEl = document.querySelector(".authors-container");

const initActiveAuthor = 0;
const renderAuthorImage = arr => {
    let html = "";
    arr.forEach(e => {
        html += `
            <div class="author"><img src="${e.image}" alt=""></div>
        `
    });
    authorsContainerEl.innerHTML = html;

    // Event
    const authorImages = document.querySelectorAll(".author");
    Array.from(authorImages).map((e, i) => e.addEventListener("click", () => {
        activeTestimonial(arr, i)
    }))
}

const activeTestimonial = (arr, index) => {
    // Active image
    const preSelectedEl = document.querySelector(".selected");
    if (preSelectedEl) {
        preSelectedEl.classList.remove("selected")
    }
    const currentSelectedEl = document.querySelector(`.authors-container div:nth-child(${index + 1})`)
    currentSelectedEl.classList.add("selected");

    // Show info
    const author = arr[index];
    textEl.innerText = author.quote;
    nameEl.innerText = author.name;
    testimonialsContainerEl.style.backgroundColor = author.color;
}

renderAuthorImage(testimonialList)
activeTestimonial(testimonialList, initActiveAuthor)
