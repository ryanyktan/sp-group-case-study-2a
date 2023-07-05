import { useState, useEffect } from "react"
import axios from "axios"

const Appliances = () => {

    const api = 'http://localhost:9001/api/appliances'
    const [message, setMessage] = useState('')
    const [appliances, setAppliances] = useState([])

    const getAppliances = () => {
        return axios.get(api).then(
            response => setAppliances(response.data)
        ).catch(
            error => setMessage(error)
        )
    }

    useEffect( () => {
        getAppliances()
    },[])

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