const API_URL = "http://localhost:8080/api/v1";

let params = {
    type: null,
    name: null,
    topic: null
}

const getAllCourseApi = (params) => {
    return axios.get(`${API_URL}/courses`, { params })
}

const getAllTopicApi = () => {
    return axios.get(`${API_URL}/topics`)
}

const getAllCourse = async (params) => {
    try {
        let res = await getAllCourseApi(params);
        if (res.status === 200) {
            renderCourses(res.data);
        }
    } catch (error) {
        console.log(error);
    }
}

// Hiển thị ds khóa học
const courseListEl = document.querySelector(".course-list");
const renderCourses = (courseList) => {
    if (courseList.length === 0) {
        courseListEl.innerHTML = `
            <h3 class="mt-3">Không có khóa học!</h3>
        `;
        return
    }
    let html = "";
    courseList.forEach(course => {
        html += `
            <div class="col-md-4">
                <a href="./detail.html?id=${course.id}">
                    <div class="course-item shadow-sm rounded mb-4">
                        <div class="course-item-image">
                            <img src="${course.thumbnail}"
                                alt="${course.name}">
                        </div>
                        <div class="course-item-info p-3">
                            <h2 class="fs-5 mb-3 text-dark">${course.name}</h2>
                            <p class="type fw-light text-black-50">${course.type === "online" ? "Trực tuyến" : "Phòng lab"}</p>
                        </div>
                    </div>
                </a>
            </div>
        `
    });
    courseListEl.innerHTML = html;
}

// Gọi API lấy ds topic và hiển thị
const getAllTopic = async () => {
    try {
        let res = await getAllTopicApi();
        renderTopics(res.data);
    } catch (error) {
        console.log(error);
    }
}

// Hiển thị danh sách topic
const topicListEl = document.querySelector(".topic-list");
const renderTopics = (topicList) => {
    let html = "";
    topicList.forEach(topic => {
        html += `
            <div class="topic-item input-group d-flex align-items-center mb-1">
                <input type="radio" value="${topic}" id="${topic}" name="topic">
                <label for="${topic}" class="ms-2 fs-5">${topic}</label>
            </div>
        `
    });
    topicListEl.innerHTML = html;
}

// Xử lý tìm kiếm khóa học theo tiêu đề
const searchEl = document.querySelector(".seach-form-input");
searchEl.addEventListener("search", (e) => {
    if (!e.target.value.length) {
        params = { ...params, name: null }; // ES6
    } else {
        let searchTerm = e.target.value; // Lấy dữ liệu trong ô input
        params = { ...params, name: searchTerm };
    }
    getAllCourse(params);
})

getAllCourse(params);
getAllTopic().then(() => {
    // Xử lý lọc theo topic
    const topicItemEls = document.querySelectorAll(".topic-item input");
    const btnUnFilter = document.querySelector(".btn-unfilter");
    topicItemEls.forEach(el => {
        el.addEventListener("change", (e) => {
            btnUnFilter.classList.remove("d-none");
            let value = e.target.value;
            params = { ...params, topic: value };
            getAllCourse(params);
        })
    })

    btnUnFilter.addEventListener("click", () => {
        topicItemEls.forEach(el => el.checked = false);
        btnUnFilter.classList.add("d-none");

        params = { ...params, topic: null };
        getAllCourse(params);
    })
});