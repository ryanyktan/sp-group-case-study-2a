import Login from './components/Login';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import Appliances from './components/Appliances';

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/appliances" element={<Appliances />} />
      </Routes>
      
    </div>
  );
}

export default App;
