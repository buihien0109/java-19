// const promise = new Promise((resolve, reject) => { });
// console.log(promise);

// const promiseSuccess = new Promise((resolve, reject) => {
//     resolve("Success")
// });
// console.log(promiseSuccess);

// const promiseFail = new Promise((resolve, reject) => {
//     reject("Fail")
// });
// console.log(promiseFail);

let money = 35

let buyIphone = () => {
    return new Promise(function (resolve, reject) {
        if (money > 30) {
            resolve("Mua iphone thôi") // Lời hứa được thực hiện thành công
        } else {
            reject("Kiếm thêm tiền đi") // Lời hứa được thực hiện thất bại
        }
    })
}

let buyAirpod = () => {
    return new Promise(function (resolve, reject) {
        if (money - 30 - 4 >= 0) {
            resolve("Mua thêm airpod")
        } else {
            reject("Không đủ tiền mua airpod")
        }
    })
}

function finish() {
    console.log("Kết thúc công việc");
}

buyIphone()
    .then(res => {
        console.log(res);

        // buyAirpod()
        //     .then(res1 => {
        //         console.log(res1);
        //     })
        //     .catch(err => {
        //         console.log(err);
        //     })
        return buyAirpod()
    })
    .then(res => {
        console.log(res);
    })
    .catch(err => {
        console.log(err);
    })
    .finally(() => {
        console.log("Về nhà");
    })

finish()

