import { Formik, useFormik } from "formik"
import axios from "axios"

const Login = () => {

    const loginapi = 'http://localhost:9001/api/users/login'

    // This method initialises and handles the form submission
    const formik = useFormik({
        initialValues: {
            username: '',
            password: '',
        },
        onSubmit: values => {
            // This calls POST Mapping at the api url.
            axios.post(loginapi, (JSON.stringify(values, null, 2))
            ).then(
                // Successful login
                alert("Login successful.")
            ).catch(
                // Unsuccessful login
                alert("Unsuccessful login, Username or password was incorrect.")
            )
        }
    })

    return (
        <div>
            <form onSubmit={formik.handleSubmit}> 
                <label htmlFor="username">Username</label>
                    <input
                     id="username"
                     name="username"
                     type="text"
                     onChange={formik.handleChange}
                     value={formik.values.username}
                    />
                <br />
                <label htmlFor="password">Password</label>
                    <input
                     id="password"
                     name="password"
                     type="password"
                     onChange={formik.handleChange}
                     value={formik.values.password}
                    />
                <br />
                <button type="submit">Submit</button>
            </form>
        </div>
    )
}
export default Login