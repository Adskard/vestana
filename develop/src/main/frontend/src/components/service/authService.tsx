const uri = "auth/";
export function login(username: String, password: String){
    console.log("User " + username + " attempting login");
    try{
        const result = fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
              },
            body: JSON.stringify({
                username: username,
                password: password
             })
        }).then(response => response.json())
        .then(result => {
            if(result.accessToken){
                localStorage.setItem("user", JSON.stringify(result));
                console.log(username + " succesfully loged in");
            }
            return result;
        })
        return result;
    }
    catch(er){
        console.error(er);
    }
}
export function logout(){
    console.log("Logging out");
    localStorage.removeItem("user");
}

export function register(username: String, password: String){
    console.log("Registering user " + username);
    return fetch(uri + "registration", {
        method: "POST",
        body: JSON.stringify({
            email: username,
            password: password 
         })
    })
}

export function getCurrentUser(){
    const userStr = localStorage.getItem("user");
    if (userStr) return JSON.parse(userStr);
    return null;
}