import { Formik, useFormik } from "formik"
import axios from "axios"
import { useNavigate } from "react-router-dom"

const Login = () => {

    const navigate = useNavigate()
    const loginapi = 'http://localhost:9001/api/users/login'

    return (
        <div>
            <Formik
                initialValues={{
                    username: '',
                    password: '',
                }}
                onSubmit={(values, {setSubmitting} ) => {
                    console.log("form submitted")
                setTimeout(() => {

                    setSubmitting(false)
                    alert((JSON.stringify(values, null, 2)))

                    // This calls POST Mapping at the api url.
                    axios.post(loginapi, (JSON.stringify(values, null, 2)),{
                        headers: {
                          'Content-Type': 'application/json'
                        }
                      }
                    ).then(
                        response => {
                            // Successful login
                            alert("Login successful.")
                        }
                    ).catch(
                        error => {
                            // Unsuccessful login
                            alert("Unsuccessful login, Username or password was incorrect.")
                        }
                    );
                }, 1000);
                }}
            >
                {props => 
                    <form onSubmit={props.handleSubmit}> 
                    <label htmlFor="username">Username</label>
                    <input
                     id="username"
                     name="username"
                     type="text"
                     onChange={props.handleChange}
                     value={props.values.username}
                    />
                    <br />
                    <label htmlFor="password">Password</label>
                    <input
                     id="password"
                     name="password"
                     type="password"
                     onChange={props.handleChange}
                     value={props.values.password}
                    />
                    <br />
                    <button type='submit'>Submit</button>
                    </form>
                }
            
            </Formik>
        </div>
    )
}
export default Login