const url = new URL("http://localhost:9090/register");
const api=Object.freeze(
    {
        host:'http://localhost:9090',
        student_role:{
            user:{
                register:'/register',
                forgotPassword:'/forgot-password',
                resetPassword:'/reset-password'
            }
        },admin_role:{
            user:{
                admin:'user/add'
            }
        }
        
    }
)
