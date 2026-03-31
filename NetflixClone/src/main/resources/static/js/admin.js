function addMovie(){

const movie = {

title: document.getElementById("title").value,
description: document.getElementById("desc").value,
poster: document.getElementById("poster").value,
trailer: document.getElementById("trailer").value,
iframeLink: document.getElementById("iframe").value,
category: document.getElementById("category").value,
premium: document.getElementById("premium").checked

};

fetch("/api/movies/add",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body: JSON.stringify(movie)
})
.then(res=>res.json())
.then(data=>{
alert("Movie Added Successfully");
});

}