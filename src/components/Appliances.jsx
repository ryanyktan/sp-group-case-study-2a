import { useState, useEffect } from "react"
import axios from "axios"
import { useParams, useNavigate } from "react-router-dom"

const Appliances = () => {

    const params = useParams()
    const navigate = useNavigate()
    const userId = params.userId

    const baseApi = 'http://localhost:9001/api/appliances/'
    const [message, setMessage] = useState('')
    const [appliances, setAppliances] = useState([])

    // This function calls the get mapping to retrieve appliance data
    const getAppliances = () => {
        return axios.get(baseApi + userId).then(
            response => setAppliances(response.data)
        ).catch(
            error => setMessage(error.data)
        )
    }

    // This function navigates the user to the add appliance form
    const addAppliance = () => {
        navigate('/appliances/' + params.userId + '/addedit/' + 0)
    }

    // This function navigates the user to the update appliance form
    const updateAppliance = (id) => {
        navigate('/appliances/' + params.userId + '/addedit/' + id)
    }

    // This function calls the delete mapping to delete an appliance
    const deleteAppliance = (id) => {
        axios.delete(baseApi + id).then(
            response => {
                alert("Delete Success!")
                getAppliances()
            }
        ).catch(
            error => {
                alert(error.data)
            }
        )
    }

    useEffect( () => {
        getAppliances()
    },[])

    // TODO: Filtering in this table, buttons to update and delete appliances.
    return(
        <div>
            <button onClick={addAppliance}>Add an appliance here</button>
            <p>{message}</p>
            <table>
                <thead>
                    <tr>
                        <td>Brand</td>
                        <td>Model</td>
                        <td>Serial Number</td>
                        <td>Date Bought</td>
                        <td>Status</td>
                    </tr>
                </thead>
                <tbody>
                    {
                        appliances.map(
                            appliance => 
                            <tr key={appliance.id}>
                                <td>{appliance.brand}</td>
                                <td>{appliance.model}</td>
                                <td>{appliance.serialNumber}</td>
                                <td>{appliance.dateBought}</td>
                                <td>{appliance.status}</td>
                                <td>
                                    <button value="Update" onClick={() => updateAppliance(appliance.id)}>Update</button>
                                </td>
                                <td>
                                    <button value="Delete" onClick={() => deleteAppliance(appliance.id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}
export default Appliances