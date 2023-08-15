const getAllPost = () => {
    fetch("http://localhost:8080/posts")
        .then(rs => rs.json())
        .then(data => console.log(data))
        .catch(err => console.log(err))
}

getAllPost();