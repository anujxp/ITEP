import React, { useState } from 'react'

const Password = () => {
    const [length,setLength] = useState(0);
    const [numberAlllowed , setNumberAllowed] = useState(false);
    const [charecterAllowed , setCharecterAllowed] = useState(false);
    const [password, setPassword] = useState("")
    const passwordGenerator = () =>{
        let pass = ""
        let str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        if(numberAlllowed) str+= "1234567890";
        if(charecterAllowed) str+= "!@#$%^&*(){}<>?~";
        for(let i =0 ; i< length;i++){
            let char =Math.floor(Math.random() * str.length + 1);
            pass += str.charAt(char)
        }
        setPassword(pass);

    }
  return (
    <>
          <div className = 'w-full max-w-md shadow-md rounded-lg px-4 my-8 text-orange-500 bg-gray-500'>
            <input 
            type="text"
            value = {password} 
            className='outline-none w-full py-1 px-3 text-white'
            placeholder='password'
            
            readOnly
            />           
            </div>    
    </>
  )
}

export default Password
