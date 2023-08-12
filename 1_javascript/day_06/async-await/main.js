let money = 31

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

const buy = async () => {
    try {
        let res = await buyIphone();
        console.log(res);

        let res1 = await buyAirpod();
        console.log(res1);
    } catch (error) {
        console.log(error);
    }

    return "Về nhà"; // Trả về promise
}

// console.log(buy());
buy().then(res => console.log(res))