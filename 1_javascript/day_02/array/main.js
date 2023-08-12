// Khai báo array rỗng
let arr = [];

// Khai báo array
let number = [];

// int[] 3 -> [0, 0, 0]
// Integer[] 3 -> [null, null, null]
// Gán giá trị cho các phần tử của array thông qua chỉ số
number[0] = 1;
number[1] = "Bùi Hiên";
number[2] = true;
number[7] = 10;
console.log(number);

// Khai báo và khởi tạo giá trị cho array
let myArr = [1, 2, 3, 4, true, false, "Nguyễn Văn A"];
console.log(myArr);

// Dự đoán
let arr1 = [1, 2, 3];
let arr2 = [1, 2, 3];
let arr3 = [3, 2, 1];
let str = "1,2,3";

// arr1 == arr2; //true || false
// arr2 == arr3.reverse(); // true || false
// arr1 == arr3.sort(); // true || false
// arr3 == arr3.sort(); // true || true
// arr1 == arr1.reverse(); // false || true
// arr2 == str; // false || true
// arr1 === str; // true || false

const fruits = ["Banana", "Orange", "Lemon", "Apple", "Mango"];
const citrus = fruits.slice(); // ["Banana", "Orange", "Lemon", "Apple", "Mango"];
const citrus1 = fruits.slice(1); // ["Orange", "Lemon", "Apple", "Mango"];
const citrus2 = fruits.slice(1, 4); // ["Orange", "Lemon", "Apple"];

// pop(), push(), shift(), unshift()
// slice(), splice()
// reverse(), sort()
// indexOf, lastIndexOf, includes == contains

const numbers = [4, 2, 6, 3, 7, 23, 34, 45];
console.log(numbers.sort((a, b) => a - b));
console.log(numbers.sort((a, b) => b - a));

for (let i = 0; i < numbers.length; i++) {
    console.log(numbers[i]);
}

numbers.forEach((e) => {
    console.log(e);
})

const modulo2 = (arr) => {
    // let result = [];
    // arr.forEach((e) => {
    //     result.push(e % 2);
    // })
    // return result;
    return arr.map(e => e % 2);
};
console.log(modulo2([3, 2, 6, 7, 3]));

// OBJECT
let user = {
    name: "Nguyễn Văn A",
    age: 23,
    email: "abc@gmail.com",
    sayHello: function () {
        console.log("Hello");
    },
    eat(food) {
        console.log("Eat " + food);
    }
}

let { name, age } = user;
console.log(name, age);

// console.log(user.name);
// console.log(user["name"]);
// user.eat("Cơm")

// C1 : Sử dụng Object.keys
// Lấy ra mảng các keys của object user
// let keys = Object.keys(user)
// console.log(keys);

// Duyệt qua mảng các keys để in ra value tương ứng
// for (let i = 0; i < keys.length; i++) {
//     console.log(user[keys[i]]);
// }

let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

const findByBrand = (arr, brand) => {
    return arr.filter(e => e.brand == brand);
}

console.log(findByBrand(products, "Apple"));