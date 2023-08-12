let todos = [];
const API_URL = "https://lovely-dirndl-pike.cyclic.app/todos"; // deploy xong thì thay URL vào đây
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
const todoListEl = document.querySelector("#list");
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
                <input 
                    type="checkbox" 
                    ${t.status ? "checked" : ""} 
                    onclick="toggleStatus(${t.id})"
                >
                <span class="${t.status ? "active" : ""}">${t.title}</span>
                <button onclick="editTodo(${t.id})">Edit</button>
                <button onclick="deleteTodo(${t.id})">Delete</button>
            </li>
        `;
    });
    todoListEl.innerHTML = html;
};

// 1. Lấy danh sách todo
const getAllTodo = async () => {
    try {
        let rs = await todoApis.getAllTodo();
        todos = rs.data;

        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
};

// 2. Tạo todo
const btnAdd = document.querySelector("#btn-add");
const inputEl = document.querySelector("#todo-input");

const createTodo = async () => {
    let title = inputEl.value;
    if (title.trim().length === 0) {
        alert("Title không được để trống");
        return;
    }
    let newTodo = {
        title,
        status: false,
    };
    let res = await todoApis.createTodo(newTodo);
    todos.push(res.data);
    renderTodo(todos);
    inputEl.value = "";
};
btnAdd.addEventListener("click", createTodo);

// 3. Xóa todo
const deleteTodo = async (id) => {
    const isConfirm = window.confirm("Bạn có muốn xóa không?");
    if (!isConfirm) return;
    try {
        await todoApis.deleteTodo(id);
        todos = todos.filter((t) => t.id !== id);
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
};

// 4. Cập nhật title
const editTodo = async (id) => {
    try {
        let todo = todos.find((t) => t.id === id);
        let newTitle = window.prompt("Cập nhật title", todo.title);
        if (newTitle === null) return;
        if (newTitle.trim().length === 0) {
            alert("Title không được để trống");
            return;
        }
        todo.title = newTitle;
        await todoApis.updateTodo(todo);
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
};

// 5. Cập nhật status
const toggleStatus = async (id) => {
    try {
        let todo = todos.find((t) => t.id === id);
        todo.status = !todo.status;

        await todoApis.updateTodo(todo);
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
};

getAllTodo();
