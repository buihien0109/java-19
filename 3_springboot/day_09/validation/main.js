// Xử lý validate form
$('#form').validate({
    rules: {
        name: {
            required: true
        },
        email: {
            required: true,
            email: true,
        },
        phone: {
            required: true,
            pattern: /^(0\d{9}|(\+84|84)[1-9]\d{8})$/
        },
        description: {
            required: true,
            minlength: 10,
            maxlength: 20
        },
    },
    messages: {
        name: {
            required: "Tên user không được để trống"
        },
        email: {
            required: "Email không được để trống",
            email: "Email không đúng định dạng"
        },
        phone: {
            required: "Số điện thoại không được để trống",
            pattern: "Số điện thoại không đúng định dạng",

        },
        description: {
            required: "Mô tả không được để trống",
            minlength: "Mô tả phải có ít nhất 10 ký tự",
            maxlength: "Mô tả không được quá 20 ký tự"
        },
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
    }
});

// Xử lý submit form
const formEl = document.getElementById('form');
formEl.addEventListener('submit', (e) => {
    e.preventDefault();

    if (!$('#form').valid()) return;
    alert('Submit thành công');
})