console.log("script looded")

let currentTheme = getTheme();
console.log(currentTheme)

document.addEventListener('DOMContentLoaded', () => {
    changeTheme()
    
})

function changeTheme() {
    //set to webpage
   changePageTheme(currentTheme, currentTheme)
    
    //set the listener to change theme button
    const changeThemeButton = document.querySelector("#theme_change_button")
    const oldTheme = currentTheme;
    changeThemeButton.addEventListener("click", (event) => {
        if (currentTheme === "dark") {
           currentTheme = "light" 
        } else {
            currentTheme = "dark"
        }
        changePageTheme(currentTheme, oldTheme)
       
    })
}

//set theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme)
}


//get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme")
    return theme ? theme : "light"
    // if (theme) return theme
    // else return "light"
}

//change current page theme

function changePageTheme(theme, oldTheme) {
     //update in local storage
    setTheme(currentTheme)
    //remove the current theme
    document.querySelector('html').classList.remove(oldTheme) 
    //set the current theme
     document.querySelector('html').classList.add(theme) 

     document.querySelector("#theme_change_button").querySelector("span").textContent = (theme === "light" ? " Dark" : " Light"); 
}