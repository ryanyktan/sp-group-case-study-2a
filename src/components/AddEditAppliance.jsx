import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { Formik } from "formik";
import { useState, useEffect } from "react";
import '../styles/AddEditAppliance.css'

// We use the same form to add/edit appliances, differentiating between the two by deciding to add when
// the appId path variable is 0, and editing when the appId path variable is a valid one.
const AddEditAppliance = () => {

    const params = useParams()
    const navigate = useNavigate()
    const appId = params.appId
    const userId = params.userId
    
    const getApi = 'http://localhost:9001/api/appliances/get/' + appId
    const postApi = 'http://localhost:9001/api/appliances/' + params.userId
    const [isEdit, setIsEdit] = useState(false)
    const [errMessage, setErrMessage] = useState('')
    const [appliance, setAppliance] = useState(null) 

    const goBack = () => {
        navigate('/appliances/' + userId)
    }

    // This runs when the page loads
    useEffect( () => {
        console.log("use effect running")
        console.log(appId == 0)

        // If the appId path varible is set to 0, the form is used to add an appliance.
        // Otherwise, we are modifying an existing appliance and will autofill the form
        // with the current details of the appliance
        if (appId != 0) {
            console.log("get request sent")

            // This is the getmapping request to pull details of an existing appliance to be updated.
            axios.get(getApi).then(
                response => {
                    
                    // Before autofilling, first ensure that the appliance does indeed belong
                    // to the logged in user.
                    const apiapp = response.data
                    if (apiapp.user.id == userId) {
                        setAppliance(response.data)
                        setIsEdit(true)
                    }
                }
            ).catch(
                error => {
                    setErrMessage(error.response.data)
                }
            )
        }
    }, [])

    return (
        <div className="addEditContainer">
            <p>{errMessage}</p>
            <Formik
                initialValues={{
                    id: appId,
                    serialNumber: isEdit ? appliance.serialNumber : '',
                    brand: isEdit ? appliance.brand : '',
                    model: isEdit ? appliance.model : '',
                    dateBought: isEdit ? appliance.dateBought : '',
                    status: isEdit ? appliance.status : '',
                }}
                enableReinitialize = {true}
                onSubmit={(values, {setSubmitting} ) => {
                    console.log("form submitted")

                setTimeout(() => {

                    setSubmitting(false)

                    console.log(JSON.stringify(values, null, 2))

                    // This calls POST Mapping at the api url.
                    axios.post(postApi, (JSON.stringify(values, null, 2)),{
                        headers: {
                          'Content-Type': 'application/json'
                        }
                      }
                    ).then(
                        response => {
                            // Successful appliance update
                            alert("Add/Update Success!")
                            navigate('/appliances/' + params.userId)
                        }
                    ).catch(
                        error => {
                            // Unsuccessful appliance update
                            alert("Add/Update was unsuccessful, please try again.")
                        }
                    );
                }, 1000);
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
                     type="date"
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

            <br />
            <button className="goBackButton" onClick={() => goBack()}>Go Back to Appliance List</button>
        </div>
    )
}
export default AddEditAppliance