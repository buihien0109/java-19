const API_URL = "http://localhost:8080/api/v1/courses";

// Lấy ID trên URL
const params = new URLSearchParams(window.location.search);
const id = params.get("id");

// API lấy chi tiết khóa học
const getCourseApi = (id) => {
    return axios.get(`${API_URL}/${id}`, { params })
}

// Gọi API lấy dữ liệu + hiển thị
const getCourse = async (id) => {
    try {
        let res = await getCourseApi(id);

        renderCourse(res.data);
    } catch (error) {
        console.log(error);
    }
}

// Hiển thị thông tin khóa học
const courseTitleEl = document.querySelector(".course-title");
const courseDescriptionEl = document.querySelector(".course-description p");
const breadCrumbEl = document.querySelector(".breadcrumb-item.active")
const renderCourse = (course) => {
    document.title = course.name;
    courseTitleEl.innerHTML = course.name;
    courseDescriptionEl.innerHTML = course.description;
    breadCrumbEl.innerHTML = course.name;

    renderSupporter(course.user);
}

// Hiển thị thông tin người tư vấn
const supporterEl = document.querySelector(".supporter")
const renderSupporter = (supporter) => {
    supporterEl.innerHTML = `
        <div class="supporter-image">
            <img src="${supporter.avatar}"
                alt="${supporter.name}" class="rounded-circle me-3">
        </div>
        <div class="supporter-info">
            <p>
                <b>Tư vấn viên :</b>
                ${supporter.name}
            </p>
            <p>
                <b>Email :</b>
                ${supporter.email}
            </p>
            <p class="mb-0">
                <b>Số điện thoại :</b>
                ${supporter.phone}
            </p>
        </div>
    `
}

getCourse(id)

