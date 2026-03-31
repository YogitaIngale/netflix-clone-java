const modal = document.getElementById("movieModal");
const modalImg = document.getElementById("modalImg");
const modalTitle = document.getElementById("modalTitle");
const modalDesc = document.getElementById("modalDesc");
const playBtn = document.getElementById("playBtn");
const closeBtn = document.querySelector(".close");

const videoContainer = document.getElementById("videoContainer");
const videoFrame = document.getElementById("videoFrame");

let trailerLink = "";

document.querySelectorAll(".row img").forEach(img => {

    img.addEventListener("click", function(){

        modal.style.display = "block";

        modalImg.src = this.src;
        modalTitle.innerText = this.getAttribute("data-title") || "Movie";
        modalDesc.innerText = this.getAttribute("data-desc") || "Awesome movie";

        trailerLink = this.getAttribute("data-trailer");

        videoContainer.style.display = "none";
        videoFrame.src = "";

    });

});

closeBtn.onclick = () => {
    modal.style.display = "none";
    videoFrame.src = "";
};

window.onclick = function(e){
    if(e.target == modal){
        modal.style.display = "none";
        videoFrame.src = "";
    }
}

playBtn.onclick = function(){
    videoContainer.style.display = "block";
    videoFrame.src = trailerLink + "?autoplay=1";
}