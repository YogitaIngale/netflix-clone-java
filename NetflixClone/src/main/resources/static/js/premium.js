function selectPlan(plan){
    localStorage.setItem("plan", plan);
    window.location.href = "/payment";
}