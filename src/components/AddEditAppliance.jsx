import { useParams } from "react-router-dom";
import axios from "axios";
import { Formik } from "formik";

const AddEditAppliance = () => {

    const params = useParams()
    const api = 'http://localhost:9001/api/appliances/' + params.userId

    return (
        <div>
            <Formik
                initialValues={{
                    id: 0,
                    serialNumber: '',
                    brand: '',
                    model: '',
                    dateBought: '',
                    status: '',
                    userId: params.userId,
                }}
                onSubmit={(values, {setSubmitting} ) => {
                    console.log("form submitted")

                    alert(JSON.stringify(values, null, 2))

                // setTimeout(() => {

                //     setSubmitting(false)

                //     // This calls POST Mapping at the api url.
                //     axios.post(api, (JSON.stringify(values, null, 2)),{
                //         headers: {
                //           'Content-Type': 'application/json'
                //         }
                //       }
                //     ).then(
                //         response => {
                //             // Successful appliance update
                //         }
                //     ).catch(
                //         error => {
                //             // Unsuccessful appliance update
                //             alert("Unsuccessful login, Username or password was incorrect.")
                //         }
                //     );
                // }, 1000);
                }}
            >
                {props => 
                    <form onSubmit={props.handleSubmit}> 

                    <input
                     id="id"
                     name="id"
                     type="hidden"
                     value={props.values.id}
                    />
                    <input
                     id="userId"
                     name="userId"
                     type="hidden"
                     value={props.values.userId}
                    />

                    <label htmlFor="serialNumber">Serial Number</label>
                    <input
                     id="serialNumber"
                     name="serialNumber"
                     type="text"
                     onChange={props.handleChange}
                     value={props.values.serialNumber}
                    />
                    <br />
                    <label htmlFor="brand">Brand</label>
                    <input
                     id="brand"
                     name="brand"
                     type="text"
                     onChange={props.handleChange}
                     value={props.values.brand}
                    />
                    <br />
                    <label htmlFor="model">Model</label>
                    <input
                     id="model"
                     name="model"
                     type="text"
                     onChange={props.handleChange}
                     value={props.values.model}
                    />
                    <br />
                    <label htmlFor="dateBought">Date Bought</label>
                    <input
                     id="dateBought"
                     name="dateBought"
                     type="text"
                     onChange={props.handleChange}
                     value={props.values.dateBought}
                    />
                    <br />
                    <label htmlFor="status">Status</label>
                    <input
                     id="status"
                     name="status"
                     type="text"
                     onChange={props.handleChange}
                     value={props.values.status}
                    />
                    <br />
                    <button type='submit'>Submit</button>
                    </form>
                }
            
            </Formik>
        </div>
    )
}
export default AddEditAppliance