const uri = "http://localhost:8080/auth/";
export async function login(username: string, password: string){
    console.log("User " + username + " attempting login");
    let result;
    try{
        result = await fetch(uri + "login", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
                },
            body: JSON.stringify({
                username: username,
                password: password
                })
        });
        console.log(result.status);
        if(result.ok){
            const response = await result.json();
            
            if(response.accessToken){
                
                const user = {
                    "accessToken": response.accessToken,
                    "username" : username
                };
                const data = JSON.stringify(user);
                localStorage.setItem("user", data);
                console.log(username + " succesfully loged in");
            }
        }
    }
    catch(er){
        console.error(er);
        return [null, er]
    }
    finally{
        return [result, null];
    }
}
export function logout(){
    console.log("Logging out");
    localStorage.removeItem("user");
}

export async function register(username: String, password: String){
    console.log("Registering user " + username);
    let result
    try{
        result = await fetch(uri + "registration", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
                },
            body: JSON.stringify({
                username: username,
                password: password 
             })
        })
        console.log(result.status);
    }
    catch(er){
        console.error(er);
        return [null, er];
    }
    
    return [result, null];
}

export function getCurrentUser(){
    const userStr = localStorage.getItem("user");
    let user;
    if(userStr){user = JSON.parse(userStr)};
    if (userStr && user.username) return user.username;
    return null;
}