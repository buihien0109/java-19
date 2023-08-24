const API_URL = "http://localhost:8080/api/v1";

const getAllCourseApi = () => {
    return axios.get(`${API_URL}/courses`)
}

const getAllCourse = async () => {
    try {
        let res = await getAllCourseApi();
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

getAllCourse();