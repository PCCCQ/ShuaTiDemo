export const getStorage=function(key){
   return localStorage.getItem(key)
   
}

export const setStorage=function(key,data){
    return localStorage.setItem(key,data)
}

export const removeStorage=function(key){
    return localStorage.removeItem(key)
}