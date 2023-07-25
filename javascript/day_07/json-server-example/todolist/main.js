const API_URL = "http://localhost:3000/todos";
const todoApis = {
    getAllTodo() {
        return axios.get(API_URL);
    },
    createTodo(newTodo) {
        return axios.post(API_URL, newTodo); // promise
    },
    updateTodo(todo) {
        // todo = {id, title, status}
        const { id, ...updatedTodo } = todo; // updatedTodo = {title, status}
        return axios.put(`${API_URL}/${id}`, updatedTodo);
    },
    deleteTodo(id) {
        return axios.delete(`${API_URL}/${id}`);
    },
};

// Hiển thị danh sách todo
// Gọi API -> ds todos
// ds -> Hiển thị UI
const todoListEl = document.querySelector("ul");
const renderTodo = (arr) => {
    todoListEl.innerHTML = "";
    if (arr.length === 0) {
        todoListEl.insertAdjacentHTML(
            "afterbegin",
            "<li>Không có công việc nào trong danh sách</li>"
        );
        return;
    }
    let html = "";
    arr.forEach((t) => {
        html += `
            <li>
                <input type="checkbox" ${t.status ? "checked" : ""} onclick="toggleStatus(${t.id})">
                <span class="${t.status ? "active" : ""}">${t.title}</span>
                <button onclick="editTodo(${t.id})">Edit</button>
                <button onclick="deleteTodo(${t.id})">Delete</button>
            </li>
        `;
    });
    todoListEl.innerHTML = html;
};

const getAllTodo = async () => {
    try {
        let rs = await todoApis.getAllTodo();
        console.log(rs);

        renderTodo(rs.data);
    } catch (error) {
        console.log(error);
    }
};

getAllTodo();
