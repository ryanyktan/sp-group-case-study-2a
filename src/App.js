import Login from './components/Login';
import { Route, Routes } from 'react-router-dom';
import Appliances from './components/Appliances';
import AddEditAppliance from './components/AddEditAppliance';

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/appliances/:userId" element={<Appliances />} />
        <Route path="/appliances/:userId/addedit/:appId" element={<AddEditAppliance />} />
      </Routes>
      
    </div>
  );
}

export default App;
