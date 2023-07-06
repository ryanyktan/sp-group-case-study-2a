import { useState, useEffect } from "react"
import axios from "axios"
import { useParams } from "react-router-dom"

const Appliances = () => {

    const params = useParams()

    const api = 'http://localhost:9001/api/appliances/' + params.userId
    const [message, setMessage] = useState('')
    const [appliances, setAppliances] = useState([])

    const getAppliances = () => {
        return axios.get(api).then(
            response => setAppliances(response.data)
        ).catch(
            error => setMessage(error.data)
        )
    }

    useEffect( () => {
        getAppliances()
    },[])

    // TODO: Filtering in this table, buttons to update and delete appliances.
    return(
        <div>
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
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}
export default Appliances