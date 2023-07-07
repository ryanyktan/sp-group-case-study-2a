import { useState, useEffect, useMemo } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { useTable, useFilters } from "react-table";
import '../styles/Appliances.css'

const DefaultColumnFilter = ({
  column: { filterValue, preFilteredRows, setFilter },
}) => {
  const count = preFilteredRows.length;

  return (
    <input
      value={filterValue || ""}
      onChange={(e) => {
        setFilter(e.target.value || undefined);
      }}
      placeholder={`Search ${count} records...`}
    />
  );
};

const Appliances = () => {
  const params = useParams();
  const navigate = useNavigate();
  const userId = params.userId;

  const baseApi = 'http://localhost:9001/api/appliances/';
  const [message, setMessage] = useState('');
  const [appliances, setAppliances] = useState([]);

  const getAppliances = () => {
    return axios.get(baseApi + userId).then(
      response => setAppliances(response.data)
    ).catch(
      error => setMessage(error.message)
    );
  }

  const addAppliance = () => {
    navigate('/appliances/' + userId + '/addedit/' + 0);
  }

  const updateAppliance = (id) => {
    navigate('/appliances/' + userId + '/addedit/' + id);
  }

  const deleteAppliance = (id) => {
    axios.delete(baseApi + id).then(
      response => {
        alert("Delete Success!");
        getAppliances();
      }
    ).catch(
      error => {
        alert(error.message);
      }
    );
  }

  useEffect(() => {
    getAppliances();
  },[]);

  const columns = useMemo(
    () => [
      {
        Header: 'Brand',
        accessor: 'brand',
        Filter: DefaultColumnFilter,
      },
      {
        Header: 'Model',
        accessor: 'model',
        Filter: DefaultColumnFilter,
      },
      {
        Header: 'Serial Number',
        accessor: 'serialNumber',
        Filter: DefaultColumnFilter,
      },
      {
        Header: 'Date Bought',
        accessor: 'dateBought',
        Filter: DefaultColumnFilter,
      },
      {
        Header: 'Status',
        accessor: 'status',
        Filter: DefaultColumnFilter,
      },
      {
        Header: 'Update',
        id: 'update',
        Cell: ({ row }) => (<button onClick={() => updateAppliance(row.original.id)}>Update</button>),
      },
      {
        Header: 'Delete',
        id: 'delete',
        Cell: ({ row }) => (<button onClick={() => deleteAppliance(row.original.id)}>Delete</button>),
      },
    ],
    [] // dependencies of useMemo
  );

  const data = useMemo(() => appliances, [appliances]);

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow,
  } = useTable(
    {
      columns,
      data,
      defaultColumn: { Filter: DefaultColumnFilter },
    },
    useFilters // Using the filter HOOK
  );

  return (
    <div>
      <button onClick={addAppliance}>Add an appliance here</button>
      <p>{message}</p>
      <table {...getTableProps()} style={{margin: 'auto'}}>
        <thead>
          {headerGroups.map(headerGroup => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map(column => (
                <th {...column.getHeaderProps()}>
                  {column.render('Header')}
                  {/* Render the columns filter UI */}
                  <div>{column.canFilter ? column.render('Filter') : null}</div>
                </th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {rows.map(row => {
            prepareRow(row);
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map(cell => (
                  <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                ))}
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default Appliances;
