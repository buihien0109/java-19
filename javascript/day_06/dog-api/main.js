/*
API là gì? Application Programming Inferface (Giao diện lập trình ứng dụng)
API công cụ dùng kết nối (trao đổi dữ liệu - JSON/XML) các ứng dụng hoặc thư viện với nhau

Client - Server
Client gửi request (yêu cầu) -> Server
Server xử lý yêu cầu -> Response (phản hồi) cho client
API : Xử lý trung gian (tiếp nhận request, trả về response)

Khách hàng (client) -> Nhân viên (API) -> Bếp (server)

API : URL - HTTP Method (dữ liệu gửi lên - option)
- URL : Xác định tài nguyên cần thao tác (resource)? users
- HTTP Method : Hành động đối với tài nguyên đấy là gì? (CRUD) 
    - Create : POST : 
    - Read : GET
    - Update : PUT/PATCH
    - Delete : DELETE

USERS:
    - POST : /api/users + request body
    - GET : /api/users, /api/users/1, /api/users/1/posts, /api/users?name=an&sort=asc
    - PUT : /api/users/1 + request body
    - DELETE : /api/users/1

*/

// URL - GET : https://dog.ceo/api/breeds/image/random
// Cách 1 : fetch API (build-in Javascript)
// Cách 2 : axios (3rd - library)

const imageEl = document.querySelector("img")
// Promise + fetch
// const fetchImage = () => {
//     fetch("https://dog.ceo/api/breeds/image/random")
//         .then(res => {
//             console.log(res);
//             return res.json()
//         })
//         .then(res => {
//             console.log(res);
//             imageEl.src = res.message;
//         })
//         .catch(err => {
//             console.log(err);
//         })
// }

// Async Await + fetch
// const fetchImage = async () => {
//     try {
//         let res = await fetch("https://dog.ceo/api/breeds/image/random");
//         let resJson = await res.json();
//         console.log(resJson);

//         imageEl.src = resJson.message;
//     } catch (error) {
//         console.log(error);
//     }
// }

// Async Await + axios
const fetchImage = async () => {
    try {
        let res = await axios.get("https://dog.ceo/api/breeds/image/random");
        console.log(res);

        imageEl.src = res.data.message;
    } catch (error) {
        console.log(error);
    }
}

fetchImage();